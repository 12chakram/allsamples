
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
 *         &lt;element name="utm" type="{http://terraservice-usa.com/}UtmPt"/>
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
    "utm"
})
@XmlRootElement(name = "ConvertUtmPtToLonLatPt")
public class ConvertUtmPtToLonLatPt {

    @XmlElement(required = true)
    protected UtmPt utm;

    /**
     * Gets the value of the utm property.
     * 
     * @return
     *     possible object is
     *     {@link UtmPt }
     *     
     */
    public UtmPt getUtm() {
        return utm;
    }

    /**
     * Sets the value of the utm property.
     * 
     * @param value
     *     allowed object is
     *     {@link UtmPt }
     *     
     */
    public void setUtm(UtmPt value) {
        this.utm = value;
    }

}
