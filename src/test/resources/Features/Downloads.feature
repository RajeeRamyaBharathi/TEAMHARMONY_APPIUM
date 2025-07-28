@Download
Feature: Testing the Download Module in skytube application

Scenario: Validate the Downloads tab is displayed and the page is empty initially
Given The user is already in the trending page of the Skytube application
When Click on the Downloads tab
Then Downloads tab is opened successfully and message Download some videos to find them here is displayed

Scenario: Validate the user is able to Download and Delete Download a video from the Trending page without opening the video
Given The user is already in the trending page of the Skytube application
When User clicks on the Download and Delete Download options from the Trending page of any video
Then Clicking on Download option from the Trending page adds the Video to the Downloads tab and clicking on Delete Download option from the Trending page removes the video from the Downloads tab

Scenario: Validate the user is able to Download and Delete Download a video after playing the video
Given The user just clicks on a video and plays it
When User clicks on the Download and Delete Download options after playing the video
Then Clicking on Download option when the video plays adds the Video to the Downloads tab and clicking on Delete Download option when the video is played removes the video from the Downloads tab