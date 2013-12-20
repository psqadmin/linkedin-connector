package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Group;

public class GetGroupByIdTestCases extends LinkedInTestParent{

	@Category({SmokeTests.class})
	@Test
	public void testGetGroupByIdC1() {
		try{
			Group group = linkedInConn.getGroupById(instance.getDefaultGroup().getId(), null);
			assertEquals(group.getId(), instance.getDefaultGroup().getId());
			assertEquals(group.getName(), instance.getDefaultGroup().getName());
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetGroupByIdC2() {
		try{
			Group group = linkedInConn.getGroupById(instance.getDefaultGroup().getId(), instance.getGroupFieldList());
			assertEquals(group.getId(), instance.getDefaultGroup().getId());
			assertEquals(group.getName(), instance.getDefaultGroup().getName());
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}

