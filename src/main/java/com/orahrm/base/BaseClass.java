package com.orahrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	
	

	protected static Properties prop;
	protected static WebDriver driver;

	@BeforeSuite
	public void loadConfig() throws IOException {
		// Load the configuration file
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);

	}
	
	@BeforeMethod
	public void setup() throws IOException {
		System.out.println("Setting webdriver for: " +this.getClass().getSimpleName());
		launchBrowser();
		configureBrowser();
		staticWait(2);

	}

	// initialize the web driver based on browser in config.properties file
	private void launchBrowser() {

		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Browser Not Supported: " + browser);
		}
	}
	//Configure browser settings such as implicit wait ,maximize the browser and navigate to the url
	private void configureBrowser() {
		// Implicit wait
		int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		// maximize the driver
		driver.manage().window().maximize();

		// navigate to URL
		try {
			driver.get(prop.getProperty("url"));
		} catch (Exception e) {
			System.out.println("Faild to Navigate to the URL: "+e.getMessage());
			
		}

	}



	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println("Unable to quite the browser: "+e.getMessage());
			}
		}
	}
	
	//static wait for pause
	public void staticWait(int secounds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(secounds));
	}

}
