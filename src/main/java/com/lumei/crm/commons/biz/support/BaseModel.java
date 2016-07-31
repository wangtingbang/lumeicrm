package com.lumei.crm.commons.biz.support;

public abstract class BaseModel<T> {
  public abstract void setId(T id);

  public abstract T getId();

  public abstract void setDeleteFlag(Byte flag);


}
