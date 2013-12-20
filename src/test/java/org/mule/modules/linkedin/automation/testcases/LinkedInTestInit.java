package org.mule.modules.linkedin.automation.testcases;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.mule.modules.linkedin.LinkedInConnector;

import com.google.code.linkedinapi.client.enumeration.CommentField;
import com.google.code.linkedinapi.client.enumeration.CompanyField;
import com.google.code.linkedinapi.client.enumeration.ConnectionModificationType;
import com.google.code.linkedinapi.client.enumeration.GroupField;
import com.google.code.linkedinapi.client.enumeration.GroupMembershipField;
import com.google.code.linkedinapi.client.enumeration.JobField;
import com.google.code.linkedinapi.client.enumeration.NetworkUpdateType;
import com.google.code.linkedinapi.client.enumeration.PostField;
import com.google.code.linkedinapi.client.enumeration.PostSortOrder;
import com.google.code.linkedinapi.client.enumeration.ProductField;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.enumeration.ProfileType;
import com.google.code.linkedinapi.schema.Activity;
import com.google.code.linkedinapi.schema.Comment;
import com.google.code.linkedinapi.schema.Comments;
import com.google.code.linkedinapi.schema.Company;
import com.google.code.linkedinapi.schema.Connections;
import com.google.code.linkedinapi.schema.Group;
import com.google.code.linkedinapi.schema.GroupMembership;
import com.google.code.linkedinapi.schema.GroupMemberships;
import com.google.code.linkedinapi.schema.Job;
import com.google.code.linkedinapi.schema.Network;
import com.google.code.linkedinapi.schema.Person;
import com.google.code.linkedinapi.schema.Post;
import org.mule.modules.linkedin.enumeration.LocalVisibilityType;
//import com.google.code.linkedinapi.schema.PostCategoryCode;
import org.mule.modules.linkedin.enumeration.LocalPostCategoryCode;
import com.google.code.linkedinapi.schema.Posts;
import com.google.code.linkedinapi.schema.Update;

public class LinkedInTestInit {

	private static LinkedInTestInit instance;
	
	private String otherUserPublicURL;
	private String recepientEmailId;
	private String recepientFirstName;
	private String recepientLastName;
	private String submittedURL;
	private String imageURL;
	private String companyUniversalName;
	private String jobId;
	
	private long postShareTimeout;
	private long waitTime;
	
	private Person currentUser;
	private Person otherUser;
	private Connections currentUserConnections;
	private Date startDate;
	private String postNetworkUpdateText;
	private String postNetworkUpdateCommentText;
	private String networkUpdateId;
	private GroupMembership defaultGroupMembership;
	private Group defaultGroup;
	private Post defaultPost;
	private Comment defaultPostComment;
	private Company defaultCompany; 
	private Job defaultJob;
	
	//connection params
	private Date modifiedSince;
	private Integer start;
	private Integer count;
	
	//post details
	private String commentText;
	private String title;
	private String description;
	
	//group post details
	private String groupPostTitle;
	private String groupPostSummary;
	private String groupPostComment;
	
	private String shareId;
	private String companyEmailDomain;

