package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.InputStream;

@Configuration
public class PurchaseApplicationConfig {

    @Bean
    public Validator validator() throws SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        InputStream resourceAsStream = PurchaseApplicationConfig.class.getClassLoader().getResourceAsStream("schema.xsd");
        Source schemaUser = new StreamSource(resourceAsStream);
        Schema schema = factory.newSchema(new Source[]{schemaUser});

        return schema.newValidator();
    }
}
