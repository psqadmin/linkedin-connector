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
 *         &lt;element ref="{}id"/>
 *         &lt;element ref="{}title"/>
 *         &lt;element ref="{}date"/>
 *         &lt;element ref="{}url"/>
 *         &lt;element ref="{}summary"/>
 *         &lt;element ref="{}number"/>
 *         &lt;element ref="{}status"/>
 *         &lt;element ref="{}office"/>
 *         &lt;element ref="{}inventors"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public interface Patent
        extends SchemaEntity {


    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     *         {@link String }
     */
    String getId();

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    void setId(String value);

    /**
     * Gets the value of the title property.
     *
     * @return possible object is
     *         {@link String }
     */
    String getTitle();

    /**
     * Sets the value of the title property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    void setTitle(String value);

    /**
     * Gets the value of the date property.
     *
     * @return possible object is
     *         {@link Date }
     */
    Date getDate();

    /**
     * Sets the value of the date property.
     *
     * @param value allowed object is
     *              {@link Date }
     */
    void setDate(Date value);

    /**
     * Gets the value of the url property.
     *
     * @return possible object is
     *         {@link String }
     */
    String getUrl();

    /**
     * Sets the value of the url property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    void setUrl(String value);

    /**
     * Gets the value of the summary property.
     *
     * @return possible object is
     *         {@link String }
     */
    String getSummary();

    /**
     * Sets the value of the summary property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    void setSummary(String value);

    /**
     * Gets the value of the number property.
     *
     * @return possible object is
     *         {@link String }
     */
    String getNumber();

    /**
     * Sets the value of the number property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    void setNumber(String value);

    /**
     * Gets the value of the status property.
     *
     * @return possible object is
     *         {@link Status }
     */
    Status getStatus();

    /**
     * Sets the value of the status property.
     *
     * @param value allowed object is
     *              {@link Status }
     */
    void setStatus(Status value);

    /**
     * Gets the value of the office property.
     *
     * @return possible object is
     *         {@link Office }
     */
    Office getOffice();

    /**
     * Sets the value of the office property.
     *
     * @param value allowed object is
     *              {@link Office }
     */
    void setOffice(Office value);

    /**
     * Gets the value of the inventors property.
     *
     * @return possible object is
     *         {@link Inventors }
     */
    Inventors getInventors();

    /**
     * Sets the value of the inventors property.
     *
     * @param value allowed object is
     *              {@link Inventors }
     */
    void setInventors(Inventors value);

}
