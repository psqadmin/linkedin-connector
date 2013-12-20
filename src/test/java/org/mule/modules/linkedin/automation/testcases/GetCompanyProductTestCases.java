package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.google.code.linkedinapi.client.enumeration.CompanyField;
import com.google.code.linkedinapi.client.enumeration.GroupMembershipField;
import com.google.code.linkedinapi.client.enumeration.ProductField;
import com.google.code.linkedinapi.schema.Products;

public class GetCompanyProductTestCases extends LinkedInTestParent {
	
	private String companyId;
	private List<ProductField> list;
	private Integer start;
	private Integer count;	
	
	@Before
	public void setup(){
		companyId = instance.getDefaultCompany().getId();
		list = instance.getProductFieldList();
		start = instance.getStart();
		count = instance.getCount();
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetCompanyProductC1() {
		try{
			Products products = linkedInConn.getCompanyProducts(companyId, list, start, count);
			assertNotNull(products);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetCompanyProductC2() {
		try{
			Products products = linkedInConn.getCompanyProducts(companyId, null, start, count);
			assertNotNull(products);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetCompanyProductC3() {
		try{
			Products products = linkedInConn.getCompanyProducts(companyId, list, null, null);
			assertNotNull(products);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	@Category({SmokeTests.class})
	@Test
	public void testGetCompanyProductC4() {
		try{
			Products products = linkedInConn.getCompanyProducts(companyId, null, null, null);
			assertNotNull(products);
		}catch(Exception e){
			fail(ConnectorTestUtils.getStackTrace(e));			
		}
	}
	
	
}
