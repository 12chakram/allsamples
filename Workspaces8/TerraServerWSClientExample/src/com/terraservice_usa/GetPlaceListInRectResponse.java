
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
 *         &lt;element name="GetPlaceListInRectResult" type="{http://terraservice-usa.com/}ArrayOfPlaceFacts" minOccurs="0"/>
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
    "getPlaceListInRectResult"
})
@XmlRootElement(name = "GetPlaceListInRectResponse")
public class GetPlaceListInRectResponse {

    @XmlElement(name = "GetPlaceListInRectResult")
    protected ArrayOfPlaceFacts getPlaceListInRectResult;

    /**
     * Gets the value of the getPlaceListInRectResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPlaceFacts }
     *     
     */
    public ArrayOfPlaceFacts getGetPlaceListInRectResult() {
        return getPlaceListInRectResult;
    }

    /**
     * Sets the value of the getPlaceListInRectResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPlaceFacts }
     *     
     */
    public void setGetPlaceListInRectResult(ArrayOfPlaceFacts value) {
        this.getPlaceListInRectResult = value;
    }

}
