package com.orahrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
	
	
	
	
	protected Properties prop;
	protected WebDriver driver;
	
	@BeforeMethod
	public void setup() throws IOException {
		//Load the configuration file
		prop= new Properties ();
		FileInputStream fis=new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);
		
		//initialize the web driver based on browser in config.properties file
		 String browser = prop.getProperty("browser");
		 
		 if(browser.equalsIgnoreCase("chrome")) {
			 driver = new ChromeDriver();
		 }
		 else if(browser.equalsIgnoreCase("firefox")) {
			 driver = new FirefoxDriver();
		 }
		 else if(browser.equalsIgnoreCase("edge")) {
			 driver = new EdgeDriver();
		 }
		 else {
			 throw new IllegalArgumentException("Browser Not Supported: "+browser);
		 }
		 
		 //Implicit wait
		 int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
		 
		 //maximize the driver
		 driver.manage().window().maximize();
		 
		 //navigate to URL
		 driver.get(prop.getProperty("url"));
		 
	}
	@AfterMethod
	public void tearDown() {
		if(driver !=null) {
			driver.quit();
		}
	}

}
