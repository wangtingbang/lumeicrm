package com.lumei.crm.commons.mybatis.support;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author dave
 *
 * @param <T>
 */
public class Example<T> implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private Class<T> clazz;
  private Set<String> fieldSet;
  private boolean distinct;
  private Criteria criteria;
  private List<Criteria> params;
  private List<Order> order;

  public Class<T> getClazz() {
    return clazz;
  }

  public void setClazz(Class<T> clazz) {
    this.clazz = clazz;
  }

  public Example(Class<T> clz) {
    this.clazz = clz;
    Field[] fields = clz.getDeclaredFields();
    this.fieldSet = new HashSet<String>();
    for (int i = 0; i < fields.length; i++) {
      this.fieldSet.add(propertyToColumn(fields[i].getName()));
    }
    this.criteria = new Criteria();
    this.params = new LinkedList<Criteria>();
    this.params.add(criteria);
    this.order = new LinkedList<Example.Order>();
  }

  public Example<T> or() {
    criteria = new Criteria();
    params.add(criteria);
    return this;
  }

  private void param(String key, Object value, Object value2, Condition condition) {
    if (Condition.BETWEEN.equals(condition) || Condition.NOT_BETWEEN.equals(condition)) {
      if (value == null || value2 == null) {
        throw new RuntimeException("Between values for " + key + " cannot be null");
      }
    }
    if (!Condition.NULL.equals(condition) && !Condition.NOT_NULL.equals(condition)) {
      if (value == null) {
        return;
      }
    }
    Criterion criterion = new Criterion(key, value, value2, condition);
    if (fieldSet.contains(criterion.getColunm())) {
      this.criteria.getCriteria().put(criterion.getColunm() + "$" + condition.getKey(), criterion);
    }
  }

  public Example<T> paramEqualToDeclaredFields(T obj) {
    if (null == obj)
      return this;
    try {
      Class<?> clz = obj.getClass();
      Field[] fields = clz.getDeclaredFields();
      for (int i = 0; i < fields.length; i++) {
        Field field = fields[i];
        if (Modifier.isStatic(field.getModifiers())) {
          continue;
        }
        String prefix = "get";
        if ("class java.lang.Boolean".equals(field.getGenericType().toString())
            || "boolean".equals(field.getGenericType().toString())) {
          prefix = "is";
        }
        String key = field.getName();
        Object value = null;
        Method method = null;
        try {
          method = clz.getMethod(prefix + getMethodName(key));
        } catch (NoSuchMethodException e) {
          continue;
        }
        if (null != method) {
          value = method.invoke(obj);
        }
        if (null != value) {
          this.paramEqualTo(key, value);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this;
  }

  private static String getMethodName(String fildeName) throws Exception {
    byte[] items = fildeName.getBytes();
    items[0] = (byte) ((char) items[0] - 'a' + 'A');
    return new String(items);
  }

  public Example<T> paramEqualTo(String key, Object value) {
    param(key, value, null, Condition.EQ);
    return this;
  }

  public Example<T> paramNotEqualTo(String key, Object value) {
    param(key, value, null, Condition.NOT_EQ);
    return this;
  }

  public Example<T> paramLikeTo(String key, Object value) {
    param(key, value, null, Condition.LIKE);
    return this;
  }

  public Example<T> paramNotLikeTo(String key, Object value) {
    param(key, value, null, Condition.NOT_LIKE);
    return this;
  }

  public Example<T> paramLessThan(String key, Object value) {
    param(key, value, null, Condition.LT);
    return this;
  }

  public Example<T> paramGreaterThan(String key, Object value) {
    param(key, value, null, Condition.GT);
    return this;
  }

  public Example<T> paramLessThanOrEqualTo(String key, Object value) {
    param(key, value, null, Condition.LE);
    return this;
  }

  public Example<T> paramGreaterThanOrEqualTo(String key, Object value) {
    param(key, value, null, Condition.GE);
    return this;
  }

  public Example<T> paramBetween(String key, Object value, Object value2) {
    param(key, value, value2, Condition.BETWEEN);
    return this;
  }

  public Example<T> paramNotBetween(String key, Object value, Object value2) {
    param(key, value, value2, Condition.NOT_BETWEEN);
    return this;
  }

  public Example<T> paramIn(String key, List<?> value) {
    param(key, value, null, Condition.IN);
    return this;
  }

  public Example<T> paramIn(String key, Object... values) {
    return paramIn(key, Arrays.asList(values));
  }

  public Example<T> paramNotIn(String key, List<?> value) {
    param(key, value, null, Condition.NOT_IN);
    return this;
  }

  public Example<T> paramNotIn(String key, Object... values) {
    return paramNotIn(key, Arrays.asList(values));
  }

  public Example<T> paramIsNull(String key) {
    param(key, null, null, Condition.NULL);
    return this;
  }

  public Example<T> paramNotNull(String key) {
    param(key, null, null, Condition.NOT_NULL);
    return this;
  }

  public static <T> Example<T> newExample(Class<T> clz) {
    return new Example<T>(clz);
  }

  public Example<T> distinct() {
    this.distinct = true;
    return this;
  }

  private static String propertyToColumn(String property) {
    if (property == null || property.isEmpty()) {
      return "";
    }
    if (property.contains("_"))
      return property;
    StringBuilder column = new StringBuilder();
    column.append(property.substring(0, 1).toLowerCase());
    for (int i = 1; i < property.length(); i++) {
      String s = property.substring(i, i + 1);
      if (!Character.isDigit(s.charAt(0)) && s.equals(s.toUpperCase())) {
        column.append("_");
      }
      column.append(s.toLowerCase());
    }
    return column.toString();
  }

  private Example<T> orderBy(String column, Seq seq) {
    if (column == null || "".equals(column))
      return this;
    String columnStr = propertyToColumn(column.toLowerCase());
    if (!fieldSet.contains(columnStr)) {
      throw new RuntimeException("no column [" + columnStr + "] in table");
    }
    this.order.add(new Order(columnStr, seq));
    return this;
  }

  public Example<T> orderBy(String column) {
    return this.orderBy(column, Seq.ASC);
  }

  public Example<T> orderByDesc(String column) {
    return this.orderBy(column, Seq.DESC);
  }

  /**
   * 
   * @author dave
   *
   */
  public static class Criteria implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Map<String, Criterion> criteria;

    public Criteria() {
      this.criteria = new HashMap<String, Criterion>();
    }

    public Map<String, Criterion> getCriteria() {
      return criteria;
    }

    public void setCriteria(Map<String, Criterion> criteria) {
      this.criteria = criteria;
    }

  }

  /**
   * 
   * @author dave
   *
   */
  public static class Criterion implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String colunm;
    private Object value;
    private Object secondValue;
    private Condition condition;
    private boolean iseq;
    private boolean noteq;
    private boolean like;
    private boolean notlike;
    private boolean islt;
    private boolean isgt;
    private boolean isle;
    private boolean isge;
    private boolean between;
    private boolean notbetween;
    private boolean isin;
    private boolean notin;
    private boolean isnull;
    private boolean notnull;

    public Criterion() {}

    public Criterion(String colunm, Object value, Object secondValue, Condition condition) {
      this.colunm = propertyToColumn(colunm);
      this.value = value;
      this.secondValue = secondValue;
      this.condition = condition;
      switch (condition) {
        case EQ:
          this.iseq = true;
          break;
        case NOT_EQ:
          this.noteq = true;
          break;
        case LIKE:
          this.like = true;
          break;
        case NOT_LIKE:
          this.notlike = true;
          break;
        case LT:
          this.islt = true;
          break;
        case GT:
          this.isgt = true;
          break;
        case LE:
          this.isle = true;
          break;
        case GE:
          this.isge = true;
        case BETWEEN:
          this.between = true;
        case NOT_BETWEEN:
          this.notbetween = true;
        case IN:
          this.isin = true;
        case NOT_IN:
          this.notin = true;
          break;
        case NULL:
          this.isnull = true;
          break;
        case NOT_NULL:
          this.notnull = true;
          break;
        default:
          break;
      }
    }

    public String getColunm() {
      return colunm;
    }

    public void setColunm(String colunm) {
      this.colunm = colunm;
    }

    public Object getValue() {
      return value;
    }

    public void setValue(Object value) {
      this.value = value;
    }

    public Object getSecondValue() {
      return secondValue;
    }

    public void setSecondValue(Object secondValue) {
      this.secondValue = secondValue;
    }

    public Condition getCondition() {
      return condition;
    }

    public void setCondition(Condition condition) {
      this.condition = condition;
    }

    public boolean isIseq() {
      return iseq;
    }

    public void setIseq(boolean iseq) {
      this.iseq = iseq;
    }

    public boolean isNoteq() {
      return noteq;
    }

    public void setNoteq(boolean noteq) {
      this.noteq = noteq;
    }

    public boolean isLike() {
      return like;
    }

    public void setLike(boolean like) {
      this.like = like;
    }

    public boolean isNotlike() {
      return notlike;
    }

    public void setNotlike(boolean notlike) {
      this.notlike = notlike;
    }

    public boolean isIslt() {
      return islt;
    }

    public void setIslt(boolean islt) {
      this.islt = islt;
    }

    public boolean isIsgt() {
      return isgt;
    }

    public void setIsgt(boolean isgt) {
      this.isgt = isgt;
    }

    public boolean isIsle() {
      return isle;
    }

    public void setIsle(boolean isle) {
      this.isle = isle;
    }

    public boolean isIsge() {
      return isge;
    }

    public void setIsge(boolean isge) {
      this.isge = isge;
    }

    public boolean isBetween() {
      return between;
    }

    public void setBetween(boolean between) {
      this.between = between;
    }

    public boolean isNotbetween() {
      return notbetween;
    }

    public void setNotbetween(boolean notbetween) {
      this.notbetween = notbetween;
    }

    public boolean isIsin() {
      return isin;
    }

    public void setIsin(boolean isin) {
      this.isin = isin;
    }

    public boolean isNotin() {
      return notin;
    }

    public void setNotin(boolean notin) {
      this.notin = notin;
    }

    public boolean isIsnull() {
      return isnull;
    }

    public void setIsnull(boolean isnull) {
      this.isnull = isnull;
    }

    public boolean isNotnull() {
      return notnull;
    }

    public void setNotnull(boolean notnull) {
      this.notnull = notnull;
    }

  }

  /**
   * 
   * @author dave
   *
   */
  public static enum Condition {
    EQ("iseq"), //
    NOT_EQ("noteq"), //
    LIKE("like"), //
    NOT_LIKE("notlike"), //
    LT("islt"), //
    LE("isle"), //
    GT("isgt"), //
    GE("isge"), //
    BETWEEN("between"), //
    NOT_BETWEEN("notbetween"), //
    IN("isin"), //
    NOT_IN("notin"), //
    NULL("isnull"), //
    NOT_NULL("notnull");//

    private String key;

    Condition(String key) {
      this.key = key;
    }

    public String getKey() {
      return key;
    }

  }

  /**
   * 
   * @author dave
   *
   */
  public static enum Seq {
    ASC, //
    DESC;//
    Seq() {}
  }

  /**
   * 
   * @author dave
   *
   */
  public static class Order implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String column;
    private boolean desc;

    public Order() {}

    public Order(String column, Seq seq) {
      this.column = column;
      switch (seq) {
        case DESC:
          this.desc = true;
          break;
        default:
          break;
      }
    }

    public String getColumn() {
      return column;
    }

    public void setColumn(String column) {
      this.column = column;
    }

    public boolean isDesc() {
      return desc;
    }

    public void setDesc(boolean desc) {
      this.desc = desc;
    }

  }

  public boolean isDistinct() {
    return distinct;
  }

  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  public List<Order> getOrder() {
    return order;
  }

  public void setOrder(List<Order> order) {
    this.order = order;
  }

  public List<Criteria> getParams() {
    return params;
  }

  public void setParams(List<Criteria> params) {
    this.params = params;
  }

  @Override
  public String toString() {
    return "";
  }

}

