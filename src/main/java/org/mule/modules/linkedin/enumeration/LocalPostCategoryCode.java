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
 * Class used as wrapper for {@link com.google.code.linkedinapi.schema.PostCategoryCode}
 * @author Mulesoft Inc
 *
 */
public enum LocalPostCategoryCode implements FieldEnum{
	/**
	 * discussion.
	 */
	DISCUSSION("discussion");
    
    /**
     * Field Description.
     */
	private static final Map<String, LocalPostCategoryCode> stringToEnum = new HashMap<String, LocalPostCategoryCode>();

	static { // Initialize map from constant name to enum constant
		for (LocalPostCategoryCode op : values()) {
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
    LocalPostCategoryCode(String name) {
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
	 * @return Returns PostCategoryCode for string, or null if string is invalid
	 */
	public static LocalPostCategoryCode fromString(String symbol) {
		return stringToEnum.get(symbol);
	}
}