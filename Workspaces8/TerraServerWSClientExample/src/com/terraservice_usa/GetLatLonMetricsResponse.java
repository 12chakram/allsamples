
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
 *         &lt;element name="GetLatLonMetricsResult" type="{http://terraservice-usa.com/}ArrayOfThemeBoundingBox" minOccurs="0"/>
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
    "getLatLonMetricsResult"
})
@XmlRootElement(name = "GetLatLonMetricsResponse")
public class GetLatLonMetricsResponse {

    @XmlElement(name = "GetLatLonMetricsResult")
    protected ArrayOfThemeBoundingBox getLatLonMetricsResult;

    /**
     * Gets the value of the getLatLonMetricsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfThemeBoundingBox }
     *     
     */
    public ArrayOfThemeBoundingBox getGetLatLonMetricsResult() {
        return getLatLonMetricsResult;
    }

    /**
     * Sets the value of the getLatLonMetricsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfThemeBoundingBox }
     *     
     */
    public void setGetLatLonMetricsResult(ArrayOfThemeBoundingBox value) {
        this.getLatLonMetricsResult = value;
    }

}
