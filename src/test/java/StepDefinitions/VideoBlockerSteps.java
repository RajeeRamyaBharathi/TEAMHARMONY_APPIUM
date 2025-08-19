package StepDefinitions;

import PageObjects.PreferencesPage;
import PageObjects.VideoBlockerPage;
import Utilities.loggerLoad;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class VideoBlockerSteps {

    PreferencesPage prefPage;
    VideoBlockerPage vbPage;

    @Given("The user is on the Preferences tab")
    public void the_user_is_on_the_preferences_tab() {
        prefPage = new PreferencesPage();
        vbPage = new VideoBlockerPage();
        prefPage.changeinversionok();
        prefPage.moreOptions();
        prefPage.preferences();
        prefPage.notification();
        loggerLoad.info("User navigated to the Preferences tab");
    }

    @When("The user clicks the Video Blocker button")
    public void the_user_clicks_the_video_blocker_button() {
        vbPage.clickVideoBlockerButton();
        loggerLoad.info("Clicked Video Blocker button");
    }

    @Then("The user should see Video Blocker check box checked, Channel Filters options, Language and Region Filters, and Other Filters")
    public void the_user_should_see_video_blocker_button_checked_and_filters() {
    	vbPage.verifyVideoBlockerChecked();
        vbPage.verifyChannelFiltersDisplayed();
        vbPage.verifyLanguageRegionFiltersDisplayed();
        vbPage.verifyOtherFiltersDisplayed();
        loggerLoad.info("Verified Video Blocker checkbox is checked and all filter options are displayed");
    }
}
