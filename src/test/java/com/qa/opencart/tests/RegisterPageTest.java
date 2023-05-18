package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtils;

public class RegisterPageTest extends BaseTest {
	@BeforeClass
	public void regPageSetup() {
		regPage = loginPage.goToRegisterPage();
	}
	public String getRandomNumber() {
		Random randomGen = new Random();
		String email = "sm12im"+randomGen.nextInt(1000)+"@gmail.com";
		return email;
	}
	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][] = ExcelUtils.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}

	@Test(dataProvider = "getRegisterData")
	void userRegistrationTest(String firstName, String lastName,  String telephone, String password,
			String subscribe) {
		System.out.println(firstName);
		Assert.assertTrue(regPage.accountRegistration(firstName, lastName, getRandomNumber(), telephone, password, subscribe));

	}
}
