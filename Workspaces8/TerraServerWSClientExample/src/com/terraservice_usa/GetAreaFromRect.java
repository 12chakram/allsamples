
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
 *         &lt;element name="upperLeft" type="{http://terraservice-usa.com/}LonLatPt"/>
 *         &lt;element name="lowerRight" type="{http://terraservice-usa.com/}LonLatPt"/>
 *         &lt;element name="theme" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="scale" type="{http://terraservice-usa.com/}Scale"/>
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
    "upperLeft",
    "lowerRight",
    "theme",
    "scale"
})
@XmlRootElement(name = "GetAreaFromRect")
public class GetAreaFromRect {

    @XmlElement(required = true)
    protected LonLatPt upperLeft;
    @XmlElement(required = true)
    protected LonLatPt lowerRight;
    protected int theme;
    @XmlElement(required = true)
    protected Scale scale;

    /**
     * Gets the value of the upperLeft property.
     * 
     * @return
     *     possible object is
     *     {@link LonLatPt }
     *     
     */
    public LonLatPt getUpperLeft() {
        return upperLeft;
    }

    /**
     * Sets the value of the upperLeft property.
     * 
     * @param value
     *     allowed object is
     *     {@link LonLatPt }
     *     
     */
    public void setUpperLeft(LonLatPt value) {
        this.upperLeft = value;
    }

    /**
     * Gets the value of the lowerRight property.
     * 
     * @return
     *     possible object is
     *     {@link LonLatPt }
     *     
     */
    public LonLatPt getLowerRight() {
        return lowerRight;
    }

    /**
     * Sets the value of the lowerRight property.
     * 
     * @param value
     *     allowed object is
     *     {@link LonLatPt }
     *     
     */
    public void setLowerRight(LonLatPt value) {
        this.lowerRight = value;
    }

    /**
     * Gets the value of the theme property.
     * 
     */
    public int getTheme() {
        return theme;
    }

    /**
     * Sets the value of the theme property.
     * 
     */
    public void setTheme(int value) {
        this.theme = value;
    }

    /**
     * Gets the value of the scale property.
     * 
     * @return
     *     possible object is
     *     {@link Scale }
     *     
     */
    public Scale getScale() {
        return scale;
    }

    /**
     * Sets the value of the scale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Scale }
     *     
     */
    public void setScale(Scale value) {
        this.scale = value;
    }

}
