package com.lumei.crm.commons.mybatis.support.plugin;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

public class OffsetLimitPreparedStatementCallback implements PreparedStatementCallback {

  public OffsetLimitPreparedStatementCallback(int offset, int limit, RowMapper rowMapper) {
    Assert.notNull(rowMapper, "'rowMapper' must be not null");
    this.offset = offset;
    this.limit = limit;
    this.rowMapper = rowMapper;
  }

  public Object doInPreparedStatement(PreparedStatement ps) throws SQLException,
      DataAccessException {
    java.sql.ResultSet rs;
    ps.setMaxRows(limit);
    rs = null;
    try {
      List list;
      rs = ps.executeQuery();
      OffsetLimitResultSetExtractor rse =
          new OffsetLimitResultSetExtractor(offset, limit, rowMapper);
      list = (List) rse.extractData(rs);
      JdbcUtils.closeResultSet(rs);
      return list;
    } catch (SQLException e) {
      JdbcUtils.closeResultSet(rs);
      throw e;
    } catch (DataAccessException e) {
      JdbcUtils.closeResultSet(rs);
      throw e;
    }

  }

  private int offset;
  private int limit;
  private RowMapper rowMapper;
}
