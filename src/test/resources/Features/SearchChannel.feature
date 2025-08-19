@Search
Feature: Search Channel functionality in Skytube application

  Scenario: User verifies viewing a specific channel by searching its name
    Given The user is in Skytube home page
    When The user searches for a channel with the name "Avatar"
    Then The user should see the listed videos in the home page for "Avatar"

  Scenario: User verifies viewing a specific video with the URL
    Given The user is in Skytube home page
    When The user searches for a specific video with its link "https://youtu.be/nb_fFj_0rq8"
    Then The user should see the video in the home page for "Avatar: Fire and Ash | Official Trailer"

  Scenario: Verify the user is able to search channel in Skytube application with special characters
    Given The user is in Skytube home page
    When The user types the special characters in the search tab "!@#$"
    Then The user should see an empty column below the search tab
