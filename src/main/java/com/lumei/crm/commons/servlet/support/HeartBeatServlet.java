package com.lumei.crm.commons.servlet.support;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumei.crm.commons.util.DateTimeUtil;

/**
 * 
 * @author dave
 *
 */
@SuppressWarnings("serial")
@WebServlet(name = "heartbeat", urlPatterns = {"/heartbeat"}, loadOnStartup = 1,
    displayName = "heartbeat")
public class HeartBeatServlet extends HttpServlet {
  private static final Logger logger = LoggerFactory.getLogger(HeartBeatServlet.class);
  
  @Override
  public void init() throws ServletException {
    logger.info("HeartBeatServlet start");
    super.init();
  }
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    resp.setHeader("Content-type","text/html;charset=UTF-8");
    resp.getWriter().write(DateTimeUtil.fromDate(DateTimeUtil.now(), "yyyy-MM-dd HH:mm:ss SSS"));
  }
  
}
