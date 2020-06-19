#Author: mhaskar

Feature: Create a user and verify on store front

  Scenario: Create user using API POST <host>/rest/<store_code>/V1/customers
  
  	Given I perform authentication operation for "/integration/admin/token" with body from properties fileone
    
    Given I perform POST operation with URL "/customers" with body
    
    Then I should see the status as 200 
  
  Scenario: Verify user on front end Magento website
    
    Given I open browser and Navigate to Magento website front end
    
    Given I navigate to login page and enter username, password and click on sign-in button 
    
    Then I verify if user is logged-in successfully
    