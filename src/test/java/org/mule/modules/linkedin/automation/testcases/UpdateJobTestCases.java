package org.mule.modules.linkedin.automation.testcases;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.google.code.linkedinapi.schema.Job;
import com.google.code.linkedinapi.schema.impl.JobImpl;

public class UpdateJobTestCases extends LinkedInTestParent {
	@Category({SmokeTests.class})
	@Test
	public void testUpdateJob() {
		String partnerJobId = "sample_partner_id"; 
		
		Job job = new JobImpl();
		job.setPartnerJobId(partnerJobId);
		job.setContractId(5678L);
		job.setCustomerJobCode("update sample customer job code");
		
		
		linkedInConn.updateJob(partnerJobId, job);
	}
}
