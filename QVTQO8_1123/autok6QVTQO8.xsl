<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <body>
        <h1>városonként mennyi az ottani autók darabszáma és összára</h1>
        <ul>
          <xsl:for-each-group select="autok/auto" group-by="tulaj/varos">
            <li>
              <xsl:value-of select="current-grouping-key()" />
              " darab: "
              <xsl:value-of select="count(current-group())" />
              " összár: "
              <xsl:value-of select="sum(current-group()/ar)" />
              " Ft"
            </li>
          </xsl:for-each-group>
        </ul>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>