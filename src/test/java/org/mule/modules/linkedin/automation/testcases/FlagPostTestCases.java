package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Post;
import com.google.code.linkedinapi.schema.Posts;

import com.google.code.linkedinapi.schema.PostCategoryCode;

public class FlagPostTestCases extends LinkedInTestParent {
	
	/*private String postId;
	
	@Before
	public void setup(){
		try{
			String randomText = instance.generateRandomText();
			String title = "Sample group post title: " + randomText;
			String summary = "Sample group post summary: " + randomText;
			linkedInConn.createPost(instance.getDefaultGroup().getId(), title, summary);
			
			Posts posts = linkedInConn.getPostsByGroup(instance.getDefaultGroup().getId(), 
					instance.getPostFieldList(), null, null, null, null, instance.getStartDate());
			instance.sleep();
			
			boolean isPostPresent = false;
			for(Post post: posts.getPostList()){
				if(post.getTitle().equals(title) &&
						post.getSummary().equals(summary)){
					isPostPresent = true;
					postId = post.getId();
					break;
				}			
			}
			
			assertTrue(isPostPresent);
			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testFlagPost() {
		try{
			linkedInConn.flagPost(postId, PostCategoryCode.JOB);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}*/
}
