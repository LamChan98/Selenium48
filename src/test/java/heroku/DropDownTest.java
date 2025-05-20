package heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class DropDownTest {
    /*TC03: DropDown : Select option
    Open browser
    Navigate to https://the-internet.herokuapp.com/dropdown
    Select "option 1"
    Validate "option 1" is selected*/

    @Test
    void tc03(){
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        driver.get("https://the-internet.herokuapp.com/dropdown");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h3"), "Dropdown List"));

        Select select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByContainsVisibleText("Option 1");
        Assert.assertTrue(driver.findElement(By.xpath("//option[.='Option 1']")).isSelected());
//        Assert.assertTrue(driver.findElement(By.cssSelector("option[value='Option1']")).isSelected());

        select.selectByValue("1");
        Assert.assertTrue(driver.findElement(By.cssSelector("option[value='1']")).isSelected());

        driver.quit();
    }

    @Test
    void ableSelectMultipleOptions(){
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        driver.get("https://output.jsbin.com/osebed/2");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fruits")));

        Select select = new Select(driver.findElement(By.id("fruits")));
        Assert.assertTrue(select.isMultiple());

        select.selectByVisibleText("Banana");
        select.selectByVisibleText("Apple");
        select.selectByVisibleText("Orange");

        Assert.assertTrue(driver.findElement(By.xpath("//option[.='Banana']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//option[.='Apple']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//option[.='Orange']")).isSelected());
    }

    @Test
    void verifyTestFieldDisable(){
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h4"),"Dynamic Controls"));

        Assert.assertFalse(driver.findElement(By.cssSelector("form#input-example input")).isEnabled());
        driver.findElement(By.cssSelector("form#input-example button")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#message"),"It's enabled!"));
        Assert.assertTrue(driver.findElement(By.cssSelector("form#input-example input")).isEnabled());
        driver.quit();
    }
}