package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.client.enumeration.ConnectionModificationType;
import com.google.code.linkedinapi.client.enumeration.GroupMembershipField;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.schema.GroupMembership;
import com.google.code.linkedinapi.schema.GroupMemberships;

public class GetGroupMembershipsTestCases extends LinkedInTestParent {
	
	private String groupId;
	private List<GroupMembershipField> list;
	private Integer start;
	private Integer count;	
	
	@Before
	public void setup(){
		groupId = instance.getDefaultGroup().getId();
		list = instance.getGroupMembershipFieldList();
		start = instance.getStart();
		count = instance.getCount();
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetGroupMembershipsC1() {
		try{
			GroupMemberships groupMemberships = linkedInConn.getGroupMemberships(groupId, 
					list, start, count);
			verify(groupMemberships);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetGroupMembershipsC2() {
		try{
			GroupMemberships groupMemberships = linkedInConn.getGroupMemberships(null, 
					list, start, count);
			verify(groupMemberships);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetGroupMembershipsC3() {
		try{
			GroupMemberships groupMemberships = linkedInConn.getGroupMemberships(groupId, 
					list, null, null);
			verify(groupMemberships);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetGroupMembershipsC4() {
		try{
			GroupMemberships groupMemberships = linkedInConn.getGroupMemberships(groupId, 
					null, null, null);
			//email digest frequency is not returned by default, so resorting to asserting
			//null check
			assertNotNull(groupMemberships);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetGroupMembershipsC5() {
		try{
			GroupMemberships groupMemberships = linkedInConn.getGroupMemberships(null, 
					list, null, null);
			assertNotNull(groupMemberships);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetGroupMembershipsC6() {
		try{
			GroupMemberships groupMemberships = linkedInConn.getGroupMemberships(null, 
					null, null, null);
			assertNotNull(groupMemberships);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	private void verify(GroupMemberships groupMemberships){
		boolean groupPresent = false;
		for(GroupMembership groupMembership: groupMemberships.getGroupMembershipList()){
			if(groupMembership.getGroup().getId().equalsIgnoreCase(instance.getDefaultGroup().getId())
					&& groupMembership.getGroup().getName().equalsIgnoreCase(instance.getDefaultGroup().getName())
					&& groupMembership.getEmailDigestFrequency().getCode().equals(instance.getDefaultGroupMembership().getEmailDigestFrequency().getCode())){
				groupPresent = true;
			}
		}
		assertTrue(groupPresent);
	}
}
