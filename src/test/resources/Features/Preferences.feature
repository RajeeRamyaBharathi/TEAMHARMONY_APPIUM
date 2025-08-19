@Preferences
Feature: Preferences

  Scenario: Verify navigation to Preferences tab
    Given the user is on the SkyTube home page
    When the user clicks on the three-dot menu at the top-right corner and selects "Preferences"
    Then the user is navigated to the Preferences tab

  Scenario Outline: Verify the presence of individual menu options in Preferences
    Given the user is on the Preferences tab
    Then the user should see the menu option "<MenuOption>" and click the menuOption

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

  Scenario: Validate clicking and viewing of individual Preferences menu items
    Given the user is on the Preferences tab
    When the user clicks each Preferences menu item
    Then the user should be able to view the content of each clicked Preferences menu item
