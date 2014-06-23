
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
 *         &lt;element name="CountPlacesInRectResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "countPlacesInRectResult"
})
@XmlRootElement(name = "CountPlacesInRectResponse")
public class CountPlacesInRectResponse {

    @XmlElement(name = "CountPlacesInRectResult")
    protected int countPlacesInRectResult;

    /**
     * Gets the value of the countPlacesInRectResult property.
     * 
     */
    public int getCountPlacesInRectResult() {
        return countPlacesInRectResult;
    }

    /**
     * Sets the value of the countPlacesInRectResult property.
     * 
     */
    public void setCountPlacesInRectResult(int value) {
        this.countPlacesInRectResult = value;
    }

}
