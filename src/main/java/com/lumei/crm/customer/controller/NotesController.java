package com.lumei.crm.customer.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.mybatis.support.Pagination;
import com.lumei.crm.commons.util.DateTimeUtil;
import com.lumei.crm.customer.biz.NotesBusiness;
import com.lumei.crm.customer.biz.UserInfoBusiness;
import com.lumei.crm.customer.dto.Notes;
import com.lumei.crm.customer.entity.TNotes;
import com.lumei.crm.util.SessionUtil;

/**
 * Created by wangtingbang on 16/8/13.
 */

@Controller
@RequestMapping(value = "notes")
public class NotesController {

  private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  NotesBusiness notesBusiness;
  @Autowired
  UserInfoBusiness userInfoBusiness;
  
  @RequestMapping(value = "list", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<Notes> listNotesByPage(
	String customerId,//
    String serviceId,//
    int page,//
    int limit) {

    log.debug("customerId:{}, serviceId:{}, page:{}, limit:{}",customerId, serviceId,
    		page, limit);
    Example<TNotes> example = Example.newExample(TNotes.class);

    if (StringUtils.isBlank(serviceId)|| "0".equals(serviceId) || "1".equals(serviceId) 
    		||"null".equals(serviceId) ||"undefined".equals(serviceId)) {
      Pagination<Notes> pg = Pagination.newInstance(page, limit);
      pg.setResult(new ArrayList<Notes>());
      pg.setTotal(0);
      pg.setTotalPage(0);
      return pg;
    }
    
    if("0".equals(customerId)){
    	example.paramEqualTo("serviceId", serviceId);
    }else{
    	example.paramEqualTo("userId", customerId);
    }
    
    example.orderByDesc("createTime");
    Pagination<Notes> pg = notesBusiness.listByPage(example, page, limit);

    List<Notes> notes = pg.getResult();
    if (notes != null && notes.size() > 0) {
      List<String> uids = new ArrayList<String>();
      for (Notes tmp : notes) {
        String uid1 = tmp.getCreateUserId();
        uids.add(uid1);
      }
      List<Notes> notesNew = new ArrayList();
      Map<String,OpAuthUser> userMap = userInfoBusiness.getUserInfoById(uids);
      for (Notes tmp : notes) {
        String crtUId = tmp.getCreateUserId();
        OpAuthUser crtUser = userMap.get(crtUId);
        
        if(SessionUtil.notesReadonly(tmp.getCreateUserId())){
        	tmp.setReadonly(true);
        }
        
        String crtUName = crtUser == null ? "" : crtUser.getNickName();
        tmp.setCreateUserName(crtUName);
        notesNew.add(tmp);
      }
      pg.setResult(notesNew);
    }
    return pg;
  }

  @RequestMapping(value = "save", method = RequestMethod.POST)
  @ResponseBody
  public String saveNotes(Notes notes) {
	log.info("delete user:{}, service:{}, opId:{},opName:{}", notes.getUserId(), notes.getServiceId(), 
			SessionUtil.getCurrentUserId(),
		      SessionUtil.getCurrentUserName());
    int result = 0;
    Date now = DateTimeUtil.now();
    notes.setCreateTime(now);
    notes.setUpdateTime(now);
    notes.setCreateUserId(SessionUtil.getCurrentUserId());
    notes.setUpdateUserId(SessionUtil.getCurrentUserId());
    result = notesBusiness.create(notes);
    return 1 == result ? "success" : "fail";
  }

  @RequestMapping(value = "delete", method = RequestMethod.POST)
  @ResponseBody
  public String deleteNotes(String id) {
    int result = 0;
    log.info("delete note:{}, opId:{},opName:{}", id, SessionUtil.getCurrentUserId(),
      SessionUtil.getCurrentUserName());
    result = notesBusiness.delete(id, Notes.class);
    return 1 == result ? "success" : "fail";
  }

}
