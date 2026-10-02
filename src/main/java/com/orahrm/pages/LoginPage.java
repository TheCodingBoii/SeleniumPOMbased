package com.orahrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orahrm.actiondriver.ActionDriver;

public class LoginPage {
	
	private ActionDriver actionDriver;
	
	//Define locattors using By class
	
	private By userNameField = By.name("username");
	private By passwordField = By.cssSelector("input[type='password']");
	private By loginButton = By.xpath("//button[text()=' Login ']");
	private By errorMessage =By.xpath("//p[text()='Invalid credentials ']");
	
	//Initialize the ActionDriver object by passing WebDriver instance
	public LoginPage(WebDriver driver) {
		this.actionDriver=new ActionDriver(driver);
	}
	
	
	//Method to performe login
	
	public void Login(String username, String password) {
		actionDriver.enterText(userNameField, username);
		actionDriver.enterText(passwordField, password);
		actionDriver.click(loginButton);
		
	}
	
	//Method to check if error message is displayed
	public boolean isMessageDisplayed() {
		return actionDriver.isDisplayed(errorMessage);
	}
	
	//method to get the text from error message
	public String getErrorMessageText() {
		return actionDriver.getText(errorMessage);
	}
	
	//Verify if error message is dispplayed correct or not
	public void verifyErrorMessage(String expectedError) {
		actionDriver.compareText(errorMessage, expectedError);
	}
	

}
