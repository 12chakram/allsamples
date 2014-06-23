
package com.terraservice_usa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PlaceFacts complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PlaceFacts">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Place" type="{http://terraservice-usa.com/}Place"/>
 *         &lt;element name="Center" type="{http://terraservice-usa.com/}LonLatPt"/>
 *         &lt;element name="AvailableThemeMask" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PlaceTypeId" type="{http://terraservice-usa.com/}PlaceType"/>
 *         &lt;element name="Population" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlaceFacts", propOrder = {
    "place",
    "center",
    "availableThemeMask",
    "placeTypeId",
    "population"
})
public class PlaceFacts {

    @XmlElement(name = "Place", required = true)
    protected Place place;
    @XmlElement(name = "Center", required = true)
    protected LonLatPt center;
    @XmlElement(name = "AvailableThemeMask")
    protected int availableThemeMask;
    @XmlElement(name = "PlaceTypeId", required = true)
    protected PlaceType placeTypeId;
    @XmlElement(name = "Population")
    protected int population;

    /**
     * Gets the value of the place property.
     * 
     * @return
     *     possible object is
     *     {@link Place }
     *     
     */
    public Place getPlace() {
        return place;
    }

    /**
     * Sets the value of the place property.
     * 
     * @param value
     *     allowed object is
     *     {@link Place }
     *     
     */
    public void setPlace(Place value) {
        this.place = value;
    }

    /**
     * Gets the value of the center property.
     * 
     * @return
     *     possible object is
     *     {@link LonLatPt }
     *     
     */
    public LonLatPt getCenter() {
        return center;
    }

    /**
     * Sets the value of the center property.
     * 
     * @param value
     *     allowed object is
     *     {@link LonLatPt }
     *     
     */
    public void setCenter(LonLatPt value) {
        this.center = value;
    }

    /**
     * Gets the value of the availableThemeMask property.
     * 
     */
    public int getAvailableThemeMask() {
        return availableThemeMask;
    }

    /**
     * Sets the value of the availableThemeMask property.
     * 
     */
    public void setAvailableThemeMask(int value) {
        this.availableThemeMask = value;
    }

    /**
     * Gets the value of the placeTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link PlaceType }
     *     
     */
    public PlaceType getPlaceTypeId() {
        return placeTypeId;
    }

    /**
     * Sets the value of the placeTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlaceType }
     *     
     */
    public void setPlaceTypeId(PlaceType value) {
        this.placeTypeId = value;
    }

    /**
     * Gets the value of the population property.
     * 
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Sets the value of the population property.
     * 
     */
    public void setPopulation(int value) {
        this.population = value;
    }

}
