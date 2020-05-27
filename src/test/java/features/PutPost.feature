#Author: mhaskar

@tag
Feature: PUT operation using REST-Assured

Scenario: Verify PUT operation after POST

    Given I perform post operation for "/posts" with body as
    | id     | title   			| author | 
    | 10		 | Post_for_PUT | mhaskar|
   
    And I perform PUT operation for "/posts/{postid}"
    | postid | title   				 		 | author     |
    | 10		 | Post_for_PUT_Edited | mhaskar_PUT|
    
    And I am performing GET operation for "/posts/{postid}" after post
    | postid | 
    | 10		 |
    
   Then I should see the title has a title "Post_for_PUT_Edited" and author as "mhaskar_PUT" 