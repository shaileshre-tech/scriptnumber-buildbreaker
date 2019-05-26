package com.myorg.buildbreaker.script.schema;

import com.myorg.buildbreaker.script.exception.BuildBreakerJaxbException;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.net.URL;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

/**
 * Created by Shailesh on 7/12/16.
 */
public class BuildBreakerJaxbUtility<T> {

    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public BuildBreakerJaxbUtility(String entityPackage, String entitySchema){

        try {
            URL schemaUrl = getClass().getClassLoader().getResource(entitySchema);

            SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(schemaUrl);

            JAXBContext jc = JAXBContext.newInstance(entityPackage);

            marshaller = jc.createMarshaller();
            unmarshaller = jc.createUnmarshaller();

            marshaller.setSchema(schema);
            unmarshaller.setSchema(schema);

        } catch (JAXBException jaxbe){
            throw new BuildBreakerJaxbException("Failed to create JAXBContext", jaxbe);
        } catch (SAXException saxe){
            throw new BuildBreakerJaxbException("Failed to create JAXBContext", saxe);
        }
    }

    public T unMarshal(String xmlFile) {
        try {
            return (T)unmarshaller.unmarshal(new File(xmlFile));
        } catch (JAXBException jaxbe){
            throw new BuildBreakerJaxbException("Failed to un-marshal xml file", jaxbe);
        }
    }

    public void marshal(T t, String xmlFile) {
        try {
            marshaller.marshal(t, new File(xmlFile));
        } catch (JAXBException jaxbe){
            throw new BuildBreakerJaxbException("Failed to marshal xml file", jaxbe);
        }
    }
}
