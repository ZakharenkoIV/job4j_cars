package ru.job4j.cars.controller;

import org.apache.commons.fileupload.FileItem;
import ru.job4j.cars.dao.AdRepository;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.model.AdPhoto;
import ru.job4j.cars.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static ru.job4j.cars.controller.AdServlet.PATH_TO_USERS_PHOTO_FOLDER;
import static ru.job4j.cars.model.utils.Utils.*;

public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        int adId = Integer.parseInt(req.getParameter("p"));
        Ad ad = AdRepository.getInstance().getAdById(adId);
        req.setAttribute("ad", ad);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Ad/edit.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int adId = Integer.parseInt(req.getParameter("p"));
        Ad ad1 = AdRepository.getInstance().getAdById(adId);

        ServletContext servletContext = this.getServletConfig().getServletContext();
        List<FileItem> items = getUploadItems(req, servletContext);
        Map<String, String> fields = parseFields(items);

        User authUser = (User) req.getSession().getAttribute("authUser");
        String userId = String.valueOf(authUser.getId());
        Path pathToAdPhotos = Paths.get(PATH_TO_USERS_PHOTO_FOLDER + userId + "/tempPhotoDir");
        List<AdPhoto> photos = parseFiles(items, userId, pathToAdPhotos);
        Ad ad = createNewAd(fields, photos, Integer.parseInt(userId));
        ad.setId(adId);
        ad.getPhotos().addAll(ad1.getPhotos());

        AdRepository.getInstance().update(ad);

        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
