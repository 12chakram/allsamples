<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:mb="http://merc-basic"
	xmlns:mt1="http://merc-mt-basic" xmlns:exception="biz.neustar.merc.mt.service.exceptions.ValidationExceptionThrower">
	<xsl:output omit-xml-declaration="no" method="xml" version="1.0" />
	<xsl:template match="/">
		<xsl:choose>
			<xsl:when
				test="mt1:RegisterAccount/mt1:Account/mt1:Country/text() != 'US'">
				<xsl:value-of
					select="exception:throwException('Account.Country:MERC not available in your Country.')" />
			</xsl:when>
			<xsl:otherwise>
				<mb:Merc-Coordinator>
					<mb:Account>
						<mb:DisplayName>
							<xsl:value-of
								select="mt1:RegisterAccount/mt1:Account/mt1:DisplayName/text()" />
						</mb:DisplayName>
						<mb:Country>
							<xsl:value-of
								select="mt1:RegisterAccount/mt1:Account/mt1:Country/text()" />
						</mb:Country>
						<mb:ClientAppID>
							<xsl:value-of
								select="mt1:RegisterAccount/mt1:Account/mt1:ClientAppID/text()" />
						</mb:ClientAppID>
					</mb:Account>
				</mb:Merc-Coordinator>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
