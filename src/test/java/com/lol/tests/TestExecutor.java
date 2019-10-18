package com.lol.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.lol.menu.Navigate;
import com.lol.pages.corporateUser_Obj;
import com.lol.utils.Common;
import com.lol.utils.DataUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestExecutor {

	public static WebDriver driver;
	public static String userDir = System.getProperty("user.dir");
	public static DesiredCapabilities cap = null;
	public static Navigate Navigate;
	public static Common common = new Common();
	public static CorporateUser corpUser = new CorporateUser();
	public static corporateUser_Obj corpUser_Obj = new corporateUser_Obj();

	public static ExtentTest logger;
	public static DataUtils dataUtils;
	public static Properties prop = new Properties();

	// TestData
	public String PurchaseOrdernumber = null;
	public String SalesOrdernumber = null;

	public Map<String, String> map = new HashMap<String, String>();
	// public WebDriverWait wait = new WebDriverWait(driver, 10);
	public DataFormatter formatter = new DataFormatter();
	public FileInputStream File;
	public XSSFWorkbook book;
	public XSSFSheet sheet;

	static ExtentReports report;
	FileInputStream fis = null;

	@BeforeClass(enabled = true)
	public static ExtentReports launch() {
		String destFile = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss");
		destFile = userDir + "\\test-output\\extentreports\\ecom";
		String destDir = dateFormat.format(new Date()) + ".html";
		report = new ExtentReports(destFile + "\\" + destDir, true);
		report.loadConfig(new File(userDir + "\\src\\test\\java\\com\\lol\\config\\reports.xml"));
		return report;
	}

	@AfterClass(enabled = false)
	public void result() {
		driver.close();
	}

	@AfterSuite(enabled = true)
	public void teartown() {
		driver.close();
	}

	@BeforeSuite(enabled = true)
	public void start() {
		// driver.close();
	}

	@BeforeMethod(enabled = true)
	public void startTest(Method method) throws IOException {
		initialize();
		dataUtils.setSheetName("TD_ProductDetailsValidation");

		// driver.get("https://mpis.landolakes.com");

		driver.get(prop.getProperty("URL"));
		System.out.println("Successfully Openedapplication ");
		driver.manage().window().maximize();
	}

	@AfterMethod(enabled = false)
	public void reportclosewindows() throws IOException, InterruptedException {
		report.endTest(logger);
		report.flush();
	}

	public static void loadPropertiesFile() throws IOException {
		FileInputStream fn = null;
		fn = new FileInputStream(userDir + "\\src\\test\\java\\com\\lol\\config\\config.properties");
		prop.load(fn);
	}

	public void login() {
		common.isElementExist(corpUser_Obj.iHGLogo, "iHGLogo");
		
		common.isElementExist(corpUser_Obj.environment, "environment");
		common.clickByXPath(corpUser_Obj.environment, "environment");
		
		common.isElementExist(corpUser_Obj.username, "Username");
		common.getObjectByXpath(corpUser_Obj.username).clear();
		common.inputbyxpath(corpUser_Obj.username, "Username", getData("User"));
		
		common.isElementExist(corpUser_Obj.password, "password");
		common.getObjectByXpath(corpUser_Obj.password).clear();
		common.inputbyxpath(corpUser_Obj.password, "password", getData("Password"));
		
		common.isElementExist(corpUser_Obj.login, "login");
		common.clickByXPath(corpUser_Obj.login, "login");
		
		common.waitExplicitlyForPageToLoad("Home", 60);
		
		common.isElementExist(corpUser_Obj.landingPageLogo, "landingPageLogo");
		logger.log(LogStatus.PASS, "Successfully logged in to IHG application");
	}
	
	
	@Parameters("browser")
	public static void initialize() throws IOException {

		if (driver == null) {

			// Initialize CONFIG property file
			loadPropertiesFile();

			if (prop.getProperty("browser", "Firefox").equals("Firefox")) {
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				File pathBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
				// "C:\Program Files (x86)\Mozilla Firefox\firefox.exe"
				FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
				FirefoxProfile firefoxProfile = new FirefoxProfile();
				firefoxProfile.setPreference("browser.download.folderList", 2);
				firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
				firefoxProfile.setPreference("browser.download.dir", "C:\\Automation\\DownloadWinfield");
				firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
				firefoxProfile.setPreference("pdfjs.disabled", true);

				driver = new FirefoxDriver(firefoxBinary, firefoxProfile, cap);
				// driver= new FirefoxDriver(cap);
				cap.setPlatform(Platform.ANY);
			} else if (prop.getProperty("browser", "Firefox").equals("iexplore")) {
				System.out.println(prop.getProperty("browser"));
				System.setProperty("webdriver.ie.driver",
						"U:\\permanent\\Information_System\\SQA\\Selenium\\New\\JDEWinfield\\ext\\iedriver.exe");
				cap = DesiredCapabilities.internetExplorer();
				cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				driver = new InternetExplorerDriver(cap);
				cap.setPlatform(Platform.ANY);

			} else if (prop.getProperty("browser", "chrome").equals("chrome")) {
				System.out.println(prop.getProperty("browser"));
				System.setProperty("webdriver.chrome.driver",
						"U:\\permanent\\Information_System\\SQA\\Selenium\\New\\chromedriver.exe");
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				driver = new ChromeDriver(cap);
				cap.setPlatform(Platform.ANY);
			}
			// driver = new RemoteWebDriver(
			// new URL(System.getProperty("hub")), cap);
		}

		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

		Navigate = new Navigate(driver);
		common = new Common();
		dataUtils = new DataUtils();

		// webTable = new Table(driver);
		// jscript = (JavascriptExecutor) driver;
	}

	public String getData(String columnName) {
		return dataUtils.getMapValue(columnName);
	}
}
