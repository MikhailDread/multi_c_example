package com.example.demo.utils;

import com.example.demo.data.xml.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

public class MarshallerUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarshallerUtil.class);

    public static User unmarshalToUser(String xmlPurchaseInfo) throws JAXBException {
        try {
            JAXBContext context = JAXBContext.newInstance(User.class);
            return (User) context.createUnmarshaller()
                    .unmarshal(new StreamSource(new StringReader(xmlPurchaseInfo)));
        } catch (JAXBException e) {
            LOGGER.error("Произошла ошибка в процессе сопоставления схемы с сущностью");
        }

        throw new JAXBException("Ошибка анмаршаллинга");
    }
}
