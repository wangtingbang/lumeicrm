package com.lumei.crm.commons.mybatis.support.dialect;

public class PostgreSQLDialect extends Dialect {

  public PostgreSQLDialect() {}

  public boolean supportsLimit() {
    return true;
  }

  public boolean supportsLimitOffset() {
    return true;
  }

  public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit,
      String limitPlaceholder) {
    return (new StringBuffer(sql.length() + 20))
        .append(sql)
        .append(
            offset <= 0 ? (new StringBuilder(" limit ")).append(limitPlaceholder).toString()
                : (new StringBuilder(" limit ")).append(limitPlaceholder).append(" offset ")
                    .append(offsetPlaceholder).toString()).toString();
  }
}
