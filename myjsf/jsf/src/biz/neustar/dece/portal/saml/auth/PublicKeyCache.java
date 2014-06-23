package biz.neustar.dece.portal.saml.auth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class PublicKeyCache
{
	private String publicKeyEncoded;
	private RSAPublicKey publicKey;
	private X509Certificate x509Cert;

	public X509Certificate getX509Certificate()
	{
		return x509Cert;
	}

	/*
	 * Set the BASE64 encoded value of the public key.
	 * 
	 * @param s The BASE64 encoded value.
	 */
	public void setPublicKeyEncoded(String s)
	{
		publicKeyEncoded = s;
	}

	/*
	 * Get the BASE64 encoded value of the public key.
	 * 
	 * @return The BASE64 encoded value.
	 */
	public String getPublicKeyEncoded()
	{
		return publicKeyEncoded;
	}

	/** Creates a new instance of PublicKeyCache */
	public PublicKeyCache()
	{
		publicKeyEncoded = null;
		publicKey = null;
		// Security.addProvider(new BouncyCastleProvider());
		Security.insertProviderAt(new BouncyCastleProvider(), 2);
	}

	/*
	 * Get the PublicKey object for use in signing SAML objects.
	 * 
	 * @return The PublicKey object.
	 */
	public PublicKey getPublicKey()
	{
		return publicKey;
	}

	/*
	 * Read the public key from a PEM encoded file and store the encoded string
	 * in this object.
	 * 
	 * @param publicKeyFile An InputStream containing the PEM encoded public
	 * key.
	 */
	public void readPublicKey(InputStream publicKeyFile)
			throws java.io.FileNotFoundException, java.io.IOException,
			java.security.cert.CertificateException
	{
		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(publicKeyFile));
		CertificateFactory cf;
		// X509Certificate cert;
		// RSAPublicKey k;

		cf = CertificateFactory.getInstance("X.509");
		x509Cert = (X509Certificate) cf.generateCertificate(publicKeyFile);
		publicKeyFile.close();
		publicKey = (RSAPublicKey) x509Cert.getPublicKey();

		// readPublicKey(in);
	}

	/*
	 * Read the public key from a PEM encoded file and store the encoded string
	 * in this object.
	 * 
	 * @param publicKeyFile The file containing the PEM encoded public key.
	 */
	public void readPublicKey(String publicKeyFile)
			throws java.io.FileNotFoundException, java.io.IOException
	{
		// try to load a public key
		BufferedReader in = new BufferedReader(new FileReader(publicKeyFile));
		readPublicKey(in);
	}

	/*
	 * Read the public key from a PEM encoded file and store the encoded string
	 * in this object.
	 * 
	 * @param publicKeyFile A BufferedReader containing the PEM encoded public
	 * key.
	 */
	private void readPublicKey(BufferedReader publicKeyFile)
			throws java.io.FileNotFoundException, java.io.IOException
	{
		String line, encodedPublicKey;

		encodedPublicKey = "";

		line = publicKeyFile.readLine();
		while (line != null)
		{
			encodedPublicKey += line + "\r\n";
			line = publicKeyFile.readLine();
		}
		publicKeyFile.close();
		// Remove the markers from the data
		encodedPublicKey = encodedPublicKey.replace(
				"-----BEGIN CERTIFICATE-----", "");
		encodedPublicKey = encodedPublicKey.replace(
				"-----END CERTIFICATE-----", "");
		encodedPublicKey = encodedPublicKey.trim();
		// set the value in this object
		// System.out.println("encoded public key="+encodedPublicKey);
		this.setPublicKeyEncoded(encodedPublicKey);

		return;
	}
}
