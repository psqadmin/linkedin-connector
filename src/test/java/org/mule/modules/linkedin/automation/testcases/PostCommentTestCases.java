package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.UpdateComment;
import com.google.code.linkedinapi.schema.UpdateComments;

public class PostCommentTestCases extends LinkedInTestParent {
	@Category({SmokeTests.class})
	@Test
	public void testPostComment() {
		try{
			String commentText = "Sample post comment from within test " + instance.generateRandomText();
			linkedInConn.postComment(instance.getNetworkUpdateId(), commentText);	
			instance.sleep();
			
			boolean postCommentSuccess = false;
			UpdateComments updateComments = linkedInConn.getNetworkUpdateComments(instance.getNetworkUpdateId());
			for(UpdateComment updateComment: updateComments.getUpdateCommentList()){
				if(updateComment.getComment().equals(commentText)){
					postCommentSuccess = true;
				}
			}
			assertTrue(postCommentSuccess);	
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
