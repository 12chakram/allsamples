<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:mt="http://merc-mt-basic/1/0"
	xmlns:mt1="http://merc-mt-basic">
	<xsl:output omit-xml-declaration="no" method="xml" version="1.0" />
	<xsl:template match="/">
		<mt1:RegisterAccount>
			<mt1:Account>
				<mt1:DisplayName>
					<xsl:value-of select="mt:RegisterAccount/mt:Account/mt:DisplayName/text()" />
				</mt1:DisplayName>
				<mt1:Country>
					<xsl:value-of select="mt:RegisterAccount/mt:Account/mt:Country/text()" />
				</mt1:Country>
				<mt1:ClientAppID>CLIENT-APP-111</mt1:ClientAppID>
			</mt1:Account>
			<mt1:User>
				<mt1:DisplayName>
					<xsl:value-of select="mt:RegisterAccount/mt:User/mt:DisplayName/text()" />
				</mt1:DisplayName>
				<mt1:Credentials>
					<mt1:SigninName>
						<xsl:value-of
							select="mt:RegisterAccount/mt:User/mt:Credentials/mt:SigninName/text()" />
					</mt1:SigninName>
					<mt1:Password>
						<xsl:value-of
							select="mt:RegisterAccount/mt:User/mt:Credentials/mt:Password/text()" />
					</mt1:Password>
				</mt1:Credentials>
				<mt1:DateOfBirth>
					<xsl:value-of select="mt:RegisterAccount/mt:User/mt:DateOfBirth/text()" />
				</mt1:DateOfBirth>
				<mt1:RecoveryHints>
					<xsl:for-each select="mt:RegisterAccount/mt:User/mt:RecoveryHints/mt:Hint">
						<mt1:Hint>
							<mt1:QuestionID>
								<xsl:value-of select="mt:QuestionID/text()" />
							</mt1:QuestionID>
							<mt1:Question>
								<xsl:value-of select="mt:Question/text()" />
							</mt1:Question>
							<mt1:Answer>
								<xsl:value-of select="mt:Answer/text()" />
							</mt1:Answer>
						</mt1:Hint>
					</xsl:for-each>
				</mt1:RecoveryHints>
			</mt1:User>
		</mt1:RegisterAccount>
	</xsl:template>
</xsl:stylesheet>
