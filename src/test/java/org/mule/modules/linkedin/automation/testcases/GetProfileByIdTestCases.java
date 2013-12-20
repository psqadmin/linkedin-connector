package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import org.junit.experimental.categories.Category;
import org.mule.modules.linkedin.LinkedInConnectorTest;
import org.mule.modules.tests.ConnectorTestUtils;
import com.google.code.linkedinapi.schema.Person;

public class GetProfileByIdTestCases extends LinkedInTestParent{

	@Category({SmokeTests.class})
	@Test
	public void testGetProfileByIdC1() {
		try{
			Person person = linkedInConn.getProfileById(instance.getCurrentUser().getId(), null);
			//As per LinkedIn API's by default, firstname, lastname should be returned if we
			//are not specifying any field selector
			assertEquals(instance.getCurrentUser().getFirstName(), person.getFirstName());
			assertEquals(instance.getCurrentUser().getLastName(), person.getLastName());
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetProfileByIdC2() {
		try{
			Person person = linkedInConn.getProfileById(instance.getCurrentUser().getId(), instance.getProfileFieldList());
			
			assertEquals(instance.getCurrentUser().getFirstName(), person.getFirstName());
			assertEquals(instance.getCurrentUser().getLastName(), person.getLastName());
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
