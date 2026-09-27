package com.oraHRM.test;

import org.testng.annotations.Test;

import com.orahrm.base.BaseClass;

public class DummyTest extends BaseClass{
	
	@Test
	public void dummyTest(){
		
		String title = driver.getTitle();
		assert title.equals("OrangeHRM"):"Test Failed - Title is not matching";
		
		System.out.println("Test Passed - Title is Maching");
		
	}

}
