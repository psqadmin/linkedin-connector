package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.schema.Like;
import com.google.code.linkedinapi.schema.Likes;

public class UnlikePostTestCases extends LinkedInTestParent{
	@Category({SmokeTests.class})
	@Test
	public void testLikePost() {
		try{
			boolean postLikeSuccess = false;
			linkedInConn.unlikePost(instance.getNetworkUpdateId());
			instance.sleep();
			
			Likes likes = linkedInConn.getNetworkUpdateLikes(instance.getNetworkUpdateId());
			for(Like like: likes.getLikeList()){
				if(like.getPerson().getId().equals(instance.getCurrentUser().getId())){
					postLikeSuccess = true;
				}
			}
			assertFalse(postLikeSuccess);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}

	@After
	public void tearDown(){
		linkedInConn.likePost(instance.getNetworkUpdateId());
	}
}
