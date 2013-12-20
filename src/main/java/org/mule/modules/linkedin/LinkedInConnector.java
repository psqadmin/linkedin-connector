/**
 * Copyright (c) MuleSoft, Inc. All rights reserved. http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */
package org.mule.modules.linkedin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.display.Placement;
import org.mule.api.annotations.oauth.OAuth;
import org.mule.api.annotations.oauth.OAuthAccessToken;
import org.mule.api.annotations.oauth.OAuthAccessTokenSecret;
import org.mule.api.annotations.oauth.OAuthConsumerKey;
import org.mule.api.annotations.oauth.OAuthConsumerSecret;
import org.mule.api.annotations.oauth.OAuthScope;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.Parameter;
import com.google.code.linkedinapi.client.enumeration.CommentField;
import com.google.code.linkedinapi.client.enumeration.CompanyField;
import com.google.code.linkedinapi.client.enumeration.ConnectionModificationType;
import com.google.code.linkedinapi.client.enumeration.FacetField;
import com.google.code.linkedinapi.client.enumeration.GroupField;
import com.google.code.linkedinapi.client.enumeration.GroupMembershipField;
import com.google.code.linkedinapi.client.enumeration.JobField;
import com.google.code.linkedinapi.client.enumeration.NetworkUpdateType;
import com.google.code.linkedinapi.client.enumeration.PostField;
import com.google.code.linkedinapi.client.enumeration.PostSortOrder;
import com.google.code.linkedinapi.client.enumeration.ProductField;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.enumeration.ProfileType;
import com.google.code.linkedinapi.client.enumeration.SearchParameter;
import com.google.code.linkedinapi.client.enumeration.SearchSortOrder;
import com.google.code.linkedinapi.schema.Comments;
import com.google.code.linkedinapi.schema.Companies;
import com.google.code.linkedinapi.schema.Company;
import com.google.code.linkedinapi.schema.CompanySearch;
import com.google.code.linkedinapi.schema.Connections;
import com.google.code.linkedinapi.schema.FacetType;

import org.mule.modules.linkedin.enumeration.LocalVisibilityType;
import org.mule.modules.linkedin.enumeration.LocalFacetType;
import com.google.code.linkedinapi.schema.Group;
import com.google.code.linkedinapi.schema.GroupMemberships;
import com.google.code.linkedinapi.schema.Groups;
import com.google.code.linkedinapi.schema.Job;
import com.google.code.linkedinapi.schema.JobBookmarks;
import com.google.code.linkedinapi.schema.JobSearch;
import com.google.code.linkedinapi.schema.Jobs;
import com.google.code.linkedinapi.schema.Likes;
import com.google.code.linkedinapi.schema.Network;
import com.google.code.linkedinapi.schema.People;
import com.google.code.linkedinapi.schema.PeopleSearch;
import com.google.code.linkedinapi.schema.Person;
import com.google.code.linkedinapi.schema.Post;
import com.google.code.linkedinapi.schema.PostCategoryCode;
import org.mule.modules.linkedin.enumeration.LocalPostCategoryCode;
import com.google.code.linkedinapi.schema.Posts;
import com.google.code.linkedinapi.schema.Products;
import com.google.code.linkedinapi.schema.UpdateComments;
import com.google.code.linkedinapi.schema.VisibilityType;


/**
 * LinkedIn is a business-related social networking site. Founded in December 2002 and launched in
 * May 2003, it is
 * mainly used for
 * professional networking. This connector allows you to interact with LinkedIn API.
 * 
 * @author MuleSoft, Inc.
 */
@Connector(name="linkedin", friendlyName = "LinkedIn", schemaVersion="1.0")
@OAuth(requestTokenUrl = "https://api.linkedin.com/uas/oauth/requestToken",
        accessTokenUrl = "https://api.linkedin.com/uas/oauth/accessToken",
        authorizationUrl = "https://api.linkedin.com/uas/oauth/authorize")

public class LinkedInConnector
{

    /**
     * API Key
     */
    @Configurable
    @OAuthConsumerKey
    private String apiKey; // api / client / consumer
    /**
     * API Secret
     */
    @Configurable
    @OAuthConsumerSecret
    private String apiSecret; // api / client / consumer

    /**
     * Access Token
     */
    @OAuthAccessToken
    private String accessToken;

    /**
     * Access Token Secret
     */
    @OAuthAccessTokenSecret
    private String accessTokenSecret;

    /**
     * LinkedIn permissions
     */
    @Configurable
    @Optional
    @Default(value = "r_basicprofile+r_emailaddress")
    @OAuthScope
    private String scope;

    /**
     * LinkedIn API Client
     */
    private LinkedInApiClient client;

    /**
     * Gets the profile for current user. For details see <a
     * href="http://developer.linkedin.com/documents/profile-api"
     * >http://developer.linkedin.com/documents/profile-api</a>
     * <p/>
     * 
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-profile-for-current-user}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getProfileForCurrentUser}
     *
     * @param profileFields
     *            the profile fields to retrieve
     * @return the profile for current user
     */
    @Processor
    public Person getProfileForCurrentUser(@Placement(group = "Profile Fields") @Optional List<ProfileField> profileFields) {
        if (profileFields == null) {
            return getClient(accessToken, accessTokenSecret).getProfileForCurrentUser();
        } else {
            return getClient(accessToken, accessTokenSecret).getProfileForCurrentUser(createSet(profileFields));
        }
    }

    /**
     * Gets the profile by id. For details see <a href="http://developer.linkedin.com/documents/profile-api"
     * >http://developer.linkedin.com/documents/profile-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-profile-by-id}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getProfileById}
     *
     * @param id
     *            the id to search
     * @param profileFields
     *            the profile fields to retrieve
     * @return the profile by id
     */
    @Processor
    public Person getProfileById(String id,
                                 @Placement(group = "Profile Fields") @Optional List<ProfileField> profileFields) {
        if (profileFields == null) {
            return getClient(accessToken, accessTokenSecret).getProfileById(id);
        } else {
            return getClient(accessToken, accessTokenSecret).getProfileById(id, createSet(profileFields));
        }
    }

	/**
	 * Gets the profile by url. For details see <a
	 * href="http://developer.linkedin.com/documents/profile-api"
	 * >http://developer.linkedin.com/documents/profile-api</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-profile-by-url}
	 * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getProfileByUrl}
	 * 
	 * @param url
	 *            the url to search
	 * @param profileType
	 *            the profile type to search
	 * @param profileFields
	 *            the profile fields to retrieve
	 * @return the profile by url
	 */
	@Processor
	public Person getProfileByUrl(String url, ProfileType profileType, 
	        @Placement(group = "Profile Fields") @Optional List<ProfileField> profileFields) {
		if (profileFields == null) {
			return getClient(accessToken, accessTokenSecret).getProfileByUrl(url, profileType);
		} else {
			return getClient(accessToken, accessTokenSecret).getProfileByUrl(url, profileType, createSet(profileFields));
		}
	}

