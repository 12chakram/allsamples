
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
 *         &lt;element name="GetTileMetaFromTileIdResult" type="{http://terraservice-usa.com/}TileMeta"/>
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
    "getTileMetaFromTileIdResult"
})
@XmlRootElement(name = "GetTileMetaFromTileIdResponse")
public class GetTileMetaFromTileIdResponse {

    @XmlElement(name = "GetTileMetaFromTileIdResult", required = true)
    protected TileMeta getTileMetaFromTileIdResult;

    /**
     * Gets the value of the getTileMetaFromTileIdResult property.
     * 
     * @return
     *     possible object is
     *     {@link TileMeta }
     *     
     */
    public TileMeta getGetTileMetaFromTileIdResult() {
        return getTileMetaFromTileIdResult;
    }

    /**
     * Sets the value of the getTileMetaFromTileIdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TileMeta }
     *     
     */
    public void setGetTileMetaFromTileIdResult(TileMeta value) {
        this.getTileMetaFromTileIdResult = value;
    }

}
