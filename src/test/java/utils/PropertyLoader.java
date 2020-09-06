package utils;

import java.util.ResourceBundle;

public class PropertyLoader {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("test");

    public static String loadProperty(String name) {
        return resourceBundle.getString(name);
    }

}
