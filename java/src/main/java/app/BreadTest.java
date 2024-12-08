package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class BreadTest implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/breadtest.html";

    @Override
    public void handle(Context context) throws Exception {
        String html = "<html>";
        html = html + "<head>" + 
               "<title>Bakr.io</title>";

        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        html = html + "<body>";

                // Links to other pages
        html = html + "<div class='topnav''>";
        html = html + "<a class='active' href='/'>Home</a>";
        html = html + "<a href='breadtest.html'>Does it have Bread?</a>";
        html = html + "<a href='page2.html'>Where is the Bread?</a>";
        html = html + "<a href='page3.html'>Types of Bread</a>";
        html = html + "</div>"; 
        
        html = html + "<main>";

        html = html + "<h2> Do you want to know if your text passage contains the word bread? Look no further!</h2>";

        html = html + "<form id=breadbox>";
        // html = html +     "<label for='breadtext'>Insert text here:</label>";
        html = html +     "<textarea id='breadtext' name='breadtext' placeholder='Insert text here:'></textarea>";
        html = html +     "<button type='submit'>Submit</button>";
        html = html + "</form>";

        // Finish the HTML webpage
        html = html + "</main>" + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}