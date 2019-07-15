package fileio;

import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;

import java.io.File;

public class ReadHander implements ElementHandler {
    @Override
    public void onStart(ElementPath elementPath) {

    }

    @Override
    public void onEnd(ElementPath elementPath) {
        if (elementPath.getCurrent().getName().equals("VendorName")) {
            System.out.println(elementPath.getCurrent().getText());
        }
        elementPath.getCurrent().detach();
    }
}
