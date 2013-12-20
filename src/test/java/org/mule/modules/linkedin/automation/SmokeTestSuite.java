package org.mule.modules.linkedin.automation;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.modules.linkedin.automation.testcases.*;

@RunWith(Categories.class)
@IncludeCategory(SmokeTests.class)

@SuiteClasses({
	GetProfileForCurrentUserTestCases.class,
	GetProfileByIdTestCases.class,
	GetProfileByUrlTestCases.class,
	GetNetworkUpdatesTestCases.class,
	GetUserUpdatesTestCases.class,
	GetUserUpdatesByIdTestCases.class,
	GetNetworkUpdateCommentsTestCases.class,
	GetNetworkUpdateLikesTestCases.class,
	GetConnectionsForCurrentUserTestCases.class,
	GetConnectionsByIdTestCases.class,
	GetConnectionsByUrlTestCases.class,
	GetUserUpdatesByIdTestCases.class,
	PostNetworkUpdateTestCases.class,
	PostCommentTestCases.class,
	LikePostTestCases.class,
	UnlikePostTestCases.class,
	PostShareTestCases.class,
	ReshareTestCases.class,
	SendInviteByEmailTestCases.class,
	SendMessageTestCases.class,
	GetGroupByIdTestCases.class,
	GetGroupMembershipsTestCases.class,
	GetPostsByGroupTestCases.class,
	GetPostTestCases.class,
	GetPostCommentsTestCases.class,
	CreatePostTestCases.class,
	LikeGroupPostTestCases.class,
	UnlikeGroupPostTestCases.class,
	FollowPostTestCases.class,
	UnfollowPostTestCases.class,
	DeletePostTestCases.class,
	AddPostCommentTestCases.class,
	DeletePostCommentTestCases.class,
	GetSuggestedGroupsTestCases.class,
	DeleteSuggestedGroupsTestCases.class,
	GetCompanyByIdTestCases.class,
	GetCompanyByUniversalNameTestCases.class,
	GetCompaniesByEmailDomainTestCases.class,
	GetFollowedCompaniesTestCases.class,
	FollowCompanyTestCases.class,
	UnfollowCompanyTestCases.class,
	GetSuggestedCompaniesTestCases.class,
	SearchCompaniesTestCases.class,
	SearchCompaniesWithFacetsTestCases.class,
	SearchCompaniesWithFacetFieldsTestCases.class,
	GetCompanyProductTestCases.class,
	GetJobSuggestionsTestCases.class,
	GetJobBookmarkTestCases.class,
	UnbookmarkJobTestCases.class
	//JoinGroupTestCases.class - Cannot be automated as the joining group only sends the request to the
	//group admin and group admin should manually accept the request
	//LeaveGroupTestCases.class - Cannot be automated as the joining group only sends the request to the
	//group admin and group admin should manually accept the request
	
	/*
	People Search API and Job API is a part of our Vetted API Access Program. One needs to 
	get LinkedIn's approval before using this API 
	http://developer.linkedin.com/documents/job-lookup-api-and-fields
	http://developer.linkedin.com/documents/people-search-api
	*/
	//SearchPeopleTestCases.class,
	//SearchPeopleWithFacetsTestCases.class,
	//SearchPeopleWithFacetFieldsTestCases.class,
	//GetJobByIdTestCases.class,
	//PostJobTestCases.class,
	//UpdateJobTestCases.class,
	//RenewJobTestCases.class,
	//CloseJobTestCases.class,
	//SearchJobsTestCases.class,
	//SearchJobsWithFacetsTestCases.class,
	//SearchJobsWithFacetFieldsTestCases.class
	
	//GetPostCommentTestCases.class - linkedin-j wrapper issue
	//UpdateGroupMembershipTestCases.class - linkedin-j wrapper issue
	//BookmarkJobTestCases.class - linkedin-j wrapper issue
	//FlagPostTestCases.class - linkedin-j wrapper issue - Though it flags the post but throws exception
	
})
public class SmokeTestSuite {

}
