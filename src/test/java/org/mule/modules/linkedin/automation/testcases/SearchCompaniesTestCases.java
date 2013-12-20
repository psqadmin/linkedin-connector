package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.google.code.linkedinapi.client.enumeration.CompanyField;
import com.google.code.linkedinapi.client.enumeration.SearchParameter;
import com.google.code.linkedinapi.client.enumeration.SearchSortOrder;
import com.google.code.linkedinapi.schema.Companies;

public class SearchCompaniesTestCases extends LinkedInTestParent {
	
	private Map<SearchParameter, String> searchParameters;
	private List<CompanyField> companyFields;
	private Integer start;
	private Integer count;
	private SearchSortOrder searchSortOrder;
	
	@Before
	public void setup(){
		searchParameters = new HashMap<SearchParameter, String>();		
		searchParameters.put(SearchParameter.CURRENT_COMPANY, "Mulesoft");
		searchParameters.put(SearchParameter.TITLE, "Engineer");
		
		companyFields = instance.getCompanyFieldList();
		start = instance.getStart();
		count = instance.getCount();
		searchSortOrder = SearchSortOrder.RELEVANCE;
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesC1() {
		Companies people = linkedInConn.searchCompanies(searchParameters, companyFields, 
				start, count, searchSortOrder);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesC2() {
		Companies people = linkedInConn.searchCompanies(searchParameters, companyFields, 
				start, count, null);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesC3() {
		Companies people = linkedInConn.searchCompanies(searchParameters, companyFields, 
				null, null, searchSortOrder);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesC4() {
		Companies people = linkedInConn.searchCompanies(searchParameters, null, 
				start, count, searchSortOrder);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesC5() {
		Companies people = linkedInConn.searchCompanies(searchParameters, companyFields, 
				null, null, null);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesC6() {
		Companies people = linkedInConn.searchCompanies(searchParameters, null, 
				start, count, null);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesC7() {
		Companies people = linkedInConn.searchCompanies(searchParameters, null, 
				null, null, searchSortOrder);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesC8() {
		Companies people = linkedInConn.searchCompanies(searchParameters, null, 
				null, null, null);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesC9() {
		Companies people = linkedInConn.searchCompanies(null, null, 
				null, null, null);
		assertNotNull(people);
	}
}
