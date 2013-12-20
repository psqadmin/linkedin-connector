package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.enumeration.SearchParameter;
import com.google.code.linkedinapi.client.enumeration.SearchSortOrder;
import com.google.code.linkedinapi.schema.People;

public class SearchPeopleTestCases extends LinkedInTestParent {
	
	private Map<SearchParameter, String> searchParameters;
	private List<ProfileField> profileFields;
	private Integer start;
	private Integer count;
	private SearchSortOrder searchSortOrder;
	
	@Before
	public void setup(){
		searchParameters = new HashMap<SearchParameter, String>();		
		searchParameters.put(SearchParameter.CURRENT_COMPANY, "Mulesoft");
		searchParameters.put(SearchParameter.TITLE, "Engineer");
		
		profileFields = instance.getProfileFieldList();
		start = instance.getStart();
		count = instance.getCount();
		searchSortOrder = SearchSortOrder.RELEVANCE;
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleC1() {
		People people = linkedInConn.searchPeople(searchParameters, profileFields, 
				start, count, searchSortOrder);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleC2() {
		People people = linkedInConn.searchPeople(searchParameters, profileFields, 
				start, count, null);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleC3() {
		People people = linkedInConn.searchPeople(searchParameters, profileFields, 
				null, null, searchSortOrder);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleC4() {
		People people = linkedInConn.searchPeople(searchParameters, null, 
				start, count, searchSortOrder);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleC5() {
		People people = linkedInConn.searchPeople(searchParameters, profileFields, 
				null, null, null);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleC6() {
		People people = linkedInConn.searchPeople(searchParameters, null, 
				start, count, null);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleC7() {
		People people = linkedInConn.searchPeople(searchParameters, null, 
				null, null, searchSortOrder);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleC8() {
		People people = linkedInConn.searchPeople(searchParameters, null, 
				null, null, null);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchPeopleC9() {
		People people = linkedInConn.searchPeople(null, null, 
				null, null, null);
		assertNotNull(people);
	}
}
