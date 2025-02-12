package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.CommonFunctions;

import java.time.Duration;
import java.util.List;

public class JobListPage {

    WebDriver driver;
    CommonFunctions commonFunctions;
    String location="Istanbul, Turkiye";
    String department="Quality Assurance";
    public void setDriver(WebDriver webDriver) {
        this.driver = webDriver;
    }

    //constructor
    public JobListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        setDriver(driver);
        this.commonFunctions=new CommonFunctions(driver);

    }

    //elements
    @FindBy(xpath ="//a[@class=\"btn btn-outline-secondary rounded text-medium mt-2 py-3 px-lg-5 w-100 w-md-50\"]")
    public WebElement seeAllJobsButton;

    @FindBy(xpath ="//span[@id=\"select2-filter-by-location-container\"]")
    public WebElement locationFilter;

    @FindBy(xpath ="//span[@id=\"select2-filter-by-department-container\"]")
    public WebElement departmentFilter;

    @FindBy(xpath="//li[contains(text(),'Istanbul')]")
    public WebElement locationIstanbulTurkey;

    @FindBy(xpath ="//li[contains(text(),'Quality Assurance')]")
    public WebElement departmentQualityAssurance;

    @FindBy(xpath = "//div[@class=\"position-location text-large\"]")
    public List<WebElement> locationListForJobList;

    @FindBy(xpath = "//span[@class=\"position-department text-large font-weight-600 text-primary\"]")
    public List<WebElement> departmentListForJobList;

    //functions
    public void goToCareersQualityAssurancePage(){
        driver.get("https://useinsider.com/careers/quality-assurance/");
    }

    public void filterJobsByLocation() {

        commonFunctions.waitForSecond(17);
        commonFunctions.acceptAllCookies();
        commonFunctions.waitForElementThenClick(locationFilter);
        commonFunctions.waitForElementThenClick(locationIstanbulTurkey);

    }

    public void filterJobsByDepartment() {
        commonFunctions.waitForElementThenClick(departmentFilter);
        commonFunctions.acceptAllCookies();
        commonFunctions.moveTOElement(departmentQualityAssurance);
        commonFunctions.waitForSecond(3);
        commonFunctions.waitForElementThenClick(departmentQualityAssurance);
        commonFunctions.closeAdvertisementIfExist();
        commonFunctions.acceptAllCookies();

    }

    public void isJobListDisplayed() {
        commonFunctions.waitForSecond(4);
        Assert.assertTrue(commonFunctions.checkElementExistsByUsingText(departmentListForJobList.get(1),"Quality Assurance"),"Department name is not displayed so that job list not displayed.");
        Assert.assertTrue(commonFunctions.checkElementExistsByUsingText(locationListForJobList.get(1),"Istanbul"),"Location name is not displayed so that job list doesn't displayed");
        commonFunctions.waitForSecond(3);
    }

    public void clickSeeAllJobs() {
        commonFunctions.waitForElementThenClick(seeAllJobsButton);
    }

    public void verifyJobDetails() {
        //commonFunctions.swipeUp();
        checkLocationInfo();
        checkDepartmentInfo();
    }

    public void checkLocationInfo(){
        for(WebElement element : locationListForJobList) {
            try{
                if(commonFunctions.checkElementExistsByUsingText(element,location)==true) {
                    System.out.println("Location info 3 ya da 4 tane"+element.getText());
                    Assert.assertTrue(true,"Location info doesn't contain Istanbul Turkey");
                }
            }
            catch (Exception e) {
                commonFunctions.captureScreenshot("LocationInfoIsNotIstanbulTurkey");
                Assert.fail("Location Info is not Istanbul");
            }
        }
    }

    public void checkDepartmentInfo(){
        for(WebElement element : departmentListForJobList) {
            try{
                if(commonFunctions.checkElementExistsByUsingText(element,department)==true) {
                    System.out.println("Department info 3 ya da 4 tane"+element.getText());
                    Assert.assertTrue(true,"Department name doesn't contain Quality Assurance");
                }
            }
            catch (Exception e) {
                commonFunctions.captureScreenshot("DepartmentNameIsNotQualityAssurance");
                Assert.fail("Department info doesn't contain Quality Assurance ");
            }
        }
    }


}
