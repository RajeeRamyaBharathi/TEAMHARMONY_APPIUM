@VideoBlocker
Feature: Testing the VideoBlocker Module in the skytube application

  Scenario: Validate the display of Video Blocker button
    Given The user is on the Preferences tab
    When The user clicks the Video Blocker button
    Then The user should see Video Blocker check box checked, Channel Filters options, Language and Region Filters, and Other Filters
