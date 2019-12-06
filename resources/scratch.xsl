<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:saxon="http://saxon.sf.net/"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                exclude-result-prefixes="saxon xsd"
>
    <xsl:output indent="yes" method="xml" omit-xml-declaration="yes"/>

    <xsl:template match="*">
        <xsl:copy>
            <xsl:call-template name="id-maker"/>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>

    <xsl:template name="id-maker">
        <xsl:attribute name="id">
            <xsl:choose>
                <xsl:when test="@id">
                    <xsl:value-of select="@id"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="generate-id()"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:attribute>
    </xsl:template>
</xsl:stylesheet>