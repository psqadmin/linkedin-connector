package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Groups;

public class GetSuggestedGroupsTestCases extends LinkedInTestParent {
	
	@Category({SmokeTests.class})
	@Test
	public void testGetSuggestedGroupsC1() {
		try{
			Groups groups = linkedInConn.getSuggestedGroups(instance.getGroupFieldList());
			assertNotNull(groups);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetSuggestedGroupsC2() {
		try{
			Groups groups = linkedInConn.getSuggestedGroups(null);
			assertNotNull(groups);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
