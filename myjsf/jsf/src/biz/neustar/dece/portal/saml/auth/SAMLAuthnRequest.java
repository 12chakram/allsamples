package biz.neustar.dece.portal.saml.auth;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;

import org.joda.time.DateTime;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.common.Extensions;
import org.opensaml.saml2.common.impl.ExtensionsBuilder;
import org.opensaml.saml2.core.Audience;
import org.opensaml.saml2.core.AudienceRestriction;
import org.opensaml.saml2.core.AuthnContextClassRef;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.RequestedAuthnContext;
import org.opensaml.saml2.core.impl.AuthnRequestImpl;
import org.opensaml.saml2.metadata.AssertionConsumerService;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.ws.transport.http.HTTPTransportUtils;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallerFactory;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.signature.SignatureException;
import org.opensaml.xml.signature.impl.SignatureImpl;
import org.opensaml.xml.util.DatatypeHelper;
import org.w3c.dom.Element;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import biz.neustar.dece.portal.saml.util.Util;

public class SAMLAuthnRequest
{
	private String actionURL = null;
	public static final String REDIRECT_BINDING = SAMLConstants.SAML2_REDIRECT_BINDING_URI;
	public static final String POST_BINDING = SAMLConstants.SAML2_POST_BINDING_URI;
	private String bindingUriFormat;

	private SAMLCommon samlCommon = null;
	/** For building audience restriction. */
	private SAMLObjectBuilder<AudienceRestriction> audienceRestrictionBuilder;
	/** For building audience. */
	private SAMLObjectBuilder<Audience> audienceBuilder;
	private SAMLObjectBuilder<RequestedAuthnContext> requestAuthContextBuilder;
	/** Builder of AuthnContextClassRef objects. */
	private SAMLObjectBuilder<AuthnContextClassRef> authnContextClassRefBuilder;
	private SAMLObjectBuilder<AuthnRequest> arb;
	private SAMLObjectBuilder<Issuer> ib;
	private SAMLObjectBuilder<Endpoint> endpointBuilder;
	private SAMLObjectBuilder<Extensions> extenionBuilder;
	private int display;

	public int getDisplay()
	{
		return display;
	}

	public void setDisplay(int display)
	{
		this.display = display;
	}

	public String getLoc()
	{
		return loc;
	}

	public void setLoc(String loc)
	{
		this.loc = loc;
	}

	private String loc;

	public SAMLAuthnRequest(String privateKeyLoc, String publicKeyloc) 
			throws FileNotFoundException, CertificateException, ConfigurationException, IOException
	{
		samlCommon = new SAMLCommon(privateKeyLoc, publicKeyloc);
		audienceRestrictionBuilder = (SAMLObjectBuilder<AudienceRestriction>) this.samlCommon
				.getBuilderFactory().getBuilder(
						AudienceRestriction.DEFAULT_ELEMENT_NAME);
		audienceBuilder = (SAMLObjectBuilder<Audience>) this.samlCommon
				.getBuilderFactory().getBuilder(Audience.DEFAULT_ELEMENT_NAME);
		this.requestAuthContextBuilder = (SAMLObjectBuilder<RequestedAuthnContext>) this.samlCommon
				.getBuilderFactory().getBuilder(
						RequestedAuthnContext.DEFAULT_ELEMENT_NAME);
		authnContextClassRefBuilder = (SAMLObjectBuilder<AuthnContextClassRef>) this.samlCommon
				.getBuilderFactory().getBuilder(
						AuthnContextClassRef.DEFAULT_ELEMENT_NAME);
		arb = (SAMLObjectBuilder<AuthnRequest>) this.samlCommon
				.getBuilderFactory().getBuilder(
						AuthnRequest.DEFAULT_ELEMENT_NAME);
		ib = (SAMLObjectBuilder<Issuer>) this.samlCommon.getBuilderFactory()
				.getBuilder(Issuer.DEFAULT_ELEMENT_NAME);
		endpointBuilder = (SAMLObjectBuilder<Endpoint>) this.samlCommon
				.getBuilderFactory().getBuilder(
						AssertionConsumerService.DEFAULT_ELEMENT_NAME);
		extenionBuilder = new ExtensionsBuilder();
	}

	public Endpoint getEndPoint(String url)
	{
		Endpoint samlEndpoint = endpointBuilder.buildObject();
		String displayString = this.buildDisplayQuery(url, this.getDisplay());
		System.out.println("URL to redirect=" + displayString);
		samlEndpoint.setLocation(displayString);
		return samlEndpoint;
	}

