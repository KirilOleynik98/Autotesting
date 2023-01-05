package constants;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class EndPoints {

    static ISettingsFile environment = new JsonSettingsFile("config.json");
    private static final String BASE_PATH = environment.getValue("/basePath").toString();
    public static final String GET_TOKEN = String.format(BASE_PATH, "token/get?variant=%s");
    public static final String TEST_NAME_CEll = "Test name";
    public static final String TEST_METHOD_CEll = "Test method";
    public static final String TEST_RESULT_CEll = "Latest test result";
    public static final String TEST_START_TIME_CEll = "Latest test start time";
    public static final String TEST_END_TIME_CEll = "Latest test start time";
    public static final String TEST_DURATION_CEll = "Latest test duration (H:m:s.S)";
}
