package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.linkedin.enumeration.LocalFacetType;

import com.google.code.linkedinapi.client.constant.RelationshipCodes;
import com.google.code.linkedinapi.client.enumeration.FacetField;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.enumeration.SearchParameter;
import com.google.code.linkedinapi.client.enumeration.SearchSortOrder;
import com.google.code.linkedinapi.schema.PeopleSearch;

public class SearchPeopleWithFacetFieldsTestCases extends LinkedInTestParent {
	
	private Map<SearchParameter, String> searchParameters;
	private List<ProfileField> profileFields;
	private Integer start;
	private Integer count;
	private SearchSortOrder searchSortOrder;
	private Map<LocalFacetType, String> facets;
	private List<FacetField> facetFields;
	
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
		
		facetFields = new ArrayList<FacetField>();
		facetFields.add(FacetField.BUCKET_CODE);
		facetFields.add(FacetField.BUCKET_NAME);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetFieldsC1() {
		PeopleSearch peopleSearch = linkedInConn.searchPeopleWithFacetFields(searchParameters, profileFields, 
				facetFields, start, count, searchSortOrder, facets);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetFieldsC2() {
		PeopleSearch peopleSearch = linkedInConn.searchPeopleWithFacetFields(searchParameters, profileFields, 
				facetFields, start, count, null, facets);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetFieldsC3() {
		PeopleSearch peopleSearch = linkedInConn.searchPeopleWithFacetFields(searchParameters, profileFields, 
				facetFields, start, count, searchSortOrder, null);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetFieldsC4() {
		PeopleSearch peopleSearch = linkedInConn.searchPeopleWithFacetFields(searchParameters, profileFields, 
				facetFields, null, null, searchSortOrder, facets);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetFieldsC5() {
		PeopleSearch peopleSearch = linkedInConn.searchPeopleWithFacetFields(searchParameters, profileFields, 
				facetFields, start, count, null, null);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetFieldsC6() {
		PeopleSearch peopleSearch = linkedInConn.searchPeopleWithFacetFields(searchParameters, profileFields, 
				facetFields, null, null, null, facets);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetFieldsC7() {
		PeopleSearch peopleSearch = linkedInConn.searchPeopleWithFacetFields(searchParameters, profileFields, 
				facetFields, null, null, searchSortOrder, null);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleWithFacetFieldsC8() {
		PeopleSearch peopleSearch = linkedInConn.searchPeopleWithFacetFields(searchParameters, profileFields, 
				facetFields, null, null, null, null);
		assertNotNull(peopleSearch);
	}
	
	

}
