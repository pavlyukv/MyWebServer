package ua.lviv.pancha;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.lviv.pancha.accounts.AccountService;
import ua.lviv.pancha.servlets.MirrorServlet;
import ua.lviv.pancha.servlets.SignInServlet;
import ua.lviv.pancha.servlets.SignUpServlet;
import ua.lviv.pancha.servlets.WebSocketChatServlet;

/**
 * Created by Vasyl on 11.04.2017.
 */
public class Main {
    public static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        try (AccountService accountService = new AccountService()) {
            ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
            contextHandler.addServlet(new ServletHolder(new MirrorServlet()), "/mirror");
            contextHandler.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
            contextHandler.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
            contextHandler.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");

            ResourceHandler resourceHandler = new ResourceHandler();
            resourceHandler.setDirectoriesListed(true);
            resourceHandler.setResourceBase("public_html");

            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{resourceHandler, contextHandler});

            Server server = new Server(8080);
            server.setHandler(handlers);

            server.start();
            LOG.info("Server started");
            server.join();
        } catch (Exception e) {
            LOG.fatal("Server crashed", e);
        }
    }
}
