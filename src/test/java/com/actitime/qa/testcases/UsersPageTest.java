package com.actitime.qa.testcases;

import com.actitime.qa.base.TestBase;
import com.actitime.qa.pages.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UsersPageTest extends TestBase {
	LoginPage loginPage;
    HomePage homePage;
    UsersPage usersPage;

    public UsersPageTest() {
        super();

    }


    @BeforeMethod
    public void setup() {
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.loging(properties.getProperty("username"), properties.getProperty("password"));
        usersPage = new UsersPage();

    }


    @Test
    public void validateViewUserProfileTest() {
        SoftAssert softAssertion = new SoftAssert();
        homePage.clickOnUsersLink();
        softAssertion.assertTrue(usersPage.validateUserPageTitle(), "Cannot find the Users section page title");
        softAssertion.assertTrue(usersPage.validateUserTable(), "Cannot find the Users Table");
        softAssertion.assertTrue(usersPage.validateUserListCount(), "List of users in Users Table is zero");
        softAssertion.assertAll();

    }


    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
