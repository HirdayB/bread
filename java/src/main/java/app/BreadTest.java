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

public class BreadTest implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/breadtext.html";

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

    static ArrayList<String> HasBread(String text) {// method to split sentences and search for bread
        String transformed = text.toLowerCase();
        String regex = "[.\\?\\!]"; // splits on ., ? and ! as those are sentence ends
        String bread = "bread";
        ArrayList<String> breadSentences = new ArrayList<String>();
        String[] sentences = transformed.split(regex);
        String[] textSentences = text.split(regex);
        for (int i = 0; i < sentences.length; i++) {
            if (sentences[i].contains(bread)){ // checks each sentence for bread
                // System.out.println(textSentences[i].trim());
                breadSentences.add(textSentences[i].trim()); // removes whitespace
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
        html = html + "<a class='active' href='breadtext.html'>Check Text for Bread</a>";
        html = html + "<a href='moretext.html'>Check Text for more Breads</a>";
        html = html + "<a href='breadfile.html'>Check Files for Bread</a>";
        html = html + "<a href='morefiles.html'>Check Files for more Breads</a>";
        html = html + "</div>"; 
        
        html = html + "<main>";

        html = html + "<div id=bakrio>";
        html = html + "<h2> Do you want to know if your text passage contains the word bread? Look no further!</h2>";
        html = html + "<h2> Here at Bakr.io you can submit any text you want and we'll show you where you can find the word bread!</h2>";
        html = html + "</div>";

        // REMOVED: METHOD TO MERGE TEXT AND FILE UPLOADS INTO ONE PAGE
            // html = html + "<form id='method' action='/breadtest.html' method='POST'>";
            // html = html + "<input type='radio' id='text file' name='method' value='text file'";
            // html = html + "<label for='text file'>Text File</label>";
            // html = html + "<input type='radio' id='text' name='method' value='text'";
            // html = html + "<label for='text'>Text</label>";
            // html = html + "<button type='submit'>Submit</button>";
            // html = html + "</form>";
            // String method = context.formParam("method");

        
        // textbox for user text 
        html = html + "<form id=breadbox action='/breadtext.html' method='POST'>";
        html = html + "<textarea id='breadtext' name='breadtext' placeholder='Insert text here:'></textarea>";
        html = html + "<button id='breadbutton' type='submit'>Submit</button>";
        html = html + "</form>";
    
        String breadtext = context.formParam("breadtext");
           
        ArrayList<String> breadsentences = new ArrayList<String>();
         

        if (breadtext != null) {
            breadsentences = HasBread(breadtext); // checks string for bread sentences
            if (breadsentences == null){
                html = html + "<h4 id='h4padding'>DOESN'T HAVE BREAD</h4>";
            } else {
                String img = randimg();
                html = html + "<style>body {background-image: url('"+img+"');}</style>";
                html = html + "<h4 id='h4padding'>HAS BREAD</h4>";
                html = html + "<div id=hasbread>";
                html = html + "<h4></h4>";
                for (int i = 0; i < breadsentences.size(); i++) {
                    String sentence = breadsentences.get(i);
                    String[] breadsplit = sentence.split(" ");
                    html = html + "<h4>";
                    for (int j = 0; j < breadsplit.length; j++) { // highlights bread within sentences
                        if (breadsplit[j].toLowerCase().equals("bread")){
                            html = html + "<mark>"+breadsplit[j]+"</mark> ";
                        } else {
                            html = html + breadsplit[j] + " ";
                        }
                    }
                    html = html + "</h4>";
                    // html = html + "<h4>"+breadsentences.get(i)+"</h4>";
                }
                html = html + "</div>";
            }
            
        }


        html = html + "</main>" + "</body>" + "</html>";

        context.html(html);
    }

}