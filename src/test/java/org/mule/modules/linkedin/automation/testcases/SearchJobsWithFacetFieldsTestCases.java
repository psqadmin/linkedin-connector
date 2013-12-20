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
import com.google.code.linkedinapi.client.enumeration.JobField;
import com.google.code.linkedinapi.client.enumeration.SearchParameter;
import com.google.code.linkedinapi.client.enumeration.SearchSortOrder;
import com.google.code.linkedinapi.schema.JobSearch;

public class SearchJobsWithFacetFieldsTestCases extends LinkedInTestParent {
	
	private Map<SearchParameter, String> searchParameters;
	private List<JobField> jobFields;
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
		
		jobFields = instance.getJobFieldList();
		start = instance.getStart();
		count = instance.getCount();
		searchSortOrder = SearchSortOrder.RELEVANCE;
		
		facets = new HashMap<LocalFacetType, String>();
		facets.put(LocalFacetType.NETWORK, RelationshipCodes.FIRST_DEGREE_CONNECTIONS);
		
		facetFields = new ArrayList<FacetField>();
		facetFields.add(FacetField.BUCKET_CODE);
		facetFields.add(FacetField.BUCKET_NAME);
	}
	
	/*@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetFieldsC1() {
		JobSearch peopleSearch = linkedInConn.searchJobsWithFacetFields(searchParameters, jobFields, 
				facetFields, start, count, searchSortOrder, facets);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetFieldsC2() {
		JobSearch peopleSearch = linkedInConn.searchJobsWithFacetFields(searchParameters, jobFields, 
				facetFields, start, count, null, facets);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetFieldsC3() {
		JobSearch peopleSearch = linkedInConn.searchJobsWithFacetFields(searchParameters, jobFields, 
				facetFields, start, count, searchSortOrder, null);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetFieldsC4() {
		JobSearch peopleSearch = linkedInConn.searchJobsWithFacetFields(searchParameters, jobFields, 
				facetFields, null, null, searchSortOrder, facets);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetFieldsC5() {
		JobSearch peopleSearch = linkedInConn.searchJobsWithFacetFields(searchParameters, jobFields, 
				facetFields, start, count, null, null);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetFieldsC6() {
		JobSearch peopleSearch = linkedInConn.searchJobsWithFacetFields(searchParameters, jobFields, 
				facetFields, null, null, null, facets);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetFieldsC7() {
		JobSearch peopleSearch = linkedInConn.searchJobsWithFacetFields(searchParameters, jobFields, 
				facetFields, null, null, searchSortOrder, null);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetFieldsC8() {
		JobSearch peopleSearch = linkedInConn.searchJobsWithFacetFields(searchParameters, jobFields, 
				facetFields, null, null, null, null);
		assertNotNull(peopleSearch);
	}*/
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetFieldsC1() {
		JobSearch peopleSearch = linkedInConn.searchJobsWithFacetFields(searchParameters, jobFields, 
				facetFields, start, count, facets);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetFieldsC2() {
		JobSearch peopleSearch = linkedInConn.searchJobsWithFacetFields(searchParameters, jobFields, 
				facetFields, start, count, null);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetFieldsC3() {
		JobSearch peopleSearch = linkedInConn.searchJobsWithFacetFields(searchParameters, jobFields, 
				facetFields, null, null, facets);
		assertNotNull(peopleSearch);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsWithFacetFieldsC4() {
		JobSearch peopleSearch = linkedInConn.searchJobsWithFacetFields(searchParameters, jobFields, 
				facetFields, null, null, null);
		assertNotNull(peopleSearch);
	}	
}
