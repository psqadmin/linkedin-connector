package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Person;

public class GetProfileForCurrentUserTestCases extends LinkedInTestParent{
	
	@Category({SmokeTests.class})
	@Test
	public void testGetProfileForCurrentUserC1() {
		try {
			Person person = linkedInConn.getProfileForCurrentUser(null);
			assertNotNull(person);
		} catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
		
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetProfileForCurrentUserC2() {
		try {
			Person person = linkedInConn.getProfileForCurrentUser(instance.getProfileFieldList());
			assertNotNull(person);
		} catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}		
	}
}
