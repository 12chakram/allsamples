package biz.neustar.dece.portal.saml.auth;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.opensaml.DefaultBootstrap;
import org.opensaml.saml2.core.SubjectQuery;
import org.opensaml.xml.Configuration;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.security.keyinfo.KeyInfoHelper;
import org.opensaml.xml.security.x509.BasicX509Credential;
import org.opensaml.xml.signature.SignatureConstants;
import org.opensaml.xml.signature.impl.KeyInfoBuilder;
import org.opensaml.xml.signature.impl.KeyInfoImpl;
import org.opensaml.xml.signature.impl.SignatureBuilder;
import org.opensaml.xml.signature.impl.SignatureImpl;

import biz.neustar.dece.portal.saml.extensions.DecePolicy;
import biz.neustar.dece.portal.saml.extensions.DecePolicyList;
import biz.neustar.dece.portal.saml.extensions.DeceResourceStatus;
import biz.neustar.dece.portal.saml.extensions.impl.DecePolicyBuilder;
import biz.neustar.dece.portal.saml.extensions.impl.DecePolicyListBuilder;
import biz.neustar.dece.portal.saml.extensions.impl.DecePolicyListMarshaller;
import biz.neustar.dece.portal.saml.extensions.impl.DecePolicyListUnMarshaller;
import biz.neustar.dece.portal.saml.extensions.impl.DecePolicyMarshaller;
import biz.neustar.dece.portal.saml.extensions.impl.DecePolicyUnMarshaller;
import biz.neustar.dece.portal.saml.extensions.impl.DeceResourceStatusBuilder;
import biz.neustar.dece.portal.saml.extensions.impl.DeceResourceStatusMarshaller;
import biz.neustar.dece.portal.saml.extensions.impl.DeceResourceStatusUnMarshaller;
import biz.neustar.dece.portal.saml.extensions.impl.DeceSubjectQueryBuilder;
import biz.neustar.dece.portal.saml.extensions.impl.DeceSubjectQueryMarshaller;
import biz.neustar.dece.portal.saml.extensions.impl.DeceSubjectQueryUnMarshaller;

public class SAMLCommon
{
	private PrivateKeyCache privateKeyCache = null;
	private PublicKeyCache publicKeyCache = null;
	private final Logger logger = Logger.getLogger(SAMLCommon.class);
	private XMLObjectBuilderFactory builderFactory = null;
	private Random generator = null;
	private BasicX509Credential credential = null;

	public BasicX509Credential getCredential()
	{
		return credential;
	}

	public void setCredential(BasicX509Credential credential)
	{
		this.credential = credential;
	}

	public Random getGenerator()
	{
		return generator;
	}

	public void setGenerator(Random generator)
	{
		this.generator = generator;
	}

	public XMLObjectBuilderFactory getBuilderFactory()
	{
		return builderFactory;
	}

	public void setBuilderFactory(XMLObjectBuilderFactory builderFactory)
	{
		this.builderFactory = builderFactory;
	}

	/**
	 * @throws ConfigurationException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws CertificateException 
	 * @throws Exception 
	 * 
	 */
	public SAMLCommon(String privateKeyLoc, String publicKeyLoc)
			throws ConfigurationException, FileNotFoundException, IOException,
			CertificateException
	{
		super();

		System.out.println("Initializing SAML Common");
		DefaultBootstrap.bootstrap();
		Configuration.registerObjectProvider(
				DecePolicyList.DEFAULT_ELEMENT_NAME,
				new DecePolicyListBuilder(), new DecePolicyListMarshaller(),
				new DecePolicyListUnMarshaller());
		Configuration.registerObjectProvider(DecePolicy.DEFAULT_ELEMENT_NAME,
				new DecePolicyBuilder(), new DecePolicyMarshaller(),
				new DecePolicyUnMarshaller());
		Configuration.registerObjectProvider(
				DeceResourceStatus.DEFAULT_ELEMENT_NAME,
				new DeceResourceStatusBuilder(),
				new DeceResourceStatusMarshaller(),
				new DeceResourceStatusUnMarshaller());
		Configuration.registerObjectProvider(SubjectQuery.DEFAULT_ELEMENT_NAME,
				new DeceSubjectQueryBuilder(),
				new DeceSubjectQueryMarshaller(),
				new DeceSubjectQueryUnMarshaller());
		privateKeyCache = new PrivateKeyCache();
		publicKeyCache = new PublicKeyCache();
		this.privateKeyCache.readPrivateKey(privateKeyLoc);
		logger.info("Certificate Location=" + publicKeyLoc);
		InputStream in = new FileInputStream(publicKeyLoc);
		this.publicKeyCache.readPublicKey(in);
		builderFactory = Configuration.getBuilderFactory();
		generator = new Random();
	}

	public SignatureImpl createSignature() throws CertificateEncodingException
	{
		SignatureImpl signature = null;

		KeyInfoImpl keyInfo = null;

		// Use the OpenSAML Configuration singleton to get a builder factory
		// object
		XMLObjectBuilderFactory builderFactory = Configuration
				.getBuilderFactory();

		// Set up the signing credentials if we have been given them.
		logger.info("Before checking private key");
		if (privateKeyCache == null)
			logger.info("Private Cache is null");
		if (privateKeyCache != null)
		{
			logger.info("Configuring signature ...");

			// org.opensaml.xml.signature.impl.SignatureBuilder
			SignatureBuilder signatureBuilder = new SignatureBuilder();

			signature = signatureBuilder.buildObject();
			credential = new BasicX509Credential();
			// Set the private key used to sign the messages
			credential.setPrivateKey(privateKeyCache.getPrivateKey());
			// add the public key if we have it
			if (publicKeyCache != null)
			{
				credential.setPublicKey(publicKeyCache.getPublicKey());
				// Now add a KeyInfo section to the signature so we can send
				// our public certificate in it
				KeyInfoBuilder keyInfoBuilder = new KeyInfoBuilder();
				keyInfo = (KeyInfoImpl) keyInfoBuilder.buildObject();
				// KeyInfoHelper.addPublicKey(keyInfo,
				// publicKeyCache.getPublicKey());
				if (keyInfo == null)
					System.out.println("KeyInfo is null");
				if (publicKeyCache.getX509Certificate() == null)
					System.out.println("Certificate is null");
				KeyInfoHelper.addCertificate(keyInfo,
						publicKeyCache.getX509Certificate());
				signature.setKeyInfo(keyInfo);
				if (logger.isDebugEnabled())
					logger.debug("KeyInfo added to signature.");
			}

			signature.setSigningCredential(credential);
			signature.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA1);
			signature.setCanonicalizationAlgorithm(SignatureConstants.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);
		}
		return signature;
	}
}
