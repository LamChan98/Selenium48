package heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.zip.CheckedOutputStream;

public class MouseActionsTest {
    @Test
    void hoverTest() {
/*
        TC08:  Hover elements
        Open browser
        Navigate to https://the-internet.herokuapp.com/hovers
        When user hover on user 1 avatar
        Then the "name: user1" label is present.
*/
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/hovers");

        Actions action = new Actions(driver);
        WebElement avatar1 = driver.findElements(By.className("figure")).get(1);
        action.moveToElement(avatar1).perform();

        Assert.assertTrue(avatar1.findElement(By.xpath(".//h5")).isDisplayed());

        String hrefLink = driver.findElement(By.linkText("View profile")).getDomAttribute("href");
        avatar1.findElement(By.linkText("View profile")).click();
        Assert.assertNotNull(hrefLink);
        Assert.assertTrue(driver.getCurrentUrl().contains(hrefLink));
    }

    @Test
    void DragAndDropA_And_B() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        Actions action = new Actions(driver);
        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

//        action.clickAndHold(source).moveToElement(target).release().build().perform();
        action.dragAndDrop(source, target).perform();

        Assert.assertEquals(source.getText(), "B");
        Assert.assertEquals(target.getText(), "A");

        driver.quit();
    }

    @Test
    void horizontalSliderTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/horizontal_slider");
        Actions action = new Actions(driver);
        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
        System.out.println(slider.getSize().getWidth());

        action.clickAndHold(slider).moveByOffset(100, 0).release().perform();
        String value = driver.findElement(By.id("range")).getText();
        Assert.assertEquals(value, "5");
        driver.quit();
    }

    @Test
    void infiniteScrollTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/infinite_scroll");
        Actions action = new Actions(driver);
        for (int i = 0; i < 5; i++) {
            action.scrollByAmount(0, 100).perform();
            Thread.sleep(1000);
        }
        driver.quit();
    }

    @Test
    void contextMenuTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/context_menu");
        Actions action = new Actions(driver);
        WebElement box = driver.findElement(By.id("hot-spot"));
        action.contextClick(box).perform();
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert text: " + alertText);
        Assert.assertEquals(alertText, "You selected a context menu");
        driver.switchTo().alert().accept();
        driver.quit();
    }

    @Test
    void keyPressTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/key_presses");
        Actions action = new Actions(driver);
        action.sendKeys(Keys.BACK_SPACE).perform();

        String resultText = driver.findElement(By.id("result")).getText();
        Assert.assertTrue(resultText.contains("You entered: A"));

        driver.quit();
    }
}
