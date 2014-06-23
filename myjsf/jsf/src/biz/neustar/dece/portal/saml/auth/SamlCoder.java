package biz.neustar.dece.portal.saml.auth;

import org.opensaml.DefaultBootstrap;
import org.opensaml.xml.ConfigurationException;

public class SamlCoder {
  static {
	  try {
		DefaultBootstrap.bootstrap();
	} catch (ConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
