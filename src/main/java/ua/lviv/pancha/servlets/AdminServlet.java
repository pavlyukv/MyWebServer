package ua.lviv.pancha.servlets;

import ua.lviv.pancha.accounts.AccountServerI;
import ua.lviv.pancha.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vasyl on 22.05.2017.
 */
public class AdminServlet extends HttpServlet {
    private final AccountServerI accountServer;

    public AdminServlet(AccountServerI accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("message", accountServer.getUsersLimit());
        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
