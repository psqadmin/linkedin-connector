package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Comment;

public class GetPostCommentTestCases extends LinkedInTestParent {
	
	//FIXME: Incorrect implementation in BaseLinkedInApiClient
	/*@Category({SmokeTests.class})
	@Test
	public void testGetPostCommentC1() {
		try{			
			Comment comment = linkedInConn.getPostComment(instance.getDefaultPostComment().getId(), 
					instance.getCommentFieldList());
			verify(comment);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostCommentC2() {
		try{			
			Comment comment = linkedInConn.getPostComment(instance.getDefaultPostComment().getId(), null);
			verify(comment);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	private void verify(Comment comment){
		boolean isCommentPresent = false;
		if(comment.getText().equals(instance.getGroupPostComment())){
			isCommentPresent = true;
		}		
		assertTrue(isCommentPresent);	
	}*/
}
