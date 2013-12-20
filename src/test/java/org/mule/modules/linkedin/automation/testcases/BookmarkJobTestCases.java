package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.JobBookmark;
import com.google.code.linkedinapi.schema.JobBookmarks;

public class BookmarkJobTestCases extends LinkedInTestParent{
	//FIXME: Incorrect implementation in BaseLinkedInApiClient
	/*@Category({SmokeTests.class})
	@Test
	public void testBookmarkJob() {
		try{
			linkedInConn.bookmarkJob(instance.getJobId());
			
			boolean isJobBookmarked = false;
			JobBookmarks bookmarks = linkedInConn.getJobBookmarks(null);
			for(JobBookmark bookmark: bookmarks.getJobBookmarkList()){
				if(bookmark.getJob().getId().equals(instance.getJobId()))
					isJobBookmarked = true;
			}
			
			assertTrue(isJobBookmarked);
			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@After
	public void tearDown(){
		linkedInConn.unbookmarkJob(instance.getJobId());
	}*/
}