	protected RequestedAuthnContext buildAuthnContext()
	{
		RequestedAuthnContext requestedAuthnContext = this.requestAuthContextBuilder
				.buildObject();
		AuthnContextClassRef authnContextClassRef = this.authnContextClassRefBuilder
				.buildObject();
		authnContextClassRef
				.setAuthnContextClassRef("urn:oasis:names:tc:SAML:2.0:ac:classes:Password");
		requestedAuthnContext.getAuthnContextClassRefs().add(
				authnContextClassRef);
		return requestedAuthnContext;
	}

	public String buildDisplayQuery(String url, int display)
	{
		StringBuilder builder = new StringBuilder();
		// if (queryParams.size() > 0) {
		String name = "display";
		String value = String.valueOf(display);

		// Pair<String, String> param;
		if (name != null)
		{
			builder.append(HTTPTransportUtils.urlEncode(name));
			value = DatatypeHelper.safeTrimOrNullString(value);
			if (value != null)
			{
				builder.append("=");
				builder.append(HTTPTransportUtils.urlEncode(value));
			}

		}
		StringBuffer compDisplay = new StringBuffer();
		compDisplay.append(url);
		compDisplay.append("?");
		compDisplay.append(builder.toString());

		return compDisplay.toString();
	}

	public String getBindingUriFormat()
	{
		return bindingUriFormat;
	}

	public void setBindingUriFormat(String bindingUriFormat)
	{
		this.bindingUriFormat = bindingUriFormat;
	}

	public String getActionURL()
	{
		return actionURL;
	}

	public void setActionURL(String actionURL)
	{
		this.actionURL = actionURL;
	}

	public SAMLCommon getSamlCommon()
	{
		return samlCommon;
	}

	public void setSamlCommon(SAMLCommon samlCommon)
	{
		this.samlCommon = samlCommon;
	}

	public AuthnRequest buildAuthnRequest(SAMLRequestParams samlRequestParams) 
			throws ConfigurationException, CertificateEncodingException, MarshallingException, ClassCastException, 
			ClassNotFoundException, InstantiationException, IllegalAccessException, SignatureException
	{
		org.opensaml.DefaultBootstrap.bootstrap();

		XMLObjectBuilderFactory builderFactory = this.samlCommon
				.getBuilderFactory();
		AuthnRequestImpl auth = (AuthnRequestImpl) arb.buildObject();
		auth.setID(String.valueOf(this.samlCommon.getGenerator().nextInt()));
		auth.setIsPassive(new Boolean(false));

		DateTime issueInstant = new DateTime();
		auth.setIssueInstant(issueInstant);
		auth.setAssertionConsumerServiceURL("https://lp3.dev.uvvu.com:8443/ssp/authResponse");
		auth.setForceAuthn(new Boolean(true));
		auth.setVersion(SAMLVersion.VERSION_20);
		auth.setRequestedAuthnContext(this.buildAuthnContext());

		Issuer newIssuer = ib.buildObject();
		newIssuer.setValue(samlRequestParams.getIssuer());
		auth.setIssuer(newIssuer);

		auth.setConditions(Util.buildSamlConditions(samlRequestParams
				.getAudience()));

		auth.setDestination(actionURL);
		auth.setProtocolBinding(getBindingUriFormat());

		auth.setExtensions(Util.buildSamlExtensions(samlRequestParams));

		SignatureImpl sign1 = this.samlCommon.createSignature();
		auth.setSignature(sign1);

		MarshallerFactory marshallerFactory = org.opensaml.xml.Configuration
				.getMarshallerFactory();
		Marshaller marshaller = marshallerFactory.getMarshaller(auth);
		String msg = "";
		Element authElement = null;
		if (marshaller == null)
		{
			msg = "unable to locate marshaller for ["; // newIssuer.getElementQName
														// * () + "]";
		}
		else
		{
			authElement = marshaller.marshall(auth);
		}

		DOMImplementationRegistry registry = DOMImplementationRegistry
				.newInstance();

		DOMImplementationLS impl = (DOMImplementationLS) registry
				.getDOMImplementation("LS");

		LSSerializer writer = impl.createLSSerializer();
		String str = writer.writeToString(authElement);
		str = str.replace("<", "&lt;");
		str = str.replace(">", "&gt;");

		if (sign1 == null)
			System.out.println("Signature object was  null");
		org.opensaml.xml.signature.Signer.signObject(sign1);

		return auth;
	}
}
