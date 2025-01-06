package com.qa.qaassessment.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.qaassessment.appconstants.AppConstants;
import com.qa.qaassessment.basetest.BaseTest;
import com.qa.qaassessment.pages.BasicInformation;

public class BasicInfoPageTest extends BaseTest
{

	
	BasicInformation bi;
	
	@Test
	public void basicPageTitleTest()
	{
		bi=new BasicInformation(driver);
		
		String BasicTitle=bi.getBasicInfoTitle();
		
		Assert.assertEquals(BasicTitle,AppConstants.EXPEXTED_TITLE_BASIC_INFO);
	}
	
	
	@Test
	public void basicUrlTest()
	{
		bi=new BasicInformation(driver);
		
		String BasicURL=bi.getCurrentURL();
		
		Assert.assertEquals(BasicURL,AppConstants.EXPECTED_URL_BASIC_INFO );
	}
	
	
	
	@Test
	public void fillBasicInfoPageAndSaveTest()
	{
		bi=new BasicInformation(driver);
		
		String successMesg=bi.fillBasicInfoPageAndSave();
		
		Assert.assertTrue(successMesg.contains("successfully"));
		
	}
	
	@Test
	public void purposeOfAssessmentTest()
	{
		bi=new BasicInformation(driver);
		
		List<String> listOfPurposeOfTest=bi.listOfPurposeOfTest();
		
		List<String> expected_List_Of_Purpose=new ArrayList<String>();
		expected_List_Of_Purpose.add("Certification Assessments");
		expected_List_Of_Purpose.add("Educational Examination");
		expected_List_Of_Purpose.add("Hackathons");
		expected_List_Of_Purpose.add("Hiring");
		expected_List_Of_Purpose.add("skilling");
		
		Assert.assertEquals(listOfPurposeOfTest, expected_List_Of_Purpose);
	}
	
	
	
	
}
