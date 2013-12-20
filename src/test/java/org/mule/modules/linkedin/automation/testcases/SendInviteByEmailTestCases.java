package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

public class SendInviteByEmailTestCases extends LinkedInTestParent{
	@Category({SmokeTests.class})
	@Test
	public void testSendInviteByEmail() {
		try{
			String randomText = instance.generateRandomText();
			linkedInConn.sendInviteByEmail(instance.getRecepientEmailId(), 
					instance.getRecepientFirstName(), instance.getRecepientLastName(), 
					"subject " + randomText, "message " + randomText);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
