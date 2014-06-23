
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
 *         &lt;element name="GetThemeResult" type="{http://terraservice-usa.com/}ThemeInfo"/>
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
    "getThemeResult"
})
@XmlRootElement(name = "GetThemeResponse")
public class GetThemeResponse {

    @XmlElement(name = "GetThemeResult", required = true)
    protected ThemeInfo getThemeResult;

    /**
     * Gets the value of the getThemeResult property.
     * 
     * @return
     *     possible object is
     *     {@link ThemeInfo }
     *     
     */
    public ThemeInfo getGetThemeResult() {
        return getThemeResult;
    }

    /**
     * Sets the value of the getThemeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ThemeInfo }
     *     
     */
    public void setGetThemeResult(ThemeInfo value) {
        this.getThemeResult = value;
    }

}
