package com.lumei.crm.commons.mybatis.support.plugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

public class OffsetLimitResultSetExtractor implements ResultSetExtractor {

  public OffsetLimitResultSetExtractor(int offset, int limit, RowMapper rowMapper) {
    Assert.notNull(rowMapper, "'rowMapper' must be not null");
    this.rowMapper = rowMapper;
    this.offset = offset;
    this.limit = limit;
  }

  public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
    List results = new ArrayList(limit <= 50 ? limit : 50);
    if (offset > 0)
      rs.absolute(offset);
    int rowNum = 0;
    while (rs.next()) {
      Object row = rowMapper.mapRow(rs, rowNum++);
      results.add(row);
      if (rowNum + 1 >= limit)
        break;
    }
    return results;
  }

  private int limit;
  private int offset;
  private RowMapper rowMapper;
}
