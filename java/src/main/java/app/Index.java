package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Index implements Handler {

    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {

        String html = "<html>";

        html = html + "<head>" + "<title>Bakr.io</title>";
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

        html = html + "<div class='homebanner'>";
        html = html + "<img src = 'bread2.png' width ='100%'/>";
        html = html + "<div class='centered'>Bakr.io</div>";
        html = html + "<div class='button-container'>";
        html = html + "<button onclick=\"location.href='breadtest.html'\">Check any text</button>";
        html = html + "<button onclick=\"location.href='breadfile.html'\">Check your text file</button>";
        html = html + "<button onclick=\"location.href='breadtest.html'\">Check text for more breads</button>";
        html = html + "<button onclick=\"location.href='breadtest.html'\">Check files for more breads</button>";
        html = html + "</div>";
        html = html + "</div>";

        html = html + "</main>" + "</body>" + "</html>";

        context.html(html);
    }

}