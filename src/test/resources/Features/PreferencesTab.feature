@Preferences

Feature: Preferences


  Background: 

    Given the user is connected to the android emulator and the SkyTube application is installed successfully

  Scenario: Verify navigation to Preferences tab
    Given the user is on the SkyTube home page
    When the user clicks on the three-dot menu at the top-right corner and selects "Preferences"
    Then the user is navigated to the Preferences tab

  Scenario Outline: Verify the presence of individual menu options in Preferences
    Given the user is on the Preferences tab
    Then the user should see the menu option "<MenuOption>" and clicking it

    Examples: 

      | MenuOption            |
      | Video Player          |
      | Video Blocker         |
      | SponsorBlock          |
      | Import/Export         |
      | Privacy               |
      | Network and Downloads |
      | Others                |
      | About                 |