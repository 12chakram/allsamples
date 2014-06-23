
package com.terraservice_usa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ThemeInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ThemeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Theme" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Supplier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LoScale" type="{http://terraservice-usa.com/}Scale"/>
 *         &lt;element name="HiScale" type="{http://terraservice-usa.com/}Scale"/>
 *         &lt;element name="ProjectionId" type="{http://terraservice-usa.com/}ProjectionType"/>
 *         &lt;element name="ProjectionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CopyrightNotice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ThemeInfo", propOrder = {
    "theme",
    "name",
    "description",
    "supplier",
    "loScale",
    "hiScale",
    "projectionId",
    "projectionName",
    "copyrightNotice"
})
public class ThemeInfo {

    @XmlElement(name = "Theme")
    protected int theme;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Supplier")
    protected String supplier;
    @XmlElement(name = "LoScale", required = true)
    protected Scale loScale;
    @XmlElement(name = "HiScale", required = true)
    protected Scale hiScale;
    @XmlElement(name = "ProjectionId", required = true)
    protected ProjectionType projectionId;
    @XmlElement(name = "ProjectionName")
    protected String projectionName;
    @XmlElement(name = "CopyrightNotice")
    protected String copyrightNotice;

    /**
     * Gets the value of the theme property.
     * 
     */
    public int getTheme() {
        return theme;
    }

    /**
     * Sets the value of the theme property.
     * 
     */
    public void setTheme(int value) {
        this.theme = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the supplier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * Sets the value of the supplier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplier(String value) {
        this.supplier = value;
    }

    /**
     * Gets the value of the loScale property.
     * 
     * @return
     *     possible object is
     *     {@link Scale }
     *     
     */
    public Scale getLoScale() {
        return loScale;
    }

    /**
     * Sets the value of the loScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Scale }
     *     
     */
    public void setLoScale(Scale value) {
        this.loScale = value;
    }

    /**
     * Gets the value of the hiScale property.
     * 
     * @return
     *     possible object is
     *     {@link Scale }
     *     
     */
    public Scale getHiScale() {
        return hiScale;
    }

    /**
     * Sets the value of the hiScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Scale }
     *     
     */
    public void setHiScale(Scale value) {
        this.hiScale = value;
    }

    /**
     * Gets the value of the projectionId property.
     * 
     * @return
     *     possible object is
     *     {@link ProjectionType }
     *     
     */
    public ProjectionType getProjectionId() {
        return projectionId;
    }

    /**
     * Sets the value of the projectionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProjectionType }
     *     
     */
    public void setProjectionId(ProjectionType value) {
        this.projectionId = value;
    }

    /**
     * Gets the value of the projectionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectionName() {
        return projectionName;
    }

    /**
     * Sets the value of the projectionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectionName(String value) {
        this.projectionName = value;
    }

    /**
     * Gets the value of the copyrightNotice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCopyrightNotice() {
        return copyrightNotice;
    }

    /**
     * Sets the value of the copyrightNotice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCopyrightNotice(String value) {
        this.copyrightNotice = value;
    }

}
