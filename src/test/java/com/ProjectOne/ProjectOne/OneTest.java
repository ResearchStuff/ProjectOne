package com.ProjectOne.ProjectOne;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.ProjectOne.ProjectOne.Utilities.BrowserFactory;


public class OneTest {
	//webDriver Object declaration
	WebDriver driver = null;
	
	//Test Method
	@Test
	public void TestCase1() throws InterruptedException {
		//Setup steps
		driver = BrowserFactory.getDriver("chrome");
		BrowserFactory.loadApplication();
		
		//Testing Steps
		
		
		//Post Test Steps
		BrowserFactory.closeDriver(driver);
		
	}
}

	
