package ru.job4j.cars.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import static ru.job4j.cars.Utils.getDefaultCarPhoto;
import static ru.job4j.cars.Utils.getPathToUsersPhotoFolder;

public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("image/jpeg");
        String adId = "";
        String userId = "";
        Optional<String> photoPath = Optional.ofNullable(req.getParameter("p2"));
        if (photoPath.isPresent()) {
            userId = photoPath.orElse("").split("_")[0] + "/";
            adId = req.getParameter("p1") + "/";
        }

        byte[] imageBytes = Files.readAllBytes(Paths.get(getPathToUsersPhotoFolder()
                .concat(userId)
                .concat(adId)
                .concat(photoPath.orElse(getDefaultCarPhoto()))));
        resp.setContentLength(imageBytes.length);
        ServletOutputStream outStream = resp.getOutputStream();
        outStream.write(imageBytes);
        outStream.close();
    }
}
