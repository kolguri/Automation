package com.yahoo.uiAutomation.testBase;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.yahoo.uiAutomation.excelReader.Excel_Reader;

public class TestBase {
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
    public WebDriver driver;
    String browser ="chrome";
    String url="http://automationpractice.com/index.php";
    Excel_Reader excel;
    
    
    public void init()
    {
    	String log4jpath="log4j.properties";
	    PropertyConfigurator.configure(log4jpath);
    	selectBrowser(browser);
    	getUrl(url);
    	
    }
    public void selectBrowser(String browser){

    	if (browser.equals("chrome"))
    	{
    	 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/drivers/chromedriver");
    	 log.info("creating chrome browser");
    	 driver = new ChromeDriver();
       }
    }
	
	
    public void getUrl(String url){
    	log.info("navigating to url:" +url);
    	driver.get(url);
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
    }
    
    public String[][] getData(String excelName,String sheetName){
    	String path=System.getProperty("user.dir")+"/src/main/java/com/yahoo/uiAutomation/data/"+excelName;
    	log.info("path is :" +path);
    	log.info("sheet name and excel name   is :" +sheetName +"and" +excelName);
    	excel= new Excel_Reader(path);
    	String[][] data=excel.getDataFromSheet(sheetName,excelName);
    	return data;
    }   
	
}
