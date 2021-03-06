package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.linkedin.enumeration.LocalVisibilityType;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.client.enumeration.NetworkUpdateType;
import com.google.code.linkedinapi.schema.Network;
import com.google.code.linkedinapi.schema.Person;
import com.google.code.linkedinapi.schema.Update;

public class ReshareTestCases extends LinkedInTestParent {
	@Category({SmokeTests.class})
	@Test
	public void testReShare() {
		try{
			String randomText = instance.generateRandomText();
			String updatedCommentText = "Mulesoft Post Re-Share Comment Text " + randomText;
			linkedInConn.reShare(instance.getShareId(), updatedCommentText, LocalVisibilityType.ANYONE);
			instance.setCommentText(updatedCommentText);
			
			instance.sleepForPostShare();
			
			List<NetworkUpdateType> updateTypes = new ArrayList<NetworkUpdateType>();
			updateTypes.add(NetworkUpdateType.SHARED_ITEM);
			Date endDate = new Date();
			Network network = linkedInConn.getUserUpdates(updateTypes, null, null, instance.getStartDate(), endDate);
			assertNotNull(network);
			
			boolean postSharePresent = false;
			for(Update update: network.getUpdates().getUpdateList()){
				Person p = update.getUpdateContent().getPerson();
				if(p.getCurrentShare() != null){
					if(p.getCurrentShare().getComment().equalsIgnoreCase(updatedCommentText)){
						postSharePresent = true;
					}
						
				}
			}
			assertTrue(postSharePresent);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
