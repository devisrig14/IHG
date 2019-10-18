package com.lol.pages;

import com.lol.tests.TestExecutor;

public class corporateUser_Obj extends TestExecutor{
	
	public String username = "//input[@id='username']";
	public String password = "//input[@id='password']";
	public String login = "//input[@id='submitBtn']";
	public String iHGLogo = "//img[@src='./images/ihgbrandmark.png']";
	public String environment = "//input[@value='QA']";
	public String landingPageLogo = "//img[@alt='SailPoint Logo']";
	public String home = "//i[@class='fa fa-home']";
	public String intelligence = "//*[contains(text(),'Intelligence')]";
	public String advancedAnalytics = "(//a[contains(text(),'Advanced Analytics')])[1]";
	public String searchUser = "//input[@id='identitySearchForm:userName']";
	public String runSerach  = "//div[@id='preIdentitySearchBtn']";
	public String advAnalytics_page = "//h1[text()='Advanced Analytics']";
	
	
}

