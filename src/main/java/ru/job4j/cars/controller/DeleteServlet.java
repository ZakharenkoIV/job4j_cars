package ru.job4j.cars.controller;

import org.apache.commons.io.FileUtils;
import ru.job4j.cars.dao.AdRepository;
import ru.job4j.cars.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static ru.job4j.cars.Utils.getPathToUsersPhotoFolder;

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String adId = req.getParameter("adId");
        User authUser = (User) req.getSession().getAttribute("authUser");
        AdRepository.getInstance().deleteAd(Integer.parseInt(adId));
        String pathToAdPhotos = getPathToUsersPhotoFolder()  + authUser.getId() + "/" + adId;
        FileUtils.deleteDirectory(new File(pathToAdPhotos));
    }
}
