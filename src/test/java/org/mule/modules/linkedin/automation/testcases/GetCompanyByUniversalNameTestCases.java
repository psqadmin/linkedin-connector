package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Company;

public class GetCompanyByUniversalNameTestCases extends LinkedInTestParent{
	
	
	@Category({SmokeTests.class})
	@Test
	public void testGetCompanyByUniversalNameC1() {
		try{
			Company company = linkedInConn.getCompanyByUniversalName(instance.getCompanyUniversalName(), 
					instance.getCompanyFieldList());
			assertEquals(company.getId(), instance.getDefaultCompany().getId());
			assertEquals(company.getName(), instance.getDefaultCompany().getName());
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetCompanyByUniversalNameC2() {
		try{
			Company company = linkedInConn.getCompanyByUniversalName(instance.getCompanyUniversalName(), null);
			assertEquals(company.getId(), instance.getDefaultCompany().getId());
			assertEquals(company.getName(), instance.getDefaultCompany().getName());
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
}

