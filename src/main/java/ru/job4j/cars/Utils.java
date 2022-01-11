package ru.job4j.cars;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utils {

    private static final String PATH_TO_PROPERTIES;
    private static final Properties PROPERTIES;
    private static final String DEFAULT_CAR_PHOTO;
    private static final String PATH_TO_USERS_PHOTO_FOLDER;

    static {
        PATH_TO_PROPERTIES = "C:\\Projects\\job4j_cars\\src\\main\\resources\\app.properties";
        PROPERTIES = new Properties();
        File file = new File(PATH_TO_PROPERTIES);
        try {
            PROPERTIES.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DEFAULT_CAR_PHOTO = PROPERTIES.getProperty("DEFAULT_CAR_PHOTO");
        PATH_TO_USERS_PHOTO_FOLDER = PROPERTIES.getProperty("PATH_TO_USERS_PHOTO_FOLDER");
    }

    public static String getDefaultCarPhoto() {
        return DEFAULT_CAR_PHOTO;
    }

    public static String getPathToUsersPhotoFolder() {
        return PATH_TO_USERS_PHOTO_FOLDER;
    }
}
