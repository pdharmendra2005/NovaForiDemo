
@Test
Feature :  As a user I want to check four co-ordinate that make a square

  Scenario : User supply correct co-ordinate to check that makes a square
	Given I am on Mock-up page
	Then  I should navigate to Mock-up page
	When  I enter all four co-ordinates
	And   I click on Check button
	Then  I should see that all four co-ordinates make square & navigate to new page Mock-up


  Scenario Outline: User supply incorrect co-ordinate to check that makes a square
	Given I am on Mock-up page
	Then  I should navigate to Mock-up page
	When  I enter "<Coordinate1>","<Coordinate2>","<Coordinate3>","<Coordinate4>" and click CHECK
	Then  I should see an "<ErrorMessage>"



	Examples:
	  | Coordinate1 | Coordinate2 | Coordinate3 | Coordinate4 | ErrorMessage           |
	  | (#,#)       | (#,#)       | ( , )       | (#,#)       | This field is required |
	  | (#,#)       | (# #)       | (#,#)       | (#,#)       | This field is seperated with comma |
	  | (#,#)       | (#,#)       | (%,#)       | (#,#)       | Right field is required to make a square |