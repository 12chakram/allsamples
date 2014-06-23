<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:md="http://www.movielabs.com/schema/md/v1.07/md" xmlns:merc-basic="http://www.neustar.biz/schema/2012/01/merc-basic">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<merc-basic:Transaction TransactionID="{merc-basic:Purchase/merc-basic:TransactionType/@TransactionID}">
			<merc-basic:AccountID>{AccountId}</merc-basic:AccountID>
			<merc-basic:PaymentTransactionID><xsl:value-of select="merc-basic:Purchase/merc-basic:TransactionType/merc-basic:PaymentTransactionID/text()"/>
			<xsl:value-of select="merc-basic:Purchase/merc-basic:ALID/text()"/>
			</merc-basic:PaymentTransactionID>
			<merc-basic:EntitlementID>{EntitlementId}</merc-basic:EntitlementID>		
			<merc-basic:TransactionInfo>
				<merc-basic:ResourceID><xsl:value-of select="merc-basic:Purchase/merc-basic:TransactionType/merc-basic:TransactionInfo/merc-basic:ResourceID/text()"/>
				<xsl:value-of select="merc-basic:Purchase/merc-basic:ALID/text()"/>
				</merc-basic:ResourceID>
				<merc-basic:PurchaseType><xsl:value-of select="merc-basic:Purchase/merc-basic:TransactionType/text()"/></merc-basic:PurchaseType>
			</merc-basic:TransactionInfo>
		</merc-basic:Transaction>
	</xsl:template>
</xsl:stylesheet>
