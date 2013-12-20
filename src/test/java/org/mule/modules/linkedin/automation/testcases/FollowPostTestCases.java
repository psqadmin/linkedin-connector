package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Companies;
import com.google.code.linkedinapi.schema.Company;

public class FollowPostTestCases extends LinkedInTestParent{
	
	@Before
	public void setup(){
		linkedInConn.unfollowPost(instance.getDefaultPost().getId());
		instance.sleep();
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testFollowPost() {
		try{
			linkedInConn.followPost(instance.getDefaultPost().getId());
			instance.sleep();
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@After
	public void tearDown(){
		linkedInConn.unfollowPost(instance.getDefaultPost().getId());
		instance.sleep();
	}

}
