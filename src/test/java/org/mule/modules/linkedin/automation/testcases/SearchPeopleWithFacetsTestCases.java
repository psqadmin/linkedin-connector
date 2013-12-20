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
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.enumeration.SearchParameter;
import com.google.code.linkedinapi.client.enumeration.SearchSortOrder;
import com.google.code.linkedinapi.schema.People;

public class SearchPeopleWithFacetsTestCases extends LinkedInTestParent{

	private Map<SearchParameter, String> searchParameters;
	private List<ProfileField> profileFields;
	private Integer start;
	private Integer count;
	private SearchSortOrder searchSortOrder;
	private Map<LocalFacetType, String> facets;
	
	@Before
	public void setup(){
		searchParameters = new HashMap<SearchParameter, String>();		
		searchParameters.put(SearchParameter.CURRENT_COMPANY, "Mulesoft");
		searchParameters.put(SearchParameter.TITLE, "Engineer");
		
		profileFields = instance.getProfileFieldList();
		start = instance.getStart();
		count = instance.getCount();
		searchSortOrder = SearchSortOrder.RELEVANCE;
		
		facets = new HashMap<LocalFacetType, String>();
		facets.put(LocalFacetType.NETWORK, RelationshipCodes.FIRST_DEGREE_CONNECTIONS);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetsC1() {
		People people = linkedInConn.searchPeopleWithFacets(searchParameters, profileFields, start, 
				count, searchSortOrder, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetsC2() {
		People people = linkedInConn.searchPeopleWithFacets(searchParameters, profileFields, start, 
				count, null, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetsC3() {
		People people = linkedInConn.searchPeopleWithFacets(searchParameters, profileFields, null, 
				null, searchSortOrder, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetsC4() {
		People people = linkedInConn.searchPeopleWithFacets(searchParameters, null, start, 
				count, searchSortOrder, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetsC5() {
		People people = linkedInConn.searchPeopleWithFacets(searchParameters, profileFields, null, 
				null, null, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetsC6() {
		People people = linkedInConn.searchPeopleWithFacets(searchParameters, null, start, 
				count, null, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetsC7() {
		People people = linkedInConn.searchPeopleWithFacets(searchParameters, null, null, 
				null, searchSortOrder, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetsC8() {
		People people = linkedInConn.searchPeopleWithFacets(searchParameters, null, null, 
				null, null, facets);
		assertNotNull(people);
	}
}
