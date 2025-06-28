package heroku;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotTest {
    @Test
    void TakeScreenShotFail() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/context_menu");

        try {
            Assert.assertTrue(driver.findElement(By.xpath("//h3")).getText().contains("Hello"));
        } catch (AssertionError e) {

            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            File desFile = new File(String.format("target/screenshot-%s-%s.png", "context-menu", timestamp));
            try {
                FileUtils.copyFile(srcFile, desFile);

            } catch (IOException err) {
                throw new RuntimeException(err);
            }

            throw e;
        }
    }
}
