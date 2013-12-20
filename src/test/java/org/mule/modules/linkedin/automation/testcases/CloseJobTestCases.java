package org.mule.modules.linkedin.automation.testcases;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CloseJobTestCases extends LinkedInTestParent {
	@Category({SmokeTests.class})
	@Test
	public void testCloseJob() {
		String partnerJobId = "sample_partner_id";		
		linkedInConn.closeJob(partnerJobId);
	}
}
