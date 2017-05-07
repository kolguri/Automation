package com.yahoo.uiAutomation.homePage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.yahoo.uiAutomation.testBase.TestBase;
import com.yahoo.uiAutomation.uiActions.HomePage;

public class TC002_VerifyMultipleLogins extends TestBase{
	public static final Logger log = Logger.getLogger(TC002_VerifyMultipleLogins.class.getName());
    HomePage homepage;
   // String emailid="test@test.com";
    //String password="test";
    
    @BeforeTest
	public void setup(){
	init();
	log.info("=============== After calling init===============");
	}
	
    @DataProvider(name="loginData")
    public String[][] getTestData()
    {
    	
    	String[][] testrecords = getData("TestData.xlsx","LoginDetails");
    	log.info("=============== in dataprovider after getting data===============");
    	return testrecords;
    }
	@Test(dataProvider="loginData")
	public void verifyLoginWithInvalidCredentials(String emailid,String password,String runmode){
		if(runmode.equalsIgnoreCase("n")){
			throw new SkipException("user marked this record as No Run");
		}
		log.info("===============strarting TC002_VerifyMultipleLogins===============");
		HomePage homepage=new HomePage(driver);
	    homepage.loginToApplication(emailid,password);
		Assert.assertEquals(homepage.getInvalidText(), "Authentication failed.");
		log.info("===============Finished TC002_VerifyMultipleLogins===============");
	}
	
	@AfterTest
	public void endTest(){
		driver.close();
	}
}
