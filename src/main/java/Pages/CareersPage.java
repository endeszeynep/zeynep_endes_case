package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonFunctions;

public class CareersPage {

    WebDriver driver;
    CommonFunctions commonFunctions;

    public void setDriver(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public CareersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        setDriver(driver);
        this.commonFunctions=new CommonFunctions(driver);
    }

    //elements
    @FindBy(xpath ="//h3[@class=\"category-title-media ml-0\"]")
    public WebElement locationsBlock;

    @FindBy(xpath = "//h3[contains(text(),'Find your calling')]")
    public WebElement teamsBlock;

    @FindBy(xpath = "//div[@class=\"elementor-widget-wrap elementor-element-populated e-swiper-container\"]")
    public WebElement lifeAtInsiderBlock;

    //Functions
    public void isTeamsBlockDisplayed() {
        commonFunctions.waitForSecond(3);
        commonFunctions.moveTOElement(teamsBlock);
        Assert.assertTrue(commonFunctions.checkElementIfExists(teamsBlock,5), "Teams block is not visible.");

    }
    public void isLocationsBlockDisplayed() {
        commonFunctions.moveTOElement(locationsBlock);
        Assert.assertTrue(commonFunctions.checkElementIfExists(locationsBlock,5), "Locations block is not visible.");

    }

    public void isLifeAtInsiderBlockDisplayed() {
        commonFunctions.moveTOElement(lifeAtInsiderBlock);
        Assert.assertTrue(commonFunctions.checkElementIfExists(lifeAtInsiderBlock,5), "Life at Insider block is not visible.");

    }

}
