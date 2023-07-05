#@tag
#Feature : Purchase the order from eCommerce Website
#
#Background:
#Given I land on eCommerce page
#
#@tag2
#Scenario Outline: Positive test of submitting the order 
#Given Logged in with <email> and <password>
#When I add <productName> to cart
#And Checkout <productName> and submit the order
#Then "Thankyou for the order." message is displayed on ConfirmationPage
#
#Examples:
#| email                | password    | productName     |
#| garima2404@gmail.com | Garima@2404 | ZARA COAT 3     |
#| shetty@gmail.com     | Iamking@000 | ADIDAS ORIGINAL |

@Regression
Feature: Purchase the order from eCommerce Website

Background:
Given I land on eCommerce page

@tag2
Scenario Outline: Positive test of submitting the order
Given Logged in with <email> and <password>
When I add <productName> to cart
And Checkout <productName> and submit the order
Then "Thankyou for the order." message is displayed on ConfirmationPage

Examples:
| email                | password    | productName     |
| garima2404@gmail.com | Garima@2404 | ZARA COAT 3     |

#Examples:
#| email                | password    | productName  |
#| garima2404@gmail.com | Garima@2404 | ZARA COAT 3  |
#| shetty@gmail.com     | Iamking@000 | ADIDAS ORIGINAL |
