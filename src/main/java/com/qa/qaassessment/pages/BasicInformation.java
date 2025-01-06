package com.qa.qaassessment.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.qaassessment.appconstants.AppConstants;

public class BasicInformation 
{
	private WebDriver driver;
	
	private JavascriptExecutor js;
	
	private WebDriverWait wait;
	
	
	public BasicInformation(WebDriver driver)
	{
		this.driver=driver;
		
		wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
	}
	
	//Priate By locators for the element
	
	private By nameOfAssessment=By.id("name");
	
	private By clickOnPurpose=By.id("mat-select-value-1");
	
	private By listOfSugg=By.xpath("//div[@id='cdk-overlay-0']//span");
	
	private By clickSkill=By.cssSelector("input.auto-complete");
	
	private By skillList=By.xpath("//li[@title]");
		
	private By durationOfAssessment=By.id("time-input");
	
	private By saveBasicInfoPage=By.xpath("//div[@class=\"save_bdr\"]/button");
	
	private By successMessage=By.xpath("//div[text()=' Assessment saved successfully ']");
	
	private By clickOnQuestionSettingsLink=By.xpath("//div[text()=' Question Settings ']");
	
	
	
	
	public String getBasicInfoTitle()
	{
		String title=driver.getTitle();
		return title;
	}
	
	public String getCurrentURL()
	{
		String url=driver.getCurrentUrl();
		return url;
	}
	
	
	/**
	 * Tried using Actions class and JavascriptExxecutor to upload the image through <input type="file"> but it is not working,so not automating this.
	 */
//	public void uploadAssessmentLogo()
//	{
//		WebElement clickLogo=driver.findElement(clickOnLogo);
//		clickLogo.sendKeys("C:\\Users\\rookie\\Documents\\Dummy logo for test\\pnglogo4");
//		driver.findElement(saveLogo).click();
//	}
	
	/**
	 * Selecting dropdown(Non-Select based) "Purpose of the test is" using JSExecutor as normal click and Actions class click is
	 *  not clicking on the dropdown and options are not visible after the click,so used JSExecutor
	 */
	public List<String> listOfPurposeOfTest()
	{
		
		js=(JavascriptExecutor)driver;
		
		WebElement clickPurpose=driver.findElement(clickOnPurpose);
		
		js.executeScript("arguments[0].click()",clickPurpose);
		
		List<WebElement> listOfPurpose=driver.findElements(listOfSugg);
		List<String> listPurposeOfTest=new ArrayList<String>();
		
		for(WebElement e:listOfPurpose)
		{
			String list=e.getText();
			
			if(list.length()>0)
			{
				listPurposeOfTest.add(list);
			}
		}
		return listPurposeOfTest;
	}
	
	public void purposeOfTestFill()
	{
		js=(JavascriptExecutor)driver;
		
		WebElement clickPurpose=driver.findElement(clickOnPurpose);
		
		js.executeScript("arguments[0].click()",clickPurpose);
		
		List<WebElement> listOfPurpose=driver.findElements(listOfSugg);
		
		for(WebElement e:listOfPurpose)
		{
			String list=e.getText();
			
			if(list.contains("Hiring"))
			{
				e.click();
				break;
			}
		}	
		
	}
	
	
	public void skillSearch() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		WebElement clickOnSkills=wait.until(ExpectedConditions.visibilityOfElementLocated(clickSkill));
		
		js.executeScript("arguments[0].scrollIntoView(true)",clickOnSkills );
		
		clickOnSkills.click();
		
		for(char c='A';c<='Z';c++)
		{
			String s=Character.toString(c);
			
			clickOnSkills.sendKeys(s);

		//	Using Below Wait,itworked for the the first insatnce of the loop,but for the second run it gave StaleElementException,so used.... 
		//	....findElements() approach i.e. without wait+Thread class
		//	List<WebElement> suggList=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@title]")));
			
			Thread.sleep(500);
			
			List<WebElement> suggList=driver.findElements(skillList);
			
			int size=suggList.size();
			
			System.out.println();
			
			System.out.println("Suggestions for "+s+" = "+size);
			
			for(WebElement e:suggList)
			{
				String skill=e.getText();
				System.out.print(skill+",");
			}
			
			clickOnSkills.clear();
		}
		

	}
	
	
	/**
	 * May use dataprovider to click on a specific skill,will do it when creating test class for this page class
	 * @throws InterruptedException
	 */
	public void clickOnSkill() 
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		WebElement clickOnSkills=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.auto-complete")));
		
		js.executeScript("arguments[0].scrollIntoView(true)",clickOnSkills );
		
		clickOnSkills.click();
		
		clickOnSkills.sendKeys("Quality");
		
		//Using wait the element wasnt getting located,strange behaviour of element
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException e)
		{
			
		}
		
		List<WebElement> suggList=driver.findElements(skillList);
		
		for(WebElement e:suggList)
		{
			String skill=e.getText();
			
			if(skill.contains("Quality Check"))
			{
				e.click();
				break;
			}
		}
	}
	
	
	
	
	public String fillBasicInfoPageAndSave() 
	{
		
		//Filling Name of Assessment Input field
		
		driver.findElement(nameOfAssessment).sendKeys(AppConstants.NAME_OF_ASSESSMENT);
		
		//Selecting dropdown option from "purpose of test" dropdown
		
		purposeOfTestFill();
		
		//click on skill after searching for the skill and then suggestion list appears,at last clicking on the suggestion of our choice
		
		clickOnSkill();
		
		//filling the duration of assemeent input text field with a valid timing
		
		driver.findElement(durationOfAssessment).sendKeys(AppConstants.DURATION_OF_ASSESSMENT);
		
		//clicking on the save button after all mnadatory field have been filled
		
		driver.findElement(saveBasicInfoPage).click();
		
		//If assessment details get saved successfully,we get a success message,getting the text of the success message
		
		WebElement success=wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
		
		String successMesg=success.getText();
		
		return successMesg;
		
	}
	
	public QuestionSettingsPage navigateToQuestionsSettingPage()
	{
		driver.findElement(clickOnQuestionSettingsLink).click();
		
		return new QuestionSettingsPage(driver);
	}

}
