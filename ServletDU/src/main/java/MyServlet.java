import java.io.IOException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRulesException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/timeZone"})
public class MyServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String zona = request.getParameter("zona");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<title>Fakt legrace</title>" +
                    "</head>" +
                    "<body>");
            try {
                ZoneId zonaID = ZoneId.of(zona);
                ZonedDateTime upraveno = ZonedDateTime.now(zonaID);
                out.println("Čas: " + upraveno);
            } catch (ZoneRulesException e) {
                out.println("Spatna zona");
            } catch (DateTimeException i) {
                out.println("Neco se pokazilo");
            }
            out.println("<br>" +
                    "<a href=\"./\">Back<a>" +
                    "</body>" +
                    "</html>");
        }
    }


}