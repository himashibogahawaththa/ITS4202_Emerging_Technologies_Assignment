Feature: As the admin I should be create new users

	@TC_001
	Scenario Outline: Create a new user as an admin user
	Given I am logged in as an admin user
	When I navigate to the Home page and select Users
  And I click on the New User button 
  Then I should see the add user form
	And I enter the user mandatory details such as <firstname>, <middleinitial>, <lastname> and <email>
	And I click on the Create User button
	Then I should see the user in the users list

Examples:
| firstname | middleinitial | lastname   | email          
| john    	| J             | perera     | johnj@gmail.com