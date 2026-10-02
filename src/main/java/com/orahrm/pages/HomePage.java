package com.orahrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orahrm.actiondriver.ActionDriver;

public class HomePage {

	private ActionDriver actionDriver;

	// Define locattors using By class
	private By adminTab = By.xpath("//span[text()='Admin']");
	private By userIDButton = By.className("oxd-userdropdown-name");
	private By logoutButton = By.xpath("//a[text()='Logout']");
	private By orangeHRMLogo = By.xpath("//div[@class='oxd-brand-banner']//img");

	// Initialize the ActionDriver object by passing WebDriver instance
	public HomePage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
	}

	// Method to verify if admin tab is visible
	public boolean isAdminTabVisible() {
		return actionDriver.isDisplayed(adminTab);
	}

	// Method to verify if admin tab is visible
	public boolean verifyOrageHRMLogo() {
		return actionDriver.isDisplayed(orangeHRMLogo);
	}
	//Method to performe logout operation
	public void logout() {
		actionDriver.click(userIDButton);
		actionDriver.click(logoutButton);
	}

}
