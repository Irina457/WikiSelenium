#language: en

  Feature: check wiki page

  Background:
    Given I open wiki search page

    Scenario Outline: check wiki
      Then I click the button
      Given I write text "<searchText>" to search input
      And I click the button to search it
      Then Result page has count more than 1
      Examples:
          | searchText     |
          | Франция        |
          | hello selenide |
          | 12345          |
          | a              |
          | @#@#$#!        |

