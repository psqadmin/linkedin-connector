package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Companies;
import com.google.code.linkedinapi.schema.Company;

public class GetCompaniesByEmailDomainTestCases extends LinkedInTestParent {
	
	@Category({SmokeTests.class})
	@Test
	public void testGetCompanyByUniversalNameC1() {
		try{
			Companies companies = linkedInConn.getCompaniesByEmailDomain(instance.getCompanyEmailDomain(),
					instance.getCompanyFieldList());
			verify(companies);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetCompanyByUniversalNameC2() {
		try{
			Companies companies = linkedInConn.getCompaniesByEmailDomain(instance.getCompanyEmailDomain(), null);
			verify(companies);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	private void verify(Companies companies){
		assertNotNull(companies);
		boolean isDefaultCompanyPresent = false;
		for(Company company: companies.getCompanyList()){
			if(instance.getDefaultCompany().getId().equals(company.getId())
					&& instance.getDefaultCompany().getName().equals(company.getName())){
				isDefaultCompanyPresent = true;
			}
		}
		assertTrue(isDefaultCompanyPresent);
	}
}
