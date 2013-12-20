package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Company;

public class GetCompanyByIdTestCases extends LinkedInTestParent{
	
	@Category({SmokeTests.class})
	@Test
	public void testGetCompanyByIdC1() {
		try{
			Company company = linkedInConn.getCompanyById(instance.getDefaultCompany().getId(), 
					instance.getCompanyFieldList());
			assertEquals(company.getId(), instance.getDefaultCompany().getId());
			assertEquals(company.getName(), instance.getDefaultCompany().getName());
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetCompanyByIdC2() {
		try{
			Company company = linkedInConn.getCompanyById(instance.getDefaultCompany().getId(), null);
			assertEquals(company.getId(), instance.getDefaultCompany().getId());
			assertEquals(company.getName(), instance.getDefaultCompany().getName());
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
}
