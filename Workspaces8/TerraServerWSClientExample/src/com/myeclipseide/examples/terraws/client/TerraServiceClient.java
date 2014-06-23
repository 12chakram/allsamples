
package com.myeclipseide.examples.terraws.client;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import javax.xml.namespace.QName;
import org.codehaus.xfire.XFireRuntimeException;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Endpoint;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.soap.AbstractSoapBinding;
import org.codehaus.xfire.transport.TransportManager;

public class TerraServiceClient {

    private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
    private HashMap endpoints = new HashMap();
    private Service service0;

    public TerraServiceClient() {
        create0();
        Endpoint TerraServiceSoapEP = service0 .addEndpoint(new QName("http://terraservice-usa.com/", "TerraServiceSoap"), new QName("http://terraservice-usa.com/", "TerraServiceSoap"), "http://terraserver-usa.com/TerraService2.asmx");
        endpoints.put(new QName("http://terraservice-usa.com/", "TerraServiceSoap"), TerraServiceSoapEP);
        Endpoint TerraServiceSoapLocalEndpointEP = service0 .addEndpoint(new QName("http://terraservice-usa.com/", "TerraServiceSoapLocalEndpoint"), new QName("http://terraservice-usa.com/", "TerraServiceSoapLocalBinding"), "xfire.local://TerraService");
        endpoints.put(new QName("http://terraservice-usa.com/", "TerraServiceSoapLocalEndpoint"), TerraServiceSoapLocalEndpointEP);
    }

    public Object getEndpoint(Endpoint endpoint) {
        try {
            return proxyFactory.create((endpoint).getBinding(), (endpoint).getUrl());
        } catch (MalformedURLException e) {
            throw new XFireRuntimeException("Invalid URL", e);
        }
    }

    public Object getEndpoint(QName name) {
        Endpoint endpoint = ((Endpoint) endpoints.get((name)));
        if ((endpoint) == null) {
            throw new IllegalStateException("No such endpoint!");
        }
        return getEndpoint((endpoint));
    }

    public Collection getEndpoints() {
        return endpoints.values();
    }

    private void create0() {
        TransportManager tm = (org.codehaus.xfire.XFireFactory.newInstance().getXFire().getTransportManager());
        HashMap props = new HashMap();
        props.put("annotations.allow.interface", true);
        AnnotationServiceFactory asf = new AnnotationServiceFactory(new Jsr181WebAnnotations(), tm, new AegisBindingProvider(new JaxbTypeRegistry()));
        asf.setBindingCreationEnabled(false);
        service0 = asf.create((com.myeclipseide.examples.terraws.client.TerraServiceSoap.class), props);
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://terraservice-usa.com/", "TerraServiceSoap"), "http://schemas.xmlsoap.org/soap/http");
        }
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://terraservice-usa.com/", "TerraServiceSoapLocalBinding"), "urn:xfire:transport:local");
        }
    }

    public TerraServiceSoap getTerraServiceSoap() {
        return ((TerraServiceSoap)(this).getEndpoint(new QName("http://terraservice-usa.com/", "TerraServiceSoap")));
    }

    public TerraServiceSoap getTerraServiceSoap(String url) {
        TerraServiceSoap var = getTerraServiceSoap();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public TerraServiceSoap getTerraServiceSoapLocalEndpoint() {
        return ((TerraServiceSoap)(this).getEndpoint(new QName("http://terraservice-usa.com/", "TerraServiceSoapLocalEndpoint")));
    }

    public TerraServiceSoap getTerraServiceSoapLocalEndpoint(String url) {
        TerraServiceSoap var = getTerraServiceSoapLocalEndpoint();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public static void main(String[] args) {
        

        TerraServiceClient client = new TerraServiceClient();
        
		//create a default service endpoint
        TerraServiceSoap service = client.getTerraServiceSoap();
        
		//TODO: Add custom client code here
        		//
        		//service.yourServiceOperationHere();
        
		System.out.println("test client completed");
        		System.exit(0);
    }

}
