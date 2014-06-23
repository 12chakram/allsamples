
package com.terraservice_usa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LonLatPtOffset complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LonLatPtOffset">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Point" type="{http://terraservice-usa.com/}LonLatPt"/>
 *         &lt;element name="XOffset" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="YOffset" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LonLatPtOffset", propOrder = {
    "point",
    "xOffset",
    "yOffset"
})
public class LonLatPtOffset {

    @XmlElement(name = "Point", required = true)
    protected LonLatPt point;
    @XmlElement(name = "XOffset")
    protected int xOffset;
    @XmlElement(name = "YOffset")
    protected int yOffset;

    /**
     * Gets the value of the point property.
     * 
     * @return
     *     possible object is
     *     {@link LonLatPt }
     *     
     */
    public LonLatPt getPoint() {
        return point;
    }

    /**
     * Sets the value of the point property.
     * 
     * @param value
     *     allowed object is
     *     {@link LonLatPt }
     *     
     */
    public void setPoint(LonLatPt value) {
        this.point = value;
    }

    /**
     * Gets the value of the xOffset property.
     * 
     */
    public int getXOffset() {
        return xOffset;
    }

    /**
     * Sets the value of the xOffset property.
     * 
     */
    public void setXOffset(int value) {
        this.xOffset = value;
    }

    /**
     * Gets the value of the yOffset property.
     * 
     */
    public int getYOffset() {
        return yOffset;
    }

    /**
     * Sets the value of the yOffset property.
     * 
     */
    public void setYOffset(int value) {
        this.yOffset = value;
    }

}
