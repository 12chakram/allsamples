package biz.neustar.dece.portal.saml.metadata;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.provider.FilesystemMetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.parse.BasicParserPool;
import org.opensaml.xml.parse.ParserPool;

public class DeceMetdataProviderFile
{
	ByteArrayInputStream metadata = null;
	private ParserPool parser;
	static Logger log = Logger.getLogger(DeceMetdataProviderFile.class);
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

	public DeceMetdataProviderFile(String metaDataLoc) throws IOException,
			MetadataProviderException
	{
		// A Db call to get meta data. Currently trying to read using FIle

		this.setMetaDataLocation(metaDataLoc);
		filesystemMetadataProvider = new FilesystemMetadataProvider(new File(
				this.getMetaDataLocation()));
		this.filesystemMetadataProvider.setParserPool(new BasicParserPool());
	}

	public static void main(String[] args)
	{
		try
		{
			org.opensaml.DefaultBootstrap.bootstrap();
			DeceMetdataProviderFile deceMetaDataProvider = new DeceMetdataProviderFile(
					"C:\\dev\\Metadata2.xml");
			EntityDescriptor entityDescriptor = deceMetaDataProvider
					.getFilesystemMetadataProvider().getEntityDescriptor(
							"urn:mace:incommon:internet2.edu");
			if (entityDescriptor != null)
			{
				System.out.println("Entity Descriptor was not null");
			}
			// XMLObject xmlObject=deceMetaDataProvider.getMetadata();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (MetadataProviderException e)
		{
			e.printStackTrace();
		}
		catch (ConfigurationException e)
		{
			e.printStackTrace();
		}
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
