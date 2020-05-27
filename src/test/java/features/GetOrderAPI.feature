#Author: mhaskar

@tag
Feature: GET Order API operation using REST-Assured

Scenario: Verify GET operation with bearer authentication token

    Given I perform authentication operation for "/integration/admin/token" with body from properties file
    
    Given I perform GET operation for "/orders/{postid}" with token and body as
    | postid | 
    | 4		   |
    
   Then I should see the status as 200 & order id as "4" 