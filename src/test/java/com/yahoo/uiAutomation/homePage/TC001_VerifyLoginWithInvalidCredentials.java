package com.yahoo.uiAutomation.homePage;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.yahoo.uiAutomation.testBase.TestBase;
import com.yahoo.uiAutomation.uiActions.HomePage;

public class TC001_VerifyLoginWithInvalidCredentials extends TestBase {
	public static final Logger log = Logger.getLogger(TC001_VerifyLoginWithInvalidCredentials.class.getName());
	//WebDriver driver;
    HomePage homepage;
    
	@BeforeTest
	public void setup(){
	init();
	}
	
	@Test
	public void verifyLoginWithInvalidCredentials(){
		log.info("===============strarting test===============");
		HomePage homepage=new HomePage(driver);
	    homepage.loginToApplication("test@test.com", "test");
		Assert.assertEquals(homepage.getInvalidText(), "Invalid password.");
		log.info("===============Finished test===============");
	}
	
	@AfterTest
	public void endTest(){
		driver.close();
	}
}
