
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
 *         &lt;element name="center" type="{http://terraservice-usa.com/}LonLatPt"/>
 *         &lt;element name="theme" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="scale" type="{http://terraservice-usa.com/}Scale"/>
 *         &lt;element name="displayPixWidth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="displayPixHeight" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "center",
    "theme",
    "scale",
    "displayPixWidth",
    "displayPixHeight"
})
@XmlRootElement(name = "GetAreaFromPt")
public class GetAreaFromPt {

    @XmlElement(required = true)
    protected LonLatPt center;
    protected int theme;
    @XmlElement(required = true)
    protected Scale scale;
    protected int displayPixWidth;
    protected int displayPixHeight;

    /**
     * Gets the value of the center property.
     * 
     * @return
     *     possible object is
     *     {@link LonLatPt }
     *     
     */
    public LonLatPt getCenter() {
        return center;
    }

    /**
     * Sets the value of the center property.
     * 
     * @param value
     *     allowed object is
     *     {@link LonLatPt }
     *     
     */
    public void setCenter(LonLatPt value) {
        this.center = value;
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

    /**
     * Gets the value of the displayPixWidth property.
     * 
     */
    public int getDisplayPixWidth() {
        return displayPixWidth;
    }

    /**
     * Sets the value of the displayPixWidth property.
     * 
     */
    public void setDisplayPixWidth(int value) {
        this.displayPixWidth = value;
    }

    /**
     * Gets the value of the displayPixHeight property.
     * 
     */
    public int getDisplayPixHeight() {
        return displayPixHeight;
    }

    /**
     * Sets the value of the displayPixHeight property.
     * 
     */
    public void setDisplayPixHeight(int value) {
        this.displayPixHeight = value;
    }

}
