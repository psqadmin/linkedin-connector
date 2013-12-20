package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.linkedin.enumeration.LocalFacetType;

import com.google.code.linkedinapi.client.constant.RelationshipCodes;
import com.google.code.linkedinapi.client.enumeration.CompanyField;
import com.google.code.linkedinapi.client.enumeration.SearchParameter;
import com.google.code.linkedinapi.client.enumeration.SearchSortOrder;
import com.google.code.linkedinapi.schema.Companies;

public class SearchCompaniesWithFacetsTestCases extends LinkedInTestParent{

	private Map<SearchParameter, String> searchParameters;
	private List<CompanyField> companyFields;
	private Integer start;
	private Integer count;
	private SearchSortOrder searchSortOrder;
	private Map<LocalFacetType, String> facets;
	
	@Before
	public void setup(){
		searchParameters = new HashMap<SearchParameter, String>();		
		searchParameters.put(SearchParameter.CURRENT_COMPANY, "Mulesoft");
		searchParameters.put(SearchParameter.TITLE, "Engineer");
		
		companyFields = instance.getCompanyFieldList();
		start = instance.getStart();
		count = instance.getCount();
		searchSortOrder = SearchSortOrder.RELEVANCE;
		
		facets = new HashMap<LocalFacetType, String>();
		facets.put(LocalFacetType.NETWORK, RelationshipCodes.FIRST_DEGREE_CONNECTIONS);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesWithFacetsC1() {
		Companies people = linkedInConn.searchCompaniesWithFacets(searchParameters, companyFields, start, 
				count, searchSortOrder, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesWithFacetsC2() {
		Companies people = linkedInConn.searchCompaniesWithFacets(searchParameters, companyFields, start, 
				count, null, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesWithFacetsC3() {
		Companies people = linkedInConn.searchCompaniesWithFacets(searchParameters, companyFields, null, 
				null, searchSortOrder, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesWithFacetsC4() {
		Companies people = linkedInConn.searchCompaniesWithFacets(searchParameters, null, start, 
				count, searchSortOrder, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesWithFacetsC5() {
		Companies people = linkedInConn.searchCompaniesWithFacets(searchParameters, companyFields, null, 
				null, null, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesWithFacetsC6() {
		Companies people = linkedInConn.searchCompaniesWithFacets(searchParameters, null, start, 
				count, null, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesWithFacetsC7() {
		Companies people = linkedInConn.searchCompaniesWithFacets(searchParameters, null, null, 
				null, searchSortOrder, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchCompaniesWithFacetsC8() {
		Companies people = linkedInConn.searchCompaniesWithFacets(searchParameters, null, null, 
				null, null, facets);
		assertNotNull(people);
	}
}
