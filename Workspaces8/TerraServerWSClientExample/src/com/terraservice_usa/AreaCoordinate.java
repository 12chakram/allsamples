
package com.terraservice_usa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AreaCoordinate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AreaCoordinate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TileMeta" type="{http://terraservice-usa.com/}TileMeta"/>
 *         &lt;element name="Offset" type="{http://terraservice-usa.com/}LonLatPtOffset"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AreaCoordinate", propOrder = {
    "tileMeta",
    "offset"
})
public class AreaCoordinate {

    @XmlElement(name = "TileMeta", required = true)
    protected TileMeta tileMeta;
    @XmlElement(name = "Offset", required = true)
    protected LonLatPtOffset offset;

    /**
     * Gets the value of the tileMeta property.
     * 
     * @return
     *     possible object is
     *     {@link TileMeta }
     *     
     */
    public TileMeta getTileMeta() {
        return tileMeta;
    }

    /**
     * Sets the value of the tileMeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link TileMeta }
     *     
     */
    public void setTileMeta(TileMeta value) {
        this.tileMeta = value;
    }

    /**
     * Gets the value of the offset property.
     * 
     * @return
     *     possible object is
     *     {@link LonLatPtOffset }
     *     
     */
    public LonLatPtOffset getOffset() {
        return offset;
    }

    /**
     * Sets the value of the offset property.
     * 
     * @param value
     *     allowed object is
     *     {@link LonLatPtOffset }
     *     
     */
    public void setOffset(LonLatPtOffset value) {
        this.offset = value;
    }

}
