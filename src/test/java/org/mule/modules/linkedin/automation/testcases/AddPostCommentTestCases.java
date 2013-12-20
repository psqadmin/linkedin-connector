package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Comment;
import com.google.code.linkedinapi.schema.Comments;

public class AddPostCommentTestCases extends LinkedInTestParent {

	private String commentText;
	private String commentId;
	
	@Category({SmokeTests.class})
	@Test
	public void testAddPostComment() {
		try{
			String randomText = instance.generateRandomText();
			commentText = "Sample group post comment: " + randomText;
			linkedInConn.addPostComment(instance.getDefaultPost().getId(), commentText);
			instance.sleep();
			
			boolean isCommentPresent = false;
			Comments comments = linkedInConn.getPostComments(instance.getDefaultPost().getId(), null, null, null);
			for(Comment comment:comments.getCommentList()){
				if(comment.getText().equals(commentText)){
					isCommentPresent = true;
					commentId = comment.getId();
				}
			}
			
			assertTrue(isCommentPresent);
			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@After
	public void tearDown(){
		linkedInConn.deletePostComment(commentId);
		instance.sleep();
	}
}
