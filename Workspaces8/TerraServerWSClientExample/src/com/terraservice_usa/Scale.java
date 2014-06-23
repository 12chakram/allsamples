
package com.terraservice_usa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 * <p>Java class for Scale.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Scale">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Scale1mm"/>
 *     &lt;enumeration value="Scale2mm"/>
 *     &lt;enumeration value="Scale4mm"/>
 *     &lt;enumeration value="Scale8mm"/>
 *     &lt;enumeration value="Scale16mm"/>
 *     &lt;enumeration value="Scale32mm"/>
 *     &lt;enumeration value="Scale63mm"/>
 *     &lt;enumeration value="Scale125mm"/>
 *     &lt;enumeration value="Scale250mm"/>
 *     &lt;enumeration value="Scale500mm"/>
 *     &lt;enumeration value="Scale1m"/>
 *     &lt;enumeration value="Scale2m"/>
 *     &lt;enumeration value="Scale4m"/>
 *     &lt;enumeration value="Scale8m"/>
 *     &lt;enumeration value="Scale16m"/>
 *     &lt;enumeration value="Scale32m"/>
 *     &lt;enumeration value="Scale64m"/>
 *     &lt;enumeration value="Scale128m"/>
 *     &lt;enumeration value="Scale256m"/>
 *     &lt;enumeration value="Scale512m"/>
 *     &lt;enumeration value="Scale1km"/>
 *     &lt;enumeration value="Scale2km"/>
 *     &lt;enumeration value="Scale4km"/>
 *     &lt;enumeration value="Scale8km"/>
 *     &lt;enumeration value="Scale16km"/>
 *     &lt;enumeration value="Scale32km"/>
 *     &lt;enumeration value="Scale64km"/>
 *     &lt;enumeration value="Scale128km"/>
 *     &lt;enumeration value="Scale256km"/>
 *     &lt;enumeration value="Scale512km"/>
 *     &lt;enumeration value="Scale1024km"/>
 *     &lt;enumeration value="Scale2048km"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum Scale {

    @XmlEnumValue("Scale1024km")
    SCALE_1024_KM("Scale1024km"),
    @XmlEnumValue("Scale125mm")
    SCALE_125_MM("Scale125mm"),
    @XmlEnumValue("Scale128km")
    SCALE_128_KM("Scale128km"),
    @XmlEnumValue("Scale128m")
    SCALE_128_M("Scale128m"),
    @XmlEnumValue("Scale16km")
    SCALE_16_KM("Scale16km"),
    @XmlEnumValue("Scale16m")
    SCALE_16_M("Scale16m"),
    @XmlEnumValue("Scale16mm")
    SCALE_16_MM("Scale16mm"),
    @XmlEnumValue("Scale1km")
    SCALE_1_KM("Scale1km"),
    @XmlEnumValue("Scale1m")
    SCALE_1_M("Scale1m"),
    @XmlEnumValue("Scale1mm")
    SCALE_1_MM("Scale1mm"),
    @XmlEnumValue("Scale2048km")
    SCALE_2048_KM("Scale2048km"),
    @XmlEnumValue("Scale250mm")
    SCALE_250_MM("Scale250mm"),
    @XmlEnumValue("Scale256km")
    SCALE_256_KM("Scale256km"),
    @XmlEnumValue("Scale256m")
    SCALE_256_M("Scale256m"),
    @XmlEnumValue("Scale2km")
    SCALE_2_KM("Scale2km"),
    @XmlEnumValue("Scale2m")
    SCALE_2_M("Scale2m"),
    @XmlEnumValue("Scale2mm")
    SCALE_2_MM("Scale2mm"),
    @XmlEnumValue("Scale32km")
    SCALE_32_KM("Scale32km"),
    @XmlEnumValue("Scale32m")
    SCALE_32_M("Scale32m"),
    @XmlEnumValue("Scale32mm")
    SCALE_32_MM("Scale32mm"),
    @XmlEnumValue("Scale4km")
    SCALE_4_KM("Scale4km"),
    @XmlEnumValue("Scale4m")
    SCALE_4_M("Scale4m"),
    @XmlEnumValue("Scale4mm")
    SCALE_4_MM("Scale4mm"),
    @XmlEnumValue("Scale500mm")
    SCALE_500_MM("Scale500mm"),
    @XmlEnumValue("Scale512km")
    SCALE_512_KM("Scale512km"),
    @XmlEnumValue("Scale512m")
    SCALE_512_M("Scale512m"),
    @XmlEnumValue("Scale63mm")
    SCALE_63_MM("Scale63mm"),
    @XmlEnumValue("Scale64km")
    SCALE_64_KM("Scale64km"),
    @XmlEnumValue("Scale64m")
    SCALE_64_M("Scale64m"),
    @XmlEnumValue("Scale8km")
    SCALE_8_KM("Scale8km"),
    @XmlEnumValue("Scale8m")
    SCALE_8_M("Scale8m"),
    @XmlEnumValue("Scale8mm")
    SCALE_8_MM("Scale8mm");
    private final String value;

    Scale(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Scale fromValue(String v) {
        for (Scale c: Scale.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
