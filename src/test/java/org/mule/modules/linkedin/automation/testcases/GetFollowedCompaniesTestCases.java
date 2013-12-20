package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Companies;
import com.google.code.linkedinapi.schema.Company;

public class GetFollowedCompaniesTestCases extends LinkedInTestParent {
	
	@Before
	public void setup(){
		boolean isDefaultCompanyPresent = false;
		Companies companies = linkedInConn.getFollowedCompanies(null);
		for(Company company: companies.getCompanyList()){
			if(instance.getDefaultCompany().getId().equals(company.getId())
					&& instance.getDefaultCompany().getName().equals(company.getName())){
				isDefaultCompanyPresent = true;
			}
		}
		if(!isDefaultCompanyPresent)
			linkedInConn.followCompany(instance.getDefaultCompany().getId());
		
		instance.sleep();
	}
	
	
	@Category({SmokeTests.class})
	@Test
	public void testGetFollowedCompaniesC1() {
		try{
			Companies companies = linkedInConn.getFollowedCompanies(instance.getCompanyFieldList());
			verify(companies);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetFollowedCompaniesC2() {
		try{
			Companies companies = linkedInConn.getFollowedCompanies(null);
			verify(companies);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@After
	public void tearDown(){
		linkedInConn.unfollowCompany(instance.getDefaultCompany().getId());
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
