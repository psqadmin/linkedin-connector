package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

public class UnfollowPostTestCases extends LinkedInTestParent{
	
	@Before
	public void setup(){
		linkedInConn.followPost(instance.getDefaultPost().getId());
		instance.sleep();
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testFollowPost() {
		try{
			linkedInConn.unfollowPost(instance.getDefaultPost().getId());	
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
