package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.client.enumeration.NetworkUpdateType;
import com.google.code.linkedinapi.schema.Activity;
import com.google.code.linkedinapi.schema.Network;
import com.google.code.linkedinapi.schema.Update;

public class PostNetworkUpdateTestCases extends LinkedInTestParent {
	@Category({SmokeTests.class})
	@Test
	public void testPostNetworkUpdate() {
		try{
			String updateText = "Sample post update from within test " + instance.generateRandomText();
			
			//exact start date may not match, so subtracting 2 minutes 
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, -2);
			Date testStartDate = cal.getTime();
			
			linkedInConn.postNetworkUpdate(updateText);	
			instance.sleep();
			
			//retrieve the update key of the posted network update
			List<NetworkUpdateType> updateTypes = new ArrayList<NetworkUpdateType>();
			updateTypes.add(NetworkUpdateType.APPLICATION_UPDATE);
			Date endDate = new Date();
			
			boolean postSuccessful = false;
			Network network = linkedInConn.getUserUpdatesById(instance.getCurrentUser().getId(), 
					updateTypes, null, null, testStartDate, endDate);
			for(Update update: network.getUpdates().getUpdateList()){
				for(Activity activity: update.getUpdateContent().getPerson().getPersonActivities().getActivityList()){
					if(activity.getBody().equals(updateText)){
						postSuccessful = true;
						break;
					}						
				}
			}
			assertTrue(postSuccessful);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
