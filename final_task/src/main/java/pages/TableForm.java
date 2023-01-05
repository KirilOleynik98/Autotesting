package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Label;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import constants.EndPoints;
import elements.Table;
import models.TestTable;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;

public class TableForm extends Form {

    private final Table tableElement = AqualityServices.getElementFactory().getCustomElement(Table::new, By.xpath("//table[contains(@class, 'table')]"), "Table Element", ElementState.EXISTS_IN_ANY_STATE);
    private final IButton testButton = AqualityServices.getElementFactory().getButton(By.xpath("//div[contains(@class, 'panel-dafault')]//a[(contains(@href, 'testInfo']"), "Test Button");

    public TableForm() {
        super(By.xpath("//table[contains(@class, 'table')]"), "Table Form");
    }

    public List<Label> getAllRows() {
        List<Label> allRows = tableElement.findChildElements(By.tagName("tr"), "rows", Label.class);
        allRows.remove(0);
        return allRows;
    }

    public List<Label> getAllCellsFromTheRow(Label row) {
        return row.findChildElements(By.tagName("td"), "rows", Label.class);
    }

    public int getIndexOfColumns(String nameOfColumn) {
        List<Label> columnName = tableElement.findChildElements(By.tagName("th"), "names", Label.class);
        return columnName.indexOf(nameOfColumn);
    }

    public List<TestTable> getTestsFromTable() {
        List<TestTable> tables = new ArrayList<>();
        List<Label> rows = getAllRows();
        for (Label row : rows) {
            List<Label> cells = getAllCellsFromTheRow(row);
            tables.add(new TestTable(cells.get(getIndexOfColumns(EndPoints.TEST_NAME_CEll)).getText(), cells.get(getIndexOfColumns(EndPoints.TEST_METHOD_CEll)).getText(), cells.get(getIndexOfColumns(EndPoints.TEST_RESULT_CEll)).getText(),
                    cells.get(getIndexOfColumns(EndPoints.TEST_START_TIME_CEll)).getText(), cells.get(getIndexOfColumns(EndPoints.TEST_END_TIME_CEll)).getText(), cells.get(getIndexOfColumns(EndPoints.TEST_DURATION_CEll)).getText()));
        }
        return tables;
    }

    public boolean isTestButtonAppeared() {
        return testButton.state().isDisplayed();
    }

    public void clickTestButton() {
        testButton.click();
    }
}
