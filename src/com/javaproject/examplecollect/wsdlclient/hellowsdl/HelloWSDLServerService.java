
package com.javaproject.examplecollect.wsdlclient.hellowsdl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "HelloWSDLServerService", targetNamespace = "http://wsdl.examplecollect.javaproject.com/", wsdlLocation = "http://192.168.0.3/hello?wsdl")
public class HelloWSDLServerService
    extends Service
{

    private final static URL HELLOWSDLSERVERSERVICE_WSDL_LOCATION;
    private final static WebServiceException HELLOWSDLSERVERSERVICE_EXCEPTION;
    private final static QName HELLOWSDLSERVERSERVICE_QNAME = new QName("http://wsdl.examplecollect.javaproject.com/", "HelloWSDLServerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://192.168.0.3/hello?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        HELLOWSDLSERVERSERVICE_WSDL_LOCATION = url;
        HELLOWSDLSERVERSERVICE_EXCEPTION = e;
    }

    public HelloWSDLServerService() {
        super(__getWsdlLocation(), HELLOWSDLSERVERSERVICE_QNAME);
    }

    public HelloWSDLServerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), HELLOWSDLSERVERSERVICE_QNAME, features);
    }

    public HelloWSDLServerService(URL wsdlLocation) {
        super(wsdlLocation, HELLOWSDLSERVERSERVICE_QNAME);
    }

    public HelloWSDLServerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, HELLOWSDLSERVERSERVICE_QNAME, features);
    }

    public HelloWSDLServerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HelloWSDLServerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns HelloWSDLServer
     */
    @WebEndpoint(name = "HelloWSDLServerPort")
    public HelloWSDLServer getHelloWSDLServerPort() {
        return super.getPort(new QName("http://wsdl.examplecollect.javaproject.com/", "HelloWSDLServerPort"), HelloWSDLServer.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HelloWSDLServer
     */
    @WebEndpoint(name = "HelloWSDLServerPort")
    public HelloWSDLServer getHelloWSDLServerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://wsdl.examplecollect.javaproject.com/", "HelloWSDLServerPort"), HelloWSDLServer.class, features);
    }

    private static URL __getWsdlLocation() {
        if (HELLOWSDLSERVERSERVICE_EXCEPTION!= null) {
            throw HELLOWSDLSERVERSERVICE_EXCEPTION;
        }
        return HELLOWSDLSERVERSERVICE_WSDL_LOCATION;
    }

}
