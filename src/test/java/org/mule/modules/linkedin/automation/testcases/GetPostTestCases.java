package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Post;

public class GetPostTestCases extends LinkedInTestParent {
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostC1() {
		try{			
			Post post = linkedInConn.getPost(instance.getDefaultPost().getId(), instance.getPostFieldList());
			verify(post);			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostC2() {
		try{			
			Post post = linkedInConn.getPost(instance.getDefaultPost().getId(), null);
			verify(post);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	private void verify(Post post){
		boolean isPostPresent = false;
		if(post.getTitle().equals(instance.getGroupPostTitle())){
			isPostPresent = true;
		}		
		assertTrue(isPostPresent);
	}
}
