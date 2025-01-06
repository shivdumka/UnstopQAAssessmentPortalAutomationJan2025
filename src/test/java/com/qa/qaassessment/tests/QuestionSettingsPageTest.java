package com.qa.qaassessment.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.qaassessment.appconstants.AppConstants;
import com.qa.qaassessment.basetest.BaseTest;
import com.qa.qaassessment.pages.QuestionSettingsPage;

public class QuestionSettingsPageTest extends BaseTest
{

	QuestionSettingsPage qs;
	
	@Test
	public void basicPageTitleTest()
	{
		qs=new QuestionSettingsPage(driver);
		
		String QuesSettingPageTitle=qs.getQuesPageTitle();
		
		Assert.assertEquals(QuesSettingPageTitle,AppConstants.EXPEXTED_TITLE_BASIC_INFO);
	}
	
	
	@Test
	public void basicUrlTest()
	{
		qs=new QuestionSettingsPage(driver);
		
		String QuesSettingPageURL=qs.getQuesPageURL();
		
		Assert.assertEquals(QuesSettingPageURL,AppConstants.EXPECTED_URL_BASIC_INFO );
	}
	
	
	@Test
	public void fillQuestionSettingsPageAndSaveTest()
	{
		qs=new QuestionSettingsPage(driver);
		
		String successMesg=qs.fillQuestionSettingsPage();
	
		Assert.assertTrue(successMesg.contains("successfully"));
	}
	
}
