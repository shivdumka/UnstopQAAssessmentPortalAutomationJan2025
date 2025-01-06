package com.qa.qaassessment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class QuestionSettingsPage 
{
	private WebDriver driver;
	
	Actions act;
	
	JavascriptExecutor js;
	
	BasicInformation bs;
	
	public QuestionSettingsPage(WebDriver driver)
	{
		this.driver=driver;
		act=new Actions(driver);
		
		js=(JavascriptExecutor)driver;
		
	}
	
	private By noOfQues=By.id("question");
	
	private By maxiMarks=By.xpath("(//input[@id='question'])[2]");
	
	private By shuffleQuesRadioBtn=By.xpath("//label[text()='Only with Tags']");
	
	private By shufflingOptToggleBtn= By.xpath("//div[@class='bdr_box d2c-switch with_bg mob-flx-clm']//span[@class='lever']");
	
	private By timerToggle=By.xpath("//div[@class='bdr_box d2c-switch with_bg']/label[@for='timer_per_question']/span[@class='lever']");
	
	private By saveBtn=By.xpath("//button[@class='d2c_btn waves-effect']");
			
	private By succMesg=By.xpath("//div[text()=' Assessment saved successfully ']");
	
	
	public String getQuesPageTitle()
	{
		String title=driver.getTitle();
		return title;
	}
	
	public String getQuesPageURL()
	{
		String url=driver.getCurrentUrl();
		return url;
	}
	
	public String fillQuestionSettingsPage() 
	{
		
		//Filling No of Questions input field,clear() not working so needed to use Keys enum in charactersequence arguments to delete the default numbers 
		
		bs=new BasicInformation(driver);
		
		bs.navigateToQuestionsSettingPage();
		
		WebElement noOfQuestions= driver.findElement(noOfQues);
		
		act.sendKeys(noOfQuestions,Keys.BACK_SPACE)
			.sendKeys(noOfQuestions,Keys.BACK_SPACE)
			.sendKeys(noOfQuestions,"40")
			.perform();
		
		//Filling Maximum marks input field,clear() not working so needed to use Keys enum in charactersequence arguments to delete the default numbers
		
		WebElement maxMarks=driver.findElement(maxiMarks);
		
		act.sendKeys(maxMarks,Keys.BACK_SPACE)
			.sendKeys(maxMarks,Keys.BACK_SPACE)
			.sendKeys(maxMarks,"200")
			.perform();
		
		//Shuffle Questions Radio button
		
		driver.findElement(shuffleQuesRadioBtn).click();
		
		
		//Shuffle OPtions Toggle button
		
		WebElement shuffleOptions=driver.findElement(shufflingOptToggleBtn);
		
		js.executeScript("arguments[0].click()", shuffleOptions);
		
		
		//Shuffle Timer toggle On
		
		WebElement timerToggleOn=driver.findElement(timerToggle);
		
		js.executeScript("arguments[0].click()", timerToggleOn);
		
		
		WebElement clickSaveBtn=driver.findElement(saveBtn);
		
		js.executeScript("arguments[0].click()", clickSaveBtn);
		

		String succMesgPrint=driver.findElement(succMesg).getText();
		
	//	System.out.println(succMesgPrint);
		return succMesgPrint;
	
		
	}
	
	
	public RegistrationFormPage navigateToRegistrationPage()
	{
		driver.findElement(By.xpath("//div[text()=' Registration Form ']")).click();
		
		return new RegistrationFormPage(driver);
	}
}
