
package com.terraservice_usa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfThemeBoundingBox complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfThemeBoundingBox">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ThemeBoundingBox" type="{http://terraservice-usa.com/}ThemeBoundingBox" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfThemeBoundingBox", propOrder = {
    "themeBoundingBox"
})
public class ArrayOfThemeBoundingBox {

    @XmlElement(name = "ThemeBoundingBox", required = true)
    protected List<ThemeBoundingBox> themeBoundingBox;

    /**
     * Gets the value of the themeBoundingBox property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the themeBoundingBox property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getThemeBoundingBox().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ThemeBoundingBox }
     * 
     * 
     */
    public List<ThemeBoundingBox> getThemeBoundingBox() {
        if (themeBoundingBox == null) {
            themeBoundingBox = new ArrayList<ThemeBoundingBox>();
        }
        return this.themeBoundingBox;
    }

}
