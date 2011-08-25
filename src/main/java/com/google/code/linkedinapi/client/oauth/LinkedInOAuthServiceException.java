/*
 * Copyright 2010-2011 Nabeel Mukhtar 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * 
 */
package com.google.code.linkedinapi.client.oauth;

/**
 * Class description
 */
public class LinkedInOAuthServiceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -4345556572105572685L;

    /**
     * Constructs ...
     */
    public LinkedInOAuthServiceException() {
        super();
    }

    /**
     * Constructs ...
     *
     * @param message
     */
    public LinkedInOAuthServiceException(String message) {
        super(message);
    }

    /**
     * Constructs ...
     *
     * @param cause
     */
    public LinkedInOAuthServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs ...
     *
     * @param message
     * @param cause
     */
    public LinkedInOAuthServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
