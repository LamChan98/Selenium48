package americanairline;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class BookFlight {
    @Test
    void verifyBuyOneWayTicket() throws InterruptedException {
/*      1. open browser
        2. navigate to https://www.aa.com/homePage.do?locale=en_US
        3. select one way
        4. select day 7/4/2025
        5. verify day selected*/


        // Initialize driver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        driver.get("https://www.aa.com/homePage.do?locale=en_US");
//        driver.findElement(By.id("flightSearchForm.tripType.oneWay")).click();
        WebElement oneWayRadioLabel = driver.findElement(By
                .xpath("//label[@for='flightSearchForm.tripType.oneWay']"));
        //True: scroll up, False: Scroll down
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", oneWayRadioLabel);
        oneWayRadioLabel.click();

        WebElement searchDestinationTo = driver.findElement(By
                .cssSelector("[data-for='reservationFlightSearchForm.destinationAirport']"));
        // scroll down
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", searchDestinationTo);
        searchDestinationTo.click();

        // Select country code
        Select selectCountryCode = new Select(driver.findElement(By.cssSelector("select#countryCode")));
        selectCountryCode.selectByContainsVisibleText("United States");

        // Select state code
        Select selectStateCode = new Select(driver.findElement(By.cssSelector("select#stateCode")));
        selectStateCode.selectByContainsVisibleText("New York");

        //select airport NYC
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
//                driver.findElement(By.xpath("//span[.='NYC']")));
                driver.findElement(By.id("airport_NYC")));

        //wait
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='NYC']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("airport_NYC")));
//        Thread.sleep(8000);

        // Double click to NYC airport code
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(By.id("airport_LGA"))).perform();

        driver.findElement(By.xpath("//button[@class='ui-datepicker-trigger']")).click();
        driver.findElements(By.cssSelector("td[data-handler='selectDay'] a"))
                .stream()
                .filter(el -> el.getText()
                        .equals("25"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Date 25 not found"))
                .click();

        driver.findElement(By.id("flightSearchForm.button.reSubmit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Choose flights']")));
        WebElement chooseFlightHead = driver.findElement(By.xpath("//span[text()='Choose flights']"));
        Assert.assertEquals(chooseFlightHead.getText(),"Choose flights");
    }
}
