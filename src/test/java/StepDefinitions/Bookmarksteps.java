package StepDefinitions;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import PageObjects.Bookmarkspage;
import Utilities.drivermanager;
import Utilities.loggerLoad;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.*;

public class Bookmarksteps {

	Bookmarkspage bookpage;	
	
	@Given("The user is in the trending page of the Skytube application")
	public void the_user_is_in_the_trending_page_of_the_skytube_application() throws MalformedURLException, URISyntaxException {
		bookpage = new Bookmarkspage();	
		bookpage.changeinversionok();
		loggerLoad.info("Closing the pop up and navigating to the Trending page");
	}

	@When("Click on the Bookmarks tab")
	public void click_on_the_bookmarks_tab() throws MalformedURLException, URISyntaxException {
		bookpage.bookmarkpage();
		loggerLoad.info("In Bookmarks page");
	}

	@Then("Bookmarks tab is opened successfully and message Bookmark some videos to find them here is displayed")
	public void bookmarks_tab_is_opened_successfully_and_message_bookmark_some_videos_to_find_them_here_is_displayed() throws MalformedURLException, URISyntaxException {
		String emptytext = bookpage.emptybookmarkcheck();
		loggerLoad.info("Empty text in the bookmark page is :"+emptytext);
		assertEquals(emptytext, "Bookmark some videos to find them here.", "Incorrect message");    
	}

	@When("User clicks on the Bookmark and unbookmark options from the Trending page of any video")
	public void user_clicks_on_the_bookmark_and_unbookmark_options_from_the_trending_page_of_any_video() {
	   bookpage.bookmarkvideo();
	}

	@Then("Clicking on bookmark option from the Trending page adds the Video to the Bookmarks tab and clicking on unbookmark option from the Trending page removes the video from the bookmark tab")
	public void clicking_on_bookmark_option_from_the_trending_page_adds_the_video_to_the_bookmarks_tab_and_clicking_on_unbookmark_option_from_the_trending_page_removes_the_video_from_the_bookmark_tab() {
	  String videoname = bookpage.checkbookmarkpage();
	  loggerLoad.info("Bookmarked video:"+videoname);
	  assertEquals(videoname, "Zootopia 2 | Trailer", "Incorrect Videoname"); 
	   bookpage.trendingpage();
	   bookpage.unbookmarkvideo();
	  String emptytext = bookpage.checkunbookmarkpage();
	  loggerLoad.info(emptytext);
		assertEquals(emptytext, "Bookmark some videos to find them here.", "Incorrect message");
	}

	@Given("The user bookmarks a video")
	public void the_user_bookmarks_a_video() {
		bookpage = new Bookmarkspage();	
		bookpage.changeinversionok();
		loggerLoad.info("Closing the pop up and navigating to the Trending page");
		bookpage.bookmarkvideo();
	}

	@When("User unbookmark the video from the Bookmarks page")
	public void user_unbookmark_the_video_from_the_bookmarks_page() {
		bookpage.unbookmark_bookmarkpage();	   
	}

	@Then("The video is successfully unbookmarked from the Bookmarks page")
	public void the_video_is_successfully_unbookmarked_from_the_bookmarks_page() {
		String emptytext = bookpage.emptybookmarkcheck();
		loggerLoad.info("Empty text in the bookmark page is :"+emptytext);
		assertEquals(emptytext, "Bookmark some videos to find them here.", "Incorrect message");  
	}

}
