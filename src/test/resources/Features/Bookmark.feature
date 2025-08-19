@Bookmark
Feature: Testing the Bookmark Module in skytube application

  Scenario: Validate the Bookmarks tab is displayed and the page is empty initially
    Given The user is in the trending page of the Skytube application
    When Click on the Bookmarks tab
    Then Bookmarks tab is opened successfully and message Bookmark some videos to find them here is displayed

  Scenario: Validate the user is able to bookmark and unbookmark a video from the Trending page without opening the video
    Given The user is in the trending page of the Skytube application
    When User clicks on the Bookmark and unbookmark options from the Trending page of any video
    Then Clicking on bookmark option from the Trending page adds the Video to the Bookmarks tab and clicking on unbookmark option from the Trending page removes the video from the bookmark tab

  Scenario: Validate the user is able to unbookmark from the bookmark page
    Given The user bookmarks a video
    When User unbookmark the video from the Bookmarks page
    Then The video is successfully unbookmarked from the Bookmarks page
