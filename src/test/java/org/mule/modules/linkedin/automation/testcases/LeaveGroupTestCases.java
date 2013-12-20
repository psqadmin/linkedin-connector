package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.GroupMembership;
import com.google.code.linkedinapi.schema.GroupMemberships;

public class LeaveGroupTestCases extends LinkedInTestParent{
	
	@Category({SmokeTests.class})
	@Test
	public void testLeaveGroup() {
		try{
			boolean groupPresent = false;
			GroupMemberships gm = linkedInConn.getGroupMemberships(instance.getCurrentUser().getId(), null, null, null);
			for(GroupMembership membership: gm.getGroupMembershipList()){
				if(membership.getGroup().equals(instance.getDefaultGroup().getId())){
					groupPresent = true;
				}
			}
			assertTrue(groupPresent);
			
			linkedInConn.leaveGroup(instance.getDefaultGroup().getId());
			
			gm = linkedInConn.getGroupMemberships(instance.getCurrentUser().getId(), null, null, null);
			for(GroupMembership membership: gm.getGroupMembershipList()){
				if(membership.getGroup().equals(instance.getDefaultGroup().getId())){
					groupPresent = false;
				}
			}
			assertFalse(groupPresent);
			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@After
	public void tearDown(){
		linkedInConn.joinGroup(instance.getDefaultGroup().getId());
	}
}
