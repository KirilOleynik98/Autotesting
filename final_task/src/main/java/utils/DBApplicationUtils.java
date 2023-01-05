package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import models.TestTable;
import java.util.List;

public class DBApplicationUtils {

    static ISettingsFile environment = new JsonSettingsFile("testData.json");

    private static final String TEST_PROJECTS_LIST = environment.getValue("/testProjectsList").toString();
    private static final String PROJECT_NAME_NEXAGE = environment.getValue("/projectNameNexage").toString();
    private static final String PROJECT_NAME_ALOHA = environment.getValue("/projectNameAloha").toString();
    private static final String ADD_TEST_TO_PROJECT = environment.getValue("/addTestToProject").toString();
    private static final String ADD_PROJECT_ID = environment.getValue("/addProjectId").toString();
    private static final String ADD_PROJECT_NAME = environment.getValue("/addProjectName").toString();
    private static final String ADD_PROJECT_STATUS_ID = environment.getValue("/addProjectStatusId").toString();
    private static final String ADD_PROJECT_METHOD_NAME = environment.getValue("/addProjectMethodName").toString();
    private static final String ADD_PROJECT_PROJECT_ID = environment.getValue("/addProjectProjectId").toString();
    private static final String ADD_PROJECT_SESSION_ID = environment.getValue("/addProjectSessionId").toString();
    private static final String ADD_PROJECT_START_TIME = environment.getValue("/addProjectStartTime").toString();
    private static final String ADD_PROJECT_END_TIME = environment.getValue("/addProjectEndTime").toString();
    private static final String ADD_PROJECT_ENV = environment.getValue("/addProjectEnv").toString();
    private static final String ADD_PROJECT_BROWSER = environment.getValue("/addProjectBrowser").toString();
    private static final String ADD_PROJECT_AUTHOR_ID = environment.getValue("/addProjectAuthorId").toString();
    private static final String ADD_SCREENSHOT_TO_PROJECT = environment.getValue("/addScreenshot").toString();
    private static final String SCREENSHOT_ID = environment.getValue("/screenshotId").toString();
    private static final String SCREENSHOT_TEST_ID = environment.getValue("/screenshotTestId").toString();
    private static final String SCREENSHOT_CONTENT_TYPE = environment.getValue("/screenshotContentType").toString();
    private static final String SCREENSHOT_CONTENT_PATH = environment.getValue("/screenshotPath").toString();

    public static List<TestTable> testProjectsListNexage() {
        List<TestTable> dbTable = DBUtils.performSqlRequest(String.format(TEST_PROJECTS_LIST, PROJECT_NAME_NEXAGE));
        return dbTable;
    }

    public static void addTestAndScreenshotToProject() {
        DBUtils.performSqlRequest(String.format(ADD_TEST_TO_PROJECT, ADD_PROJECT_ID, ADD_PROJECT_NAME, ADD_PROJECT_STATUS_ID, ADD_PROJECT_METHOD_NAME,
                ADD_PROJECT_PROJECT_ID, ADD_PROJECT_SESSION_ID, ADD_PROJECT_START_TIME, ADD_PROJECT_END_TIME, ADD_PROJECT_ENV, ADD_PROJECT_BROWSER, ADD_PROJECT_AUTHOR_ID));
        DBUtils.performSqlRequest(String.format(ADD_SCREENSHOT_TO_PROJECT, SCREENSHOT_ID, SCREENSHOT_TEST_ID, SCREENSHOT_CONTENT_TYPE, SCREENSHOT_CONTENT_PATH));
    }

    public static List<TestTable> testProjectsListAloha() {
        List<TestTable> dbTable = DBUtils.performSqlRequest(String.format(TEST_PROJECTS_LIST, PROJECT_NAME_ALOHA));
        return dbTable;
    }
}

