<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <h1>Átlag</h1>
      <body>
        <table border="1">
          <tr>
            <th>Kurzus</th>
            <th>Jegy</th>
          </tr>
          <xsl:for-each select="root/vizsgak/vizsga">
            <tr>
              <td>
                <xsl:value-of select="kurzus" />
              </td>
              <td>
                <xsl:value-of select="jegy" />
              </td>
            </tr>
          </xsl:for-each>
          <tr>
            <td>Átlag:</td>
            <td>
              <xsl:value-of select="sum(root/vizsgak/vizsga/jegy) div count(root/vizsgak/vizsga/jegy)"></xsl:value-of>
            </td>
          </tr>
        </table>


      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>