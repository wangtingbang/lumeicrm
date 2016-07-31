package com.lumei.crm.support.mvc;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.lumei.crm.commons.util.DateTimeUtil;
/**
 * 
 * @author dave
 *
 */
public class DateFormatter implements Formatter<Date>{

  @Override
  public String print(Date arg0, Locale arg1) {
    return DateTimeUtil.fromDateTime(arg0);
  }

  @Override
  public Date parse(String arg0, Locale arg1) throws ParseException {
   Date date = null;
    try {
      date = DateTimeUtil.toDate(arg0);
    } catch (Exception e) {
        date = DateTimeUtil.toDateTime(arg0);
    }
    return date;
  }

}
