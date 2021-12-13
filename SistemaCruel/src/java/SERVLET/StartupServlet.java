package SERVLET;
import BEANS.ConfigBean;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "Startup", loadOnStartup = 1)
public class StartupServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        ConfigBean config = new ConfigBean();
        config.setEmailAdmin("gustavoachinitz@gmail.com,Laerteneto@gmail.com e felipe@gmail.com");
        ServletContext ctx = getServletContext();
        ctx.setAttribute("configuracao", config);
    }
}