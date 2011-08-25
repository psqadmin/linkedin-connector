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
 *         &lt;element ref="{}author"/>
 *         &lt;element ref="{}question-categories"/>
 *         &lt;choice>
 *           &lt;element ref="{}web-url"/>
 *           &lt;element ref="{}answers"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public interface Question
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
     * Gets the value of the author property.
     *
     * @return possible object is
     *         {@link Author }
     */
    Author getAuthor();

    /**
     * Sets the value of the author property.
     *
     * @param value allowed object is
     *              {@link Author }
     */
    void setAuthor(Author value);

    /**
     * Gets the value of the questionCategories property.
     *
     * @return possible object is
     *         {@link QuestionCategories }
     */
    QuestionCategories getQuestionCategories();

    /**
     * Sets the value of the questionCategories property.
     *
     * @param value allowed object is
     *              {@link QuestionCategories }
     */
    void setQuestionCategories(QuestionCategories value);

    /**
     * Gets the value of the webUrl property.
     *
     * @return possible object is
     *         {@link String }
     */
    String getWebUrl();

    /**
     * Sets the value of the webUrl property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    void setWebUrl(String value);

    /**
     * Gets the value of the answers property.
     *
     * @return possible object is
     *         {@link Answers }
     */
    Answers getAnswers();

    /**
     * Sets the value of the answers property.
     *
     * @param value allowed object is
     *              {@link Answers }
     */
    void setAnswers(Answers value);

}
