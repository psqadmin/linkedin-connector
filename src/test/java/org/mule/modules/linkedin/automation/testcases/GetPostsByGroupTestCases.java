package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.linkedin.enumeration.LocalPostCategoryCode;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.client.enumeration.PostField;
import com.google.code.linkedinapi.client.enumeration.PostSortOrder;
import com.google.code.linkedinapi.schema.Post;
import com.google.code.linkedinapi.schema.Posts;

public class GetPostsByGroupTestCases extends LinkedInTestParent{
	
	private String groupId;
	private List<PostField> list;
	private Integer start;
	private Integer count;
	private PostSortOrder order;
	private LocalPostCategoryCode category;
	private Date modifiedSince;
	
	@Before
	public void setup(){
		groupId = instance.getDefaultGroup().getId();
		list = instance.getPostFieldList();
		start = instance.getStart();
		count = instance.getCount();
		order = PostSortOrder.RECENCY;
		category = LocalPostCategoryCode.DISCUSSION;
		modifiedSince = instance.getModifiedSince();
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostsByGroupC1() {
		try{			
			Posts posts = linkedInConn.getPostsByGroup(groupId, list, start, count, 
					order, category, modifiedSince);
			verify(posts);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostsByGroupC2() {
		try{			
			Posts posts = linkedInConn.getPostsByGroup(groupId, list, null, null, 
					null, category, modifiedSince);
			verify(posts);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostsByGroupC3() {
		try{			
			Posts posts = linkedInConn.getPostsByGroup(groupId, list, null, null, 
					null, category, null);
			verify(posts);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostsByGroupC4() {
		try{			
			Posts posts = linkedInConn.getPostsByGroup(groupId, list, null, null, 
					null, null, modifiedSince);
			verify(posts);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostsByGroupC5() {
		try{			
			Posts posts = linkedInConn.getPostsByGroup(groupId, null, null, null, 
					null, category, modifiedSince);
			verify(posts);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostsByGroupC6() {
		try{			
			Posts posts = linkedInConn.getPostsByGroup(groupId, list, null, null, 
					null, null, null);
			verify(posts);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostsByGroupC7() {
		try{			
			Posts posts = linkedInConn.getPostsByGroup(groupId, null, null, null, 
					null, category, null);
			verify(posts);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostsByGroupC8() {
		try{			
			Posts posts = linkedInConn.getPostsByGroup(groupId, null, null, null, 
					null, null, modifiedSince);
			verify(posts);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetPostsByGroupC9() {
		try{			
			Posts posts = linkedInConn.getPostsByGroup(groupId, null, null, null, 
					null, null, null);
			verify(posts);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	private void verify(Posts posts){
		boolean isPostPresent = false;
		for(Post post: posts.getPostList()){
			if(post.getTitle().equals(instance.getGroupPostTitle())){
				isPostPresent = true;
			}			
		}		
		assertTrue(isPostPresent);
	}
}
