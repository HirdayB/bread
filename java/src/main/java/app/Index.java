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

        html = html + "<div class='homebanner'>" +
                    "<img src = 'breads.png' width ='100%'/>" +
                    "<div class='centered'>Bakr.io</div>" +
                    "</div>";

        html = html + "</main>" + "</body>" + "</html>";

        context.html(html);
    }

}