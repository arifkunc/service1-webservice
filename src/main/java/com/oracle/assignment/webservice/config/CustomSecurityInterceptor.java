package com.oracle.assignment.webservice.config;

import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CustomSecurityInterceptor implements EndpointInterceptor {

    private final String HEADER_NAMESPACE = "http://www.oracle.com";

    private String username;
    private String password;

    public CustomSecurityInterceptor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean handleRequest(MessageContext messageContext, Object o) throws Exception {
        SaajSoapMessage saajSoapMessage = (SaajSoapMessage) messageContext.getRequest();
        SOAPMessage soapMessage = saajSoapMessage.getSaajMessage();

        SOAPHeader soapHeader = soapMessage.getSOAPHeader();

        Node usernameElement = soapHeader.getElementsByTagNameNS(HEADER_NAMESPACE, "username").item(0);
        Node passwordElement = soapHeader.getElementsByTagNameNS(HEADER_NAMESPACE, "password").item(0);

        String username = usernameElement.getTextContent();
        String password = passwordElement.getTextContent();

        validateUsernamePassword(username, password);

        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object o) throws Exception {
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object o) throws Exception {
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object o, Exception e) throws Exception {

    }

    private void validateUsernamePassword(String username, String password) throws Exception {
        if(!(username.equals(this.username) && password.equals(this.password))){
            throw new Exception("Invalid username or password");
        }
    }
}
