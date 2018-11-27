
package com.javaproject.examplecollect.wsdlclient.hellowsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.javaproject.examplecollect.wsdlclient.hellowsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ReturnMessageResponse_QNAME = new QName("http://wsdl.examplecollect.javaproject.com/", "returnMessageResponse");
    private final static QName _ReturnMessage_QNAME = new QName("http://wsdl.examplecollect.javaproject.com/", "returnMessage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.javaproject.examplecollect.wsdlclient.hellowsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReturnMessage }
     * 
     */
    public ReturnMessage createReturnMessage() {
        return new ReturnMessage();
    }

    /**
     * Create an instance of {@link ReturnMessageResponse }
     * 
     */
    public ReturnMessageResponse createReturnMessageResponse() {
        return new ReturnMessageResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsdl.examplecollect.javaproject.com/", name = "returnMessageResponse")
    public JAXBElement<ReturnMessageResponse> createReturnMessageResponse(ReturnMessageResponse value) {
        return new JAXBElement<ReturnMessageResponse>(_ReturnMessageResponse_QNAME, ReturnMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsdl.examplecollect.javaproject.com/", name = "returnMessage")
    public JAXBElement<ReturnMessage> createReturnMessage(ReturnMessage value) {
        return new JAXBElement<ReturnMessage>(_ReturnMessage_QNAME, ReturnMessage.class, null, value);
    }

}
