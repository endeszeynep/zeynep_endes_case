package utils;

import org.apache.commons.io.FileUtils;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.testng.Assert;


import static java.time.Duration.of;
import static java.time.Duration.ofMillis;

public class CommonFunctions {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    FluentWait fluentWait;

    public CommonFunctions(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions=new Actions(driver);
        this.fluentWait=new FluentWait(driver);
    }

    //elements
    @FindBy(xpath = "//a[@id=\"wt-cli-accept-all-btn\"]")
    public WebElement acceptAllBtn;//span[@class="ins-close-button"]

    @FindBy(xpath = "//span[@class=\"ins-close-button\"]")
    public WebElement closeBtn;

    //functions

    public void acceptAllCookies(){
        waitForElementThenClickIfExists(acceptAllBtn,3);
    }

    public void closeAdvertisementIfExist(){
        waitForElementThenClickIfExists(closeBtn,3);
    }

    public void waitForElementThenClickIfExists(WebElement element, int waitingTime) {
        try {
            wait.withTimeout(Duration.ofSeconds(waitingTime)).until(ExpectedConditions.visibilityOf(element));
            if (element.isDisplayed()) {
                element.click();
            }
        } catch (Exception e) {
           captureScreenshot("Element doesn't exist to ");
        }
    }

    public boolean checkElementIfExists(WebElement element, int waitingTime) {
        try {
            wait.withTimeout(Duration.ofSeconds(waitingTime)).until(ExpectedConditions.visibilityOf(element));
            if (element.isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
            captureScreenshot("Element doesn't exist");
        }
        return false;
    }

    public boolean checkElementExistsByUsingText(WebElement element,String text) {
        waitForSecond(4);

        try {
            if (element.getText().contains(text)==true) {
                System.out.println(element.getText()+"element exist by text");

                return true;
            }
        } catch (Exception e) {
            captureScreenshot("Failed not contains given text");
            Assert.fail("Element doesn't contain defined text");
        }
             return false;
    }

    public void captureScreenshot(String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitForElementThenClick(WebElement element) {
        waitForElement(element);
        element.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public void moveTOElement(WebElement element) {

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            waitForSecond(2);
        }
        catch (Exception e){
            captureScreenshot("ElementIsNotVisible");
            Assert.fail("Element is not visible");
            e.printStackTrace();
        }
    }

    public void hoverElement(WebElement element) {

        waitForSecond(3);
        try {
            actions.moveToElement(element);
            actions.perform();
        }
        catch (Exception e){
            captureScreenshot("ElementIsNotVisible");
            Assert.fail("Element is not visible");
            e.printStackTrace();
        }
    }

    public void waitForElement(WebElement element) {

        FluentWait waitfluent= new FluentWait<>(driver);
        try {
            waitfluent.until(ExpectedConditions.visibilityOf(element));
            wait.withTimeout(Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            captureScreenshot("ElementNotDisplayed");
            Assert.fail("Element " + element.getText() + " is not visible");
            e.printStackTrace();
        }
    }

    public void swipeUp(){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)","");
    }

    public void waitForSecond(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

