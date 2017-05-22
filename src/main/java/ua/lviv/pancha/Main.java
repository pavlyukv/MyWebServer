package ua.lviv.pancha;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.lviv.pancha.accounts.*;
import ua.lviv.pancha.servlets.*;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Created by Vasyl on 11.04.2017.
 */
public class Main {
    static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try (AccountService accountService = new AccountService()) {
            AccountServerI accountServer = new AccountServer(10);
            AccountServerControllerMBean serverStatistics = new AccountServerController(accountServer);

            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("Admin:type=AccountServerController.usersLimit");
            mbs.registerMBean(serverStatistics, name);

            ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
            contextHandler.addServlet(new ServletHolder(new MirrorServlet()), "/mirror");
            contextHandler.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
            contextHandler.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
            contextHandler.addServlet(new ServletHolder(new AdminServlet(accountServer)), "/admin");
            contextHandler.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");

            ResourceHandler resourceHandler = new ResourceHandler();
            resourceHandler.setDirectoriesListed(true);
            resourceHandler.setResourceBase("public_html");

            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{resourceHandler, contextHandler});

            Server server = new Server(8080);
            server.setHandler(handlers);

            server.start();
            logger.info("Server started");
            server.join();
        } catch (Exception e) {
            logger.fatal("Server crashed", e);
        }
    }
}
