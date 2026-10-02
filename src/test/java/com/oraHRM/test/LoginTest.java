package com.oraHRM.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orahrm.base.BaseClass;
import com.orahrm.pages.HomePage;
import com.orahrm.pages.LoginPage;

public class LoginTest extends BaseClass{
	
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPAges() {
		loginPage =new LoginPage(getDriver());
		homePage=new HomePage(getDriver());
	}
	@Test
	public void verifyValidLoginTest() {
		loginPage.Login("Admin", "admin123");
		Assert.assertTrue(homePage.isAdminTabVisible(),"Admin should be visible after succesfull Login");
		homePage.logout();
		staticWait(2);
	}
	

}
