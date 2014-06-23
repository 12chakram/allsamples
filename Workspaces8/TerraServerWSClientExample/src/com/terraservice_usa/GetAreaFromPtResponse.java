
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
 *         &lt;element name="GetAreaFromPtResult" type="{http://terraservice-usa.com/}AreaBoundingBox"/>
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
    "getAreaFromPtResult"
})
@XmlRootElement(name = "GetAreaFromPtResponse")
public class GetAreaFromPtResponse {

    @XmlElement(name = "GetAreaFromPtResult", required = true)
    protected AreaBoundingBox getAreaFromPtResult;

    /**
     * Gets the value of the getAreaFromPtResult property.
     * 
     * @return
     *     possible object is
     *     {@link AreaBoundingBox }
     *     
     */
    public AreaBoundingBox getGetAreaFromPtResult() {
        return getAreaFromPtResult;
    }

    /**
     * Sets the value of the getAreaFromPtResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaBoundingBox }
     *     
     */
    public void setGetAreaFromPtResult(AreaBoundingBox value) {
        this.getAreaFromPtResult = value;
    }

}
