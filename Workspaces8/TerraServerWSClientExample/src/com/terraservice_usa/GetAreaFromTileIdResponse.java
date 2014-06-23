
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
 *         &lt;element name="GetAreaFromTileIdResult" type="{http://terraservice-usa.com/}AreaBoundingBox"/>
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
    "getAreaFromTileIdResult"
})
@XmlRootElement(name = "GetAreaFromTileIdResponse")
public class GetAreaFromTileIdResponse {

    @XmlElement(name = "GetAreaFromTileIdResult", required = true)
    protected AreaBoundingBox getAreaFromTileIdResult;

    /**
     * Gets the value of the getAreaFromTileIdResult property.
     * 
     * @return
     *     possible object is
     *     {@link AreaBoundingBox }
     *     
     */
    public AreaBoundingBox getGetAreaFromTileIdResult() {
        return getAreaFromTileIdResult;
    }

    /**
     * Sets the value of the getAreaFromTileIdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaBoundingBox }
     *     
     */
    public void setGetAreaFromTileIdResult(AreaBoundingBox value) {
        this.getAreaFromTileIdResult = value;
    }

}
