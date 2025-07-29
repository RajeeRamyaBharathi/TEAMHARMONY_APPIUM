@WatchedVideoCleaner
Feature: Testing the Watched Video Cleaner Module in the SkyTube application

Scenario: Validate Removed watched downloads button
Given The user clicks the three dot tab
When The user clicks the Watched Video Cleaner tab
Then The user should see options for cleaning Watched Video Cleaner message

Scenario: Validate Remove watched downloads click
Given The user clicks the Watched Video Cleaner button
When The user clicks the Remove watched downloads and clicks CLEAN button
Then The user should see DOWNLOADS tab has no downloaded videos

Scenario: Validate Remove watched bookmarks click
Given The user clicks the Watched Video Cleaner button
When The user clicks the Remove watched bookmarks and clicks CLEAN
Then The user should see DOWNLOADS tab has no bookmarked videos

Scenario: Validate Remove watched downloads and Remove watched bookmarks click
Given The user clicks the Watched Video Cleaner button
When The user clicks both Remove watched downloads and Remove watched bookmarks
Then The user should see DOWNLOADS tab has no both downloaded and bookmarked videos
