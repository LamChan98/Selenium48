package heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;

public class DragandDropTest {
    @Test
    void DragAndDropA_And_B() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        Actions action = new Actions(driver);
        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

//        action.clickAndHold(source).moveToElement(target).release().build().perform();
        action.dragAndDrop(source, target).perform();

        Assert.assertEquals(source.getText(),"B");
        Assert.assertEquals(target.getText(),"A");

        driver.quit();
    }
}
