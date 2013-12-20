package org.mule.modules.linkedin.automation.testcases;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.google.code.linkedinapi.schema.Post;
import com.google.code.linkedinapi.schema.Posts;
import static org.junit.Assert.assertFalse;

public class DeletePostTestCases extends LinkedInTestParent {

	private String postId;
	private String title;
	private String summary;
	
	@Before
	public void setup(){
		String randomText = instance.generateRandomText();
		title = "Sample group post title: " + randomText;
		summary = "Sample group post summary: " + randomText;
		linkedInConn.createPost(instance.getDefaultGroup().getId(), title, summary);
		instance.sleep();
		
		Posts posts = linkedInConn.getPostsByGroup(instance.getDefaultGroup().getId(), instance.getPostFieldList(), 
				null, null, null, null, instance.getStartDate());
		
		for(Post post: posts.getPostList()){
			if(post.getTitle().equals(title) &&
					post.getSummary().equals(summary)){
				postId = post.getId();
				break;
			}			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testDeletePost() {
		linkedInConn.deletePost(postId);
		instance.sleep();
		
		Posts posts = linkedInConn.getPostsByGroup(instance.getDefaultGroup().getId(), instance.getPostFieldList(), null, 
				null, null, null, instance.getStartDate());
		
		boolean isPostPresent = false;
		for(Post post: posts.getPostList()){
			if(post.getTitle().equals(title) &&
					post.getSummary().equals(summary)){
				isPostPresent = true;
				break;
			}			
		}
		
		assertFalse(isPostPresent);
	}
}
