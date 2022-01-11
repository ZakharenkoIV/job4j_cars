package ru.job4j.cars.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import ru.job4j.cars.dao.AdRepository;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.model.AdPhoto;
import ru.job4j.cars.model.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static ru.job4j.cars.Utils.getPathToUsersPhotoFolder;
import static ru.job4j.cars.model.utils.Utils.*;

public class AdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        if ("true".equals(req.getParameter("myAd"))) {
            req.setAttribute("allAds", AdRepository.getInstance().getMyAds(
                    ((User) req.getSession().getAttribute("authUser")).getId()));
            req.setAttribute("myAd", "true");
        } else {
            req.setAttribute("allAds", AdRepository.getInstance().getAllAds());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User authUser = (User) req.getSession().getAttribute("authUser");
        String userId = String.valueOf(authUser.getId());
        ServletContext servletContext = this.getServletConfig().getServletContext();
        List<FileItem> items = getUploadItems(req, servletContext);
        Map<String, String> fields = parseFields(items);
        Path pathToAdPhotos = Paths.get(getPathToUsersPhotoFolder()  + userId + "/tempPhotoDir");
        List<AdPhoto> photos = parseFiles(items, userId, pathToAdPhotos);
        Ad newAd = createNewAd(fields, photos, Integer.parseInt(userId));
        AdRepository.getInstance().save(newAd);
        File dirAdPhoto = pathToAdPhotos.toFile();
        if (newAd.getId() == 0) {
            FileUtils.deleteDirectory(dirAdPhoto);
        } else {
            dirAdPhoto.renameTo(new File(dirAdPhoto.getParent() + "/" + newAd.getId()));
        }
        resp.sendRedirect("index.jsp");
    }
}
