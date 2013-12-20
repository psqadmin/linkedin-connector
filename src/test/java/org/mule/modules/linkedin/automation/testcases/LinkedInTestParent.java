package org.mule.modules.linkedin.automation.testcases;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.mule.modules.linkedin.LinkedInConnector;
import org.mule.modules.tests.ConnectorTestCase;

public class LinkedInTestParent extends ConnectorTestCase{
	
	private static String apiKey;
	private static String apiSecret;
	private static String accessToken;
	private static String accessTokenSecret;
	private static String scope;
	
	protected LinkedInConnector linkedInConn;
	protected LinkedInTestInit instance;
	
	public LinkedInTestParent(){
		linkedInConn = new LinkedInConnector();
        linkedInConn.setAccessToken(accessToken);
        linkedInConn.setAccessTokenSecret(accessTokenSecret);
        linkedInConn.setApiKey(apiKey);
        linkedInConn.setApiSecret(apiSecret);
        linkedInConn.setScope(scope);
        
        instance = LinkedInTestInit.getInstance(linkedInConn);
	}
	
	static{
		InputStream is = null;
		try{
			Properties properties = new Properties();
			
			is = LinkedInTestParent.class.getClassLoader().getResourceAsStream("automation-credentials.properties");
			properties.load(is);
			
			apiKey = (String)properties.get("linkedin.apiKey");
			apiSecret = (String)properties.get("linkedin.apiSecret");
			accessToken = (String)properties.get("linkedin.accessToken");
			accessTokenSecret = (String)properties.get("linkedin.accessTokenSecret");
			scope = (String)properties.get("linkedin.scope");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally{
			if(is != null){
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}


