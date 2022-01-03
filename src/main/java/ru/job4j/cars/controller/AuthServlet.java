package ru.job4j.cars.controller;

import ru.job4j.cars.dao.UserRepository;
import ru.job4j.cars.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String email = req.getParameter("loginEmail");
        String password = req.getParameter("loginPassword");
        User foundUser = UserRepository.getInstance().getUserByEmail(email);
        if (foundUser.getPassword().equals(password)) {
            req.getSession().setAttribute("authUser", foundUser);
            resp.sendRedirect("index.jsp");
        }
    }
}
