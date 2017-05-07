package com.yahoo.uiAutomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public static final Logger log = Logger.getLogger(HomePage.class.getName());
	WebDriver driver;
	
	@FindBy(xpath="//*[@id='header']/div[2]/div/div/nav/div[1]/a")
	WebElement signIn;
	
	@FindBy(xpath="//input[@id='email']")
	WebElement loginEmailAddress;
	
	@FindBy(xpath="//input[@id='passwd']")
	WebElement loginPassword;
	
	@FindBy(xpath="//*[@id='SubmitLogin']")
	WebElement submitCredentials;
	
	@FindBy(xpath="//*[@id='center_column']/div[1]/ol/li")
	WebElement sucessFailureMessage;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	public void loginToApplication(String emailid,String password){
		signIn.click();
		log.info("clicked on sign and signIn object is :"+signIn.toString());
		loginEmailAddress.sendKeys(emailid);
		log.info("Entered email address:"+emailid+"and object is :" + emailid.toString());
		loginPassword.sendKeys(password);
		log.info("Entered password:"+password +"and object is :" + password.toString());
		submitCredentials.click();
		log.info("Clicked on Submit Button and object is :"+submitCredentials.toString());
		
	}
	public String getInvalidText(){
		log.info("Error Message is  :"+sucessFailureMessage.getText());
		return sucessFailureMessage.getText();
	}
}

