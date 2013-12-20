package org.mule.modules.linkedin.automation.testcases;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.google.code.linkedinapi.schema.Job;
import com.google.code.linkedinapi.schema.impl.JobImpl;

public class PostJobTestCases extends LinkedInTestParent {

	@Category({SmokeTests.class})
	@Test
	public void testPostJob() {
		Job job = new JobImpl();
		job.setPartnerJobId("sample_partner_id");
		job.setContractId(1234L);
		job.setCustomerJobCode("sample customer job code");
		linkedInConn.postJob(job);
	}
}
