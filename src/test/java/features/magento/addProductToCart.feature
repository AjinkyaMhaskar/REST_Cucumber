#Author: mhaskar

Feature: Verify add product to cart functionality

    Scenario: Create a quote using API POST <host>/rest/<store_code>/V1/carts/mine
    
    Given I perfomr POST operation with URL "/integration/customer/token" with customer details for authentication
    
    Given  I perform POST operation with URL "/carts/mine" with customer token
    
    Then I should get quote id and API status code 200
    
    Scenario: Add item to cart using API POST <host>/rest/<store_code>/V1/carts/mine/items
    
    Given I perfomr POST operation with URL "/carts/mine/items" with customer token and body
    
    Then I should get Product name, type and price in response
    
    Scenario: Verify if items are added successfully to customer cart
    
    Given I open browser and Navigate to Magento website front end
    
    Given I navigate to login page and enter username, password and click on sign-in button 
    
    Then I navigate to customer cart verify added product 