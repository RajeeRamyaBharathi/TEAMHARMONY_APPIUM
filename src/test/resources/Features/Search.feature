@Search
Feature: Search Channel functionality in Skytube application

Background:
    Given The user has opened the Skytube application

Scenario: User verifies viewing a specific channel by searching its name
    Given The user is in Skytube home page
    When The user searches for a channel with the name "Disney Plus"
    Then The user should see the listed videos in the home page for "Disney Plus"

Scenario: User verifies viewing a specific video with the URL
    Given The user is in Skytube home page
    When The user searches for a specific video with its link "https://youtu.be/abc123"
    Then The user should see the video in the home page for "https://youtu.be/abc123"

Scenario: Verify the user is able to search channel in Skytube application with special characters
    Given The user is in Skytube home page
    When The user types the special characters in the search tab "@#$%^&*"
    Then The user should see an empty column below the search tab
