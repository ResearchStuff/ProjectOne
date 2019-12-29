package com.ProjectOne.ProjectOne.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {
	//Factory to initialize driver object
	private static WebDriver driver;
	private static String SysPath;
	
	public static WebDriver getDriver(String browser){
		//Getting project's relative path
		SysPath = System.getProperty("user.dir") + "//Driver//";
		
		//Initializing the driver with passed browser string
		if(browser.toLowerCase().equals("chrome")){
			System.setProperty("webdriver.chrome.driver", SysPath + "chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browser.toLowerCase().equals("firefox")){
			System.setProperty("webdriver.gecko.driver", SysPath + "geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(browser.toLowerCase().equals("ie")){
			System.setProperty("webdriver.ie.driver",SysPath + "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		//returning back the driver object
		return driver;
	}
	
	//common method for closing 
	public static void closeDriver(WebDriver driver){
		driver.close();
	}
			
	//Load application for the execution
	public static void loadApplication(){
		driver.get("https://www.google.com");
	}
}
