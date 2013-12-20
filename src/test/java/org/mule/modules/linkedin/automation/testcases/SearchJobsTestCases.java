package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.google.code.linkedinapi.client.enumeration.JobField;
import com.google.code.linkedinapi.client.enumeration.SearchParameter;
import com.google.code.linkedinapi.client.enumeration.SearchSortOrder;
import com.google.code.linkedinapi.schema.Jobs;

public class SearchJobsTestCases extends LinkedInTestParent {
	
	private Map<SearchParameter, String> searchParameters;
	private List<JobField> jobFields;
	private Integer start;
	private Integer count;
	private SearchSortOrder searchSortOrder;
	
	@Before
	public void setup(){
		searchParameters = new HashMap<SearchParameter, String>();		
		searchParameters.put(SearchParameter.CURRENT_COMPANY, "Mulesoft");
		searchParameters.put(SearchParameter.TITLE, "Engineer");
		
		jobFields = instance.getJobFieldList();
		start = instance.getStart();
		count = instance.getCount();
		searchSortOrder = SearchSortOrder.RELEVANCE;
	}
	
	/*@Category({SmokeTests.class})
	@Test
	public void testSearchJobsC1() {
		Jobs people = linkedInConn.searchJobs(searchParameters, jobFields, 
				start, count, searchSortOrder);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsC2() {
		Jobs people = linkedInConn.searchJobs(searchParameters, jobFields, 
				start, count, null);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsC3() {
		Jobs people = linkedInConn.searchJobs(searchParameters, jobFields, 
				null, null, searchSortOrder);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsC4() {
		Jobs people = linkedInConn.searchJobs(searchParameters, null, 
				start, count, searchSortOrder);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsC5() {
		Jobs people = linkedInConn.searchJobs(searchParameters, jobFields, 
				null, null, null);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsC6() {
		Jobs people = linkedInConn.searchJobs(searchParameters, null, 
				start, count, null);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsC7() {
		Jobs people = linkedInConn.searchJobs(searchParameters, null, 
				null, null, searchSortOrder);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsC8() {
		Jobs people = linkedInConn.searchJobs(searchParameters, null, 
				null, null, null);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsC9() {
		Jobs people = linkedInConn.searchJobs(null, null, 
				null, null, null);
		assertNotNull(people);
	}
	*/
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsC1() {
		Jobs people = linkedInConn.searchJobs(searchParameters, jobFields, 
				start, count);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsC2() {
		Jobs people = linkedInConn.searchJobs(searchParameters, jobFields, 
				null, null);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsC3() {
		Jobs people = linkedInConn.searchJobs(searchParameters, null, 
				start, count);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsC4() {
		Jobs people = linkedInConn.searchJobs(searchParameters, null, 
				null, null);
		assertNotNull(people);
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testSearchJobsC5() {
		Jobs people = linkedInConn.searchJobs(null, null, 
				null, null);
		assertNotNull(people);
	}
}
