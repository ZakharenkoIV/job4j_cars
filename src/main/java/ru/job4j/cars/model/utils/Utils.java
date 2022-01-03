package ru.job4j.cars.model.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.model.AdPhoto;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Utils {

    public static Ad createNewAd(Map<String, String> fields, List<AdPhoto> photos, int userId) {
        Ad newAd = new Ad.Builder()
                .withPrice(fields.get("inputPrice"))
                .withPhoneNumber(fields.get("inputPhoneNumber"))
                .withMileage(fields.get("inputMileage"))
                .withColor(fields.get("inputColor"))
                .withDescription(fields.get("inputDescription"))
                .withBrandCar(fields.get("inputBrandCar"))
                .withBodyType(fields.get("inputBodyType"))
                .withCreated(new Date())
                .withOnSale(true)
                .withUserId(userId)
                .build();
        newAd.setPhotos(photos);
        return newAd;
    }

    public static Map<String, String> parseFields(List<FileItem> items) {
        Map<String, String> fields = new HashMap<>();
        for (FileItem item : items) {
            if (item.isFormField()) {
                fields.put(item.getFieldName(), item.getString());
            }
        }
        return fields;
    }

    public static List<FileItem> getUploadItems(HttpServletRequest req, ServletContext context) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setDefaultCharset("UTF-8");
        File repository = (File) context.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = new ArrayList<>();
        try {
            items = upload.parseRequest(req);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static List<AdPhoto> parseFiles(List<FileItem> items, String userId, Path pathToAdPhotos)
            throws IOException {
        List<AdPhoto> photos = new ArrayList<>();
        Files.createDirectories(pathToAdPhotos);
        for (FileItem item : items) {
            if (!item.isFormField()) {
                String newFileName = newFileName(userId, item.getName());
                if (!"".equals(newFileName)) {
                    File photo = pathToAdPhotos.resolve(newFileName).toFile();
                    try {
                        item.write(photo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    photos.add(new AdPhoto(newFileName));
                }
            }
        }
        return photos;
    }

    public static String newFileName(String userId, String photoName) {
        StringJoiner newFileName = new StringJoiner("");
        if (!"".equals(photoName)) {
            String extension = photoName.substring(photoName.lastIndexOf("."));
            newFileName.add(userId);
            newFileName.add("_");
            newFileName.add(String.valueOf(System.currentTimeMillis()));
            newFileName.add(extension);
        }
        return newFileName.toString();
    }
}
