#Author: mhaskar

@tag
Feature: Verify POST operations using REST-Assured

		Scenario: Verify POST operation
    Given I perform POST operation for "/post"
    
    Scenario: Verify post operation for Profile
    Given I perform post operation for "/posts/{profileNo}/profile" with body
    | name      |profile | 
    | mhaskar_1 |3		   |
    Then I should see the body has a name "mhaskar_1"
