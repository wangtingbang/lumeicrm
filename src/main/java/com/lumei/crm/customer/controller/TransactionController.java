package com.lumei.crm.customer.controller;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.mybatis.support.Pagination;
import com.lumei.crm.customer.biz.TransactionBusiness;
import com.lumei.crm.customer.dto.Transaction;
import com.lumei.crm.customer.entity.TTransaction;

@Controller
@RequestMapping(value = "transaction")
public class TransactionController {

  private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

  @Autowired
  TransactionBusiness transactionBusiness;

  @RequestMapping(value = "list", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<Transaction> listTransactionByPage(String customerId, int page, int limit) {
    log.debug("param, customerId:{}, page:{}, limit:{}", customerId, page, limit);
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

}
