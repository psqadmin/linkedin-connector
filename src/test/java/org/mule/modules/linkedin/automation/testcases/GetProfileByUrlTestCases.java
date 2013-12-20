package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.client.enumeration.ProfileType;
import com.google.code.linkedinapi.schema.Person;


public class GetProfileByUrlTestCases extends LinkedInTestParent{

	@Category({SmokeTests.class})
	@Test
	public void testGetProfileByUrlC1() {
		try{			
			Person person = linkedInConn.getProfileByUrl(instance.getCurrentUser().getPublicProfileUrl(), 
					ProfileType.STANDARD, null);
			
			assertEquals(instance.getCurrentUser().getFirstName(),person.getFirstName());
			assertEquals(instance.getCurrentUser().getLastName(),person.getLastName());
			assertEquals(instance.getCurrentUser().getHeadline(),person.getHeadline());
			
			//we have not asked for this parameter in the output, so should be null
			assertNull(person.getDateOfBirth());
			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetProfileByUrlC2() {
		try{			
			Person person = linkedInConn.getProfileByUrl(instance.getCurrentUser().getPublicProfileUrl(), 
					ProfileType.STANDARD, instance.getProfileFieldList());
			
			assertEquals(instance.getCurrentUser().getFirstName(),person.getFirstName());
			assertEquals(instance.getCurrentUser().getLastName(),person.getLastName());
			assertEquals(instance.getCurrentUser().getHeadline(),person.getHeadline());
			
			//we have not asked for this parameter in the output, so should be null
			assertNull(person.getDateOfBirth());
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
