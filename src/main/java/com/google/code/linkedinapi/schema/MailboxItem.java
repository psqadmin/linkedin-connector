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

package com.google.code.linkedinapi.schema;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}recipients"/>
 *         &lt;element ref="{}subject"/>
 *         &lt;element ref="{}body"/>
 *         &lt;element ref="{}item-content" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public interface MailboxItem
        extends SchemaEntity {


    /**
     * Gets the value of the recipients property.
     *
     * @return possible object is
     *         {@link Recipients }
     */
    Recipients getRecipients();

    /**
     * Sets the value of the recipients property.
     *
     * @param value allowed object is
     *              {@link Recipients }
     */
    void setRecipients(Recipients value);

    /**
     * Gets the value of the subject property.
     *
     * @return possible object is
     *         {@link String }
     */
    String getSubject();

    /**
     * Sets the value of the subject property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    void setSubject(String value);

    /**
     * Gets the value of the body property.
     *
     * @return possible object is
     *         {@link String }
     */
    String getBody();

    /**
     * Sets the value of the body property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    void setBody(String value);

    /**
     * Gets the value of the itemContent property.
     *
     * @return possible object is
     *         {@link ItemContent }
     */
    ItemContent getItemContent();

    /**
     * Sets the value of the itemContent property.
     *
     * @param value allowed object is
     *              {@link ItemContent }
     */
    void setItemContent(ItemContent value);

}
