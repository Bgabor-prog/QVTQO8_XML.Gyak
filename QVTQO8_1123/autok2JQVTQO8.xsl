<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <body>
        <h1>A 30000Ft-nál drágább autók száma:</h1>
        <p>
          <xsl:value-of select="count(/autok/auto[ar > 30000])"></xsl:value-of>
        </p>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>