package com.qa.qaassessment.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationFormPage 
{
	
private WebDriver driver;
	
private JavascriptExecutor js;

private Actions act;

private WebDriverWait wait;

QuestionSettingsPage qs;

	public RegistrationFormPage(WebDriver driver)
	{
		this.driver=driver;
		
		js=(JavascriptExecutor)driver;
		
		wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		act=new Actions(driver);
		
		qs=new QuestionSettingsPage(driver);
	}
	
	
	private By gender=By.xpath("(//div/label[text()='All'])[1]");
	
	private By clickSelectListOfOrg=By.xpath("//label[@for='select-list-company']");
	
	private By typeOrg=By.xpath("//input[@placeholder='Start typing Organisation name to select one']");
	
	private By clickOnSelectOrg= By.xpath("//input[@placeholder='Start typing Organisation name to select one']");
	
	private By createOneOrg=By.xpath("//em[text()='Create One']");
	
	private By orgnzName=By.xpath("//input[@id='org_name']");
	
	private By clickOrgType=By.xpath("//label[@for='org_type41']");
	
	private By submitOrgBtn=By.xpath("//button[text()='Submit']");
	
	private By successMesg=By.xpath("//div[text()=' Organisation added successfully! ']");
	
	
	
	public String getRegistrationTitle()
	{
		String title=driver.getTitle();
		return title;
	}
	
	public String getRegistrationURL()
	{
		String url=driver.getCurrentUrl();
		return url;
	}
	
	
	public String addNewOrganization()
	
	{
		qs.navigateToRegistrationPage();

		WebElement genderAll=driver.findElement(gender);
	
		js.executeScript("arguments[0].click()", genderAll);
		
		js.executeScript("arguments[0].click()", genderAll);
		
		WebElement clickOnSelectListOfOrg=wait.until(ExpectedConditions.visibilityOfElementLocated(clickSelectListOfOrg));
		
		js.executeScript("arguments[0].click()",clickOnSelectListOfOrg);
		
		WebElement selectListOfOrg=wait.until(ExpectedConditions.visibilityOfElementLocated(typeOrg));
		
		js.executeScript("arguments[0].scrollIntoView(true)",selectListOfOrg);	
		
		act.click(driver.findElement(clickOnSelectOrg))
		.perform();	
		
		WebElement clickOnCreateOneOrg=wait.until(ExpectedConditions.visibilityOfElementLocated(createOneOrg));
		
		js.executeScript("arguments[0].click()", clickOnCreateOneOrg);
			
		
		WebElement orgName=driver.findElement(orgnzName);
		
		orgName.sendKeys("UnstopComp"+System.currentTimeMillis());
		
		
		WebElement clickOnOrgType=driver.findElement(clickOrgType);
		
		clickOnOrgType.click();
		
		//WebElement clickSubmit=driver.findElement(By.xpath("//button[text()='Submit']"));
		
		WebElement clickOnSubmit=wait.until(ExpectedConditions.visibilityOfElementLocated(submitOrgBtn));
		
		clickOnSubmit.click();
		
		WebElement successMessg=wait.until(ExpectedConditions.visibilityOfElementLocated(successMesg));
		String printSuccessMesg=successMessg.getText();
		
		return printSuccessMesg;
		
		
		
	}	
}
