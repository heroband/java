package org.example.webapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.DatabaseManager;
import org.example.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Servlet extends HttpServlet {
    private final DatabaseManager dbManager = new DatabaseManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> users = dbManager.getAllUsers();
        request.setAttribute("users", users);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/users.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        if (!username.trim().isEmpty() && !email.trim().isEmpty()) {
            dbManager.insertUser(new User(0, username, email));
        }

        request.setAttribute("userEmail", email);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userSaved.jsp");
        dispatcher.forward(request, response);
    }
}
