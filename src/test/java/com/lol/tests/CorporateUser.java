package com.lol.tests;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.lol.utils.Common;
import com.relevantcodes.extentreports.LogStatus;

public class CorporateUser extends TestExecutor {

	@Test(enabled = true)
	public void CorporateUser() throws IOException, InterruptedException {
		try {
			dataUtils.setSheetName("CorporateUser");
			int i = 1;
			while (i <= dataUtils.noOfRows()) {
				dataUtils.getRowData(i);
				if (getData("RunThis").toLowerCase().equals("yes")) {
					System.out.println(i);

					String scenario = getData("Scenario Description");
					logger = report.startTest(scenario);
					logger.log(LogStatus.INFO, "=======Start Execution Report========");

					driver.get(prop.getProperty("URL"));
					logger.log(LogStatus.PASS, "Entering URL");
					logger.log(LogStatus.INFO, prop.getProperty("URL"));
					
					login();
					
					common.isElementExist(corpUser_Obj.home, "Home");
					
					common.isElementExist(corpUser_Obj.intelligence, "Intelligence");
					common.clickByXPath(corpUser_Obj.intelligence, "Intelligence");
					
					common.isElementExist(corpUser_Obj.advancedAnalytics, "AdvancedAnalytics");
					common.clickByXPath(corpUser_Obj.advancedAnalytics, "AdvancedAnalytics");
					
					common.waitExplicitlyForPageToLoad("Advanced Analytics", 60);
					
					common.isElementExist(corpUser_Obj.advancedAnalytics, "AdvancedAnalytics");
					
					common.isElementExist(corpUser_Obj.searchUser, "searchUserName ");
					common.getObjectByXpath(corpUser_Obj.searchUser).clear();
					common.inputbyxpath(corpUser_Obj.searchUser, "searchUserName", getData("searchUserName"));
					
					common.isElementExist(corpUser_Obj.runSerach, "runSerach");
					common.clickByXPath(corpUser_Obj.runSerach, "runSerach");
					
					common.waitExplicitlyForPresence("//div[text()='"+getData("searchUserName")+"']", 60);
					
					common.clickByXPath("//div[text()='"+getData("searchUserName")+"']", "User");
					

					logger.log(LogStatus.INFO, "=======End Execution Report======");
					reportclosewindows();
				} else {
					System.out.println("Skipped scenario : " + getData("Scenario Description"));

				}

				i++;
			}
		} catch (Exception e) {

			common.captureSS("Errors found", LogStatus.FAIL);
			reportclosewindows();
			System.out.println("Exception occured during execution: " + e.getMessage());
			Assert.assertTrue(false);
		}

	}

}
