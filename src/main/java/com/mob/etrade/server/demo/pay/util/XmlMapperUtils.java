package com.mob.etrade.server.demo.pay.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/23
 */
public class XmlMapperUtils {

    private static final XmlMapper XML_MAPPER = new XmlMapper();

    public static <T> T toBean(String xmlStr, Class<T> beanType) {
        try {
            return XML_MAPPER.readValue(xmlStr, beanType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> String toXml(T bean) {
        try {
            return XML_MAPPER.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
