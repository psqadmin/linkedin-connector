package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.GroupMembership;
import com.google.code.linkedinapi.schema.GroupMemberships;

public class JoinGroupTestCases extends LinkedInTestParent {
	
	@Before
	public void setUp(){
		linkedInConn.leaveGroup(instance.getDefaultGroup().getId());
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testJoinGroup() {
		try{
			boolean groupPresent = false;
			GroupMemberships gm = linkedInConn.getGroupMemberships(instance.getCurrentUser().getId(), null, null, null);
			for(GroupMembership membership: gm.getGroupMembershipList()){
				if(membership.getGroup().equals(instance.getDefaultGroup().getId())){
					groupPresent = true;
				}
			}
			assertFalse(groupPresent);
			
			linkedInConn.joinGroup(instance.getDefaultGroup().getId());
			
			gm = linkedInConn.getGroupMemberships(instance.getCurrentUser().getId(), null, null, null);
			for(GroupMembership membership: gm.getGroupMembershipList()){
				if(membership.getGroup().getId().equals(instance.getDefaultGroup().getId())){
					groupPresent = true;
				}
			}
			assertTrue(groupPresent);
			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
