package americanairline;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
        ChromeOptions options = new ChromeOptions();

        // Hide automation flags
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        // Incognito optional
        options.addArguments("--incognito");
        options.addArguments("--disable-popup-blocking");

        // Realistic user-agent
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");

        // Start driver
        WebDriver driver = new ChromeDriver(options);

        // Remove navigator.webdriver
        ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
        );


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
                driver.findElement(By.xpath("//span[.='NYC']")));
//                driver.findElement(By.id("airport_NYC")));

        //wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='NYC']")));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("airport_NYC")));
        Thread.sleep(8000);

        // Double click to NYC airport code
        Actions actions = new Actions(driver);
//        WebElement airPortNYC = driver.findElement(By.id("airport_NYC"));
//        WebElement airPortNYC = driver.findElement(By.xpath("//span[.='NYC']"));
        WebElement airPortNYC = driver.findElement(By.xpath("//*[@id=\"airport_NYC\"]/span[1]"));
        WebElement link = driver.findElement(By.partialLinkText("NYC"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
//        link.click();
//        actions.moveToElement(airPortNYC).click().perform();
//        actions.doubleClick(airPortNYC).perform();
//        actions.doubleClick(driver.findElement(By.xpath("//span[.='ISP']"))).perform();

//        driver.findElement(By.linkText("NYC'")).click();
//        driver.findElement(By.id("airport_NYC")).click();

//        driver.findElements(By.xpath("//table[@id='airportsSection']/tbody/tr/a"))
//                .stream().filter(fl -> fl.getText().equals("NYC"))
//                .forEach(el->actions.doubleClick(el).perform());

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("airport_NYC")));
//        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@class='ui-datepicker-trigger']")).click();

        driver.findElements(By.cssSelector("td[data-handler='selectDay'] a"))
                .stream()
                .filter(el -> el.getText()
                        .equals("30"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Date 30 not found"))
                .click();

        driver.findElement(By.id("flightSearchForm.button.reSubmit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Choose flights']")));
        WebElement chooseFlightHead = driver.findElement(By.xpath("//span[text()='Choose flights']"));
        Assert.assertEquals(chooseFlightHead.getText(), "Choose flights");
    }
}
