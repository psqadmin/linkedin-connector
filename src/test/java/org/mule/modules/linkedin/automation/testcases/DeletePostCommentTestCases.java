package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Comment;
import com.google.code.linkedinapi.schema.Comments;

public class DeletePostCommentTestCases extends LinkedInTestParent {
	
	private String commentId;
	private String commentText;
	
	@Before
	public void setup(){
		String randomText = instance.generateRandomText();
		commentText = "Sample group post comment: " + randomText;
		linkedInConn.addPostComment(instance.getDefaultPost().getId(), commentText);
		instance.sleep();
		
		Comments comments = linkedInConn.getPostComments(instance.getDefaultPost().getId(), null, null, null);
		for(Comment comment:comments.getCommentList()){
			if(comment.getText().equals(commentText)){
				commentId = comment.getId();
			}
		}
	}

	@Category({SmokeTests.class})
	@Test
	public void testDeletePostComment() {
		try{
			linkedInConn.deletePostComment(commentId);
			instance.sleep();
		
			boolean isCommentPresent = false;
			Comments comments = linkedInConn.getPostComments(instance.getDefaultPost().getId(), null, null, null);
			for(Comment comment:comments.getCommentList()){
				if(comment.getText().equals(commentText)){
					isCommentPresent = true;
				}
			}
			
			assertFalse(isCommentPresent);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
