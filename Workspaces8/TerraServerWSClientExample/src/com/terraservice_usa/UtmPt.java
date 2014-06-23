
package com.terraservice_usa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UtmPt complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UtmPt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Zone" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="X" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UtmPt", propOrder = {
    "zone",
    "x",
    "y"
})
public class UtmPt {

    @XmlElement(name = "Zone")
    protected int zone;
    @XmlElement(name = "X")
    protected double x;
    @XmlElement(name = "Y")
    protected double y;

    /**
     * Gets the value of the zone property.
     * 
     */
    public int getZone() {
        return zone;
    }

    /**
     * Sets the value of the zone property.
     * 
     */
    public void setZone(int value) {
        this.zone = value;
    }

    /**
     * Gets the value of the x property.
     * 
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the value of the x property.
     * 
     */
    public void setX(double value) {
        this.x = value;
    }

    /**
     * Gets the value of the y property.
     * 
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the value of the y property.
     * 
     */
    public void setY(double value) {
        this.y = value;
    }

}
