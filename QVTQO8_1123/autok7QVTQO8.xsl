<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <html>
      <body>
        <h1>autók rendszámát és árát az ár szerinti sorrendben</h1>
        <ul>
          <xsl:for-each select="autok/auto">
            <xsl:sort select="ar" />
            <li>
              <xsl:value-of select="@rsz" />
              " ár: "
              <xsl:value-of select="ar" />
              " Ft"
            </li>
          </xsl:for-each>
        </ul>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>