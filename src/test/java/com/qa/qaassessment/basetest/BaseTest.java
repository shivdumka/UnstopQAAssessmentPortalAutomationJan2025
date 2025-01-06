package com.qa.qaassessment.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.qaassessment.exceptions.BrowserExceptions;

public class BaseTest 
{
	
	protected WebDriver driver;
	
	@Parameters({"url","browser"})
	@BeforeTest
	public void setUp(@Optional("http://qa-assesments.unstop.events.s3-website.ap-south-1.amazonaws.com/basic")String url,@Optional("chrome")String browser)
	{
		System.out.println("Browser selected is:"+browser);
		
		switch(browser.toLowerCase().trim())
		{
			case "chrome":
				driver=new ChromeDriver();
			break;
			
			case "firefox":
				driver=new FirefoxDriver();
			break;
			
			case "edge":
				driver=new EdgeDriver();
			break;
			
			default:
				throw new BrowserExceptions("Wrong browser passed,please check the browsername,you have passed: "+browser);
			
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
