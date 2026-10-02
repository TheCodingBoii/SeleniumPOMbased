package com.orahrm.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orahrm.base.BaseClass;

public class ActionDriver {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		int explicitWait = Integer.parseInt(BaseClass.getProp().getProperty("explicitwait"));
		this.wait =new WebDriverWait(driver,Duration.ofSeconds(explicitWait));
	}
	
	
	//method to click an element
	public void click(By by) {
		try {
			waitForElementToClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println("Umable to click Element: "+e.getMessage());
		}
	}
	
	//method to enter text in to a input field 
	public void enterText(By by,String value) {
		try {
			waitForElementToBeVisible(by);
//			driver.findElement(by).clear();
//			driver.findElement(by).sendKeys(value);
			WebElement element = driver.findElement(by);
			element.click();
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("Element is not clickable: "+e.getMessage());
		}
	}
	
	//Wait for the page to load
	public void waitForPageLoad(int timeOutInSec) {
		try {
			wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver ->((JavascriptExecutor)WebDriver).executeScript("return document.readyState").equals("complete"));
			System.out.println("page loaded successfully");
		} catch (Exception e) {
			System.out.println("Page did not load within "+ timeOutInSec + " seconds . Exception: " + e.getMessage());
		}
	}
	
	
	//Scroll to an element
	public void scrollToElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element =driver.findElement(by);
			js.executeScript("arguments[0],scrollIntoView(true);", element);
		} catch (Exception e) {
			System.out.println("Unable to Locate Element: "+e.getMessage());
		}
	}
	
	//wait for element to clickable
	private void waitForElementToClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			System.out.println("Unable to enter the value in input box: "+e.getMessage());
		}
	}
	
	//Method to get text from an input field
	public String getText(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("Unable to get the text in input box: "+e.getMessage());
			return "";
		}
	}
	
	//Method to compare two text
	
	public void compareText(By by,String ExpectedText) {
		try {
			waitForElementToBeVisible(by);
			String actualText=driver.findElement(by).getText();
			
			if(ExpectedText.equals(actualText)) {
				System.out.println("Texta are matching: "+actualText+ " equals "+ExpectedText);
			}
			else {
				System.out.println("Texta are not matching: "+actualText+ " not equals "+ExpectedText);
			}
		} catch (Exception e) {
			System.out.println("Unable to compare texts: "+e.getMessage());
		}
	}
	
	//method to check if an element is displayed
	public boolean isDisplayed(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).isDisplayed();
			
			
		} catch (Exception e) {
			System.out.println("Element is not displayed"+ e.getMessage());
			return false;
		}
	}
	
	//wait for element to be visible
	
	private void waitForElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			System.out.println("Element is not visible: "+e.getMessage());
		}
	}
	

}
