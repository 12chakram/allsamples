<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:mt1="http://merc-mt-basic"
	xmlns:mb="http://merc-basic" xmlns:exception="biz.neustar.merc.mt.service.exceptions.ValidationExceptionThrower">
	<xsl:output omit-xml-declaration="no" method="xml" version="1.0" />
	<xsl:template match="/">
		<xsl:choose>
			<xsl:when
				test="string-length(mt1:RegisterAccount/mt1:User/mt1:DisplayName/text()) &lt; 8">
				<xsl:value-of
					select="exception:throwException('User.DisplayName:Length is less than eight characters.')" />
			</xsl:when>
			<xsl:otherwise>
				<mb:Merc-Coordinator>
					<mb:User>
						<mb:DisplayName>
							<xsl:value-of
								select="mt1:RegisterAccount/mt1:User/mt1:DisplayName/text()" />
						</mb:DisplayName>
						<mb:Credentials>
							<mb:SigninName>
								<xsl:value-of
									select="mt1:RegisterAccount/mt1:User/mt1:Credentials/mt1:SigninName/text()" />
							</mb:SigninName>
							<mb:Password>
								<xsl:value-of
									select="mt1:RegisterAccount/mt1:User/mt1:Credentials/mt1:Password/text()" />
							</mb:Password>
						</mb:Credentials>
						<mb:DateOfBirth>
							<xsl:value-of
								select="mt1:RegisterAccount/mt1:User/mt1:DateOfBirth/text()" />
						</mb:DateOfBirth>
						<mb:RecoveryHints>
							<xsl:for-each
								select="mt1:RegisterAccount/mt1:User/mt1:RecoveryHints/mt1:Hint">
								<mb:Hint>
									<mb:QuestionID>
										<xsl:value-of select="mt1:QuestionID/text()" />
									</mb:QuestionID>
									<mb:Question>
										<xsl:value-of select="mt1:Question/text()" />
									</mb:Question>
									<mb:Answer>
										<xsl:value-of select="mt1:Answer/text()" />
									</mb:Answer>
								</mb:Hint>
							</xsl:for-each>
						</mb:RecoveryHints>
					</mb:User>
				</mb:Merc-Coordinator>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
