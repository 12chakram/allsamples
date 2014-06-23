
package com.terraservice_usa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ThemeBoundingBox complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ThemeBoundingBox">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Theme" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ThemeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sparseness" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LoScale" type="{http://terraservice-usa.com/}Scale"/>
 *         &lt;element name="HiScale" type="{http://terraservice-usa.com/}Scale"/>
 *         &lt;element name="ProjectionId" type="{http://terraservice-usa.com/}ProjectionType"/>
 *         &lt;element name="ProjectionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WestLongitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="NorthLatitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="EastLongitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="SouthLatitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ThemeBoundingBox", propOrder = {
    "theme",
    "themeName",
    "sparseness",
    "loScale",
    "hiScale",
    "projectionId",
    "projectionName",
    "westLongitude",
    "northLatitude",
    "eastLongitude",
    "southLatitude"
})
public class ThemeBoundingBox {

    @XmlElement(name = "Theme")
    protected int theme;
    @XmlElement(name = "ThemeName")
    protected String themeName;
    @XmlElement(name = "Sparseness")
    protected int sparseness;
    @XmlElement(name = "LoScale", required = true)
    protected Scale loScale;
    @XmlElement(name = "HiScale", required = true)
    protected Scale hiScale;
    @XmlElement(name = "ProjectionId", required = true)
    protected ProjectionType projectionId;
    @XmlElement(name = "ProjectionName")
    protected String projectionName;
    @XmlElement(name = "WestLongitude")
    protected double westLongitude;
    @XmlElement(name = "NorthLatitude")
    protected double northLatitude;
    @XmlElement(name = "EastLongitude")
    protected double eastLongitude;
    @XmlElement(name = "SouthLatitude")
    protected double southLatitude;

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
     * Gets the value of the themeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThemeName() {
        return themeName;
    }

    /**
     * Sets the value of the themeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThemeName(String value) {
        this.themeName = value;
    }

    /**
     * Gets the value of the sparseness property.
     * 
     */
    public int getSparseness() {
        return sparseness;
    }

    /**
     * Sets the value of the sparseness property.
     * 
     */
    public void setSparseness(int value) {
        this.sparseness = value;
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
     * Gets the value of the westLongitude property.
     * 
     */
    public double getWestLongitude() {
        return westLongitude;
    }

    /**
     * Sets the value of the westLongitude property.
     * 
     */
    public void setWestLongitude(double value) {
        this.westLongitude = value;
    }

    /**
     * Gets the value of the northLatitude property.
     * 
     */
    public double getNorthLatitude() {
        return northLatitude;
    }

    /**
     * Sets the value of the northLatitude property.
     * 
     */
    public void setNorthLatitude(double value) {
        this.northLatitude = value;
    }

    /**
     * Gets the value of the eastLongitude property.
     * 
     */
    public double getEastLongitude() {
        return eastLongitude;
    }

    /**
     * Sets the value of the eastLongitude property.
     * 
     */
    public void setEastLongitude(double value) {
        this.eastLongitude = value;
    }

    /**
     * Gets the value of the southLatitude property.
     * 
     */
    public double getSouthLatitude() {
        return southLatitude;
    }

    /**
     * Sets the value of the southLatitude property.
     * 
     */
    public void setSouthLatitude(double value) {
        this.southLatitude = value;
    }

}
