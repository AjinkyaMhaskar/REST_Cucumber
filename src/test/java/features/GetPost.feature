#Author: mhaskar

@tag
Feature: Verify GET using REST-Assured

    Scenario: Verify one of the author of the post
    Given I perform GET operation for "posts"
    And I perform GET for the post number "1"
    Then I should get the author name as "mhaskar"

    Scenario: Verify path parameter of Get
    Given I perform GET operation for "/post"
    Then I should verify get parameter
    
    Scenario: Verify query parameter of Get
    Given I perform GET operation for "/post"
    Then I should verify get query parameter  