	/**
	 * Gets the network updates. For details see <a
	 * href="http://developer.linkedin.com/documents/get-network-updates-and-statistics-api"
	 * >http://developer.linkedin.com/documents/get-network-updates-and-statistics-api</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-network-updates}
	 * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getNetworkUpdates}
	 * 
	 * @param updateTypes
	 *            the update types to retrieve
	 * @param start
	 *            the start, if set count needs to be specified
	 * @param count
	 *            the count, if set start needs to be specified
	 * @param startDate
	 *            the start date, if set end date needs to be specified
	 * @param endDate
	 *            the end date, if set start date needs to be specified
	 * @param showHiddenMembers
	 *            whether to show hidden memberts
	 * @return the network updates
	 */
	@Processor
	public Network getNetworkUpdates(@Placement(group = "Network Update Types") @Optional List<NetworkUpdateType> updateTypes, 
	        @Optional Integer start, @Optional Integer count,
			@Optional Date startDate, @Optional Date endDate, @Optional Boolean showHiddenMembers) {
		if (updateTypes != null && start != null && count != null && startDate != null && endDate != null && showHiddenMembers != null) {
			return getClient(accessToken, accessTokenSecret).getNetworkUpdates(createSet(updateTypes), start, count, startDate, endDate,
					showHiddenMembers);
		}
		if (updateTypes != null && start != null && count != null && startDate != null && endDate != null) {
			getClient(accessToken, accessTokenSecret).getNetworkUpdates(createSet(updateTypes), start, count, startDate, endDate);
		}
		if (updateTypes != null && start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).getNetworkUpdates(createSet(updateTypes), start, count);
		}
		if (updateTypes != null && startDate != null && endDate != null) {
			return getClient(accessToken, accessTokenSecret).getNetworkUpdates(createSet(updateTypes), startDate, endDate);
		}
		if (start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).getNetworkUpdates(start, count);
		}
		if (startDate != null && endDate != null) {
			return getClient(accessToken, accessTokenSecret).getNetworkUpdates(startDate, endDate);
		}
		if (updateTypes != null) {
			return getClient(accessToken, accessTokenSecret).getNetworkUpdates(createSet(updateTypes));
		}
		return getClient(accessToken, accessTokenSecret).getNetworkUpdates();
	}

	/**
	 * Gets the network updates. For details see <a
	 * href="http://developer.linkedin.com/documents/get-network-updates-and-statistics-api"
	 * >http://developer.linkedin.com/documents/get-network-updates-and-statistics-api</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-user-updates}
	 * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getUserUpdates}
	 * 
	 * @param updateTypes
	 *            the update types to retrieve
	 * @param start
	 *            the start, if set count needs to be specified
	 * @param count
	 *            the count, if set start needs to be specified
	 * @param startDate
	 *            the start date, if set end date needs to be specified
	 * @param endDate
	 *            the end date, if set start date needs to be specified
	 * @return the network updates
	 */
	@Processor
	public Network getUserUpdates(@Placement(group = "Network Update Types") @Optional List<NetworkUpdateType> updateTypes, 
	        @Optional Integer start, @Optional Integer count,
			@Optional Date startDate, @Optional Date endDate) {
		if (updateTypes != null && start != null && count != null && startDate != null && endDate != null) {
			return getClient(accessToken, accessTokenSecret).getUserUpdates(createSet(updateTypes), start, count, startDate, endDate);
		}
		if (updateTypes != null && start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).getUserUpdates(createSet(updateTypes), start, count);
		}
		if (updateTypes != null && startDate != null && endDate != null) {
			return getClient(accessToken, accessTokenSecret).getUserUpdates(createSet(updateTypes), startDate, endDate);
		}
		if (startDate != null && endDate != null) {
			return getClient(accessToken, accessTokenSecret).getUserUpdates(startDate, endDate);
		}
		if (start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).getUserUpdates(start, count);
		}
		if (updateTypes != null) {
			return getClient(accessToken, accessTokenSecret).getUserUpdates(createSet(updateTypes));
		}
		return getClient(accessToken, accessTokenSecret).getUserUpdates();
	}

	/**
	 * Gets the network updates. For details see <a
	 * href="http://developer.linkedin.com/documents/get-network-updates-and-statistics-api"
	 * >http://developer.linkedin.com/documents/get-network-updates-and-statistics-api</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-user-updates-by-id}
	 * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getUserUpdatesById}
	 * 
	 * @param id
	 *            the id to search
	 * @param updateTypes
	 *            the update types to retrieve
	 * @param start
	 *            the start, if set count needs to be specified
	 * @param count
	 *            the count, if set start needs to be specified
	 * @param startDate
	 *            the start date, if set end date needs to be specified
	 * @param endDate
	 *            the end date, if set end date needs to be specified
	 * @return the network updates
	 */
	@Processor
	public Network getUserUpdatesById(String id, @Placement(group = "Network Update Types") @Optional List<NetworkUpdateType> updateTypes, 
	        @Optional Integer start, @Optional Integer count, @Optional Date startDate, @Optional Date endDate) {
		if (updateTypes != null && start != null && count != null && startDate != null && endDate != null) {
			return getClient(accessToken, accessTokenSecret).getUserUpdates(id, createSet(updateTypes), start, count, startDate, endDate);
		}
		if (updateTypes != null && start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).getUserUpdates(id, createSet(updateTypes), start, count);
		}
		if (updateTypes != null && startDate != null && endDate != null) {
			return getClient(accessToken, accessTokenSecret).getUserUpdates(id, createSet(updateTypes), startDate, endDate);
		}
		if (start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).getUserUpdates(id, start, count);
		}
		if (startDate != null && endDate != null) {
			return getClient(accessToken, accessTokenSecret).getUserUpdates(id, startDate, endDate);
		}
		if (updateTypes != null) {
			return getClient(accessToken, accessTokenSecret).getUserUpdates(id, createSet(updateTypes));
		}
		return getClient(accessToken, accessTokenSecret).getUserUpdates(id);
	}

	/**
	 * Gets the network update comments. For details see <a
	 * href="http://developer.linkedin.com/documents/commenting-reading-comments-and-likes-network-updates"
	 * >http://developer.linkedin.com/documents/commenting-reading-comments-and-likes-network-updates</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-network-update-comments}
	 * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getNetworkUpdateComments}
	 * 
	 * @param networkUpdateId
	 *            the network update id to search
	 * @return the network update comments
	 */
	@Processor
	public UpdateComments getNetworkUpdateComments(String networkUpdateId) {
		return getClient(accessToken, accessTokenSecret).getNetworkUpdateComments(networkUpdateId);
	}

	/**
	 * Gets the network update likes. For details see <a
	 * href="http://developer.linkedin.com/documents/commenting-reading-comments-and-likes-network-updates"
	 * >http://developer.linkedin.com/documents/commenting-reading-comments-and-likes-network-updates</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-network-update-likes}
	 * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getNetworkUpdateLikes}
	 * 
	 * @param networkUpdateId
	 *            the network update id to search
	 * @return the network update likes
	 */
	@Processor
	public Likes getNetworkUpdateLikes(String networkUpdateId) {
		return getClient(accessToken, accessTokenSecret).getNetworkUpdateLikes(networkUpdateId);
	}

	/**
	 * Gets the connections for current user. For details see <a
	 * href="http://developer.linkedin.com/documents/connections-api"
	 * >http://developer.linkedin.com/documents/connections-api</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample
	 * linkedin:get-connections-for-current-user} 
	 * {@sample.java
	 * ../../../doc/linkedin-connector.java.sample linkedin:getConnectionsForCurrentUser}
	 * 
	 * @param profileFields
	 *            the profile fields to retrieve
	 * @param start
	 *            the start, if set count needs to be specified
	 * @param count
	 *            the count, if set start needs to be specified
	 * @param modificationDate
	 *            the modification date, if set modification type needs to be specified
	 * @param modificationType
	 *            the modification type, if set modification date needs to be specified
	 * @return the connections for current user
	 */
	@Processor
	public Connections getConnectionsForCurrentUser(@Placement(group = "Profile Fields") @Optional List<ProfileField> profileFields, 
	        @Optional Integer start, @Optional Integer count, @Optional Date modificationDate, @Optional ConnectionModificationType modificationType) {
		if (profileFields != null && start != null && count != null && modificationDate != null && modificationType != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsForCurrentUser(createSet(profileFields), start, count,
					modificationDate, modificationType);
		}
		if (profileFields != null && modificationDate != null && modificationType != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsForCurrentUser(createSet(profileFields), modificationDate,
					modificationType);
		}
		if (profileFields != null && start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsForCurrentUser(createSet(profileFields), start, count);
		}
		if (start != null && count != null && modificationDate != null && modificationType != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsForCurrentUser(start, count, modificationDate,
					modificationType);
		}
		if (start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsForCurrentUser(start, count);
		}
		if (modificationDate != null && modificationType != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsForCurrentUser(modificationDate, modificationType);
		}
		if (profileFields != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsForCurrentUser(createSet(profileFields));
		}
		return getClient(accessToken, accessTokenSecret).getConnectionsForCurrentUser();
	}

	/**
	 * Gets the connections by id. For details see <a
	 * href="http://developer.linkedin.com/documents/connections-api"
	 * >http://developer.linkedin.com/documents/connections-api</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-connections-by-id}
	 * {@sample.java
	 * ../../../doc/linkedin-connector.java.sample linkedin:getConnectionsById}
	 * 
	 * @param id
	 *            the id to search
	 * @param profileFields
	 *            the profile fields to retrieve
	 * @param start
	 *            the start, if set count needs to be specified
	 * @param count
	 *            the count, if set start needs to be specified
	 * @param modificationDate
	 *            the modification date, if set modification type needs to be specified
	 * @param modificationType
	 *            the modification type, if set modification date needs to be specified
	 * @return the connections by id
	 */
	@Processor
	public Connections getConnectionsById(String id, @Placement(group = "Profile Fields") @Optional List<ProfileField> profileFields, 
	        @Optional Integer start, @Optional Integer count, @Optional Date modificationDate, @Optional ConnectionModificationType modificationType) {
		if (profileFields != null && start != null && count != null && modificationDate != null && modificationType != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsById(id, createSet(profileFields), start, count,
					modificationDate, modificationType);
		}
		if (profileFields != null && modificationDate != null && modificationType != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsById(id, createSet(profileFields), modificationDate,
					modificationType);
		}
		if (profileFields != null && start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsById(id, createSet(profileFields), start, count);
		}
		if (start != null && count != null && modificationDate != null && modificationType != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsById(id, start, count, modificationDate, modificationType);
		}
		if (modificationDate != null && modificationType != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsById(id, modificationDate, modificationType);
		}
		if (start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsById(id, start, count);
		}
		if (profileFields != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsById(id, createSet(profileFields));
		}
		return getClient(accessToken, accessTokenSecret).getConnectionsById(id);
	}

	/**
	 * Gets the connections by url. For details see <a
	 * href="http://developer.linkedin.com/documents/connections-api"
	 * >http://developer.linkedin.com/documents/connections-api</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-connections-by-url}
	 * {@sample.java
	 * ../../../doc/linkedin-connector.java.sample linkedin:getConnectionsByUrl}
	 * 
	 * @param url
	 *            the url to search
	 * @param profileFields
	 *            the profile fields to retrieve
	 * @param start
	 *            the start, if set count needs to be specified
	 * @param count
	 *            the count, if set start needs to be specified
	 * @param modificationDate
	 *            the modification date, if set modification type needs to be specified
	 * @param modificationType
	 *            the modification type, if set modification date needs to be specified
	 * @return the connections by url
	 */
	@Processor
	public Connections getConnectionsByUrl(String url, @Placement(group = "Profile Fields") @Optional List<ProfileField> profileFields, 
	        @Optional Integer start, @Optional Integer count, @Optional Date modificationDate, @Optional ConnectionModificationType modificationType) {
		if (profileFields != null && start != null && count != null && modificationDate != null && modificationType != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsByUrl(url, createSet(profileFields), start, count,
					modificationDate, modificationType);
		}
		if (profileFields != null && modificationDate != null && modificationType != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsByUrl(url, createSet(profileFields), modificationDate,
					modificationType);
		}
		if (profileFields != null && start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsByUrl(url, createSet(profileFields), start, count);
		}
		if (start != null && count != null && modificationDate != null && modificationType != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsByUrl(url, start, count, modificationDate, modificationType);
		}
		if (modificationDate != null && modificationType != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsByUrl(url, modificationDate, modificationType);
		}
		if (start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsByUrl(url, start, count);
		}
		if (profileFields != null) {
			return getClient(accessToken, accessTokenSecret).getConnectionsByUrl(url, createSet(profileFields));
		}
		return getClient(accessToken, accessTokenSecret).getConnectionsByUrl(url);
	}

	/**
	 * Search people. For details see <a href="http://developer.linkedin.com/documents/people-search-api"
	 * >http://developer.linkedin.com/documents/people-search-api</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:search-people}
	 * {@sample.java
	 * ../../../doc/linkedin-connector.java.sample linkedin:searchPeople}
	 * 
	 * @param searchParameters
	 *            the search parameters to use
	 * @param profileFields
	 *            the profile fields to retriee
	 * @param start
	 *            the start, if set count needs to be specified
	 * @param count
	 *            the count, if set start needs to be specified
	 * @param sortOrder
	 *            the sort order to use, defaults to RELEVANCE
	 * @return the people
	 */
	@Processor
	public People searchPeople(@Optional @Placement(group = "Search Parameters") Map<SearchParameter, String> searchParameters,
			@Optional @Placement(group = "Profile Fields") List<ProfileField> profileFields, @Optional Integer start, @Optional Integer count,
			@Optional @Default(value = "RELEVANCE") SearchSortOrder sortOrder) {
		
		if (searchParameters != null && profileFields != null && start != null && count != null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields), start, count,
					sortOrder);
		}
		
		if (searchParameters != null && profileFields != null && start != null && count != null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields), start, count);
		}
		if (searchParameters != null && profileFields != null && start == null && count == null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields), sortOrder);
		}
		if (searchParameters != null && profileFields == null && start != null && count != null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, start, count,
					sortOrder);
		}
		
		if (searchParameters != null && profileFields != null && start == null && count == null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields));
		}
		if (searchParameters != null && profileFields == null && start != null && count != null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, start, count);
		}
		if (searchParameters != null && profileFields == null && start == null && count == null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, sortOrder);
		}
		
		if (searchParameters != null && profileFields == null && start == null && count == null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters);
		}
		
		return getClient(accessToken, accessTokenSecret).searchPeople();
	}

	/**
	 * Search people. For details see <a href="http://developer.linkedin.com/documents/people-search-api"
	 * >http://developer.linkedin.com/documents/people-search-api</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:search-people-with-facets}
	 * {@sample.java
	 * ../../../doc/linkedin-connector.java.sample linkedin:searchPeopleWithFacets}
	 * 
	 * @param searchParameters
	 *            the search parameters
	 * @param profileFields
	 *            the profile fields to retrieve
	 * @param start
	 *            the start, if set count needs to be specified
	 * @param count
	 *            the count, if set start needs to be specified
	 * @param sortOrder
	 *            the sort order to use, defaults to RELEVANCE
	 * @param facets
	 *            the facet type and a comma separated string with all the values
	 * @return the people
	 */

	@Processor
	public People searchPeopleWithFacets(@Placement(group = "Search Parameters") Map<SearchParameter, String> searchParameters,
	        @Placement(group = "Profile Fields") @Optional List<ProfileField> profileFields, 
	        @Optional Integer start, @Optional Integer count, @Optional @Default(value = "RELEVANCE") SearchSortOrder sortOrder, 
	        @Placement(group = "Facets") Map<LocalFacetType, String> facets) {
		
		Map<FacetType, String> wrapperFacets = null;
		if(facets != null){
			wrapperFacets = convertFromMulesoftFacetToGoogleFacet(facets);
		}
			
		if (profileFields != null && start != null && count != null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields), start, count,
					sortOrder, adapt(wrapperFacets));
		}
		
		if (profileFields != null && start != null && count != null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields), start, count,
					adapt(wrapperFacets));
		}		
		if (profileFields != null && start == null && count == null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields), sortOrder, 
					adapt(wrapperFacets));
		}
		if (profileFields == null && start != null && count != null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, start, count,
					sortOrder, adapt(wrapperFacets));
		}
		
		if (profileFields != null && start == null && count == null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields), adapt(wrapperFacets));
		}
		if (profileFields == null && start != null && count != null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, start, count, adapt(wrapperFacets));
		}
		if (profileFields == null && start == null && count == null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, sortOrder, adapt(wrapperFacets));
		}
		
		return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, adapt(wrapperFacets));
		
	}

	/**
	 * Search people. For details see <a href="http://developer.linkedin.com/documents/people-search-api"
	 * >http://developer.linkedin.com/documents/people-search-api</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample
	 * linkedin:search-people-with-facet-fields} 
	 * {@sample.java
	 * ../../../doc/linkedin-connector.java.sample linkedin:searchPeopleWithFacetFields}
	 * 
	 * @param searchParameters
	 *            the search parameters
	 * @param profileFields
	 *            the profile fields to retrieve
	 * @param facetFields
	 *            the facet fields to use
	 * @param start
	 *            the start, if set count needs to be specified
	 * @param count
	 *            the count, if set start needs to be specified
	 * @param sortOrder
	 *            the sort order, defaults to RELEVANCE
	 * @param facets
	 *            the facets to use
	 * @return the people
	 */

    @Processor
    public PeopleSearch searchPeopleWithFacetFields(@Placement(group = "Search Parameters") Map<SearchParameter, String> searchParameters,
                                                    @Placement(group = "Profile Fields") List<ProfileField> profileFields, @Placement(group = "Facet Fields") List<FacetField> facetFields,
                                                    @Optional Integer start, @Optional Integer count, @Optional @Default(value = "RELEVANCE") SearchSortOrder sortOrder,
                                                    @Placement(group = "Facets") @Optional Map<LocalFacetType, String> facets) {
    	
    	Map<FacetType, String> wrapperFacets = null;
		if(facets != null){
			wrapperFacets = convertFromMulesoftFacetToGoogleFacet(facets);
		}
		
		if (start != null && count != null && facets != null && sortOrder != null) {
            return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields),
                    createSet(facetFields), start, count, sortOrder, adapt(wrapperFacets));
        }
        
        if (start != null && count != null && facets != null && sortOrder == null) {
            return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields),
                    createSet(facetFields), start, count, adapt(wrapperFacets));
        }
        if (start != null && count != null && facets == null && sortOrder != null) {
            return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields),
                    createSet(facetFields), start, count, sortOrder);
        }
        if (start == null && count == null && facets != null && sortOrder != null) {
            return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields),
                    createSet(facetFields), sortOrder, adapt(wrapperFacets));
        }
        
        if (start != null && count != null && facets == null && sortOrder == null) {
            return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields),
                    createSet(facetFields), start, count);
        }
        if (start == null && count == null && facets != null && sortOrder == null) {
            return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields),
                    createSet(facetFields), adapt(wrapperFacets));
        }
        if (start == null && count == null && facets == null && sortOrder != null) {
            return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields),
                    createSet(facetFields), sortOrder);
        }
        
        return getClient(accessToken, accessTokenSecret).searchPeople(searchParameters, createSet(profileFields),
                    createSet(facetFields));
    }
    
    /**
     * Post network update. For details see <a href="http://developer.linkedin.com/documents/post-network-update"
     * >http://developer.linkedin.com/documents/post-network-update</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:post-network-update}
     * {@sample.java
     * ../../../doc/linkedin-connector.java.sample linkedin:postNetworkUpdate}
     *
     * @param updateText
     *            the update text
     */
    @Processor
    public void postNetworkUpdate(String updateText) {
        getClient(accessToken, accessTokenSecret).postNetworkUpdate(updateText);
    }

	/**
	 * Post comment. For details see <a href="http://developer.linkedin.com/documents/commenting-reading-comments-and-likes-network-updates"
	 * >http://developer.linkedin.com/documents/commenting-reading-comments-and-likes-network-updates</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:post-comment} 
	 * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:postComment}
	 * 
	 * @param networkUpdateId
	 *            the network update id
	 * @param commentText
	 *            the comment text
	 */
	@Processor
	public void postComment(String networkUpdateId, String commentText) {
		getClient(accessToken, accessTokenSecret).postComment(networkUpdateId, commentText);
	}

	/**
	 * Like post. For details see <a href="http://developer.linkedin.com/documents/commenting-reading-comments-and-likes-network-updates"
	 * >http://developer.linkedin.com/documents/commenting-reading-comments-and-likes-network-updates</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:like-post} 
	 * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:likePost}
	 * 
	 * @param networkUpdateId
	 *            the network update id
	 */
	@Processor
	public void likePost(String networkUpdateId) {
		getClient(accessToken, accessTokenSecret).likePost(networkUpdateId);
	}

	/**
	 * Unlike post. For details see <a href="http://developer.linkedin.com/documents/commenting-reading-comments-and-likes-network-updates"
	 * >http://developer.linkedin.com/documents/commenting-reading-comments-and-likes-network-updates</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:unlike-post} 
	 * {@sample.java
	 * ../../../doc/linkedin-connector.java.sample linkedin:unlikePost}
	 * 
	 * @param networkUpdateId
	 *            the network update id
	 */
	@Processor
	public void unlikePost(String networkUpdateId) {
		getClient(accessToken, accessTokenSecret).unlikePost(networkUpdateId);
	}

	/*
	/**
	 * Update current status. For details see <a href="http://developer.linkedin.com/documents/status-update-api"
	 * >http://developer.linkedin.com/documents/status-update-api</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:update-current-status}
	 * {@sample.java
	 * ../../../doc/linkedin-connector.java.sample linkedin:updateCurrentStatus}
	 * 
	 * @param status
	 *            the status
	 * @param postToTwitter
	 *            whether to post the update to Twitter
	 */
	//XXX: This API is deprecated
	/*@Processor
	public void updateCurrentStatus(String status, @Optional @Default("false") Boolean postToTwitter) {
		getClient(accessToken, accessTokenSecret).updateCurrentStatus(status, postToTwitter);
	}*/

	/**
	 * Send message. For details see <a href="http://developer.linkedin.com/documents/messaging-between-connections-api"
	 * >http://developer.linkedin.com/documents/messaging-between-connections-api</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:send-message} 
	 * {@sample.java
	 * ../../../doc/linkedin-connector.java.sample linkedin:sendMessage}
	 * 
	 * @param recipientIds
	 *            the recepient ids
	 * @param subject
	 *            the subject
	 * @param message
	 *            the message
	 */
	@Processor
	public void sendMessage(@Placement(group = "Recipient IDs") List<String> recipientIds, String subject, String message) {
		getClient(accessToken, accessTokenSecret).sendMessage(recipientIds, subject, message);
	}

	/**
	 * Send invite. For details see <a href="http://developer.linkedin.com/documents/invitation-api"
	 * >http://developer.linkedin.com/documents/invitation-api</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:send-invite-by-email}
	 * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:sendInviteByEmail}
	 * 
	 * @param email
	 *            the email
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 * @param subject
	 *            the subject
	 * @param message
	 *            the message
	 */
	@Processor
	public void sendInviteByEmail(String email, String firstName, String lastName, String subject, String message) {
		getClient(accessToken, accessTokenSecret).sendInviteByEmail(email, firstName, lastName, subject, message);
	}
	
	/**
	 * Post share. For details see <a href="http://developer.linkedin.com/documents/share-api"
	 * >http://developer.linkedin.com/documents/share-api</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:post-share} 
	 * {@sample.java
	 * ../../../doc/linkedin-connector.java.sample linkedin:postShare}
	 * 
	 * @param commentText
	 *            the comment text
	 * @param title
	 *            the title
	 * @param description
	 * 			  the description
	 * @param url
	 *            the url
	 * @param imageUrl
	 *            the image url
	 * @param visibility
	 *            the visibility
	 * @param postToTwitter
	 *            whether to post to twitter
	 */
	@Processor
	public void postShare(String commentText, String title, @Optional String description,String url, 
						String imageUrl, LocalVisibilityType visibility,
						@Optional @Default("false") Boolean postToTwitter) {
		getClient(accessToken, accessTokenSecret).postShare(commentText, title, description,url, imageUrl, 
		        VisibilityType.fromValue(visibility.value()), postToTwitter);
	}

	/**
	 * Re-share. For details see <a href="http://developer.linkedin.com/documents/share-api"
	 * >http://developer.linkedin.com/documents/share-api</a>
	 * <p/>
	 * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:re-share} 
	 * {@sample.java
	 * ../../../doc/linkedin-connector.java.sample linkedin:reShare}
	 * 
	 * @param shareId
	 *            the share id
	 * @param commentText
	 *            the comment text
	 * @param visibility
	 *            the visibility
	 */
	@Processor
	public void reShare(String shareId, String commentText, LocalVisibilityType visibility) {
		getClient(accessToken, accessTokenSecret).reShare(shareId, commentText, 
		        VisibilityType.fromValue(visibility.value()));
	}

    /**
     * Gets the group by id. For details see <a href="http://developer.linkedin.com/documents/groups-api"
     * >http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-group-by-id}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getGroupById}
     *
     * @param id
     *            The unique identifier for a LinkedIn group
     * @param groupFields
     *            The group fields to retrieve
     * @return Group
     */
    @Processor
    public Group getGroupById(String id,
                              @Placement(group = "Group Field") @Optional List<GroupField> groupFields){
        if (groupFields == null){
            return getClient(accessToken, accessTokenSecret).getGroupById(id);
        } else {
            return getClient(accessToken, accessTokenSecret).getGroupById(id, createSet(groupFields));
        }
    }
    
    /**
     * Get Group Memberships. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-group-memberships}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getGroupMemberships}
     * 
     * @param groupId
     * 		the unique identifier for a group
     * @param groupMembershipFields
     * 		the group membership fields to retrieve
     * @param start
     * 		record index at which to start pagination 		
     * @param count
     * 		number of records to return
     * @return Group Membership
     */
    @Processor
    public GroupMemberships getGroupMemberships(@Optional String groupId, 
	    		@Placement(group = "Group Membership Field") @Optional List<GroupMembershipField> groupMembershipFields,    		
	    		@Optional Integer start, 
	    		@Optional Integer count){
    	if(groupId != null && groupMembershipFields !=null && start != null && count != null)
    		return getClient(accessToken, accessTokenSecret).getGroupMemberships(groupId, createSet(groupMembershipFields), start, count);
    	if(groupMembershipFields !=null && start != null && count != null)
    		return getClient(accessToken, accessTokenSecret).getGroupMemberships(createSet(groupMembershipFields), start, count);
    	if(groupId != null && groupMembershipFields != null)
    		return getClient(accessToken, accessTokenSecret).getGroupMemberships(groupId, createSet(groupMembershipFields));
    	if(groupId != null)
    		return getClient(accessToken, accessTokenSecret).getGroupMemberships(groupId);
    	if(groupMembershipFields != null)
    		return getClient(accessToken, accessTokenSecret).getGroupMemberships(createSet(groupMembershipFields));
    	return getClient(accessToken, accessTokenSecret).getGroupMemberships();    	
    }
    
    /*
    /**
     * Update group membership. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:update-group-membership}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:updateGroupMembership}
     * @param groupId
     * 		the unique identifier for a LinkedIn group
     * @param contactEmail
     * 		the contact email address
     * @param emailFrequencyCode
     * 		indicates the frequency at which the member receives group emails
     * @param showLogoInProfile
     * 		specifies whether or not to show the group logo in the member's profile
     * @param emailAnnouncements
     * 		indicates if the member allows email messages from group managers
     * @param allowMessagesFromMembers
     * 		indicates if the member allows email contact from non-members
     * @param emailForEveryPost
     * 		indicates if the member wants to receive an email message for each new post
     */
    //FIXME: Incorrect implementation in BaseLinkedInApiClient 
    /*@Processor
    public void updateGroupMembership(String groupId, 
									String contactEmail, 
									EmailDigestFrequencyCode emailFrequencyCode,
									boolean showLogoInProfile, 
									boolean emailAnnouncements, 
									boolean allowMessagesFromMembers, 
									boolean emailForEveryPost){
    	getClient(accessToken, accessTokenSecret).updateGroupMembership(groupId, contactEmail, emailFrequencyCode, showLogoInProfile, emailAnnouncements, allowMessagesFromMembers, emailForEveryPost);
    }*/
    
    /**
     * Join any LinkedIn group that is listed in the LinkedIn.com Groups Directory. 
     * For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:join-group}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:joinGroup}
     * 
     * @param groupId
     * 		the unique identifier for a LinkedIn group
     */
    @Processor
    public void joinGroup(String groupId){
    	getClient(accessToken, accessTokenSecret).joinGroup(groupId);
    }
    
    /**
     * Leave group. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:leave-group}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:leaveGroup}
     * @param groupId
     * 		the unique identifier for a LinkedIn group
     */
    @Processor
    public void leaveGroup(String groupId){
    	getClient(accessToken, accessTokenSecret).leaveGroup(groupId);
    }
    
    /**
     * Get post by groups. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-posts-by-group}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getPostsByGroup}
     * @param groupId
     * 		the unique identifier for a LinkedIn group
     * @param postFields
     * 		the post fields to retrieve
     * @param start
     * 		record index at which to start pagination 		
     * @param count
     * 		number of records to return
     * @param order
     * 		sort order for posts
     * @param category
     * 		category of posts
     * @param modifiedSince
     * 		timestamp filter for posts created after the specified value
     * @return post by groups
     */
    @Processor
    public Posts getPostsByGroup(String groupId,
    					@Placement(group = "Post Field") @Optional List<PostField> postFields, 
    					@Optional Integer start,
    					@Optional Integer count,
    					@Optional PostSortOrder order,
    					@Optional LocalPostCategoryCode category,
    					@Optional Date modifiedSince){
    	
    	//if null initialize to some default value
    	if(start == null || count == null) {
    		start = 0;
    		count = 10;
    	} 
    	if(order == null) {
    		order = PostSortOrder.RECENCY;
    	}
    	
    	PostCategoryCode wrapperCategory = null;
    	if(category != null){
    		wrapperCategory = PostCategoryCode.fromValue(category.fieldName());
    	}
    	if(postFields != null && category != null && modifiedSince !=null)
    		return getClient(accessToken, accessTokenSecret).getPostsByGroup(groupId, createSet(postFields), start, count, order, wrapperCategory, modifiedSince);
    	
    	if(postFields != null && category != null && modifiedSince ==null)
    		return getClient(accessToken, accessTokenSecret).getPostsByGroup(groupId, createSet(postFields), start, count, order, wrapperCategory);
    	if(postFields != null && category == null && modifiedSince !=null)
    		return getClient(accessToken, accessTokenSecret).getPostsByGroup(groupId, createSet(postFields), start, count, order, modifiedSince);
    	if(postFields == null && category != null && modifiedSince !=null)
    		return getClient(accessToken, accessTokenSecret).getPostsByGroup(groupId, start, count, order, wrapperCategory, modifiedSince);
    	
    	if(postFields != null && category == null && modifiedSince ==null)
    		return getClient(accessToken, accessTokenSecret).getPostsByGroup(groupId, createSet(postFields), start, count, order);
    	if(postFields == null && category != null && modifiedSince ==null)
    		return getClient(accessToken, accessTokenSecret).getPostsByGroup(groupId, start, count, order, wrapperCategory);
    	if(postFields == null && category == null && modifiedSince !=null)
    		return getClient(accessToken, accessTokenSecret).getPostsByGroup(groupId, start, count, order, modifiedSince);
    	
    	return getClient(accessToken, accessTokenSecret).getPostsByGroup(groupId, start, count, order);
    }
    
    /**
     * Get Post. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-post}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getPost}
     * 
     * @param postId
     * 		the unique identifier for a post
     * @param postFields
     * 		the post fields to retrieve
     * @return post
     */
    @Processor
    public Post getPost(String postId, 
    		@Placement(group = "Post Field") @Optional List<PostField> postFields){
    	if(postFields != null)
    		return getClient(accessToken, accessTokenSecret).getPost(postId, createSet(postFields));
    	return getClient(accessToken, accessTokenSecret).getPost(postId);
    }
    
    /**
     * Get Post Comments. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-post-comments}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getPostComments}
     * @param postId
     * 		the unique identifier for a post
     * @param commentFields
     * 		the post fields to retrieve
     * @param start
     * 		record index at which to start pagination 		
     * @param count
     * 		number of records to return
     * @return post comments
     */
    @Processor
    public Comments getPostComments(String postId, 
    		@Placement(group = "Comment Field") @Optional List<CommentField> commentFields,
    		@Optional Integer start,
    		@Optional Integer count){
    	if(commentFields != null && start != null && count != null)
    		return getClient(accessToken, accessTokenSecret).getPostComments(postId, createSet(commentFields), start, count);
    	if(start != null && count != null)
    		return getClient(accessToken, accessTokenSecret).getPostComments(postId, start, count);
    	if(commentFields != null)
    		return getClient(accessToken, accessTokenSecret).getPostComments(postId, createSet(commentFields));
    	return getClient(accessToken, accessTokenSecret).getPostComments(postId);
    }
    
    /**
     * Create a group discussion post. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:create-post}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:createPost}
     * @param groupId
     * 		the unique identifier for a group
     * @param title
     * 		the title of a post
     * @param summary
     * 		the summary of a post
     */
    @Processor
    public void createPost(String groupId, String title, String summary){
    	getClient(accessToken, accessTokenSecret).createPost(groupId, title, summary);
    }
    
    /**
     * Like a post. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:like-group-post}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:likeGroupPost}
     * @param postId
     * 		the unique identifier for a post
     */
    @Processor
    public void likeGroupPost(String postId){
    	getClient(accessToken, accessTokenSecret).likeGroupPost(postId);
    }
    
    /**
     * Unlike a post. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:unlike-group-post}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:unlikeGroupPost}
     * @param postId
     * 		the unique identifier for a post
     */
    @Processor
    public void unlikeGroupPost(String postId){
    	getClient(accessToken, accessTokenSecret).unlikeGroupPost(postId);
    }
    
    /**
     * Follow a post. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:follow-post}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:followPost}
     * @param postId
     * 		the unique identifier for a post
     */
    @Processor
    public void followPost(String postId){
    	getClient(accessToken, accessTokenSecret).followPost(postId);
    }
    
    /**
     * Unfollow a post. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:unfollow-post}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:unfollowPost}
     * @param postId
     * 		the unique identifier for a post
     */
    @Processor
    public void unfollowPost(String postId){
    	getClient(accessToken, accessTokenSecret).unfollowPost(postId);
    }
    /*
    /**
     * Flag a post. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:flag-post}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:flagPost}
     * @param postId
     * 		the unique identifier for a post
     * @param code
     * 		category of posts
     */
    //FIXME: Incorrect implementation in BaseLinkedInApiClient
    /*@Processor
    public void flagPost(String postId, PostCategoryCode code){
    	getClient(accessToken, accessTokenSecret).flagPost(postId, code);    	
    }*/
    
    /**
     * Delete a post. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:delete-post}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:deletePost}
     * @param postId
     * 		the unique identifier for a post
     */
    @Processor
    public void deletePost(String postId){
    	getClient(accessToken, accessTokenSecret).deletePost(postId);
    }
    
    /*
    /**
     * Get comment on a post. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-post-comment}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getPostComment}
     * @param commentId
     * 		the unique identifier for a comment
     * @param commentFields
     * 		the comment fields to retrieve
     * @return comment
     */
    //FIXME: Incorrect implementation in BaseLinkedInApiClient
    /*@Processor    
    public Comment getPostComment(String commentId, 
    		@Placement(group = "Comment Field") @Optional List<CommentField> commentFields){
    	if(commentFields != null)
    		return getClient(accessToken, accessTokenSecret).getPostComment(commentId, createSet(commentFields));
    	return getClient(accessToken, accessTokenSecret).getPostComment(commentId);
    }*/
    
    /**
     * Add comment on a post. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:add-post-comment}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:addPostComment}
     * @param postId
     * 		the unique identifier for a post
     * @param comment
     * 		comment to be added
     */
    @Processor
    public void addPostComment(String postId, String comment){
    	getClient(accessToken, accessTokenSecret).addPostComment(postId, comment);
    }
    
    /**
     * Delete a Comment or Flag as Inappropriate. If the actor is the creator or moderator, this deletes the comment. 
     * If the actor is a member, this flags the comment as inappropriate.
     * For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:delete-post-comment}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:deletePostComment}
     * @param commentId
     * 		the unique identifier for a comment
     */
    @Processor
    public void deletePostComment(String commentId){
    	getClient(accessToken, accessTokenSecret).deletePostComment(commentId);
    }
    
    /**
     * Get Suggested Groups for a User. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-suggested-groups}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getSuggestedGroups}
     * @param groupFields
     * 		the group fields to retrieve
     * @return suggested Groups for a User
     */
    @Processor
    public Groups getSuggestedGroups(@Placement(group = "Group Field") @Optional List<GroupField> groupFields){
    	if(groupFields != null)
    		return getClient(accessToken, accessTokenSecret).getSuggestedGroups(createSet(groupFields));
    	return getClient(accessToken, accessTokenSecret).getSuggestedGroups();
    }
    
    /**
     * Remove a Group Suggestion for a User. For details see <a href="http://developer.linkedin.com/documents/groups-api">
     * http://developer.linkedin.com/documents/groups-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:delete-group-suggestion}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:deleteGroupSuggestion}
     * @param groupId
     * 		the unique identifier for a group
     */
    @Processor
    public void deleteGroupSuggestion(String groupId){
    	getClient(accessToken, accessTokenSecret).deleteGroupSuggestion(groupId);
    }
    
    /**
     * Retrieve a company by using the company ID. For details see <a href="https://developer.linkedin.com/documents/company-lookup-api-and-fields">
     * https://developer.linkedin.com/documents/company-lookup-api-and-fields</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-company-by-id}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getCompanyById}
     * @param id
     * 		the unique internal numeric company identifier
     * @param companyFields
     * 		the company fields to retrieve
     * @return company
     */
    @Processor
    public Company getCompanyById(String id, 
    		@Placement(group = "Company Fields") @Optional List<CompanyField> companyFields){
    	if(companyFields != null)
    		return getClient(accessToken, accessTokenSecret).getCompanyById(id, createSet(companyFields));
    	return getClient(accessToken, accessTokenSecret).getCompanyById(id);
    }
    
    /**
     * Retrieve a company by universal-name. For details see <a href="https://developer.linkedin.com/documents/company-lookup-api-and-fields">
     * https://developer.linkedin.com/documents/company-lookup-api-and-fields</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-company-by-universal-name}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getCompanyByUniversalName}
     * @param universalName
     * 		the unique string identifier for a company
     * @param companyFields
     * 		the company fields to retrieve
     * @return company
     */
    @Processor
    public Company getCompanyByUniversalName(String universalName, 
    		@Placement(group = "Company Fields") @Optional List<CompanyField> companyFields){
    	if(companyFields != null)
    		return getClient(accessToken, accessTokenSecret).getCompanyByUniversalName(universalName, createSet(companyFields));
    	return getClient(accessToken, accessTokenSecret).getCompanyByUniversalName(universalName);
    }
    
    /**
     * Email-domain collection filter to get all matching companies. 
     * For details see <a href="https://developer.linkedin.com/documents/company-lookup-api-and-fields">
     * https://developer.linkedin.com/documents/company-lookup-api-and-fields</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-companies-by-email-domain}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getCompaniesByEmailDomain}
     * @param emailDomain
     * 		the company email domains
     * @param companyFields
     * 		the company fields to retrieve
     * @return all matching companies
     */
    @Processor
    public Companies getCompaniesByEmailDomain(String emailDomain, 
    		@Placement(group = "Company Fields") @Optional List<CompanyField> companyFields){
    	if(companyFields != null)
    		return getClient(accessToken, accessTokenSecret).getCompaniesByEmailDomain(emailDomain, createSet(companyFields));
    	return getClient(accessToken, accessTokenSecret).getCompaniesByEmailDomain(emailDomain);
    }
    
    /**
     * Retrieve a list of companies a member is following.
     * For details see <a href="https://developer.linkedin.com/documents/company-lookup-api-and-fields">
     * https://developer.linkedin.com/documents/company-lookup-api-and-fields</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-followed-companies}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getFollowedCompanies}
     * @param companyFields
     * 		the company fields to retrieve
     * @return list of companies a member is following
     */
    @Processor
    public Companies getFollowedCompanies(@Placement(group = "Company Fields") @Optional List<CompanyField> companyFields){
    	if(companyFields !=  null)
    		return getClient(accessToken, accessTokenSecret).getFollowedCompanies(createSet(companyFields));
    	return getClient(accessToken, accessTokenSecret).getFollowedCompanies();
    }
    
    /**
     * Start Following a Company. For details see <a href="https://developer.linkedin.com/documents/company-follow-and-suggestions">
     * https://developer.linkedin.com/documents/company-follow-and-suggestions</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:follow-company}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:followCompany}
     * @param id
     * 		the unique internal numeric company identifier
     */
    @Processor
    public void followCompany(String id){
    	getClient(accessToken, accessTokenSecret).followCompany(id);
    }
    
    /**
     * Stop following a company. For details see <a href="https://developer.linkedin.com/documents/company-follow-and-suggestions">
     * https://developer.linkedin.com/documents/company-follow-and-suggestions</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:unfollow-company}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:unfollowCompany}
     * @param id
     * 		the unique internal numeric company identifier
     */
    @Processor
    public void unfollowCompany(String id){
    	getClient(accessToken, accessTokenSecret).unfollowCompany(id);
    }
    
    /**
     * Suggested Companies to Follow. For details see <a href="https://developer.linkedin.com/documents/company-follow-and-suggestions">
     * https://developer.linkedin.com/documents/company-follow-and-suggestions</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-suggested-companies}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getSuggestedCompanies}
     * @param companyFields
     * 		the company fields to retrieve
     * @return suggested companies to follow
     */
    @Processor
    public Companies getSuggestedCompanies(@Placement(group = "Company Fields") @Optional List<CompanyField> companyFields){
    	if(companyFields != null)
    		return getClient(accessToken, accessTokenSecret).getSuggestedCompanies(createSet(companyFields));
    	return getClient(accessToken, accessTokenSecret).getSuggestedCompanies();
    }
    
    /**
     * Return a list of products and services supported by a company.
     * For details see <a href="https://developer.linkedin.com/documents/company-products-and-recommendations">
     * https://developer.linkedin.com/documents/company-products-and-recommendations</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-company-products}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getCompanyProducts}
     * @param id
     * 		the unique internal numeric company identifier
     * @param productFields
     * 		the product fields to retrieve
     * @param start
     * 		record index at which to start pagination 		
     * @param count
     * 		number of records to return
     * @return list of products and services supported by a company
     */
    @Processor
    public Products getCompanyProducts(String id, 
    		@Placement(group = "Product Fields") @Optional List<ProductField> productFields, 
    		@Optional Integer start, 
    		@Optional Integer count){
    	if(productFields != null && start != null && count != null)
    		return getClient(accessToken, accessTokenSecret).getCompanyProducts(id, createSet(productFields), start, count);    		
    	if(start != null && count != null)
    		return getClient(accessToken, accessTokenSecret).getCompanyProducts(id, start, count);
    	if(productFields != null)
    		return getClient(accessToken, accessTokenSecret).getCompanyProducts(id, createSet(productFields));
    	return getClient(accessToken, accessTokenSecret).getCompanyProducts(id);
    }
    
    /**
     * Search Companies. For details see <a href="https://developer.linkedin.com/documents/company-search">
     * https://developer.linkedin.com/documents/company-search</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:search-companies}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:searchCompanies}
     * @param searchParameters
     * 		the search parameters to use
     * @param companyFields
     * 		the company fields to retrieve
     * @param start
     * 		record index at which to start pagination 		
     * @param count
     * 		number of records to return
     * @param sortOrder
     * 		the sort order to use, defaults to RELEVANCE
     * @return companies
     */
    @Processor
	public Companies searchCompanies(@Optional @Placement(group = "Search Parameters") Map<SearchParameter, String> searchParameters,
			@Optional @Placement(group = "Company Fields") List<CompanyField> companyFields, @Optional Integer start, @Optional Integer count,
			@Optional @Default(value = "RELEVANCE") SearchSortOrder sortOrder) {
		
		if (searchParameters != null && companyFields != null && start != null && count != null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields), start, count,
					sortOrder);
		}
		
		if (searchParameters != null && companyFields != null && start != null && count != null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields), start, count);
		}
		if (searchParameters != null && companyFields != null && start == null && count == null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields), sortOrder);
		}
		if (searchParameters != null && companyFields == null && start != null && count != null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, start, count,
					sortOrder);
		}
		
		if (searchParameters != null && companyFields != null && start == null && count == null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields));
		}
		if (searchParameters != null && companyFields == null && start != null && count != null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, start, count);
		}
		if (searchParameters != null && companyFields == null && start == null && count == null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, sortOrder);
		}
		
		if (searchParameters != null && companyFields == null && start == null && count == null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters);
		}
		
		return getClient(accessToken, accessTokenSecret).searchCompanies();
	}
    
    /**
     * Search Companies. For details see <a href="https://developer.linkedin.com/documents/company-search">
     * https://developer.linkedin.com/documents/company-search</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:search-companies-with-facets}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:searchCompaniesWithFacets}
     * @param searchParameters
     * 		the search parameters to use
     * @param companyFields
     * 		the company fields to retrieve
     * @param start
     * 		record index at which to start pagination 		
     * @param count
     * 		number of records to return
     * @param sortOrder
     * 		the sort order to use, defaults to RELEVANCE
     * @param facets
     * 		the facet type and a comma separated string with all the values
     * @return companies
     */
    @Processor
	public Companies searchCompaniesWithFacets(@Placement(group = "Search Parameters") Map<SearchParameter, String> searchParameters,
	        @Placement(group = "Company Fields") @Optional List<CompanyField> companyFields, 
	        @Optional Integer start, @Optional Integer count, @Optional @Default(value = "RELEVANCE") SearchSortOrder sortOrder, 
	        @Placement(group = "Facets") Map<LocalFacetType, String> facets) {
		
    	Map<FacetType, String> wrapperFacets = null;
		if(facets != null){
			wrapperFacets = convertFromMulesoftFacetToGoogleFacet(facets);
		}
		
		if (companyFields != null && start != null && count != null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields), start, count,
					sortOrder, adapt(wrapperFacets));
		}
		
		if (companyFields != null && start != null && count != null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields), start, count,
					adapt(wrapperFacets));
		}		
		if (companyFields != null && start == null && count == null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields), sortOrder, 
					adapt(wrapperFacets));
		}
		if (companyFields == null && start != null && count != null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, start, count,
					sortOrder, adapt(wrapperFacets));
		}
		
		if (companyFields != null && start == null && count == null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields), adapt(wrapperFacets));
		}
		if (companyFields == null && start != null && count != null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, start, count, adapt(wrapperFacets));
		}
		if (companyFields == null && start == null && count == null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, sortOrder, adapt(wrapperFacets));
		}
		
		return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, adapt(wrapperFacets));
		
	}
    
    /**
     * Search Companies. For details see <a href="https://developer.linkedin.com/documents/company-search">
     * https://developer.linkedin.com/documents/company-search</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:search-companies-with-facet-fields}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:searchCompaniesWithFacetFields}
     * @param searchParameters
     * 		the search parameters to use
     * @param companyFields
     * 		the company fields to retrieve
     * @param facetFields
     * 		the facet fields to use
     * @param start
     * 		record index at which to start pagination 		
     * @param count
     * 		Nnumber of records to return
     * @param sortOrder
     * 		the sort order to use, defaults to RELEVANCE
     * @param facets
     * 		the facets to use
     * @return company
     */
    @Processor
    public CompanySearch searchCompaniesWithFacetFields(@Placement(group = "Search Parameters") Map<SearchParameter, String> searchParameters,
                                                    @Placement(group = "Company Fields") List<CompanyField> companyFields, @Placement(group = "Facet Fields") List<FacetField> facetFields,
                                                    @Optional Integer start, @Optional Integer count, @Optional @Default(value = "RELEVANCE") SearchSortOrder sortOrder,
                                                    @Placement(group = "Facets") @Optional Map<LocalFacetType, String> facets) {
    	Map<FacetType, String> wrapperFacets = null;
		if(facets != null){
			wrapperFacets = convertFromMulesoftFacetToGoogleFacet(facets);
		}
		
		if (start != null && count != null && facets != null && sortOrder != null) {
            return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields),
                    createSet(facetFields), start, count, sortOrder, adapt(wrapperFacets));
        }
        
        if (start != null && count != null && facets != null && sortOrder == null) {
            return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields),
                    createSet(facetFields), start, count, adapt(wrapperFacets));
        }
        if (start != null && count != null && facets == null && sortOrder != null) {
            return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields),
                    createSet(facetFields), start, count, sortOrder);
        }
        if (start == null && count == null && facets != null && sortOrder != null) {
            return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields),
                    createSet(facetFields), sortOrder, adapt(wrapperFacets));
        }
        
        if (start != null && count != null && facets == null && sortOrder == null) {
            return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields),
                    createSet(facetFields), start, count);
        }
        if (start == null && count == null && facets != null && sortOrder == null) {
            return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields),
                    createSet(facetFields), adapt(wrapperFacets));
        }
        if (start == null && count == null && facets == null && sortOrder != null) {
            return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields),
                    createSet(facetFields), sortOrder);
        }
        
        return getClient(accessToken, accessTokenSecret).searchCompanies(searchParameters, createSet(companyFields),
                    createSet(facetFields));
    }
    
    
    /**
     * Retrieve a job by using the job ID. For details see <a href="https://developer.linkedin.com/documents/job-lookup-api-and-fields">
     * https://developer.linkedin.com/documents/job-lookup-api-and-fields</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-job-by-id}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getJobById}
     * @param id
     * 		the unique identifier for a job
     * @param jobFields
     * 		the job fields to retrieve
     * @return job
     */
    @Processor
    public Job getJobById(String id, 
    		@Placement(group = "Job Fields") @Optional List<JobField> jobFields){
    	if(jobFields != null)
    		return getClient(accessToken, accessTokenSecret).getJobById(id, createSet(jobFields));
    	return getClient(accessToken, accessTokenSecret).getJobById(id);
    }
    
    /**
     * Retrieving a list of Bookmarked Jobs for a Member.
     * For details see <a href="https://developer.linkedin.com/documents/job-bookmarks-and-suggestions">
     * https://developer.linkedin.com/documents/job-bookmarks-and-suggestions</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-job-bookmarks}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getJobBookmarks}
     * @param jobFields
     * 		the job fields to retrieve
     * @return list of Bookmarked Jobs for a Member
     */
    @Processor
    public JobBookmarks getJobBookmarks(@Placement(group = "Job Fields") @Optional List<JobField> jobFields){
    	if(jobFields != null)
    		return getClient(accessToken, accessTokenSecret).getJobBookmarks(createSet(jobFields));
    	return getClient(accessToken, accessTokenSecret).getJobBookmarks();
    }
    /*
    /**
     * Bookmarking a Job. For details see <a href="https://developer.linkedin.com/documents/job-bookmarks-and-suggestions">
     * https://developer.linkedin.com/documents/job-bookmarks-and-suggestions</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:bookmark-job}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:bookmarkJob}
     * @param jobId
     * 		the unique identifier for a job
     */
    //FIXME: Incorrect implementation in BaseLinkedInApiClient
    /*@Processor    
    public void bookmarkJob(String jobId){
    	getClient(accessToken, accessTokenSecret).bookmarkJob(jobId);
    }*/
    
    /**
     * Deleting a Job Bookmark. For details see <a href="https://developer.linkedin.com/documents/job-bookmarks-and-suggestions">
     * https://developer.linkedin.com/documents/job-bookmarks-and-suggestions</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:unbookmark-job}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:unbookmarkJob}
     * @param jobId
     * 		the unique identifier for a job
     */
    @Processor
    public void unbookmarkJob(String jobId){
    	getClient(accessToken, accessTokenSecret).unbookmarkJob(jobId);
    }
    
    /**
     * Retrieving a List of a Member's Suggested Jobs.
     * For details see <a href="https://developer.linkedin.com/documents/job-bookmarks-and-suggestions">
     * https://developer.linkedin.com/documents/job-bookmarks-and-suggestions</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:get-job-suggestions}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:getJobSuggestions}
     * @param jobFields
     * 		the job fields to retrieve
     * @return jobs
     */
    @Processor
    public Jobs getJobSuggestions(@Placement(group = "Job Fields") @Optional List<JobField> jobFields){
    	if(jobFields != null)
    		return getClient(accessToken, accessTokenSecret).getJobSuggestions(createSet(jobFields));
    	return getClient(accessToken, accessTokenSecret).getJobSuggestions();
    }
    
    /**
     * Post a Job.
     * For details see <a href="http://developer.linkedin.com/documents/posting-job">
     * http://developer.linkedin.com/documents/posting-job</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:post-job}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:postJob}
     * @param job
     * 		the job
     */
    @Processor
    public void postJob(Job job){
    	getClient(accessToken, accessTokenSecret).postJob(job);
    }
    
    /**
     * Update a Job.
     * For details see <a href="http://developer.linkedin.com/documents/editing-job">
     * http://developer.linkedin.com/documents/editing-job</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:update-job}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:updateJob}
     * @param partnerJobId
     * 		the partner job id
     * @param job
     * 		the job
     */
    @Processor
    public void updateJob(String partnerJobId, Job job){
    	getClient(accessToken, accessTokenSecret).updateJob(partnerJobId, job);
    }
    
    /**
     * Renew a Job.
     * For details see <a href="http://developer.linkedin.com/documents/renewing-job">
     * http://developer.linkedin.com/documents/renewing-job</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:renew-job}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:renewJob}
     * @param partnerJobId
     * 		the partner job id
     * @param contractId
     * 		the contract id
     */
    @Processor
    public void renewJob(String partnerJobId, String contractId){
    	getClient(accessToken, accessTokenSecret).renewJob(partnerJobId, contractId);
    }
    
    /**
     * Close a Job.
     * For details see <a href="http://developer.linkedin.com/documents/closing-job">
     * http://developer.linkedin.com/documents/closing-job</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:close-job}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:closeJob}
     * @param partnerJobId
     * 		the partner job id
     */
    @Processor
    public void closeJob(String partnerJobId){
    	getClient(accessToken, accessTokenSecret).closeJob(partnerJobId);
    }
    
    /**
     * Search Jobs. For details see <a href="https://developer.linkedin.com/documents/job-search-api">
     * https://developer.linkedin.com/documents/job-search-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:search-jobs}
     * @param searchParameters
     * 		the search parameters
     * @param jobFields
     * 		the job fields to retrieve
     * @param start
     * 		record index at which to start pagination 		
     * @param count
     * 		number of records to return
     * @return jobs
     */
    /*@Processor
	public Jobs searchJobs(@Optional @Placement(group = "Search Parameters") Map<SearchParameter, String> searchParameters,
			@Optional @Placement(group = "Job Fields") List<JobField> jobFields, @Optional Integer start, @Optional Integer count,
			@Optional @Default(value = "RELEVANCE") SearchSortOrder sortOrder) {
		
		if (searchParameters != null && jobFields != null && start != null && count != null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields), start, count,
					sortOrder);
		}
		
		if (searchParameters != null && jobFields != null && start != null && count != null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields), start, count);
		}
		if (searchParameters != null && jobFields != null && start == null && count == null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields), sortOrder);
		}
		if (searchParameters != null && jobFields == null && start != null && count != null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, start, count,
					sortOrder);
		}
		
		if (searchParameters != null && jobFields != null && start == null && count == null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields));
		}
		if (searchParameters != null && jobFields == null && start != null && count != null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, start, count);
		}
		if (searchParameters != null && jobFields == null && start == null && count == null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, sortOrder);
		}
		
		if (searchParameters != null && jobFields == null && start == null && count == null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters);
		}
		
		return getClient(accessToken, accessTokenSecret).searchJobs();
	}*/
    @Processor
	public Jobs searchJobs(@Optional @Placement(group = "Search Parameters") Map<SearchParameter, String> searchParameters,
			@Optional @Placement(group = "Job Fields") List<JobField> jobFields, @Optional Integer start, @Optional Integer count) {
		
		if (searchParameters != null && jobFields != null && start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields), start, count);
		}
		
		if (searchParameters != null && jobFields != null && start == null && count == null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields));
		}
		if (searchParameters != null && jobFields == null && start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, start, count);
		}
		if (searchParameters != null && jobFields == null && start == null && count == null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters);
		}
		
		return getClient(accessToken, accessTokenSecret).searchJobs();
	}
    
    /**
     * Search Jobs. For details see <a href="https://developer.linkedin.com/documents/job-search-api">
     * https://developer.linkedin.com/documents/job-search-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:search-jobs-with-facets}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:searchJobsWithFacets}
     * @param searchParameters
     * 		the search parameters
     * @param jobFields
     * 		the job fields to retrieve
     * @param start
     * 		record index at which to start pagination 		
     * @param count
     * 		number of records to return
     * @param facets
     * 		the facet type and a comma separated string with all the values
     * @return jobs
     */
    //FIXME: Incorrect implementation in BaseLinkedInApiClient
    /*@Processor
    public Jobs searchJobsWithFacets(@Placement(group = "Search Parameters") Map<SearchParameter, String> searchParameters,
	        @Placement(group = "Job Fields") @Optional List<JobField> jobFields, 
	        @Optional Integer start, @Optional Integer count, @Optional @Default(value = "RELEVANCE") SearchSortOrder sortOrder, 
	        @Placement(group = "Facets") Map<FacetType, String> facets) {
		
		if (jobFields != null && start != null && count != null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields), start, count,
					sortOrder, adapt(facets));
		}
		
		if (jobFields != null && start != null && count != null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields), start, count,
					adapt(facets));
		}		
		if (jobFields != null && start == null && count == null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields), sortOrder, 
					adapt(facets));
		}
		if (jobFields == null && start != null && count != null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, start, count,
					sortOrder, adapt(facets));
		}
		
		if (jobFields != null && start == null && count == null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields), adapt(facets));
		}
		if (jobFields == null && start != null && count != null && sortOrder == null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, start, count, adapt(facets));
		}
		if (jobFields == null && start == null && count == null && sortOrder != null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, sortOrder, adapt(facets));
		}
		
		return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, adapt(facets));
		
	}*/
    
    @Processor
    public Jobs searchJobsWithFacets(@Placement(group = "Search Parameters") Map<SearchParameter, String> searchParameters,
	        @Placement(group = "Job Fields") @Optional List<JobField> jobFields, 
	        @Optional Integer start, @Optional Integer count, @Placement(group = "Facets") Map<LocalFacetType, String> facets) {
		
    	Map<FacetType, String> wrapperFacets = null;
		if(facets != null){
			wrapperFacets = convertFromMulesoftFacetToGoogleFacet(facets);
		}
		
		if (jobFields != null && start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields), start, count,
					adapt(wrapperFacets));
		}		
		
		if (jobFields != null && start == null && count == null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields), adapt(wrapperFacets));
		}
		if (jobFields == null && start != null && count != null) {
			return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, start, count, adapt(wrapperFacets));
		}		
		return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, adapt(wrapperFacets));
		
	}
    
    /**
     * Search Jobs. For details see <a href="https://developer.linkedin.com/documents/job-search-api">
     * https://developer.linkedin.com/documents/job-search-api</a>
     * <p/>
     * {@sample.xml ../../../doc/linkedin-connector.xml.sample linkedin:search-jobs-with-facet-fields}
     * {@sample.java ../../../doc/linkedin-connector.java.sample linkedin:searchJobsWithFacetFields}
     * @param searchParameters
     * 		the search parameters
     * @param jobFields
     * 		the job fields to retrieve
     * @param facetFields
     * 		the facet fields to use
     * @param start
     * 		record index at which to start pagination 		
     * @param count
     * 		number of records to return
     * @param facets
     * 		the facets to use
     * @return job
     */
    //FIXME: Incorrect implementation in BaseLinkedInApiClient
    /*@Processor
    public JobSearch searchJobsWithFacetFields(@Placement(group = "Search Parameters") Map<SearchParameter, String> searchParameters,
                                                    @Placement(group = "Job Fields") List<JobField> jobFields, @Placement(group = "Facet Fields") List<FacetField> facetFields,
                                                    @Optional Integer start, @Optional Integer count, @Optional @Default(value = "RELEVANCE") SearchSortOrder sortOrder,
                                                    @Placement(group = "Facets") @Optional Map<FacetType, String> facets) {
        if (start != null && count != null && facets != null && sortOrder != null) {
            return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields),
                    createSet(facetFields), start, count, sortOrder, adapt(facets));
        }
        
        if (start != null && count != null && facets != null && sortOrder == null) {
            return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields),
                    createSet(facetFields), start, count, adapt(facets));
        }
        if (start != null && count != null && facets == null && sortOrder != null) {
            return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields),
                    createSet(facetFields), start, count, sortOrder);
        }
        if (start == null && count == null && facets != null && sortOrder != null) {
            return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields),
                    createSet(facetFields), sortOrder, adapt(facets));
        }
        
        if (start != null && count != null && facets == null && sortOrder == null) {
            return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields),
                    createSet(facetFields), start, count);
        }
        if (start == null && count == null && facets != null && sortOrder == null) {
            return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields),
                    createSet(facetFields), adapt(facets));
        }
        if (start == null && count == null && facets == null && sortOrder != null) {
            return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields),
                    createSet(facetFields), sortOrder);
        }
        
        return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields),
                    createSet(facetFields));
    }*/
    @Processor
    public JobSearch searchJobsWithFacetFields(@Placement(group = "Search Parameters") Map<SearchParameter, String> searchParameters,
                                                    @Placement(group = "Job Fields") List<JobField> jobFields, @Placement(group = "Facet Fields") List<FacetField> facetFields,
                                                    @Optional Integer start, @Optional Integer count, @Placement(group = "Facets") @Optional Map<LocalFacetType, String> facets) {

    	Map<FacetType, String> wrapperFacets = null;
		if(facets != null){
			wrapperFacets = convertFromMulesoftFacetToGoogleFacet(facets);
		}
		
		if (start != null && count != null && facets != null) {
            return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields),
                    createSet(facetFields), start, count, adapt(wrapperFacets));
        }
        
        if (start != null && count != null && facets == null) {
            return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields),
                    createSet(facetFields), start, count);
        }
        if (start == null && count == null && facets != null) {
            return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields),
                    createSet(facetFields), adapt(wrapperFacets));
        }
        
        return getClient(accessToken, accessTokenSecret).searchJobs(searchParameters, createSet(jobFields),
                    createSet(facetFields));
    }
    
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    private <T> Set<T> createSet(List<T> list) {
        Set<T> set = new HashSet<T>(list.size());
        set.addAll(list);
        return set;
    }

    private List<Parameter<FacetType, String>> adapt(Map<FacetType, String> facets) {
        List<Parameter<FacetType, String>> facetList = new ArrayList<Parameter<FacetType, String>>();
        for (Map.Entry<FacetType, String> entry : facets.entrySet()) {
            String facetValuesCommaSeparated = entry.getValue();
            for (String facetValue : facetValuesCommaSeparated.split(",")) {
                facetList.add(new Parameter<FacetType, String>(entry.getKey(), facetValue));
            }
        }
        return facetList;
    }
    
    private Map<FacetType, String> convertFromMulesoftFacetToGoogleFacet(Map<LocalFacetType, String> facets){
    	Map<FacetType, String> wrapperFacets = new HashMap<FacetType, String>();
    	
    	Iterator<Entry<LocalFacetType, String>> it = facets.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            FacetType ft = FacetType.fromValue(((LocalFacetType)pairs.getKey()).fieldName());
            wrapperFacets.put(ft, (String)pairs.getValue());
        }
        return wrapperFacets;
    }

    private synchronized LinkedInApiClient getClient(String accessToken, String accessTokenSecret) {
        if (client == null) {
        	client = LinkedInClientFactory.getClient(apiKey, apiSecret, accessToken, accessTokenSecret);
        }
        return client;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    public void setAccessTokenSecret(String accessTockenSecret) {
        this.accessTokenSecret = accessTockenSecret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
