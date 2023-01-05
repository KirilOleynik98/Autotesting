import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import enums.NameOfProject;
import models.TestTable;
import org.testng.annotations.Test;
import pages.*;
import utils.*;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NexageTest extends BaseTest {

    private ProjectsPage projectsPage;
    private TableForm tableForm;
    private AddProjectForm addProjectForm;

    @Test
    public void nexageTest() {
        Logger.getInstance().info("Step 1: Using api request, get a token according to the variant number");
        String token = ApiApplicationRequest.getToken((Integer) TestDataUtils.VARIANT);
        Logger.getInstance().info("Step 2: Go to site. Pass the necessary authorization. Using cookie, pass the token generated in step 1 (the token parameter). Refresh the page");
        AqualityServices.getBrowser().getDriver().get(TestDataUtils.AUTH_URL);
        BrowserUtils.createCookie(token);
        AqualityServices.getBrowser().refresh();
        projectsPage = new ProjectsPage();
        assertTrue(projectsPage.state().waitForDisplayed(AqualityServices.getConfiguration().getTimeoutConfiguration().getPageLoad()), "Project page isn't open");
        assertEquals(projectsPage.getNumberOfVersionFromFooterLabel(), TestDataUtils.VARIANT, "After refreshing the page, the variant number is incorrect in the footer");
        Logger.getInstance().info("Step 3: Go to the Nexage project page. Get a list of project tests by querying the database");
        projectsPage.clickProjectButton(NameOfProject.NEXAGE);
        List<TestTable> DataBaseTestsNexage = DBApplicationUtils.testProjectsListNexage();
        tableForm = new TableForm();
        List<TestTable> UITestsNexage = tableForm.getTestsFromTable();
        List<Date> startDate = BrowserUtils.getTestStartTimeList(UITestsNexage);
        List<Date> sortStartDate = startDate;
        sortStartDate.sort(Comparator.reverseOrder());
        assertEquals(startDate, sortStartDate, "The tests on the first page are not sorted by date descending");
        assertTrue(DataBaseTestsNexage.containsAll(UITestsNexage), "The tests on the first page do not match those returned by the DB request");
        Logger.getInstance().info("Step 4: Return to the previous page in the browser (project page). Click on +Add. Enter a project name and save. Close the alert with information" +
                "about the saved project. To close the window for adding a project, call the js-method closePopUp(). Refresh the page");
        AqualityServices.getBrowser().goBack();
        projectsPage.clickAddProjectButton();
        AqualityServices.getBrowser().tabs().switchToLastTab();
        addProjectForm = new AddProjectForm();
        addProjectForm.enterProjectNameInTextBox(NameOfProject.ALOHA.getProjectName());
        addProjectForm.clickSaveProjectButton();
        assertTrue(addProjectForm.isSuccessLabelAppeared(), "After saving the project, an alert isn't appeared");
        BrowserUtils.closeCurrentTabAndSwitchToPreviousTab();
        AqualityServices.getBrowser().refresh();
        assertTrue(projectsPage.isProjectDisplayed(NameOfProject.ALOHA), "After refreshing the page, the project didn't appear in the list");
        Logger.getInstance().info("Step 5: Go to the page of the created project. Add a test through the database (together with a log and a screenshot of the current page)");
        projectsPage.clickProjectButton(NameOfProject.ALOHA);
        DBApplicationUtils.addTestAndScreenshotToProject();
        assertTrue(tableForm.isTestButtonAppeared(), "Test isn't appeared without refreshing the page");
        Logger.getInstance().info("Step 6: Go to the page of the created test");
        tableForm.clickTestButton();
        AqualityServices.getBrowser().goBack();
        List<TestTable> DataBaseTestsAloha = DBApplicationUtils.testProjectsListAloha();
        List<TestTable> UITestsAloha = tableForm.getTestsFromTable();
        assertTrue(DataBaseTestsAloha.containsAll(UITestsAloha), "All fields aren't correct");
    }
}
