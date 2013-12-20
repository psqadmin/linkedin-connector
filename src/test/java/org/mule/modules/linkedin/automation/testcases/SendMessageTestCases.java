package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

public class SendMessageTestCases extends LinkedInTestParent {
	@Category({SmokeTests.class})
	@Test
	public void testSendMessage() {
		try{
			String randomText = instance.generateRandomText();
			List<String> recepientIds = new ArrayList<String>();
			recepientIds.add(instance.getOtherUser().getId());
			linkedInConn.sendMessage(recepientIds, "subject " + randomText, "message " + randomText);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
