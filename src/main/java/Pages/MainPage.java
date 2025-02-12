package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonFunctions;



public class MainPage {

    WebDriver driver;
    CommonFunctions commonFunctions;
    public void setDriver(WebDriver webDriver) {
        this.driver = webDriver;
    }


    public MainPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        setDriver(driver);
        this.commonFunctions=new CommonFunctions(driver);
    }

    @FindBy(xpath = "//nav//a[contains(text(),'Company')]")
    public WebElement companyMenu;

    @FindBy(xpath = "//a[contains(text(),'Careers')]")
    public WebElement careersLink;


    public void openCompanyMenu() {
        commonFunctions.waitForElementThenClick(companyMenu);
    }

    public void openCareersPage() {
        commonFunctions.waitForElementThenClick(careersLink);
        commonFunctions.waitForSecond(5);
    }

    public void isMainPageOpened(){
        Assert.assertTrue(checkIfMainPageOpened(),"Main page doesn't displayed");
        commonFunctions.captureScreenshot("Main Page is opened");

    }

    public boolean checkIfMainPageOpened() {

        try {
            if (driver.getTitle().contains("Insider")) {
                Assert.assertTrue(true, "Home page did not load correctly.");
                return true;
            }
        } catch (Exception e) {
            commonFunctions.captureScreenshot("Insider title not exist");
        }
        return false;
    }
}
