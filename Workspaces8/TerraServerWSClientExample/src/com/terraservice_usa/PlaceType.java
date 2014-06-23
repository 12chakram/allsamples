
package com.terraservice_usa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 * <p>Java class for PlaceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PlaceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UnknownPlaceType"/>
 *     &lt;enumeration value="AirRailStation"/>
 *     &lt;enumeration value="BayGulf"/>
 *     &lt;enumeration value="CapePeninsula"/>
 *     &lt;enumeration value="CityTown"/>
 *     &lt;enumeration value="HillMountain"/>
 *     &lt;enumeration value="Island"/>
 *     &lt;enumeration value="Lake"/>
 *     &lt;enumeration value="OtherLandFeature"/>
 *     &lt;enumeration value="OtherWaterFeature"/>
 *     &lt;enumeration value="ParkBeach"/>
 *     &lt;enumeration value="PointOfInterest"/>
 *     &lt;enumeration value="River"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum PlaceType {

    @XmlEnumValue("AirRailStation")
    AIR_RAIL_STATION("AirRailStation"),
    @XmlEnumValue("BayGulf")
    BAY_GULF("BayGulf"),
    @XmlEnumValue("CapePeninsula")
    CAPE_PENINSULA("CapePeninsula"),
    @XmlEnumValue("CityTown")
    CITY_TOWN("CityTown"),
    @XmlEnumValue("HillMountain")
    HILL_MOUNTAIN("HillMountain"),
    @XmlEnumValue("Island")
    ISLAND("Island"),
    @XmlEnumValue("Lake")
    LAKE("Lake"),
    @XmlEnumValue("OtherLandFeature")
    OTHER_LAND_FEATURE("OtherLandFeature"),
    @XmlEnumValue("OtherWaterFeature")
    OTHER_WATER_FEATURE("OtherWaterFeature"),
    @XmlEnumValue("ParkBeach")
    PARK_BEACH("ParkBeach"),
    @XmlEnumValue("PointOfInterest")
    POINT_OF_INTEREST("PointOfInterest"),
    @XmlEnumValue("River")
    RIVER("River"),
    @XmlEnumValue("UnknownPlaceType")
    UNKNOWN_PLACE_TYPE("UnknownPlaceType");
    private final String value;

    PlaceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PlaceType fromValue(String v) {
        for (PlaceType c: PlaceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
