package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Companies;

public class GetSuggestedCompaniesTestCases extends LinkedInTestParent {
	
	@Category({SmokeTests.class})
	@Test
	public void testGetSuggestedCompaniesC1() {
		try{
			Companies companies = linkedInConn.getSuggestedCompanies(instance.getCompanyFieldList());
			assertNotNull(companies);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetSuggestedCompaniesC2() {
		try{
			Companies companies = linkedInConn.getSuggestedCompanies(null);
			assertNotNull(companies);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
