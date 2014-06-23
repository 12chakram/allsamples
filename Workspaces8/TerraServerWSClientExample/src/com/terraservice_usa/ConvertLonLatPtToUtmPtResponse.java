
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
 *         &lt;element name="ConvertLonLatPtToUtmPtResult" type="{http://terraservice-usa.com/}UtmPt"/>
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
    "convertLonLatPtToUtmPtResult"
})
@XmlRootElement(name = "ConvertLonLatPtToUtmPtResponse")
public class ConvertLonLatPtToUtmPtResponse {

    @XmlElement(name = "ConvertLonLatPtToUtmPtResult", required = true)
    protected UtmPt convertLonLatPtToUtmPtResult;

    /**
     * Gets the value of the convertLonLatPtToUtmPtResult property.
     * 
     * @return
     *     possible object is
     *     {@link UtmPt }
     *     
     */
    public UtmPt getConvertLonLatPtToUtmPtResult() {
        return convertLonLatPtToUtmPtResult;
    }

    /**
     * Sets the value of the convertLonLatPtToUtmPtResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link UtmPt }
     *     
     */
    public void setConvertLonLatPtToUtmPtResult(UtmPt value) {
        this.convertLonLatPtToUtmPtResult = value;
    }

}
