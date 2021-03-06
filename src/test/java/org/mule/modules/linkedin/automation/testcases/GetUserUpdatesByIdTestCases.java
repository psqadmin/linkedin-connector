package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.client.enumeration.NetworkUpdateType;
import com.google.code.linkedinapi.schema.Activity;
import com.google.code.linkedinapi.schema.Network;
import com.google.code.linkedinapi.schema.Update;

public class GetUserUpdatesByIdTestCases extends LinkedInTestParent {
	
	private List<NetworkUpdateType> updateTypes;
	private Date startDate;
	private Date endDate;
	private Integer start;
	private Integer count;
	
	@Before
	public void setup(){
		updateTypes = new ArrayList<NetworkUpdateType>();
		updateTypes.add(NetworkUpdateType.APPLICATION_UPDATE);
		
		startDate = instance.getStartDate();
		endDate = new Date();
		start = 0;
		count = 10;
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetUserUpdatesByIdC1() {
		try{
			Network network = linkedInConn.getUserUpdatesById(instance.getCurrentUser().getId(), 
					updateTypes, start, count, startDate, endDate);
			verify(network);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetUserUpdatesByIdC2() {
		try{
			Network network = linkedInConn.getUserUpdatesById(instance.getCurrentUser().getId(), 
					updateTypes, start, count, null, null);
			//if we are not providing start date/end date or update types, then we cannot query on the
			//particular update that we have posted. So in such cases, we are only checking for
			//network should not be asserted to null
			assertNotNull(network);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetUserUpdatesByIdC3() {
		try{
			Network network = linkedInConn.getUserUpdatesById(instance.getCurrentUser().getId(), 
					updateTypes, null, null, startDate, endDate);
			verify(network);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetUserUpdatesByIdC4() {
		try{
			Network network = linkedInConn.getUserUpdatesById(instance.getCurrentUser().getId(), 
					null, start, count, null, null);
			//if we are not providing start date/end date or update types, then we cannot query on the
			//particular update that we have posted. So in such cases, we are only checking for
			//network should not be asserted to null
			assertNotNull(network);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetUserUpdatesByIdC5() {
		try{
			Network network = linkedInConn.getUserUpdatesById(instance.getCurrentUser().getId(), 
					null, null, null, startDate, endDate);
			//if we are not providing start date/end date or update types, then we cannot query on the
			//particular update that we have posted. So in such cases, we are only checking for
			//network should not be asserted to null
			assertNotNull(network);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetUserUpdatesByIdC6() {
		try{
			Network network = linkedInConn.getUserUpdatesById(instance.getCurrentUser().getId(), 
					updateTypes, null, null, null, null);
			//if we are not providing start date/end date or update types, then we cannot query on the
			//particular update that we have posted. So in such cases, we are only checking for
			//network should not be asserted to null
			assertNotNull(network);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetUserUpdatesByIdC7() {
		try{
			Network network = linkedInConn.getUserUpdatesById(instance.getCurrentUser().getId(), 
					null, null, null, null, null);
			//if we are not providing start date/end date or update types, then we cannot query on the
			//particular update that we have posted. So in such cases, we are only checking for
			//network should not be asserted to null
			assertNotNull(network);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	private void verify(Network network){
		assertNotNull(network);
		
		boolean updatePresent = false;
		for(Update update: network.getUpdates().getUpdateList()){
			if(update.getUpdateContent() != null && update.getUpdateContent().getPerson() != null
					&& update.getUpdateContent().getPerson().getPersonActivities() != null 
					&& update.getUpdateContent().getPerson().getPersonActivities().getActivityList() != null){
				
				for(Activity activity: update.getUpdateContent().getPerson().getPersonActivities().getActivityList()){
					if(activity.getBody().equals(instance.getPostNetworkUpdateText())){
						updatePresent = true;
						break;
					}						
				}
			}
		}
		assertTrue(updatePresent);
	}
}
