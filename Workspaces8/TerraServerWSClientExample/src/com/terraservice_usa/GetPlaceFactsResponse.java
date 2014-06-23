
package com.terraservice_usa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetPlaceFactsResult" type="{http://terraservice-usa.com/}PlaceFacts"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getPlaceFactsResult"
})
@XmlRootElement(name = "GetPlaceFactsResponse")
public class GetPlaceFactsResponse {

    @XmlElement(name = "GetPlaceFactsResult", required = true)
    protected PlaceFacts getPlaceFactsResult;

    /**
     * Gets the value of the getPlaceFactsResult property.
     * 
     * @return
     *     possible object is
     *     {@link PlaceFacts }
     *     
     */
    public PlaceFacts getGetPlaceFactsResult() {
        return getPlaceFactsResult;
    }

    /**
     * Sets the value of the getPlaceFactsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlaceFacts }
     *     
     */
    public void setGetPlaceFactsResult(PlaceFacts value) {
        this.getPlaceFactsResult = value;
    }

}
