package StepDefinitions;

import static org.testng.Assert.assertEquals;

import PageObjects.Bookmarkspage;
import PageObjects.Downloadspage;
import Utilities.loggerLoad;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Downloadssteps {
	
	Downloadspage download;
	
	@Given("The user is already in the trending page of the Skytube application")
	public void the_user_is_already_in_the_trending_page_of_the_skytube_application() {
	   download = new Downloadspage();
	   download.changeinversionok();
	}

	@When("Click on the Downloads tab")
	public void click_on_the_downloads_tab() {
	   download.clickdownload();
	    
	}

	@Then("Downloads tab is opened successfully and message Download some videos to find them here is displayed")
	public void downloads_tab_is_opened_successfully_and_message_download_some_videos_to_find_them_here_is_displayed() {
	   String emptytext = download.emptydownloadpage();
	   loggerLoad.info(emptytext);
	   assertEquals(emptytext, "Download some videos to find them here.", "Incorrect message");    
	    
	}

	@When("User clicks on the Download and Delete Download options from the Trending page of any video")
	public void user_clicks_on_the_download_and_delete_download_options_from_the_trending_page_of_any_video() {
	   download.downloadvideo();
	    
	}

	@Then("Clicking on Download option from the Trending page adds the Video to the Downloads tab and clicking on Delete Download option from the Trending page removes the video from the Downloads tab")
	public void clicking_on_download_option_from_the_trending_page_adds_the_video_to_the_downloads_tab_and_clicking_on_delete_download_option_from_the_trending_page_removes_the_video_from_the_downloads_tab() throws InterruptedException {
		String videoname = download.checkdownloadspage();
		  loggerLoad.info("Downloaded video:"+videoname);
		  assertEquals(videoname, "BARBIE GIRLS!💕#shorts #shortsviral #barbie", "Incorrect Videoname"); 
		  download.trendingpage();
		  download.deletedownloadvideo();
		  String emptytext = download.checkdeletedownloadpage();
		  loggerLoad.info(emptytext);
			assertEquals(emptytext, "Download some videos to find them here.", "Incorrect message");
	    
	}


	@Given("The user downloads a video")
	public void the_user_downloads_a_video() throws InterruptedException {
		download = new Downloadspage();	
		download.changeinversionok();
		loggerLoad.info("Closing the pop up and navigating to the Trending page");
		download.downloadvideo();
		download.checkdownloadspage();
	}

	@When("User delete download the video from the Downloads page")
	public void user_delete_download_the_video_from_the_downloads_page() {
		download.deletefromdownloadpage();	
	}
	
	@Then("The video is successfully deleted from the Dowloads page")
	public void the_video_is_successfully_deleted_from_the_dowloads_page() {
		String emptytext = download.emptydownloadpage();
		loggerLoad.info(emptytext);
		assertEquals(emptytext, "Download some videos to find them here.", "Incorrect message");  
	}


}
