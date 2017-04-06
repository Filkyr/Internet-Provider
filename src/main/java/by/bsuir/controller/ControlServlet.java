package by.bsuir.controller;

import by.bsuir.command.constant.PagePath;
import by.bsuir.command.CommandManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller", urlPatterns = "/Controller")
public final class ControlServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ControlServlet.class);
    private static final String CMD = "cmd";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String command = request.getParameter(CMD);
            CommandManager manager = CommandManager.getInstance();
            manager.getCommand(command).execute(request, response);
        } catch(Exception e){
            logger.error(e);
            response.sendRedirect(PagePath.PATH_TO_500_PAGE);
        }
    }
}
