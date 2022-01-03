package ru.job4j.cars.controller;

import ru.job4j.cars.dao.UserRepository;
import ru.job4j.cars.model.Role;
import ru.job4j.cars.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("regName");
        String email = req.getParameter("regEmail");
        String password = req.getParameter("regPassword");
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        Role role = new Role();
        role.setId(1);
        newUser.setRole(role);
        UserRepository.getInstance().save(newUser);
        req.getSession().setAttribute("authUser", newUser);
        resp.sendRedirect("index.jsp");
    }
}