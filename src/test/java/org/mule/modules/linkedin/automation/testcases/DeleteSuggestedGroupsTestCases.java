package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Group;
import com.google.code.linkedinapi.schema.Groups;

public class DeleteSuggestedGroupsTestCases extends LinkedInTestParent {
	
	private Group randomGroupToDelete;
	
	@Before
	public void setup(){
		Groups groups = linkedInConn.getSuggestedGroups(null);
		if(groups!=null && groups.getGroupList() != null && groups.getGroupList().size() > 0)
			randomGroupToDelete = groups.getGroupList().get(0);
		instance.sleep();
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetSuggestedGroups() {
		try{
			if(randomGroupToDelete != null)
				linkedInConn.deleteGroupSuggestion(randomGroupToDelete.getId());
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
