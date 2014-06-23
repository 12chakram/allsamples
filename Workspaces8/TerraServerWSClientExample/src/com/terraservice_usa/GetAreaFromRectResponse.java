
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
 *         &lt;element name="GetAreaFromRectResult" type="{http://terraservice-usa.com/}AreaBoundingBox"/>
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
    "getAreaFromRectResult"
})
@XmlRootElement(name = "GetAreaFromRectResponse")
public class GetAreaFromRectResponse {

    @XmlElement(name = "GetAreaFromRectResult", required = true)
    protected AreaBoundingBox getAreaFromRectResult;

    /**
     * Gets the value of the getAreaFromRectResult property.
     * 
     * @return
     *     possible object is
     *     {@link AreaBoundingBox }
     *     
     */
    public AreaBoundingBox getGetAreaFromRectResult() {
        return getAreaFromRectResult;
    }

    /**
     * Sets the value of the getAreaFromRectResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaBoundingBox }
     *     
     */
    public void setGetAreaFromRectResult(AreaBoundingBox value) {
        this.getAreaFromRectResult = value;
    }

}
