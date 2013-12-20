/**
 * Copyright (c) MuleSoft, Inc. All rights reserved. http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */

package org.mule.modules.linkedin.enumeration;


/**
 * Class used as wrapper for {@link com.google.code.linkedinapi.schema.VisibilityType}
 * @author Mulesoft Inc
 *
 */
public enum LocalVisibilityType {

    ANYONE("anyone"),
    CONNECTIONS_ONLY("connections-only");
    private final String value;

    LocalVisibilityType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LocalVisibilityType fromValue(String v) {
        for (LocalVisibilityType c : LocalVisibilityType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
