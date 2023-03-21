package com.actitime.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.actitime.qa.base.TestBase;

public class NewUserPage extends TestBase {
	
	Logger log = Logger.getLogger(NewUserPage.class);
	
	
	//Page Factory - Object Repository
	
	@FindBy(xpath = "//div[@class='components_button withPlusIcon']")
    private WebElement newUserButton;
	
	@FindBy(xpath = "//input[@id='createUserPanel_firstNameField']")
	private WebElement firstNameInput ;
	
	@FindBy(xpath = "//input[@id='createUserPanel_middleNameField']")
	private WebElement middleInitialInput;
	
	@FindBy(xpath = "//input[@id='createUserPanel_lastNameField']")
	private WebElement lastNameInput;
	
	@FindBy(xpath = "//input[@id='createUserPanel_emailField']")
	private WebElement emailInput;
	
	@FindBy(xpath = "//div[@class='components_button submitBtn']")
	private WebElement submitButton;
	
	public NewUserPage() {

        PageFactory.initElements(driver, this);

    }
	
	
	public void enterFirstName(String firstName) {
		firstNameInput.sendKeys(firstName);
	}

	public void enterMiddleInitial(String middleInitial) {
		middleInitialInput.sendKeys(middleInitial);
	}

	public void enterLastName(String lastName) {
		lastNameInput.sendKeys(lastName);
	}

	public void enterEmail(String email) {
		emailInput.sendKeys(email);
	}

	public void clickSubmitButton() {
		submitButton.click();
	}
	
}
