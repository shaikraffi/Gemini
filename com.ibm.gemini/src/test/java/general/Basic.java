package general;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Basic extends Functions {
	static WebDriver driver;
	public static Properties prop;
	public static String data;
	public static ArrayList<String> xpathdata = new ArrayList<String>();
	public static String path = System.getProperty("user.dir");
	public static WebElement ele = null;
	public static void goTO() {
		readExcel("TestCase");
		String url = null;
		for (int i = 0; i <= lastRow; i++) {
			if (excelData[i][0].equalsIgnoreCase("goTo")) {
				url = excelData[i][1].toString().trim();
				break;
			} else {
				if (i == lastRow) {
					Assert.fail("No URL Specified-----" + url);
				}
			}
		}
		if (url.equals("/")) {
			driver.navigate().to(prop.getProperty("url"));

		} else {
			driver.get(url);
		}
	}

	public static void wait(String abc)

	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(abc)));
	}

	public static void refresh() {
		driver.navigate().refresh();

	}
	// Verify is Element Displayed
	public static void isElementPresent() {
		verifyElement("verifyisPresent");
		if (ele.isDisplayed()) {
			System.out.println("Element present success fully -- " + ele.getSize());
		} else {
			Assert.fail("Element not present on the page");
		}
	}

	public static void enterText() {
		verifyElement("verifyTextBox");
		// System.out.println(ele);
		ele.click();
		ele.clear();
		ele.sendKeys("New york");
	}

	public static void verifyTitle() {
		readExcel("TestCase");
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		String expectedTitle = null;
		for (int i = 0; i <= lastRow; i++) {
			// System.out.println("----"+excelData[i][0].toString());
			if (excelData[i][0].toString().equalsIgnoreCase("Title")) {
				expectedTitle = excelData[i][1].toString().trim();
				break;
			}			
		}assertEquals(expectedTitle,actualTitle);
	}
	
	// is Element Enabled
	public static void isElementEnabled() {
		verifyElement("verifyisPresent");
		if (ele.isEnabled()) {
			System.out.println("Element present success fully--" + ele.getSize());
		} else {
			Assert.fail("Element not Enabled on the page");
		}
	}

	// is Element Enabled
	public static void isElementClickble() {
		verifyElement("verifyisPresent");
		if (ele.isEnabled()) {
			System.out.println("Element present success fully--" + ele.getSize());
		} else {
			Assert.fail("Element not clickable on the page");
		}
	}
	
	
	
	
	
	

	public static void openBrowser() {
		readExcel("TestCase");
		String browserName = null;
		for (int i = 0; i <= lastRow; i++) {
			// System.out.println("----"+excelData[i][0].toString());
			if (excelData[i][0].toString().equalsIgnoreCase("OpenBrowser")) {
				browserName = excelData[i][1].toString().trim();
				break;
			}
		}
		if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", path + "/src/test/resources/lib/geckodriver");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			// System.setProperty("webdriver.chrome.driver",
			// path+"/src/test/resources/lib/chromedriver");
			driver = new ChromeDriver();
		}
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void verifyElement(String event) {
		readExcel("TestCase");
		String proprty = null;
		ele = null;
		for (int i = 0; i <= lastRow; i++) {
			if (excelData[i][0].equalsIgnoreCase(event)) {
				proprty = excelData[i][1].toString().trim();
				break;
			} else {
				if (i == lastRow) {
					System.out.println("No property specified to execute..Hance Testcase passsed");
				}
			}
		}
		readExcel("Home page");
		String locater = null;
		String locaterType = null;
		for (int i = 0; i <= lastRow; i++) {
			if (excelData[i][0].equalsIgnoreCase(proprty)) {
				locaterType = excelData[i][1].toString().trim();
				locater = excelData[i][2].toString().trim();
				break;
			} else {
				if (i == lastRow) {
					System.out.println("No property specified to execute..Hance Testcase passsed");
				}
			}
		}
		if (locaterType.equalsIgnoreCase("xpath")) {
			ele = driver.findElement(By.xpath(locater));
		} else if (locaterType.equalsIgnoreCase("id")) {
			ele = driver.findElement(By.id(locater));
		} else if (locaterType.equalsIgnoreCase("name")) {
			ele = driver.findElement(By.name(locater));
		} else if (locaterType.equalsIgnoreCase("cssSelector")) {
			ele = driver.findElement(By.cssSelector(locater));
		} else if (locaterType.equalsIgnoreCase("className")) {
			ele = driver.findElement(By.className(locater));
		} else if (locaterType.equalsIgnoreCase("tagName")) {
			ele = driver.findElement(By.tagName(locater));
		} else if (locaterType.equalsIgnoreCase("linkText")) {
			ele = driver.findElement(By.linkText(locater));
		} else if (locaterType.equalsIgnoreCase("partiallinkText")) {
			ele = driver.findElement(By.partialLinkText(locater));
		}

	}

	public static void clickon() {

		verifyElement("clickon");
		if (ele.isEnabled()) {
			System.out.println("Element present success fully--" + ele.getSize());
			ele.click();
		} else {
			Assert.fail("Element not clickable on the page");
		}
	}

	public static void verifyText(String Text) {

		WebElement element = driver.findElement(By.xpath("Locator Value"));
		String text = element.getText();
		if (text.equals(Text)) {

			System.out.println("Expected text is obtained");

		} else {

			System.out.println("Expected text is not obtained");
		}
	}

	public static void main(String args[]) throws IOException, InterruptedException {

		prop = new Properties();
		FileInputStream ip = new FileInputStream("D:\\Automation\\Test\\Gemini\\src\\general\\config.properties");
		prop.load(ip);

		String exePath = "D:\\Automation\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		driver = new ChromeDriver();
		// driver.get(prop.getProperty("url"));
		goTO();
		// refresh();

		// enterText("bhavishya035@gmail.com", By.xpath("//*[@id='email']"));
		// enterText("Monocept@123", By.xpath("//*[@id='pass']"));
		// click("//*[@id='u_0_2']");

		Thread.sleep(3000);

	}

}
