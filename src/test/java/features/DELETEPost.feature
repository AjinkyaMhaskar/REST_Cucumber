#Author: mhaskar

@tag
Feature: Delete operation using REST-Assured

Scenario: Verify DELET operation after POST

    Given I perform post operation for "/posts" with body as
    | id     | title   				 | author | 
    | 10		 | Post_for_delete | mhaskar|
   
    And I perform DELETE operation for "/posts/{postid}"
    | postid | 
    | 10		 |
    
    And I am performing GET operation for "/posts/{postid}"
    | postid | 
    | 10		 |
    
   Then I should not see the title has a name "Post_for_delete"