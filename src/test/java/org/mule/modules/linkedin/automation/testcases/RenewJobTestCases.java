package org.mule.modules.linkedin.automation.testcases;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RenewJobTestCases extends LinkedInTestParent {
	@Category({SmokeTests.class})
	@Test
	public void testRenewJob() {
		String partnerJobId = "sample_partner_id"; 
		Long contractId = 1234L;
		
		linkedInConn.renewJob(partnerJobId, Long.toString(contractId));
	}
}
