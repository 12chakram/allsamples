
package com.terraservice_usa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for OverlappingThemeInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OverlappingThemeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LocalTheme" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Theme" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Point" type="{http://terraservice-usa.com/}LonLatPt"/>
 *         &lt;element name="ThemeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Capture" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ProjectionId" type="{http://terraservice-usa.com/}ProjectionType"/>
 *         &lt;element name="LoScale" type="{http://terraservice-usa.com/}Scale"/>
 *         &lt;element name="HiScale" type="{http://terraservice-usa.com/}Scale"/>
 *         &lt;element name="Url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OverlappingThemeInfo", propOrder = {
    "localTheme",
    "theme",
    "point",
    "themeName",
    "capture",
    "projectionId",
    "loScale",
    "hiScale",
    "url"
})
public class OverlappingThemeInfo {

    @XmlElement(name = "LocalTheme")
    protected boolean localTheme;
    @XmlElement(name = "Theme")
    protected int theme;
    @XmlElement(name = "Point", required = true)
    protected LonLatPt point;
    @XmlElement(name = "ThemeName")
    protected String themeName;
    @XmlElement(name = "Capture", required = true)
    protected XMLGregorianCalendar capture;
    @XmlElement(name = "ProjectionId", required = true)
    protected ProjectionType projectionId;
    @XmlElement(name = "LoScale", required = true)
    protected Scale loScale;
    @XmlElement(name = "HiScale", required = true)
    protected Scale hiScale;
    @XmlElement(name = "Url")
    protected String url;

    /**
     * Gets the value of the localTheme property.
     * 
     */
    public boolean isLocalTheme() {
        return localTheme;
    }

    /**
     * Sets the value of the localTheme property.
     * 
     */
    public void setLocalTheme(boolean value) {
        this.localTheme = value;
    }

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
     * Gets the value of the point property.
     * 
     * @return
     *     possible object is
     *     {@link LonLatPt }
     *     
     */
    public LonLatPt getPoint() {
        return point;
    }

    /**
     * Sets the value of the point property.
     * 
     * @param value
     *     allowed object is
     *     {@link LonLatPt }
     *     
     */
    public void setPoint(LonLatPt value) {
        this.point = value;
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
     * Gets the value of the capture property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCapture() {
        return capture;
    }

    /**
     * Sets the value of the capture property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCapture(XMLGregorianCalendar value) {
        this.capture = value;
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
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

}
