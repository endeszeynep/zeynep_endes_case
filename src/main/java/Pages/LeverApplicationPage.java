package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonFunctions;

public class LeverApplicationPage {

    WebDriver driver;
    CommonFunctions commonFunctions;

    public void setDriver(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public LeverApplicationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        setDriver(driver);
        this.commonFunctions=new CommonFunctions(driver);
    }

    //elements
    @FindBy(xpath = "//a[@class=\"btn btn-navy rounded pt-2 pr-5 pb-2 pl-5\"]")
    public WebElement viewRoleButton;

    //functions
    public void clickViewRoleButton() {
        commonFunctions.hoverElement(viewRoleButton);
        commonFunctions.waitForElementThenClick(viewRoleButton);
        commonFunctions.waitForSecond(6);

    }

    public void checkIfPageRedirectedToLeverApplicationPage(){

                try{
                    if(driver.getCurrentUrl().contains("lever")==true) {
                        System.out.println("Successfully Redirected to Lever Application");
                        Assert.assertTrue(true,"Directed url doesn't contain lever.co");
                    }
                }
                catch (Exception e) {
                    commonFunctions.captureScreenshot("DepartmentNameIsNotQualityAssurance");
                    Assert.fail();
                }
            }

}
