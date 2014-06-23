
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
 *         &lt;element name="GetTileResult" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "getTileResult"
})
@XmlRootElement(name = "GetTileResponse")
public class GetTileResponse {

    @XmlElement(name = "GetTileResult")
    protected byte[] getTileResult;

    /**
     * Gets the value of the getTileResult property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getGetTileResult() {
        return getTileResult;
    }

    /**
     * Sets the value of the getTileResult property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setGetTileResult(byte[] value) {
        this.getTileResult = ((byte[]) value);
    }

}
