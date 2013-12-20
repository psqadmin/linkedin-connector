package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.client.enumeration.NetworkUpdateType;
import com.google.code.linkedinapi.schema.Network;

public class GetNetworkUpdatesTestCases extends LinkedInTestParent{
	
	private List<NetworkUpdateType> list;
	private Date startDate;
	private Date endDate;
	private Integer start;
	private Integer count;
	private Boolean showHidderMembers;
	
	@Before
	public void setup(){
		list = new ArrayList<NetworkUpdateType>();
		list.add(NetworkUpdateType.GROUP_UPDATE);
		
		startDate = instance.getStartDate();
		endDate = new Date();
		start = 0;
		count = 10;
		showHidderMembers = false;
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetNetworkUpdatesC1() {
		try{
			Network network = linkedInConn.getNetworkUpdates(list, start, count, startDate, 
					endDate, showHidderMembers);
			assertNotNull(network);			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetNetworkUpdatesC2() {
		try{
			Network network = linkedInConn.getNetworkUpdates(list, start, count, startDate, 
					endDate, null);
			assertNotNull(network);			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetNetworkUpdatesC3() {
		try{
			Network network = linkedInConn.getNetworkUpdates(list, start, count, null, 
					null, null);
			assertNotNull(network);			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetNetworkUpdatesC4() {
		try{
			Network network = linkedInConn.getNetworkUpdates(list, null, null, startDate, 
					endDate, null);
			assertNotNull(network);			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetNetworkUpdatesC5() {
		try{
			Network network = linkedInConn.getNetworkUpdates(null, start, count, null, 
					null, null);
			assertNotNull(network);			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetNetworkUpdatesC6() {
		try{
			Network network = linkedInConn.getNetworkUpdates(null, null, null, startDate, 
					endDate, null);
			assertNotNull(network);			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetNetworkUpdatesC7() {
		try{
			Network network = linkedInConn.getNetworkUpdates(list, null, null, null, 
					null, null);
			assertNotNull(network);			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetNetworkUpdatesC8() {
		try{
			Network network = linkedInConn.getNetworkUpdates(null, null, null, null, 
					null, null);
			assertNotNull(network);			
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}

}
