package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class BreadTest implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/breadtest.html";

    static ArrayList<String> HasBread(String text) {
        String transformed = text.toLowerCase();
        String regex = "[.\\?\\!]";
        String bread = "bread";
        ArrayList<String> breadSentences = new ArrayList<String>();
        String[] sentences = transformed.split(regex);
        String[] textSentences = text.split(regex);
        for (int i = 0; i < sentences.length; i++) {
            if (sentences[i].contains(bread)){
                // System.out.println(textSentences[i].trim());
                breadSentences.add(textSentences[i].trim());
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
        html = html + "<a class='active' href='breadtest.html'>Does it have Bread?</a>";
        html = html + "<a href='page2.html'>Where is the Bread?</a>";
        html = html + "<a href='page3.html'>Types of Bread</a>";
        html = html + "</div>"; 
        
        html = html + "<main>";

        html = html + "<h2 id='padder'> Do you want to know if your text passage contains the word bread? Look no further!</h2>";
        html = html + "<h2> Here at Bakr.io you can submit any text you want and we'll show you where you can find the word bread!</h2>";

        html = html + "<form id=breadbox action='/breadtest.html' method='POST'>";
        // html = html +     "<label for='breadtext'>Insert text here:</label>";
        html = html +     "<textarea id='breadtext' name='breadtext' placeholder='Insert text here:'></textarea>";
        html = html +     "<button type='submit'>Submit</button>";
        html = html + "</form>";

        String breadtext = context.formParam("breadtext");
        ArrayList<String> breadsentences = new ArrayList<String>(); 

        if (breadtext != null) {
            breadsentences = HasBread(breadtext);
            if (breadsentences == null){
                html = html + "<h4 id='h4padding'>DOESN'T HAVE BREAD</h4>";
            } else {
                html = html + "<h4 id='h4padding'>HAS BREAD</h4>";
                html = html + "<h4></h4>";
                for (int i = 0; i < breadsentences.size(); i++) {
                    html = html + "<h4>"+breadsentences.get(i)+"</h4>";
                }
            }
            
        }


        html = html + "</main>" + "</body>" + "</html>";

        context.html(html);
    }

}