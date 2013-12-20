package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.client.enumeration.CommentField;
import com.google.code.linkedinapi.client.enumeration.GroupMembershipField;
import com.google.code.linkedinapi.schema.Comment;
import com.google.code.linkedinapi.schema.Comments;

public class GetPostCommentsTestCases extends LinkedInTestParent {
	
	private String postId;
	private List<CommentField> list;
	private Integer start;
	private Integer count;	
	
	@Before
	public void setup(){
		postId = instance.getDefaultPost().getId();
		list = instance.getCommentFieldList();
		start = instance.getStart();
		count = instance.getCount();
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostCommentsC1() {
		try{			
			Comments comments = linkedInConn.getPostComments(postId, list, start, count);
			verify(comments);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostCommentsC2() {
		try{			
			Comments comments = linkedInConn.getPostComments(postId, null, start, count);
			verify(comments);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostCommentsC3() {
		try{			
			Comments comments = linkedInConn.getPostComments(postId, list, null, null);
			verify(comments);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostCommentsC4() {
		try{			
			Comments comments = linkedInConn.getPostComments(postId, null, null, null);
			verify(comments);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	private void verify(Comments comments){
		boolean isCommentPresent = false;
		for(Comment comment:comments.getCommentList()){
			if(comment.getText().equals(instance.getGroupPostComment())){
				isCommentPresent = true;
				break;
			}
		}		
		assertTrue(isCommentPresent);
	}
}
