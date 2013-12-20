package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.JobBookmark;
import com.google.code.linkedinapi.schema.JobBookmarks;

public class GetJobBookmarkTestCases extends LinkedInTestParent{

	//FIXME: Incorrect implementation in BaseLinkedInApiClient for BookmarkJob
	/*@Before
	public void setup(){
		linkedInConn.bookmarkJob(instance.getJobId());
	}*/
	
	@Category({SmokeTests.class})
	@Test
	public void testGetBookmarkJobsC1() {
		try{
			JobBookmarks bookmarks = linkedInConn.getJobBookmarks(instance.getJobFieldList());
			assertNotNull(bookmarks);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetBookmarkJobsC2() {
		try{
			JobBookmarks bookmarks = linkedInConn.getJobBookmarks(null);
			assertNotNull(bookmarks);			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	/*@After
	public void tearDown(){
		linkedInConn.unbookmarkJob(instance.getJobId());
	}*/
}
