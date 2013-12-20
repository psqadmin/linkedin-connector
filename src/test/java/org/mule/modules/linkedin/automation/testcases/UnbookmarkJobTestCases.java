package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.JobBookmark;
import com.google.code.linkedinapi.schema.JobBookmarks;

public class UnbookmarkJobTestCases extends LinkedInTestParent {
	
	//FIXME: Incorrect implementation in BaseLinkedInApiClient for BookmarkJob
	/*@Before
	public void setup(){
		linkedInConn.bookmarkJob(instance.getJobId());
	}*/
	
	@Category({SmokeTests.class})
	@Test
	public void testUnbookmarkJob() {
		try{
			linkedInConn.unbookmarkJob(instance.getJobId());
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
