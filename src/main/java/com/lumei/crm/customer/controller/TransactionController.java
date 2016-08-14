package com.lumei.crm.customer.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lumei.crm.auth.biz.OpAuthUserBusiness;
import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.auth.entity.TOpAuthUser;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.mybatis.support.Pagination;
import com.lumei.crm.commons.util.DateTimeUtil;
import com.lumei.crm.customer.biz.TransactionBusiness;
import com.lumei.crm.customer.dto.Transaction;
import com.lumei.crm.customer.entity.TTransaction;
import com.lumei.crm.util.SessionUtil;

@Controller
@RequestMapping(value = "transaction")
public class TransactionController {

  private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

  @Autowired
  TransactionBusiness transactionBusiness;

  @Autowired
  OpAuthUserBusiness opAuthUserBusiness;

  /**
  *
  */
  @RequestMapping(value = "list", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<Transaction> listTransactionByPage(String customerId, int page, int limit) {

    log.debug("param, customerId:{}, page:{}, limit:{}", customerId);
    Example<TTransaction> example = Example.newExample(TTransaction.class);

    if (StringUtils.isBlank(customerId) || "null".equals(customerId)
        || "undefined".equals(customerId)) {
      Pagination<Transaction> pg = Pagination.newInstance(page, limit);
      pg.setResult(new ArrayList<Transaction>());
      pg.setTotal(0);
      pg.setTotalPage(0);
      return pg;
    }

    example.param("userId", customerId);
    example.orderBy("createTime").desc();
    Pagination<Transaction> pg = transactionBusiness.listByPage(example, page, limit);
    return pg;
  }

  @RequestMapping(value = "save", method = RequestMethod.POST)
  @ResponseBody
  public String saveTransaction(Transaction notes) {
    int result = 0;
    if (notes.getCreateTime() == null) {
      Date now = DateTimeUtil.now();
      notes.setCreateTime(now);
      notes.setUpdateTime(now);
    }
    if (StringUtils.isBlank(notes.getCreateUserId())) {
      notes.setCreateUserId(SessionUtil.getCurrentUserId());
    }
    if (StringUtils.isBlank(notes.getId())) {
      result = transactionBusiness.create(notes);
    } else {
      notes.setUpdateUserId(SessionUtil.getCurrentUserId());
      result = transactionBusiness.update(notes);
    }
    return 1 == result ? "success" : "fail";
  }

  @RequestMapping(value = "save", method = RequestMethod.GET)
  @ResponseBody
  private String deleteTransaction(String id) {
    int result = 0;
    log.info("delete note:{}, opId:{},opName:{}", id, SessionUtil.getCurrentUserId(),
        SessionUtil.getCurrentUserName());

    result = transactionBusiness.delete(id, Transaction.class);
    return 1 == result ? "success" : "fail";
  }
}
