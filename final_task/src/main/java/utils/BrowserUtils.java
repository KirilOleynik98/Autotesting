package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import models.TestTable;
import org.openqa.selenium.Cookie;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BrowserUtils {

    static ISettingsFile environment = new JsonSettingsFile("config.json");

    public static void createCookie(String cookie) {
        Cookie cukie = new Cookie("token", cookie);
        AqualityServices.getBrowser().getDriver().manage().addCookie(cukie);
    }

    public static void closeCurrentTabAndSwitchToPreviousTab() {
        AqualityServices.getBrowser().tabs().closeTab();
        AqualityServices.getBrowser().tabs().switchToLastTab();
    }

    public static List<Date> getTestStartTimeList(List<TestTable> tables) {
        List<Date> startDates = tables.stream().map(s -> {
            try {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(s.getStartTime());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        return startDates;
    }
}
