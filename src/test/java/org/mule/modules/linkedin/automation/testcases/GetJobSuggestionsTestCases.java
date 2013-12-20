package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Jobs;

public class GetJobSuggestionsTestCases extends LinkedInTestParent {
	
	@Category({SmokeTests.class})
	@Test
	public void testGetJobSuggestionsC1() {
		try{
			Jobs jobs = linkedInConn.getJobSuggestions(instance.getJobFieldList());
			assertNotNull(jobs);			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetJobSuggestionsC2() {
		try{
			Jobs jobs = linkedInConn.getJobSuggestions(null);
			assertNotNull(jobs);			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
