
package com.terraservice_usa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfOverlappingThemeInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfOverlappingThemeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OverlappingThemeInfo" type="{http://terraservice-usa.com/}OverlappingThemeInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfOverlappingThemeInfo", propOrder = {
    "overlappingThemeInfo"
})
public class ArrayOfOverlappingThemeInfo {

    @XmlElement(name = "OverlappingThemeInfo", required = true)
    protected List<OverlappingThemeInfo> overlappingThemeInfo;

    /**
     * Gets the value of the overlappingThemeInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the overlappingThemeInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOverlappingThemeInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OverlappingThemeInfo }
     * 
     * 
     */
    public List<OverlappingThemeInfo> getOverlappingThemeInfo() {
        if (overlappingThemeInfo == null) {
            overlappingThemeInfo = new ArrayList<OverlappingThemeInfo>();
        }
        return this.overlappingThemeInfo;
    }

}
