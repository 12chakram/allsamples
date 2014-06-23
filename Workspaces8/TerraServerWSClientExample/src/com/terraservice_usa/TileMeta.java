
package com.terraservice_usa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TileMeta complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TileMeta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://terraservice-usa.com/}TileId"/>
 *         &lt;element name="TileExists" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="NorthWest" type="{http://terraservice-usa.com/}LonLatPt"/>
 *         &lt;element name="NorthEast" type="{http://terraservice-usa.com/}LonLatPt"/>
 *         &lt;element name="SouthWest" type="{http://terraservice-usa.com/}LonLatPt"/>
 *         &lt;element name="SouthEast" type="{http://terraservice-usa.com/}LonLatPt"/>
 *         &lt;element name="Center" type="{http://terraservice-usa.com/}LonLatPt"/>
 *         &lt;element name="Capture" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TileMeta", propOrder = {
    "id",
    "tileExists",
    "northWest",
    "northEast",
    "southWest",
    "southEast",
    "center",
    "capture"
})
public class TileMeta {

    @XmlElement(name = "Id", required = true)
    protected TileId id;
    @XmlElement(name = "TileExists")
    protected boolean tileExists;
    @XmlElement(name = "NorthWest", required = true)
    protected LonLatPt northWest;
    @XmlElement(name = "NorthEast", required = true)
    protected LonLatPt northEast;
    @XmlElement(name = "SouthWest", required = true)
    protected LonLatPt southWest;
    @XmlElement(name = "SouthEast", required = true)
    protected LonLatPt southEast;
    @XmlElement(name = "Center", required = true)
    protected LonLatPt center;
    @XmlElement(name = "Capture", required = true)
    protected XMLGregorianCalendar capture;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link TileId }
     *     
     */
    public TileId getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link TileId }
     *     
     */
    public void setId(TileId value) {
        this.id = value;
    }

    /**
     * Gets the value of the tileExists property.
     * 
     */
    public boolean isTileExists() {
        return tileExists;
    }

    /**
     * Sets the value of the tileExists property.
     * 
     */
    public void setTileExists(boolean value) {
        this.tileExists = value;
    }

    /**
     * Gets the value of the northWest property.
     * 
     * @return
     *     possible object is
     *     {@link LonLatPt }
     *     
     */
    public LonLatPt getNorthWest() {
        return northWest;
    }

    /**
     * Sets the value of the northWest property.
     * 
     * @param value
     *     allowed object is
     *     {@link LonLatPt }
     *     
     */
    public void setNorthWest(LonLatPt value) {
        this.northWest = value;
    }

    /**
     * Gets the value of the northEast property.
     * 
     * @return
     *     possible object is
     *     {@link LonLatPt }
     *     
     */
    public LonLatPt getNorthEast() {
        return northEast;
    }

    /**
     * Sets the value of the northEast property.
     * 
     * @param value
     *     allowed object is
     *     {@link LonLatPt }
     *     
     */
    public void setNorthEast(LonLatPt value) {
        this.northEast = value;
    }

    /**
     * Gets the value of the southWest property.
     * 
     * @return
     *     possible object is
     *     {@link LonLatPt }
     *     
     */
    public LonLatPt getSouthWest() {
        return southWest;
    }

    /**
     * Sets the value of the southWest property.
     * 
     * @param value
     *     allowed object is
     *     {@link LonLatPt }
     *     
     */
    public void setSouthWest(LonLatPt value) {
        this.southWest = value;
    }

    /**
     * Gets the value of the southEast property.
     * 
     * @return
     *     possible object is
     *     {@link LonLatPt }
     *     
     */
    public LonLatPt getSouthEast() {
        return southEast;
    }

    /**
     * Sets the value of the southEast property.
     * 
     * @param value
     *     allowed object is
     *     {@link LonLatPt }
     *     
     */
    public void setSouthEast(LonLatPt value) {
        this.southEast = value;
    }

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
     * Gets the value of the capture property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCapture() {
        return capture;
    }

    /**
     * Sets the value of the capture property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCapture(XMLGregorianCalendar value) {
        this.capture = value;
    }

}
