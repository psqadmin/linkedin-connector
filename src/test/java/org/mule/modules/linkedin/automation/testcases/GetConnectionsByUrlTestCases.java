package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.client.enumeration.ConnectionModificationType;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.schema.Connections;
import com.google.code.linkedinapi.schema.Person;

public class GetConnectionsByUrlTestCases extends LinkedInTestParent {
	
	private List<ProfileField> list;
	private Integer start;
	private Integer count;
	private Date modifiedSince;
	private ConnectionModificationType modificationType;
	
	@Before
	public void setup(){
		list = instance.getProfileFieldList();
		
		modifiedSince = instance.getModifiedSince(); 
		
		start = instance.getStart();
		count = instance.getCount();
		
		modificationType = ConnectionModificationType.ALL;
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetConnectionsByUrlC1() {
		try{
			Connections connectionById = linkedInConn.getConnectionsByUrl(instance.getCurrentUser().getPublicProfileUrl(), 
					list, start, count, modifiedSince, modificationType);
			verify(connectionById);	
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetConnectionsByUrlC2() {
		try{
			Connections connectionById = linkedInConn.getConnectionsByUrl(instance.getCurrentUser().getPublicProfileUrl(), 
					list, null, null, modifiedSince, modificationType);
			//default start and count value is 0 and 10. In our verify function, the comparable connection 
			//was retrieved using start as 0 and count as 10
			verify(connectionById);	
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetConnectionsByUrlC3() {
		try{
			Connections connectionById = linkedInConn.getConnectionsByUrl(instance.getCurrentUser().getPublicProfileUrl(), 
					list, start, count, null, null);
			assertNotNull(connectionById);	
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetConnectionsByUrlC4() {
		try{
			Connections connectionById = linkedInConn.getConnectionsByUrl(instance.getCurrentUser().getPublicProfileUrl(), 
					null, start, count, modifiedSince, modificationType);
			assertNotNull(connectionById);	
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetConnectionsByUrlC5() {
		try{
			Connections connectionById = linkedInConn.getConnectionsByUrl(instance.getCurrentUser().getPublicProfileUrl(), 
					null, null, null, modifiedSince, modificationType);
			assertNotNull(connectionById);	
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetConnectionsByUrlC6() {
		try{
			Connections connectionById = linkedInConn.getConnectionsByUrl(instance.getCurrentUser().getPublicProfileUrl(), 
					null, start, count, null, null);
			assertNotNull(connectionById);	
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetConnectionsByUrlC7() {
		try{
			Connections connectionById = linkedInConn.getConnectionsByUrl(instance.getCurrentUser().getPublicProfileUrl(), 
					list, null, null, null, null);
			assertNotNull(connectionById);	
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetConnectionsByUrlC8() {
		try{
			Connections connectionById = linkedInConn.getConnectionsByUrl(instance.getCurrentUser().getPublicProfileUrl(), 
					null, null, null, null, null);
			assertNotNull(connectionById);	
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	private void verify(Connections connectionById){
		assertEquals(connectionById.getPersonList().size(), 
				instance.getCurrentUserConnections().getPersonList().size());
		List<String> actualIds = new ArrayList<String>();
		for(Person person: connectionById.getPersonList()){
			actualIds.add(person.getId());
		}
		List<String> expectedIds = new ArrayList<String>();
		for(Person person: instance.getCurrentUserConnections().getPersonList()){
			expectedIds.add(person.getId());
		}
		assertTrue(actualIds.containsAll(expectedIds));
	}

}
