package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class TestDataUtils {

    private static ISettingsFile environment = new JsonSettingsFile("testData.json");
    public static final String AUTH_URL = environment.getValue("/authUrl").toString();
    public static final int VARIANT = (int) environment.getValue("/variant");
}
