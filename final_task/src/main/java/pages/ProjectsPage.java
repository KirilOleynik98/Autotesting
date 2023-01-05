package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import enums.NameOfProject;
import org.openqa.selenium.By;

public class ProjectsPage extends Form {

    private final String projectLocator = "//a[contains(@class, 'list-group-item') and contains(text(), '%s')]";
    private final ILabel textFromFooterLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//p[contains(@class, 'footer-text')]//span[text()]"), "Text from footer Label");
    private final IButton addProjectButton = AqualityServices.getElementFactory().getButton(By.xpath("//button[contains(@data-target, 'addProject')]"), "Add project Button");

    public ProjectsPage() {
        super(By.xpath("//div[contains(@class, 'list-group')]"), "Projects Page");
    }

    public int getNumberOfVersionFromFooterLabel() {
        String textFromFooter = textFromFooterLabel.getText();
        return Integer.parseInt(String.valueOf(textFromFooter.charAt(textFromFooter.length() - 1)));
    }

    public void clickAddProjectButton() {
        addProjectButton.click();
    }

    public boolean isProjectDisplayed(NameOfProject nameOfProject) {
        final ILabel projectLabel = AqualityServices.getElementFactory().getLabel(By.xpath(String.format(projectLocator, nameOfProject.getProjectName())),
                nameOfProject.getProjectName() + " project Label");
        return projectLabel.state().isDisplayed();
    }

    public void clickProjectButton(NameOfProject nameOfProject) {
        final IButton projectButton = AqualityServices.getElementFactory().getButton(By.xpath(String.format(projectLocator, nameOfProject.getProjectName())),
                nameOfProject.getProjectName() + " project Button");
        projectButton.click();
    }
}
