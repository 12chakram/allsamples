
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
 *         &lt;element name="id" type="{http://terraservice-usa.com/}TileId"/>
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
    "id",
    "displayPixWidth",
    "displayPixHeight"
})
@XmlRootElement(name = "GetAreaFromTileId")
public class GetAreaFromTileId {

    @XmlElement(required = true)
    protected TileId id;
    protected int displayPixWidth;
    protected int displayPixHeight;

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
