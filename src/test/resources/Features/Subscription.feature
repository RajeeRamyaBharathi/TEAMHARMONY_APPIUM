@SubscribeAndUnSubscribe
Feature: Subscribe and unsubscribe to channels in Skytube application

  Scenario: User subscribes to a channel by playing a video and subscribing
    Given The user is on the Skytube home page
    When The user plays a video and subscribes to the channel
    Then The user should see a Subscribed confirmation message

  Scenario: User verifies the subscribed channel is displayed in the subscription page
    Given The user is on the Skytube home page
    When The user navigates to the subscribed channels tab from the navigation menu
    Then The user should see the subscribed channel

  Scenario: User verifies the unsubscribe button is visible for subscribed channels
    Given The user is on the Skytube home page
    When The user opens the subscribed channels tab from the navigation menu
    Then The user should see the Unsubscribe button for the subscribed channel

  Scenario: User unsubscribes from a channel
    Given The user is on the subscribed channel page
    When The user clicks the Unsubscribe button
    Then The user should see an Unsubscribed confirmation message
