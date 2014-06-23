
package com.terraservice_usa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AreaBoundingBox complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AreaBoundingBox">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NorthWest" type="{http://terraservice-usa.com/}AreaCoordinate"/>
 *         &lt;element name="NorthEast" type="{http://terraservice-usa.com/}AreaCoordinate"/>
 *         &lt;element name="SouthWest" type="{http://terraservice-usa.com/}AreaCoordinate"/>
 *         &lt;element name="SouthEast" type="{http://terraservice-usa.com/}AreaCoordinate"/>
 *         &lt;element name="Center" type="{http://terraservice-usa.com/}AreaCoordinate"/>
 *         &lt;element name="NearestPlace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OverlappingThemeInfos" type="{http://terraservice-usa.com/}ArrayOfOverlappingThemeInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AreaBoundingBox", propOrder = {
    "northWest",
    "northEast",
    "southWest",
    "southEast",
    "center",
    "nearestPlace",
    "overlappingThemeInfos"
})
public class AreaBoundingBox {

    @XmlElement(name = "NorthWest", required = true)
    protected AreaCoordinate northWest;
    @XmlElement(name = "NorthEast", required = true)
    protected AreaCoordinate northEast;
    @XmlElement(name = "SouthWest", required = true)
    protected AreaCoordinate southWest;
    @XmlElement(name = "SouthEast", required = true)
    protected AreaCoordinate southEast;
    @XmlElement(name = "Center", required = true)
    protected AreaCoordinate center;
    @XmlElement(name = "NearestPlace")
    protected String nearestPlace;
    @XmlElement(name = "OverlappingThemeInfos")
    protected ArrayOfOverlappingThemeInfo overlappingThemeInfos;

    /**
     * Gets the value of the northWest property.
     * 
     * @return
     *     possible object is
     *     {@link AreaCoordinate }
     *     
     */
    public AreaCoordinate getNorthWest() {
        return northWest;
    }

    /**
     * Sets the value of the northWest property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaCoordinate }
     *     
     */
    public void setNorthWest(AreaCoordinate value) {
        this.northWest = value;
    }

    /**
     * Gets the value of the northEast property.
     * 
     * @return
     *     possible object is
     *     {@link AreaCoordinate }
     *     
     */
    public AreaCoordinate getNorthEast() {
        return northEast;
    }

    /**
     * Sets the value of the northEast property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaCoordinate }
     *     
     */
    public void setNorthEast(AreaCoordinate value) {
        this.northEast = value;
    }

    /**
     * Gets the value of the southWest property.
     * 
     * @return
     *     possible object is
     *     {@link AreaCoordinate }
     *     
     */
    public AreaCoordinate getSouthWest() {
        return southWest;
    }

    /**
     * Sets the value of the southWest property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaCoordinate }
     *     
     */
    public void setSouthWest(AreaCoordinate value) {
        this.southWest = value;
    }

    /**
     * Gets the value of the southEast property.
     * 
     * @return
     *     possible object is
     *     {@link AreaCoordinate }
     *     
     */
    public AreaCoordinate getSouthEast() {
        return southEast;
    }

    /**
     * Sets the value of the southEast property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaCoordinate }
     *     
     */
    public void setSouthEast(AreaCoordinate value) {
        this.southEast = value;
    }

    /**
     * Gets the value of the center property.
     * 
     * @return
     *     possible object is
     *     {@link AreaCoordinate }
     *     
     */
    public AreaCoordinate getCenter() {
        return center;
    }

    /**
     * Sets the value of the center property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaCoordinate }
     *     
     */
    public void setCenter(AreaCoordinate value) {
        this.center = value;
    }

    /**
     * Gets the value of the nearestPlace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNearestPlace() {
        return nearestPlace;
    }

    /**
     * Sets the value of the nearestPlace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNearestPlace(String value) {
        this.nearestPlace = value;
    }

    /**
     * Gets the value of the overlappingThemeInfos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOverlappingThemeInfo }
     *     
     */
    public ArrayOfOverlappingThemeInfo getOverlappingThemeInfos() {
        return overlappingThemeInfos;
    }

    /**
     * Sets the value of the overlappingThemeInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOverlappingThemeInfo }
     *     
     */
    public void setOverlappingThemeInfos(ArrayOfOverlappingThemeInfo value) {
        this.overlappingThemeInfos = value;
    }

}
