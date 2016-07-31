package com.lumei.crm.support.mvc;

import java.lang.annotation.Annotation;

import org.joda.time.format.DateTimeFormatter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.joda.JodaDateTimeFormatAnnotationFormatterFactory;

import com.lumei.crm.commons.util.DateTimeUtil;

public class DateTimeFormatAnnotationFormatterFactory extends JodaDateTimeFormatAnnotationFormatterFactory{
  @Override
  protected DateTimeFormatter getFormatter(final DateTimeFormat annotation, Class<?> fieldType) {
    if("".equals(annotation.pattern()) || null == annotation.pattern()){
      DateTimeFormat a = new DateTimeFormat() {
        
        @Override
        public Class<? extends Annotation> annotationType() {
          return annotation.annotationType();
        }
        
        @Override
        public String style() {
          // TODO Auto-generated method stub
          return annotation.style();
        }
        
        @Override
        public String pattern() {
          return DateTimeUtil.Pattern.DEFAULT_FORMATE_TIME;
        }
        
        @Override
        public ISO iso() {
          // TODO Auto-generated method stub
          return annotation.iso();
        }
      };
      return super.getFormatter(a, fieldType);
    }
    return super.getFormatter(annotation, fieldType);
  }
}
