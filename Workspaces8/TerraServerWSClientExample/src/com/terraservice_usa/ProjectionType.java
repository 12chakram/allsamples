
package com.terraservice_usa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 * <p>Java class for ProjectionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProjectionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Geographic"/>
 *     &lt;enumeration value="UtmNad27"/>
 *     &lt;enumeration value="UtmNad83"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum ProjectionType {

    @XmlEnumValue("Geographic")
    GEOGRAPHIC("Geographic"),
    @XmlEnumValue("UtmNad27")
    UTM_NAD_27("UtmNad27"),
    @XmlEnumValue("UtmNad83")
    UTM_NAD_83("UtmNad83");
    private final String value;

    ProjectionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProjectionType fromValue(String v) {
        for (ProjectionType c: ProjectionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
