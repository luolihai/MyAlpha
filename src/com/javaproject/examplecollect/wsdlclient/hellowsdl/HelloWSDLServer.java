
package com.javaproject.examplecollect.wsdlclient.hellowsdl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "HelloWSDLServer", targetNamespace = "http://wsdl.examplecollect.javaproject.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HelloWSDLServer {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "returnMessage", targetNamespace = "http://wsdl.examplecollect.javaproject.com/", className = "com.javaproject.examplecollect.wsdlclient.hellowsdl.ReturnMessage")
    @ResponseWrapper(localName = "returnMessageResponse", targetNamespace = "http://wsdl.examplecollect.javaproject.com/", className = "com.javaproject.examplecollect.wsdlclient.hellowsdl.ReturnMessageResponse")
    @Action(input = "http://wsdl.examplecollect.javaproject.com/HelloWSDLServer/returnMessageRequest", output = "http://wsdl.examplecollect.javaproject.com/HelloWSDLServer/returnMessageResponse")
    public String returnMessage(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1);

}
