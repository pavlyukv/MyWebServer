package ua.lviv.pancha.servlets;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import ua.lviv.pancha.services.ChatService;
import ua.lviv.pancha.services.ChatWebSocket;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Vasyl on 09.05.2017.
 */
@WebServlet(name = "WebSocketChatServlet", urlPatterns = {"/chat"})
public class WebSocketChatServlet extends WebSocketServlet {
    private final static int LOGOUT_TIME = 60 * 60 * 1000;
    private final ChatService chatService;

    public WebSocketChatServlet() {
        chatService = new ChatService();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((request, response) -> new ChatWebSocket(chatService));
    }
}