	private LinkedInTestInit(LinkedInConnector linkedInConn) {
		InputStream is = null;
		try{
			Properties properties = new Properties();
			
			is = this.getClass().getClassLoader().getResourceAsStream("automation-test.properties");
			properties.load(is);
			
			otherUserPublicURL = (String)properties.get("otherUserPublicURL");
			recepientEmailId = (String)properties.get("recepientEmailId");
			recepientFirstName = (String)properties.get("recepientFirstName");
			recepientLastName = (String)properties.get("recepientLastName");
			submittedURL = (String)properties.get("submittedURL");
			imageURL = (String)properties.get("imageURL");
			companyUniversalName = (String)properties.get("companyUniversalName");
			jobId = (String)properties.get("jobId");
			postShareTimeout = Long.parseLong((String)properties.get("postShareTimeout"));
			waitTime = Long.parseLong((String)properties.get("waitTime"));
			
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
		
		try{
		
		//start date to be queried on
		Calendar cal = Calendar.getInstance();
		//TODO: Change to minute
		cal.add(Calendar.MINUTE, -2); //Give a 2 min heads up for all the queries
		startDate = cal.getTime();
		
		//random text which will be asserted across post, comments etc.
		String randomText = generateRandomText();
				
		//get details of current user
		currentUser = linkedInConn.getProfileForCurrentUser(getProfileFieldList());
		
		//get detail of other user
		otherUser = linkedInConn.getProfileByUrl(otherUserPublicURL, ProfileType.PUBLIC, getProfileFieldList());
		
		//get details of current user connections
		cal.add(Calendar.YEAR, -1);//let modified since date be past one year
		modifiedSince = cal.getTime();
		start = 0;
		count = 10;
		currentUserConnections = linkedInConn.getConnectionsForCurrentUser(getProfileFieldList(), start, 
				count, modifiedSince, ConnectionModificationType.ALL);
		if(currentUserConnections == null || currentUserConnections.getPersonList().size() == 0)
			throw new Exception("Failure in initialization. At least one connection is required. " +
					"Please create one connection with your LinkedIn account" +
					"manually and re-run the test");
		
		//post network update
		postNetworkUpdateText = "Mulesoft Post Network Update " + randomText;
		linkedInConn.postNetworkUpdate(postNetworkUpdateText);
		sleep();
		
		//post a share
		commentText = "Mulesoft Post Share Comment Text " + randomText;
		title = "Mulesoft Post Share Title " + randomText;
		description = "Mulesoft Post Share Description " + randomText;
		
		linkedInConn.postShare(commentText, title, description, submittedURL, imageURL, LocalVisibilityType.ANYONE, false);
		sleep();
		
		//Post share does not appear immediately. hence adding a timeout. 
		//This may need to be tweaked for individual machine
		sleepForPostShare();
		
		//retrieve the update key of the posted network update
		List<NetworkUpdateType> updateTypes = new ArrayList<NetworkUpdateType>();
		updateTypes.add(NetworkUpdateType.APPLICATION_UPDATE);
		updateTypes.add(NetworkUpdateType.SHARED_ITEM);
		
		Network network = linkedInConn.getUserUpdatesById(currentUser.getId(), updateTypes, start, count, startDate, new Date());
		for(Update update: network.getUpdates().getUpdateList()){
			Person p = update.getUpdateContent().getPerson();
			if(p.getPersonActivities() != null){
				for(Activity activity: p.getPersonActivities().getActivityList()){
					if(activity.getBody().equals(postNetworkUpdateText)){
						networkUpdateId = update.getUpdateKey();
						break;
					}						
				}
			}
			if(p.getCurrentShare() != null){
				if(p.getCurrentShare().getComment().equalsIgnoreCase(commentText)){
					shareId = p.getCurrentShare().getId();
				}
					
			}
		}
		
		if(networkUpdateId == null)
			throw new Exception("Failure in initialization. Unable to retrieve the network id of a posted network id");
		if(shareId == null)
			throw new Exception("Failure in initialization. Unable to retrieve the share id of a posted share");
		
		//post network update comment
		postNetworkUpdateCommentText = "Mulesoft Post Comment " + randomText;
		linkedInConn.postComment(networkUpdateId, postNetworkUpdateCommentText);
		sleep();
		
		//like a network update
		linkedInConn.likePost(networkUpdateId);
		sleep();
		
		GroupMemberships gm = linkedInConn.getGroupMemberships(currentUser.getId(), getGroupMembershipFieldList(), start, count);
		if(gm.getGroupMembershipList().size() < 1)
			throw new Exception("Failure in initialization. At least one group needs to be associated with the current user");
		defaultGroupMembership = gm.getGroupMembershipList().get(0);
		defaultGroup = defaultGroupMembership.getGroup();
		
		groupPostTitle = "Mulesoft Group Post Title " + randomText;
		groupPostSummary = "Mulesoft Group Post Summary " + randomText;
		linkedInConn.createPost(defaultGroup.getId(), groupPostTitle, groupPostSummary);
		sleep();
		
		Posts posts = linkedInConn.getPostsByGroup(defaultGroup.getId(), getPostFieldList(), start, count, 
				PostSortOrder.RECENCY, LocalPostCategoryCode.DISCUSSION, modifiedSince);
		for(Post post: posts.getPostList()){
			if(post.getTitle() != null){
				if(post.getTitle().equals(groupPostTitle)){
					defaultPost = post;
					break;
				}
			}
		}
		
		if(defaultPost == null)
			throw new Exception("Failure in initialization. Default Post cannot be null");
		
		groupPostComment = "Mulesoft Group Post Comment " + randomText;
		linkedInConn.addPostComment(defaultPost.getId(), groupPostComment);
		sleep();
		
		Comments comments = linkedInConn.getPostComments(defaultPost.getId(), 
				getCommentFieldList(), start, count);
		for(Comment comment:comments.getCommentList()){
			if(comment.getText().equals(groupPostComment)){
				defaultPostComment = comment;
				break;
			}
		}
		
		if(defaultPostComment == null)
			throw new Exception("Failure in initialization. Default Post Comment cannot be null");
		defaultCompany = linkedInConn.getCompanyByUniversalName(companyUniversalName, getCompanyFieldList());
		if(defaultCompany != null && defaultCompany.getEmailDomains() != null && 
				defaultCompany.getEmailDomains().getEmailDomainList() != null &&
				defaultCompany.getEmailDomains().getEmailDomainList().size() > 0){
			companyEmailDomain = defaultCompany.getEmailDomains().getEmailDomainList().get(0);
		}
		
		if(defaultCompany == null)
			throw new Exception("Failure in initialization. Default Company cannot be null");
		if(companyEmailDomain == null)
			throw new Exception("Failure in initialization. Company Email domain cannot be null");
		
		//Need to have appropriate access to use this API
		/*defaultJob = linkedInConn.getJobById(jobId, null);
		if(defaultJob == null)
			throw new Exception("Failure in initialization. Default Job cannot be null");*/
		}catch(Exception e){
			e.printStackTrace();
			fail("Failure in initialization");
		}
	}

	public static LinkedInTestInit getInstance(LinkedInConnector linkedInConn) {
		if (instance == null) {
			instance = new LinkedInTestInit(linkedInConn);
		}
		return instance;
	}
	
	//get the list of profile fields to be retrieved
	protected List<ProfileField> getProfileFieldList(){
		List<ProfileField> profileFieldList = new ArrayList<ProfileField>();
		profileFieldList.add(ProfileField.ID);
		profileFieldList.add(ProfileField.FIRST_NAME);
		profileFieldList.add(ProfileField.LAST_NAME);
		profileFieldList.add(ProfileField.HEADLINE);
		profileFieldList.add(ProfileField.PUBLIC_PROFILE_URL);
		profileFieldList.add(ProfileField.CURRENT_STATUS);
		return profileFieldList;
	}
	
	protected List<GroupMembershipField> getGroupMembershipFieldList(){
		List<GroupMembershipField> groupMembershipFieldList = new ArrayList<GroupMembershipField>();
		groupMembershipFieldList.add(GroupMembershipField.GROUP_ID);
		groupMembershipFieldList.add(GroupMembershipField.GROUP_NAME);
		groupMembershipFieldList.add(GroupMembershipField.EMAIL_DIGEST_FREQUENCY);
		return groupMembershipFieldList;
	}
	
	protected List<GroupField> getGroupFieldList(){
		List<GroupField> groupFieldList = new ArrayList<GroupField>();
		groupFieldList.add(GroupField.ID);
		groupFieldList.add(GroupField.NAME);
		return groupFieldList;
	}
	
	protected List<PostField> getPostFieldList(){
		List<PostField> postFieldList = new ArrayList<PostField>();
		postFieldList.add(PostField.ID);
		postFieldList.add(PostField.TYPE);
		postFieldList.add(PostField.CREATOR);
		postFieldList.add(PostField.TITLE);
		postFieldList.add(PostField.SUMMARY);
		return postFieldList;
	}
	
	protected List<CommentField> getCommentFieldList(){
		List<CommentField> commentFieldList = new ArrayList<CommentField>();
		commentFieldList.add(CommentField.ID);
		commentFieldList.add(CommentField.TEXT);
		return commentFieldList;
	}
	
	protected List<CompanyField> getCompanyFieldList(){
		List<CompanyField> companyFieldList = new ArrayList<CompanyField>();
		companyFieldList.add(CompanyField.ID);
		companyFieldList.add(CompanyField.NAME);
		companyFieldList.add(CompanyField.COMPANY_TYPE);
		companyFieldList.add(CompanyField.EMAIL_DOMAINS);
		return companyFieldList;
	}
	
	protected List<ProductField> getProductFieldList(){
		List<ProductField> productFieldList = new ArrayList<ProductField>();
		productFieldList.add(ProductField.ID);
		productFieldList.add(ProductField.NAME);
		productFieldList.add(ProductField.DESCRIPTION);
		return productFieldList;
	}
	
	protected List<JobField> getJobFieldList(){
		List<JobField> jobFieldList = new ArrayList<JobField>();
		jobFieldList.add(JobField.ID);
		jobFieldList.add(JobField.DESCRIPTION);
		jobFieldList.add(JobField.COMPANY);
		return jobFieldList;
	}
	
	protected String generateRandomText(){
		SecureRandom random = new SecureRandom();
	    return new BigInteger(130, random).toString(32);
	}
	
	protected void sleep(){
		try{
			Thread.sleep(waitTime);
		}catch(Exception e){
		}
	}
	
	protected void sleepForPostShare(){
		try{
			Thread.sleep(postShareTimeout);
		}catch(Exception e){
		}
	}

	public String getOtherUserPublicURL() {
		return otherUserPublicURL;
	}

	public String getRecepientEmailId() {
		return recepientEmailId;
	}

	public String getRecepientFirstName() {
		return recepientFirstName;
	}

	public String getRecepientLastName() {
		return recepientLastName;
	}

	public String getSubmittedURL() {
		return submittedURL;
	}

	public String getImageURL() {
		return imageURL;
	}

	public String getCompanyUniversalName() {
		return companyUniversalName;
	}

	public String getJobId() {
		return jobId;
	}

	public long getPostShareTimeout() {
		return postShareTimeout;
	}

	public Person getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(Person user) {
		this.currentUser = user;
	}

	public Person getOtherUser() {
		return otherUser;
	}

	public Connections getCurrentUserConnections() {
		return currentUserConnections;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String getPostNetworkUpdateText() {
		return postNetworkUpdateText;
	}

	public String getPostNetworkUpdateCommentText() {
		return postNetworkUpdateCommentText;
	}

	public String getNetworkUpdateId() {
		return networkUpdateId;
	}

	public GroupMembership getDefaultGroupMembership() {
		return defaultGroupMembership;
	}

	public Group getDefaultGroup() {
		return defaultGroup;
	}

	public Post getDefaultPost() {
		return defaultPost;
	}

	public Comment getDefaultPostComment() {
		return defaultPostComment;
	}

	public Company getDefaultCompany() {
		return defaultCompany;
	}

	public Job getDefaultJob() {
		return defaultJob;
	}

	public String getCommentText() {
		return commentText;
	}
	
	public void setCommentText(String updatedCommentText) {
		this.commentText = updatedCommentText;		
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getGroupPostTitle() {
		return groupPostTitle;
	}

	public String getGroupPostSummary() {
		return groupPostSummary;
	}

	public String getGroupPostComment() {
		return groupPostComment;
	}

	public String getShareId() {
		return shareId;
	}

	public String getCompanyEmailDomain() {
		return companyEmailDomain;
	}

	public static LinkedInTestInit getInstance() {
		return instance;
	}

	public Date getModifiedSince() {
		return modifiedSince;
	}

	public Integer getStart() {
		return start;
	}

	public Integer getCount() {
		return count;
	}

	public long getWaitTime() {
		return waitTime;
	}	
}
