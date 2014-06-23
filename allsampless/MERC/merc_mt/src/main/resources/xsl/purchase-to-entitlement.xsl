<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:md="http://www.movielabs.com/schema/md/v1.07/md" xmlns:merc-basic="http://www.neustar.biz/schema/2012/01/merc-basic">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<merc-basic:Entitlement ALID="{merc-basic:Purchase/merc-basic:ALID/text()}" ContentID="{merc-basic:Purchase/merc-basic:ALID/text()}" ThirdPartyID="{merc-basic:Purchase/merc-basic:ALID/text()}">
			<merc-basic:EntitlementBasic>
				<merc-basic:EntitlementProfiles/>
			</merc-basic:EntitlementBasic>
		</merc-basic:Entitlement>
	</xsl:template>
</xsl:stylesheet>
