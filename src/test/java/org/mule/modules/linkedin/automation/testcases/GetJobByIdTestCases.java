package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Job;

public class GetJobByIdTestCases extends LinkedInTestParent {
	
	@Category({SmokeTests.class})
	@Test
	public void testGetJobByIdC1() {
		try{
			Job job = linkedInConn.getJobById(instance.getJobId(), instance.getJobFieldList());
			assertEquals(job.getId(), instance.getJobId());
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetJobByIdC2() {
		try{
			Job job = linkedInConn.getJobById(instance.getJobId(), null);
			assertEquals(job.getId(), instance.getJobId());
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
