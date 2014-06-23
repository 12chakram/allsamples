package biz.neustar.dece.portal.saml.auth;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.opensaml.common.binding.security.BaseSAMLSimpleSignatureSecurityPolicyRule;
import org.opensaml.common.binding.security.SAMLProtocolMessageXMLSignatureSecurityPolicyRule;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.binding.security.SAML2HTTPPostSimpleSignRule;
import org.opensaml.saml2.binding.security.SAML2HTTPRedirectDeflateSignatureRule;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.metadata.SPSSODescriptor;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.security.MetadataCredentialResolver;
import org.opensaml.security.MetadataCriteria;
import org.opensaml.security.SAMLSignatureProfileValidator;
import org.opensaml.xml.Configuration;
import org.opensaml.xml.parse.BasicParserPool;
import org.opensaml.xml.parse.ParserPool;
import org.opensaml.xml.security.CriteriaSet;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.credential.UsageType;
import org.opensaml.xml.security.criteria.EntityIDCriteria;
import org.opensaml.xml.security.criteria.UsageCriteria;
import org.opensaml.xml.security.keyinfo.KeyInfoCredentialResolver;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureTrustEngine;
import org.opensaml.xml.signature.impl.ExplicitKeySignatureTrustEngine;

public class SignatureVerifier
{
	private MetdataProviderFile deceMetaDataProvider = null;
	protected Logger log = Logger.getLogger(getClass());

	private ExplicitKeySignatureTrustEngine signatureTrustEngine = null;
	private SAMLSignatureProfileValidator signatureProfileValidator = null;
	private BaseSAMLSimpleSignatureSecurityPolicyRule signatureRule;
	private ParserPool parser;

	public SAMLSignatureProfileValidator getSignatureProfileValidator()
	{
		return signatureProfileValidator;
	}

	public void setSignatureProfileValidator(
			SAMLSignatureProfileValidator signatureProfileValidator)
	{
		this.signatureProfileValidator = signatureProfileValidator;
	}

	public ExplicitKeySignatureTrustEngine getSignatureTrustEngine()
	{
		return signatureTrustEngine;
	}

	public void setSignatureTrustEngine(
			ExplicitKeySignatureTrustEngine signatureTrustEngine)
	{
		this.signatureTrustEngine = signatureTrustEngine;
	}

	public SignatureVerifier(String filePath, String postORget) throws IOException, MetadataProviderException
	{
		// Remove the File Based Dece Metadataprovider to the non file based
		// or string based deceMetaDataProvider=new
		// DeceMetadataProvider(s,true);
		deceMetaDataProvider = new MetdataProviderFile(filePath);
		// System.out.println("Meta data provider has been created");
		MetadataCredentialResolver mdCredResolver = new MetadataCredentialResolver(
				deceMetaDataProvider.getFilesystemMetadataProvider());
		KeyInfoCredentialResolver keyInfoCredResolver = Configuration
				.getGlobalSecurityConfiguration()
				.getDefaultKeyInfoCredentialResolver();
		signatureTrustEngine = new ExplicitKeySignatureTrustEngine(
				mdCredResolver, keyInfoCredResolver);
		if (postORget.equalsIgnoreCase("GET"))
		{
			signatureRule = new SAML2HTTPRedirectDeflateSignatureRule(
					this.getSignatureTrustEngine());
		}
		else
		{
			parser = new BasicParserPool();
			signatureRule = new SAML2HTTPPostSimpleSignRule(
					this.getSignatureTrustEngine(), parser, keyInfoCredResolver);
		}
		// storeSignatureTrustEngine(trustEngine);
	}

	public SAMLProtocolMessageXMLSignatureSecurityPolicyRule buildSAMLProtocolMessageXMLSignatureSecurityPolicyRule(
			ExplicitKeySignatureTrustEngine ekse)
	{
		SAMLProtocolMessageXMLSignatureSecurityPolicyRule sAMLProtocolMessageXMLSignatureSecurityPolicyRule = 
														new SAMLProtocolMessageXMLSignatureSecurityPolicyRule(ekse);
		return sAMLProtocolMessageXMLSignatureSecurityPolicyRule;
	}

	public BaseSAMLSimpleSignatureSecurityPolicyRule getSignatureRule()
	{
		return signatureRule;
	}

	public void setSignatureRule(
			SAML2HTTPRedirectDeflateSignatureRule signatureRule)
	{
		this.signatureRule = signatureRule;
	}

	public void verify(AuthnRequest authnRequest, Signature signature,
			String sigAlg) throws SecurityException, Exception
	{
		SAMLSignatureProfileValidator profileValidator = getSignatureProfileValidator();
		// try {
		// profileValidator.validate(response.getSignature());
		profileValidator.validate(signature);
		/*
		 * } catch (ValidationException e) { // Indicates signature did not
		 * conform to SAML Signature profile e.printStackTrace(); }
		 */

		SignatureTrustEngine sigTrustEngine = getSignatureTrustEngine();
		CriteriaSet criteriaSet = new CriteriaSet();
		criteriaSet.add(new EntityIDCriteria(authnRequest.getIssuer()
				.getValue()));
		criteriaSet
				.add(new MetadataCriteria(SPSSODescriptor.DEFAULT_ELEMENT_NAME,
						SAMLConstants.SAML20P_NS));
		criteriaSet.add(new UsageCriteria(UsageType.SIGNING));

		// try {
		if (!sigTrustEngine.validate(signature, criteriaSet))
		{
			throw new Exception(
					"Signature was either invalid or signing key could not be established as trusted");
		}
	}

	public void verify(AuthnRequest authnRequest) throws Exception
	{
		SAMLSignatureProfileValidator profileValidator = getSignatureProfileValidator();

		profileValidator.validate(authnRequest.getSignature());

		SignatureTrustEngine sigTrustEngine = getSignatureTrustEngine();
		CriteriaSet criteriaSet = new CriteriaSet();
		criteriaSet.add(new EntityIDCriteria(authnRequest.getIssuer()
				.getValue()));
		criteriaSet
				.add(new MetadataCriteria(SPSSODescriptor.DEFAULT_ELEMENT_NAME,
						SAMLConstants.SAML20P_NS));
		criteriaSet.add(new UsageCriteria(UsageType.SIGNING));

		// try {
		if (!sigTrustEngine.validate(authnRequest.getSignature(), criteriaSet))
		{
			log.info("Signature was verified");
		}
	}

	public MetdataProviderFile getDeceMetaDataProvider()
	{
		return deceMetaDataProvider;
	}

	public void setDeceMetaDataProvider(
			MetdataProviderFile deceMetaDataProvider)
	{
		this.deceMetaDataProvider = deceMetaDataProvider;
	}
}
