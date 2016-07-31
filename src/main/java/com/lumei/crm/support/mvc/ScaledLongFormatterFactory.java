package com.lumei.crm.support.mvc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import com.lumei.crm.commons.bean.support.ScaledLongFormat;
import com.lumei.crm.commons.bean.support.ScaledLongFormatter;

/**
 * 
 * @author dave
 *
 */
public class ScaledLongFormatterFactory implements AnnotationFormatterFactory<ScaledLongFormat> {

  private static final Set<Class<?>> FIELD_TYPES;
  static {
    Set<Class<?>> fieldTypes = new HashSet<Class<?>>(1);
    fieldTypes.add(Long.class);
    FIELD_TYPES = Collections.unmodifiableSet(fieldTypes);
  }

  @Override
  public Set<Class<?>> getFieldTypes() {
    return FIELD_TYPES;
  }

  @Override
  public Parser<?> getParser(ScaledLongFormat arg0, Class<?> arg1) {
    return new ScaledLongFormatter(arg0.scale());
  }

  @Override
  public Printer<?> getPrinter(ScaledLongFormat arg0, Class<?> arg1) {
    return new ScaledLongFormatter(arg0.scale());
  }

}
