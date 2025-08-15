@WatchedVideoCleaner
Feature: Watched Video Cleaner in SkyTube

  Scenario: Display of Watched Video Cleaner option
    Given The user is on the SkyTube home page
    When The user clicks the three-dot menu at the top-right corner and selects Watched Video Cleaner
    Then The user should see the Watched Video Cleaner popup containing the options:
      | Remove watched downloads |
      | Remove watched bookmarks |

  Scenario: Attempt to clean without selecting any options
    Given The user is in the Watched Video Cleaner popup
    When The user clicks the CLEAN button without selecting any options
    Then The user should see the videos are present in the Downloads and Bookmark tab
    
  Scenario: Clean watched downloads only
    Given The user is in the Watched Video Cleaner popup
    When The user selects "Remove watched downloads" and clicks the CLEAN button
    Then All watched videos should be removed from the DOWNLOADS tab

  Scenario: Clean watched bookmarks only
    Given The user is in the Watched Video Cleaner popup
    When The user selects "Remove watched bookmarks" and clicks the CLEAN button
    Then All watched videos should be removed from the BOOKMARKS tab

  Scenario: Clean both watched downloads and bookmarks
    Given The user is in the Watched Video Cleaner popup
    When The user selects both "Remove watched downloads" and "Remove watched bookmarks" and clicks the CLEAN button
    Then All watched videos should be removed from the DOWNLOADS and BOOKMARKS tabs
