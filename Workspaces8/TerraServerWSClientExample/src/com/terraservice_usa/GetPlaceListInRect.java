
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
 *         &lt;element name="upperleft" type="{http://terraservice-usa.com/}LonLatPt"/>
 *         &lt;element name="lowerright" type="{http://terraservice-usa.com/}LonLatPt"/>
 *         &lt;element name="ptype" type="{http://terraservice-usa.com/}PlaceType"/>
 *         &lt;element name="MaxItems" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "upperleft",
    "lowerright",
    "ptype",
    "maxItems"
})
@XmlRootElement(name = "GetPlaceListInRect")
public class GetPlaceListInRect {

    @XmlElement(required = true)
    protected LonLatPt upperleft;
    @XmlElement(required = true)
    protected LonLatPt lowerright;
    @XmlElement(required = true)
    protected PlaceType ptype;
    @XmlElement(name = "MaxItems")
    protected int maxItems;

    /**
     * Gets the value of the upperleft property.
     * 
     * @return
     *     possible object is
     *     {@link LonLatPt }
     *     
     */
    public LonLatPt getUpperleft() {
        return upperleft;
    }

    /**
     * Sets the value of the upperleft property.
     * 
     * @param value
     *     allowed object is
     *     {@link LonLatPt }
     *     
     */
    public void setUpperleft(LonLatPt value) {
        this.upperleft = value;
    }

    /**
     * Gets the value of the lowerright property.
     * 
     * @return
     *     possible object is
     *     {@link LonLatPt }
     *     
     */
    public LonLatPt getLowerright() {
        return lowerright;
    }

    /**
     * Sets the value of the lowerright property.
     * 
     * @param value
     *     allowed object is
     *     {@link LonLatPt }
     *     
     */
    public void setLowerright(LonLatPt value) {
        this.lowerright = value;
    }

    /**
     * Gets the value of the ptype property.
     * 
     * @return
     *     possible object is
     *     {@link PlaceType }
     *     
     */
    public PlaceType getPtype() {
        return ptype;
    }

    /**
     * Sets the value of the ptype property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlaceType }
     *     
     */
    public void setPtype(PlaceType value) {
        this.ptype = value;
    }

    /**
     * Gets the value of the maxItems property.
     * 
     */
    public int getMaxItems() {
        return maxItems;
    }

    /**
     * Sets the value of the maxItems property.
     * 
     */
    public void setMaxItems(int value) {
        this.maxItems = value;
    }

}
