package ua.lviv.pancha;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.lviv.pancha.accounts.AccountService;
import ua.lviv.pancha.accounts.UserProfile;
import ua.lviv.pancha.services.DBService;
import ua.lviv.pancha.servlets.MirrorServlet;
import ua.lviv.pancha.servlets.SignInServlet;
import ua.lviv.pancha.servlets.SignUpServlet;

/**
 * Created by Vasyl on 11.04.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DBService dbService = new DBService();

        AccountService accountService = new AccountService(dbService);
//        accountService.addNewUser(new UserProfile("admin"));
//        accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        contextHandler.addServlet(new ServletHolder(new MirrorServlet()), "/mirror");
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        contextHandler.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, contextHandler});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        server.join();

        dbService.close();
    }
}
