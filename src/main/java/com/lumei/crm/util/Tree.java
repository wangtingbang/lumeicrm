package com.lumei.crm.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author dave
 *
 * @param <T>
 */
public class Tree<T> implements Serializable {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  public static String FOLDER = "folder";
  public static String ITEM = "item";
  private String name;
  private String type;
  private T param;

  private AdditionalParameters<T> additionalParameters;

  public AdditionalParameters<T> newAdditionalParameters() {
    if (null == additionalParameters) {
      additionalParameters = new AdditionalParameters<T>();
    }
    return additionalParameters;
  }

  public class AdditionalParameters<T> implements Serializable {
    /**
		 * 
		 */
    private static final long serialVersionUID = 1L;
    private List<Tree<T>> children = new ArrayList<Tree<T>>();

    public List<Tree<T>> getChildren() {
      return children;
    }

    public void setChildren(List<Tree<T>> children) {
      this.children = children;
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public AdditionalParameters<T> getAdditionalParameters() {
    return additionalParameters;
  }

  public void setAdditionalParameters(AdditionalParameters<T> additionalParameters) {
    this.additionalParameters = additionalParameters;
  }

  public T getParam() {
    return param;
  }

  public void setParam(T param) {
    this.param = param;
  }

}
