/**
 * Copyright (c) MuleSoft, Inc. All rights reserved. http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */
package org.mule.modules.linkedin.enumeration;

import java.util.HashMap;
import java.util.Map;

import com.google.code.linkedinapi.client.enumeration.FieldEnum;
/**
 * Class used as wrapper for {@link com.google.code.linkedinapi.schema.FacetType}
 * @author Mulesoft Inc
 *
 */
public enum LocalFacetType implements FieldEnum{
	LOCATION("location"),
    INDUSTRY("industry"),
    NETWORK("network"),
    LANGUAGE("language"),
    CURRENT_COMPANY("current-company"),
    PAST_COMPANY("past-company"),
    SCHOOL("school"),
    COMPANY_SIZE("company-size"),
    NUM_FOLLOWERS_RANGE("num-followers-range"),
    FORTUNE("fortune"),
    COMPANY("company"),
    DATE_POSTED("date-posted"),
    JOB_FUNCTION("job-function"),
    EXPERIENCE_LEVEL("experience-level"),
    SALARY("salary");
    
    /**
     * Field Description.
     */
	private static final Map<String, LocalFacetType> stringToEnum = new HashMap<String, LocalFacetType>();

	static { // Initialize map from constant name to enum constant
		for (LocalFacetType op : values()) {
			stringToEnum.put(op.fieldName(), op);
		}
	}
	
    /** Field description */
    private final String fieldName;

    /**
     * Constructs ...
     *
     *
     * @param name
     */
    LocalFacetType(String name) {
        this.fieldName = name;
    }

    /**
     * @return the name of the field
     */
    public String fieldName() {
        return this.fieldName;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public String toString() {
        return fieldName();
    }

	/**
	 *
	 * @return Returns FacetType for string, or null if string is invalid
	 */
	public static LocalFacetType fromString(String symbol) {
		return stringToEnum.get(symbol);
	}
}
