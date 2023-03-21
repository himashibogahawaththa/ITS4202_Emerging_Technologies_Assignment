package com.actitime.qa.feature;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.ParameterType;
import io.cucumber.java.PendingException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class NewUserCreationStepDefs {
	
	private WebDriver driver;

	@ParameterType(".*")
	public String any(String text) {
		return text;
	}

	@Given("I am logged in as an admin user")
	public void iAmLoggedInAsAdminUser() {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\gavee\\\\Downloads\\\\chromedriver_win32\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.actitime.com/login.do");
		WebElement usernameInput = driver.findElement(By.id("username"));
		usernameInput.sendKeys("admin");
		WebElement passwordInput = driver.findElement(By.id("pwd"));
		passwordInput.sendKeys("password");
		WebElement loginBtn = driver.findElement(By.id("loginButton"));
		loginBtn.click();
	}

	@When("I navigate to the Home page and select Users")
	public void iNavigateToHomePageAndSelectUsers() {
		WebElement homePage = driver.findElement(By.xpath("//a[HomePage()='Home']"));
		homePage.click();
		WebElement usersPage = driver.findElement(By.xpath("//a[UsersPage()='Users']"));
		usersPage.click();
	}

	@And("I click on the New User button")
	public void iClickOnNewUserButton() {
		WebElement newUserBtn = driver.findElement(By.xpath("//a[NewUserPage()='New User']"));
		newUserBtn.click();
	}

	@And("I enter the user mandatory details such as {string}, {string}, {string} and {string}")
	public void iEnterUserMandatoryDetails(String firstName, String middleInitial, String lastName, String email) {
		WebElement firstNameInput = driver.findElement(By.id("createUserPanel_firstNameField"));
		firstNameInput.sendKeys(firstName);
		WebElement middleInitialInput = driver.findElement(By.id("createUserPanel_middleNameField"));
		middleInitialInput.sendKeys(middleInitial);
		WebElement lastNameInput = driver.findElement(By.id("createUserPanel_lastNameField"));
		lastNameInput.sendKeys(lastName);
		WebElement emailInput = driver.findElement(By.id("createUserPanel_emailField"));
		emailInput.sendKeys(email);
	}

	@And("I click on the Create User button")
	public void iClickOnCreateUserButton() {
		WebElement createUserBtn = driver.findElement(By.xpath("//button[text()='Create User']"));
		createUserBtn.click();
	}

	@Then("I should see the user in the users list")
	public void iShouldSeeUserInUsersList() {
		WebElement userList = driver.findElement(By.xpath("//a[text()='User List']"));
		userList.click();
		Assert.assertTrue(driver.getPageSource().contains("John") && 
			driver.getPageSource().contains("Perera") && 
			driver.getPageSource().contains("johnj@gmail.com"));
		driver.quit();
	}
}

