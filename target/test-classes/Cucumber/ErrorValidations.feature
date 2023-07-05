
@tag
Feature: Error Validation
  I want to use this template for my feature file


  @Error
  Scenario Outline: Negative scenario of incorrect password
    Given I land on eCommerce page
    When Logged in with <email> and <password>
    Then "Incorrect email or password." is displayed

Examples:
| email                | password    | 
| garima2404@gmail.com | Garima@404 |
