package biz.neustar.dece.portal.saml.auth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.opensaml.xml.util.Base64;

public class PrivateKeyCache
{
	private String privateKeyEncoded;
	private RSAPrivateKey privateKey;

	/*
	 * Set the BASE64 encoded value of the private key.
	 * 
	 * @param s The BASE64 encoded value.
	 */
	public void setPrivateKeyEncoded(String s)
	{
		privateKeyEncoded = s;
	}

	/*
	 * Get the BASE64 encoded value of the private key.
	 * 
	 * @return The BASE64 encoded value.
	 */
	public String getPrivateKeyEncoded()
	{
		return privateKeyEncoded;
	}

	/** Creates a new instance of PrivateKeyCache */
	public PrivateKeyCache()
	{
		privateKeyEncoded = null;
		privateKey = null;
		// Security.addProvider(new BouncyCastleProvider());
		Security.insertProviderAt(new BouncyCastleProvider(), 2);
	}

	/*
	 * Get the PrivateKey object for use in signing SAML objects.
	 * 
	 * @return The PrivateKey object.
	 */
	public PrivateKey getPrivateKey()
	{
		// RSAPrivateKey privateKey = null;
		KeyFactory keyFactory;
		PKCS8EncodedKeySpec privSpec;
		byte[] binaryKey;

		// If we have created it already, just return it.
		if (privateKey != null)
			return privateKey;

		// Try to create the private key object and then return it.
		try
		{
			keyFactory = KeyFactory.getInstance("RSA");
			// System.out.println("provider = " +
			// keyFactory.getProvider().toString() );
			// decode private key
			binaryKey = Base64.decode(privateKeyEncoded);
			privSpec = new PKCS8EncodedKeySpec(binaryKey);
			privateKey = (RSAPrivateKey) keyFactory.generatePrivate(privSpec);
			return privateKey;
		}
		catch (NoSuchAlgorithmException nsae)
		{
			// System.out.append("NoSuchAlgorithmException");
			nsae.printStackTrace();
			return null;
		}
		catch (InvalidKeySpecException ikse)
		{
			// System.out.append("InvalidKeySpecException");
			ikse.printStackTrace();
			return null;
		}
	}

	/*
	 * Read the private key from a PEM encoded file and store the encoded string
	 * in this object.
	 * 
	 * @param privateKeyFile An InputStream containing the PEM encoded private
	 * key.
	 */
	public void readPrivateKey(InputStream privateKeyFile)
			throws java.io.FileNotFoundException, java.io.IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(
				privateKeyFile));
		readPrivateKey(in);
	}

	/*
	 * Read the private key from a PEM encoded file and store the encoded string
	 * in this object.
	 * 
	 * @param privateKeyFile The file containing the PEM encoded private key.
	 */
	public void readPrivateKey(String privateKeyFile)
			throws java.io.FileNotFoundException, java.io.IOException
	{
		// try to load a private key
		BufferedReader in = new BufferedReader(new FileReader(privateKeyFile));
		readPrivateKey(in);
	}

	/*
	 * Read the private key from a PEM encoded file and store the encoded string
	 * in this object.
	 * 
	 * @param privateKeyFile A BufferedReader containing the PEM encoded private
	 * key.
	 */
	public void readPrivateKey(BufferedReader privateKeyFile)
			throws java.io.FileNotFoundException, java.io.IOException
	{
		String line, encodedPrivateKey;

		encodedPrivateKey = "";

		line = privateKeyFile.readLine();
		while (line != null)
		{
			encodedPrivateKey += line + "\r\n";
			line = privateKeyFile.readLine();
		}
		privateKeyFile.close();
		// Remove the markers from the data
		encodedPrivateKey = encodedPrivateKey.replace(
				"-----BEGIN RSA PRIVATE KEY-----", "");
		encodedPrivateKey = encodedPrivateKey.replace(
				"-----END RSA PRIVATE KEY-----", "");
		encodedPrivateKey = encodedPrivateKey.trim();
		// set the value in this object
		this.setPrivateKeyEncoded(encodedPrivateKey);

		return;
	}
}
