package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.Random;
import java.util.Dictionary;
// import java.util.Enumeration;
import java.util.Hashtable;
import java.io.File;
import java.util.Scanner;

import io.javalin.http.UploadedFile;

public class MoreFiles implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/morefiles.html";
    private static final ArrayList<String> more = new ArrayList<String>(); // for storing values to search for bread

    static String randimg(){ // method for random img generation when bread found
        Dictionary<Integer, String> imgs = new Hashtable<>();
        imgs.put(0, "bread0.png");
        imgs.put(1, "bread1.png");
        imgs.put(2, "bread2.png");
        imgs.put(3, "bread3.png");
        imgs.put(4, "bread4.png");
        Random rand = new Random();
        int imgNum = rand.nextInt(0,imgs.size());
        String img = imgs.get(imgNum);
        return img;
    }

    static ArrayList<String> HasBread(String text) {  // method to split sentences and search for bread
        String transformed = text.toLowerCase();
        String regex = "[.\\?\\!]"; // splits on ., ? and ! as those are sentence ends
        // String bread = "bread";
        ArrayList<String> breadSentences = new ArrayList<String>();
        String[] sentences = transformed.split(regex);
        String[] textSentences = text.split(regex);
        for (int i = 0; i < sentences.length; i++) {
            for (int j = 0; j < more.size(); j++) {
                if (sentences[i].contains(more.get(j)) || sentences[i].contains("bread")){ // checks each sentence for containing bread
                    // System.out.println(textSentences[i].trim());
                    breadSentences.add(textSentences[i].trim()); // gets rid of whitespace
                }   
            }
        }
        for (int i = 0; i < breadSentences.size(); i++) { // for loop for getting rid of duplicates
            for (int j = 0; j < breadSentences.size(); j++) {
                if (i!=j && breadSentences.get(i).equals(breadSentences.get(j))){
                    breadSentences.remove(j);
                }
            }
        }
        if (breadSentences.isEmpty()){
            return null;
        } else {
            return breadSentences;
        }
    }

    @Override
    public void handle(Context context) throws Exception {
        String html = "<html>";
        html = html + "<head>" + 
               "<title>Bakr.io</title>";

        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        html = html + "<body>";

                // Links to other pages
                html = html + "<div class='topnav''>";
                html = html + "<a href='/'>Home</a>";
                html = html + "<a href='breadtext.html'>Check Text for Bread</a>";
                html = html + "<a href='moretext.html'>Check Text for more Breads</a>";
                html = html + "<a href='breadfile.html'>Check Files for Bread</a>";
                html = html + "<a class='active' href='morefiles.html'>Check Files for more Breads</a>";
                html = html + "</div>"; 
        
        html = html + "<main>";

        String moretext = context.formParam("moretext");

        // textbox for bread words
        if (moretext == null){
            html = html + "<div id=more>";
            html = html + "<h2> Looking for more types of bread in your text?</h2>";
            html = html + "<h2> List the words you want to search in the text box below!</h2>";
            html = html + "<h2> Make sure to separate each word or phrase with a comma (i.e Rye, Wheat, Banana Bread, etc...)</h2>";
            html = html + "</div>";
    
            html = html + "<form id=morebox action='/morefiles.html' method='POST'>";
            html = html + "<textarea id='breadtext' name='moretext' placeholder='Insert words here:'></textarea>";
            html = html + "<button id='morebutton' type='submit'>Submit</button>";
            html = html + "</form>";
        }

        // file uploads
        if (moretext != null){
            html = html + "<div id=bakrio>";
            html = html + "<h2> Do you want to know if your text passage contains the word bread? Look no further!</h2>";
            html = html + "<h2> Here at Bakr.io you can submit any text you want and we'll show you where you can find the word bread!</h2>";
            html = html + "</div>";


            html = html + "<form id=breadbox action='/morefiles.html' method='POST' enctype='multipart/form-data'>";
            html = html + "<label>Select a text file:</label>";
            html = html + "<input type='file' name='breadfile'>";
            html = html + "<button id='breadbutton' type='submit'>Submit</button>";
            html = html + "</form>";

            if (moretext.length() > 0){  // adds the user specified words to the arraylist to check
                String[] morewords = moretext.split(",");
                // more.addAll(Arrays.asList(morewords));
                for (int i = 0; i < morewords.length; i++) {
                    more.add(morewords[i].trim());
                }
            }
        }

        UploadedFile breadFile = context.uploadedFile("breadfile");
        String breadtext = null;
        if (breadFile != null){
            Scanner breadReader = new Scanner(breadFile.getContent()); 
            breadtext = "";
            while (breadReader.hasNextLine()) {   // reads the file to a string
                breadtext = breadtext.concat(breadReader.nextLine());
            }
            breadReader.close(); 
        }
        // String breadtext = context.formParam("breadtext");
        ArrayList<String> breadsentences = new ArrayList<String>();
         

        if (breadtext != null) {
            breadsentences = HasBread(breadtext); // returns which sentences have bread
            if (breadsentences == null){
                html = html + "<h4 id='h4padding'>DOESN'T HAVE BREAD</h4>";
            } else {
                String img = randimg(); // generates random img
                html = html + "<style>body {background-image: url('"+img+"');}</style>";
                html = html + "<h4 id='h4padding'>HAS BREAD</h4>";
                html = html + "<div id=hasbread>";
                html = html + "<h4></h4>";
                for (int i = 0; i < more.size(); i++) { // this for loop iterates through word pairs and separates them to assist with highlighting
                    String[] tempsplit = more.get(i).split(" "); // i.e. banana bread
                    if (tempsplit.length > 1){
                        more.remove(i);
                        for (int j = 0; j < tempsplit.length; j++) {
                            more.add(tempsplit[j]);
                        }
                        i--;
                    }
                    
                }
                for (int i = 0; i < breadsentences.size(); i++) { // goes through every sentence and highlights every bread word
                    String sentence = breadsentences.get(i);
                    String[] breadsplit = sentence.split(" ");
                    html = html + "<h4>";
                    for (int j = 0; j < breadsplit.length; j++) {
                        boolean word = false;
                        if (breadsplit[j].toLowerCase().equals("bread") && word == false){
                            html = html + "<mark>"+breadsplit[j]+"</mark> ";
                            word = true;
                        }
                        for (int k = 0; k < more.size(); k++) {
                            if (breadsplit[j].toLowerCase().equals(more.get(k)) && word == false){
                                html = html + "<mark>"+breadsplit[j]+"</mark> ";
                                word = true;
                            }
                        }
                        if (word == false){
                            html = html + breadsplit[j] + " ";
                        }
                    }
                    html = html + "</h4>";
                    // html = html + "<h4>"+breadsentences.get(i)+"</h4>";
                }
                html = html + "</div>";
            }
            more.clear();
        }


        html = html + "</main>" + "</body>" + "</html>";

        context.html(html);
    }

}