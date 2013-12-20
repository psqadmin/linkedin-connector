package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Post;

public class UnlikeGroupPostTestCases extends LinkedInTestParent{
	
	@Before
	public void setUp(){
		Post post = linkedInConn.getPost(instance.getDefaultPost().getId(), null);
		linkedInConn.likeGroupPost(post.getId());
		instance.sleep();
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testUnlikeGroupPost() {
		try{
			Post post = linkedInConn.getPost(instance.getDefaultPost().getId(), null);
			linkedInConn.likeGroupPost(post.getId());			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@After
	public void tearDown(){
		Post post = linkedInConn.getPost(instance.getDefaultPost().getId(), null);
		linkedInConn.unlikeGroupPost(post.getId());
	}	
}
