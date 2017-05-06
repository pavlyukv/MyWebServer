package ua.lviv.pancha.servlets;

import ua.lviv.pancha.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vasyl on 11.04.2017.
 */
public class MirrorServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("key") != null) {
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("message", request.getParameter("key").toString());
            response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
        } else {
            response.getWriter().println();
        }
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
