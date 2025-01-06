package com.qa.qaassessment.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.qaassessment.appconstants.AppConstants;
import com.qa.qaassessment.basetest.BaseTest;

import com.qa.qaassessment.pages.RegistrationFormPage;

public class RegistrationFormPageTest extends BaseTest
{
	RegistrationFormPage rs;
	
	@Test
	public void basicPageTitleTest()
	{
		rs=new RegistrationFormPage(driver);
		
		String registrationPageTitle=rs.getRegistrationTitle();
		
		Assert.assertEquals(registrationPageTitle,AppConstants.EXPEXTED_TITLE_BASIC_INFO);
	}
	
	
	@Test
	public void basicUrlTest()
	{
		rs=new RegistrationFormPage(driver);
		
		String registrationPageURL=rs.getRegistrationURL();
		
		Assert.assertEquals(registrationPageURL,AppConstants.EXPECTED_URL_BASIC_INFO );
	}
	
	@Test
	public void addNewOrganizationTest()
	{
		rs=new RegistrationFormPage(driver);
		
		String newOrgAdd=rs.addNewOrganization();

		Assert.assertTrue(newOrgAdd.contains("successfully"));
	}
	

}
