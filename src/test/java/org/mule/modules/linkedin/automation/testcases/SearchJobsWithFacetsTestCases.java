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
import com.google.code.linkedinapi.client.enumeration.JobField;
import com.google.code.linkedinapi.client.enumeration.SearchParameter;
import com.google.code.linkedinapi.client.enumeration.SearchSortOrder;
import com.google.code.linkedinapi.schema.Jobs;

public class SearchJobsWithFacetsTestCases extends LinkedInTestParent{

	private Map<SearchParameter, String> searchParameters;
	private List<JobField> jobFields;
	private Integer start;
	private Integer count;
	private SearchSortOrder searchSortOrder;
	private Map<LocalFacetType, String> facets;
	
	@Before
	public void setup(){
		searchParameters = new HashMap<SearchParameter, String>();		
		searchParameters.put(SearchParameter.CURRENT_COMPANY, "Mulesoft");
		searchParameters.put(SearchParameter.TITLE, "Engineer");
		
		jobFields = instance.getJobFieldList();
		start = instance.getStart();
		count = instance.getCount();
		searchSortOrder = SearchSortOrder.RELEVANCE;
		
		facets = new HashMap<LocalFacetType, String>();
		facets.put(LocalFacetType.NETWORK, RelationshipCodes.FIRST_DEGREE_CONNECTIONS);
	}
	
	/*@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetsC1() {
		Jobs people = linkedInConn.searchJobsWithFacets(searchParameters, jobFields, start, 
				count, searchSortOrder, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetsC2() {
		Jobs people = linkedInConn.searchJobsWithFacets(searchParameters, jobFields, start, 
				count, null, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetsC3() {
		Jobs people = linkedInConn.searchJobsWithFacets(searchParameters, jobFields, null, 
				null, searchSortOrder, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetsC4() {
		Jobs people = linkedInConn.searchJobsWithFacets(searchParameters, null, start, 
				count, searchSortOrder, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetsC5() {
		Jobs people = linkedInConn.searchJobsWithFacets(searchParameters, jobFields, null, 
				null, null, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetsC6() {
		Jobs people = linkedInConn.searchJobsWithFacets(searchParameters, null, start, 
				count, null, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetsC7() {
		Jobs people = linkedInConn.searchJobsWithFacets(searchParameters, null, null, 
				null, searchSortOrder, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetsC8() {
		Jobs people = linkedInConn.searchJobsWithFacets(searchParameters, null, null, 
				null, null, facets);
		assertNotNull(people);
	}*/
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetsC1() {
		Jobs people = linkedInConn.searchJobsWithFacets(searchParameters, jobFields, start, 
				count, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetsC2() {
		Jobs people = linkedInConn.searchJobsWithFacets(searchParameters, jobFields, null, 
				null, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetsC3() {
		Jobs people = linkedInConn.searchJobsWithFacets(searchParameters, null, start, 
				count, facets);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetsC4() {
		Jobs people = linkedInConn.searchJobsWithFacets(searchParameters, null, null, 
				null, facets);
		assertNotNull(people);
	}
}
