package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.client.enumeration.ConnectionModificationType;
import com.google.code.linkedinapi.client.enumeration.NetworkUpdateType;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.schema.Connections;

public class GetConnectionsForCurrentUserTestCases  extends LinkedInTestParent{
	
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
	public void testGetConnectionsForCurrentUserC1() {
		try {
			Connections connection = linkedInConn.getConnectionsForCurrentUser(list, 
					start, count, modifiedSince, modificationType);
			assertNotNull(connection);		
		} catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
		
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetConnectionsForCurrentUserC2() {
		try {
			Connections connection = linkedInConn.getConnectionsForCurrentUser(list, 
					null, null, modifiedSince, modificationType);
			assertNotNull(connection);		
		} catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
		
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetConnectionsForCurrentUserC3() {
		try {
			Connections connection = linkedInConn.getConnectionsForCurrentUser(list, 
					start, count, null, null);
			assertNotNull(connection);		
		} catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
		
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetConnectionsForCurrentUserC4() {
		try {
			Connections connection = linkedInConn.getConnectionsForCurrentUser(null, 
					start, count, modifiedSince, modificationType);
			assertNotNull(connection);		
		} catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
		
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetConnectionsForCurrentUserC5() {
		try {
			Connections connection = linkedInConn.getConnectionsForCurrentUser(null, 
					start, count, null, null);
			assertNotNull(connection);		
		} catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
		
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetConnectionsForCurrentUserC6() {
		try {
			Connections connection = linkedInConn.getConnectionsForCurrentUser(null, 
					null, null, modifiedSince, modificationType);
			assertNotNull(connection);		
		} catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}		
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetConnectionsForCurrentUserC7() {
		try {
			Connections connection = linkedInConn.getConnectionsForCurrentUser(list, 
					null, null, null, null);
			assertNotNull(connection);		
		} catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
		
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetConnectionsForCurrentUserC8() {
		try {
			Connections connection = linkedInConn.getConnectionsForCurrentUser(null, 
					null, null, null, null);
			assertNotNull(connection);		
		} catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
		
	}
	
}
