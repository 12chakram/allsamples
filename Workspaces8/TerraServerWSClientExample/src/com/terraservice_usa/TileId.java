
package com.terraservice_usa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TileId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TileId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Theme" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Scale" type="{http://terraservice-usa.com/}Scale"/>
 *         &lt;element name="Scene" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="X" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TileId", propOrder = {
    "theme",
    "scale",
    "scene",
    "x",
    "y"
})
public class TileId {

    @XmlElement(name = "Theme")
    protected int theme;
    @XmlElement(name = "Scale", required = true)
    protected Scale scale;
    @XmlElement(name = "Scene")
    protected int scene;
    @XmlElement(name = "X")
    protected int x;
    @XmlElement(name = "Y")
    protected int y;

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
     * Gets the value of the scale property.
     * 
     * @return
     *     possible object is
     *     {@link Scale }
     *     
     */
    public Scale getScale() {
        return scale;
    }

    /**
     * Sets the value of the scale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Scale }
     *     
     */
    public void setScale(Scale value) {
        this.scale = value;
    }

    /**
     * Gets the value of the scene property.
     * 
     */
    public int getScene() {
        return scene;
    }

    /**
     * Sets the value of the scene property.
     * 
     */
    public void setScene(int value) {
        this.scene = value;
    }

    /**
     * Gets the value of the x property.
     * 
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the value of the x property.
     * 
     */
    public void setX(int value) {
        this.x = value;
    }

    /**
     * Gets the value of the y property.
     * 
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the value of the y property.
     * 
     */
    public void setY(int value) {
        this.y = value;
    }

}
