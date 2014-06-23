<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:md="http://www.movielabs.com/schema/md/v1.07/md" xmlns:merc-basic="http://www.neustar.biz/schema/2012/01/merc-basic" xmlns:merc-common="http://www.neustar.biz/schema/2012/01/merc-common">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<merc-basic:Account ThirdPartyID="{merc-basic:User/@ThirdPartyID}" >
			<merc-basic:DisplayName>
				<xsl:value-of select="merc-basic:User/merc-basic:Name/merc-basic:GivenName/text()"/>
			</merc-basic:DisplayName>
			<merc-basic:Country>
				<xsl:value-of select="merc-basic:User/merc-basic:Country/text()"/>
			</merc-basic:Country>
		</merc-basic:Account>
	</xsl:template>
</xsl:stylesheet>