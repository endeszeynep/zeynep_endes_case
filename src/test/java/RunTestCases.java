import Pages.CareersPage;
import Pages.JobListPage;
import Pages.MainPage;
import Pages.LeverApplicationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class RunTestCases {

    WebDriver driver;
    MainPage mainPage;
    CareersPage careersPage;
    JobListPage jobListPage;
    LeverApplicationPage leverApplicationPage;
    ChromeOptions chromeOptions=new ChromeOptions();

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(@Optional("Chrome")String browser) {
        if(browser.equals("Chrome")){
            WebDriverManager.chromedriver().setup();
            chromeOptions.addArguments("--disable-notifications");
            driver=new ChromeDriver(chromeOptions);
            WebDriverManager.chromedriver().clearDriverCache().setup();
            WebDriverManager.chromedriver().clearResolutionCache().setup();
        }else if(browser.equals("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            WebDriverManager.firefoxdriver().clearDriverCache().setup();
            WebDriverManager.firefoxdriver().clearResolutionCache().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://useinsider.com/");

        mainPage = new MainPage(driver);
        careersPage = new CareersPage(driver);
        jobListPage = new JobListPage(driver);
        leverApplicationPage = new LeverApplicationPage(driver);
    }

    @Test
    public void testInsiderMainPage() {
        mainPage.isMainPageOpened();

    }

    @Test(dependsOnMethods = "testInsiderMainPage")
    public void testCareersPage() {
        mainPage.openCompanyMenu();
        mainPage.openCareersPage();
        careersPage.isTeamsBlockDisplayed();
        careersPage.isLocationsBlockDisplayed();
        careersPage.isLifeAtInsiderBlockDisplayed();

    }

    @Test(dependsOnMethods = "testCareersPage")
    public void testJobSearch() {
        jobListPage.goToCareersQualityAssurancePage();
        jobListPage.clickSeeAllJobs();
        jobListPage.filterJobsByLocation();
        jobListPage.filterJobsByDepartment();
        jobListPage.isJobListDisplayed();
    }

    @Test(dependsOnMethods = "testJobSearch")
    public void testJobList() {
        System.out.println("verifyJob details");
        jobListPage.verifyJobDetails();

    }

    @Test(dependsOnMethods = "testJobList")
    public void testViewRole() {
       leverApplicationPage.clickViewRoleButton();
       leverApplicationPage.checkIfPageRedirectedToLeverApplicationPage();

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
