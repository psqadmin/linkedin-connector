package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import javax.validation.constraints.AssertTrue;

import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.GroupMembership;
import com.google.code.linkedinapi.schema.GroupMemberships;

public class UpdateGroupMembershipTestCases extends LinkedInTestParent {

	//FIXME: Incorrect implementation in BaseLinkedInApiClient
	/*@Category({SmokeTests.class})
	@Test
	public void testUpdateGroupMembership() {
		try{
			String junkEmailId = "abc@def.com";
			linkedInConn.updateGroupMembership(instance.getDefaultGroup().getId(), junkEmailId, 
					instance.getDefaultGroupMembership().getEmailDigestFrequency().getCode(),
					!instance.getDefaultGroupMembership().isShowGroupLogoInProfile(),
					!instance.getDefaultGroupMembership().isEmailAnnouncementsFromManagers(),
					!instance.getDefaultGroupMembership().isAllowMessagesFromMembers(),
					!instance.getDefaultGroupMembership().isEmailForEveryNewPost());
			
			GroupMemberships groupMemberships = linkedInConn.getGroupMemberships(instance.getDefaultGroup().getId(),
					instance.getGroupMembershipFieldList(), instance.getStart(), instance.getCount());
			
			boolean emailModified = false;
			for(GroupMembership gm: groupMemberships.getGroupMembershipList()){
				if(gm.getContactEmail().equalsIgnoreCase(junkEmailId))
					emailModified = true;
			}
			assertTrue(emailModified);			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@After
	public void tearDown(){
		linkedInConn.updateGroupMembership(instance.getDefaultGroup().getId(), instance.getDefaultGroupMembership().getContactEmail(), 
				instance.getDefaultGroupMembership().getEmailDigestFrequency().getCode(),
				instance.getDefaultGroupMembership().isShowGroupLogoInProfile(),
				instance.getDefaultGroupMembership().isEmailAnnouncementsFromManagers(),
				instance.getDefaultGroupMembership().isAllowMessagesFromMembers(),
				instance.getDefaultGroupMembership().isEmailForEveryNewPost());
	}*/
}
