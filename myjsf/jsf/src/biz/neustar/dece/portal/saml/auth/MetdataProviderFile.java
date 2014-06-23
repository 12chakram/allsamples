package biz.neustar.dece.portal.saml.auth;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.opensaml.saml2.metadata.AssertionConsumerService;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.SPSSODescriptor;
import org.opensaml.saml2.metadata.provider.FilesystemMetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.xml.parse.BasicParserPool;
import org.opensaml.xml.parse.ParserPool;

public class MetdataProviderFile
{
	ByteArrayInputStream metadata = null;
	private ParserPool parser;
	static Logger log = Logger.getLogger(MetdataProviderFile.class);
	FileInputStream fis = null;
	String metaDataLocation = null;
	FilesystemMetadataProvider filesystemMetadataProvider = null;

	public String getMetaDataLocation()
	{
		return metaDataLocation;
	}

	public void setMetaDataLocation(String metaDataLocation)
	{
		this.metaDataLocation = metaDataLocation;
	}

	public MetdataProviderFile(String metaDataLoc) throws IOException,
			MetadataProviderException
	{
		// A Db call to get meta data. Currently trying to read using FIle
		this.setMetaDataLocation(metaDataLoc);
		filesystemMetadataProvider = new FilesystemMetadataProvider(new File(
				this.getMetaDataLocation()));
		this.filesystemMetadataProvider.setParserPool(new BasicParserPool());
	}

	public MetdataProviderFile()
	{

	}

	public AssertionConsumerService getAssertionConsumerService(
			String serviceProvider, String assertionURL)
			throws MetadataProviderException
	{
		EntityDescriptor spDescriptor = this.filesystemMetadataProvider
				.getEntityDescriptor(serviceProvider);
		if (spDescriptor == null)
		{
			return null;
		}
		SPSSODescriptor spSSODescriptor = spDescriptor
				.getSPSSODescriptor("urn:oasis:names:tc:SAML:2.0:protocol");
		List<AssertionConsumerService> listAcsService = spSSODescriptor
				.getAssertionConsumerServices();

		for (AssertionConsumerService assertionConsumerService : listAcsService)
		{
			if (assertionConsumerService.getLocation().equalsIgnoreCase(
					assertionURL))
				return assertionConsumerService;
		}
		return null;

	}

	public FilesystemMetadataProvider getFilesystemMetadataProvider()
	{
		return filesystemMetadataProvider;
	}

	public void setFilesystemMetadataProvider(
			FilesystemMetadataProvider filesystemMetadataProvider)
	{
		this.filesystemMetadataProvider = filesystemMetadataProvider;
	}
}
