package com.actitime.qa.testcases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.actitime.qa.base.TestBase;
import com.actitime.qa.pages.NewUserPage;
import com.actitime.qa.pages.UsersPage;
import com.actitime.qa.util.TestUtil;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;


public class NewUserPageTest extends TestBase{
	NewUserPageTest newUserPageTest;
	NewUserPage newUserPage;
	UsersPage usersPage;
	String firstName = "John";
	String mInitial = "H";
    String lastName = "Doe";
    String email = "johndoe@example.com";
    String sheetName = "Users";
	TestUtil testUtil;
	
	
	
	public NewUserPageTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setup() {
		initialization();
		newUserPageTest = new NewUserPageTest();
		testUtil = new TestUtil();
		newUserPage = new NewUserPage();
		usersPage = new UsersPage();
	}
	
	@Test(priority = 1)
	public void testNewUserButton() {
		// Find the 'Add User' button and click it
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // wait for up to 10 seconds
		By locator = By.xpath("//div[@class='components_button withPlusIcon']");
		WebElement newUserButton = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		// Click the button
		newUserButton.click();
        // Find the form for adding a new user
        WebElement newUserForm = driver.findElement(By.id("createUserPanel"));

        // Assert that the form is displayed
        assertTrue(newUserForm.isDisplayed());
    }

 
    @Test(priority = 2)
    public void testCreateNewUser() {
        // Enter user details
        newUserPage.enterFirstName(firstName);
        newUserPage.enterMiddleInitial(mInitial);
        newUserPage.enterLastName(lastName);
        newUserPage.enterEmail(email);

        // Submit new user form
        newUserPage.clickSubmitButton();

        // Verify that new user is created successfully
        assert(usersPage.isUserCreatedSuccessfully());
    }
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
}
