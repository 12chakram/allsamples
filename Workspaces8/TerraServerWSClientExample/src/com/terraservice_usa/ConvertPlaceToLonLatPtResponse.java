
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
 *         &lt;element name="ConvertPlaceToLonLatPtResult" type="{http://terraservice-usa.com/}LonLatPt"/>
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
    "convertPlaceToLonLatPtResult"
})
@XmlRootElement(name = "ConvertPlaceToLonLatPtResponse")
public class ConvertPlaceToLonLatPtResponse {

    @XmlElement(name = "ConvertPlaceToLonLatPtResult", required = true)
    protected LonLatPt convertPlaceToLonLatPtResult;

    /**
     * Gets the value of the convertPlaceToLonLatPtResult property.
     * 
     * @return
     *     possible object is
     *     {@link LonLatPt }
     *     
     */
    public LonLatPt getConvertPlaceToLonLatPtResult() {
        return convertPlaceToLonLatPtResult;
    }

    /**
     * Sets the value of the convertPlaceToLonLatPtResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link LonLatPt }
     *     
     */
    public void setConvertPlaceToLonLatPtResult(LonLatPt value) {
        this.convertPlaceToLonLatPtResult = value;
    }

}
