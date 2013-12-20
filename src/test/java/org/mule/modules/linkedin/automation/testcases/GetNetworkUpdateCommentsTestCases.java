package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.UpdateComment;
import com.google.code.linkedinapi.schema.UpdateComments;

public class GetNetworkUpdateCommentsTestCases extends LinkedInTestParent{
	@Category({SmokeTests.class})
	@Test
	public void testGetNetworkUpdateComments() {
		try{
			boolean postCommentSuccess = false;
			UpdateComments updateComments = linkedInConn.getNetworkUpdateComments(instance.getNetworkUpdateId());
			assertNotNull(updateComments);	
			for(UpdateComment updateComment: updateComments.getUpdateCommentList()){
				if(updateComment.getComment().equals(instance.getPostNetworkUpdateCommentText())){
					postCommentSuccess = true;
				}
			}
			assertTrue(postCommentSuccess);			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
}
