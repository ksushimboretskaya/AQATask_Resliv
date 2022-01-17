package utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class KeyboardUtils {

    public static void clearTheField(WebElement inputTextField){
        inputTextField.sendKeys(Keys.CONTROL + "A");
        inputTextField.sendKeys(Keys.DELETE);
    }
}
