package elements;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Element;
import org.openqa.selenium.By;

public class Table extends Element {

    String Table;

    public Table(By loc, String nameOf, ElementState stateOf) {
        super(loc, nameOf, stateOf);
    }

    @Override
    protected String getElementType() {
        return Table;
    }
}
