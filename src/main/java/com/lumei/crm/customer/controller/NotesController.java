package com.lumei.crm.customer.controller;

import com.lumei.crm.auth.bean.SysRole;
import com.lumei.crm.auth.biz.OpAuthUserBusiness;
import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.auth.entity.TOpAuthUser;
import com.lumei.crm.commons.mybatis.support.Example;
import com.lumei.crm.commons.mybatis.support.Pagination;
import com.lumei.crm.commons.util.DateTimeUtil;
import com.lumei.crm.customer.biz.NotesBusiness;
import com.lumei.crm.customer.dto.Notes;
import com.lumei.crm.customer.entity.TNotes;
import com.lumei.crm.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
  OpAuthUserBusiness opAuthUserBusiness;

  private Map<String, OpAuthUser> getUserInfoById(List<String> userIds) {
    Example<TOpAuthUser> example1 = Example.newExample(TOpAuthUser.class);
    example1.paramIn("id", userIds);
    List<OpAuthUser> users = opAuthUserBusiness.list(example1);
    Map<String, OpAuthUser> userMap = new HashMap();
    for (OpAuthUser user : users) {
      userMap.put(user.getId(), user);
    }
    return userMap;
  }

  @RequestMapping(value = "list", method = RequestMethod.POST)
  @ResponseBody
  public Pagination<Notes> listNotesByPage(
    String serviceId,//
    int page,//
    int limit) {

    log.debug("param:{}, page:{}, limit:{}", serviceId);
    Example<TNotes> example = Example.newExample(TNotes.class);

    if (StringUtils.isBlank(serviceId)|| "null".equals(serviceId) ||"undefined".equals(serviceId)) {
      Pagination<Notes> pg = Pagination.newInstance(page, limit);
      pg.setResult(new ArrayList<Notes>());
      pg.setTotal(0);
      pg.setTotalPage(0);
      return pg;
    }
    boolean readonly = false;

    example.param("serviceId", serviceId);
    example.orderBy("createTime").desc();
    Pagination<Notes> pg = notesBusiness.listByPage(example, page, limit);

    List<Notes> notes = pg.getResult();
    if (notes != null || notes.size() > 0) {
      List<String> uids = new ArrayList<String>();
      for (Notes tmp : notes) {
        String uid0 = tmp.getUpdateUserId();
        String uid1 = tmp.getCreateUserId();
        uids.add(uid0);
        uids.add(uid1);
      }
      List<Notes> notesNew = new ArrayList();
      Map<String,OpAuthUser> userMap = getUserInfoById(uids);
      for (Notes tmp : notes) {
        String crtUId = tmp.getCreateUserId();
        String updUId = tmp.getUpdateUserId();
        OpAuthUser crtUser = userMap.get(crtUId);
        OpAuthUser updUser = userMap.get(updUId);

        if(SessionUtil.getCurrentUser().getRoles().contains(SysRole.SALES.getKey())){
          if(!SessionUtil.getCurrentUserId().equals((tmp.getCreateUserId()))){
            tmp.setReadonly(true);
          }
        }
        String crtUName = crtUser == null ? "" : crtUser.getNickName();
        String updUName = updUser == null ? "" : updUser.getNickName();
        tmp.setCreateUserName(crtUName);
        tmp.setUpdateUserName(updUName);
        notesNew.add(tmp);
      }
      pg.setResult(notesNew);
    }
    return pg;
  }

  @RequestMapping(value = "save", method = RequestMethod.POST)
  @ResponseBody
  public String saveNotes(Notes notes) {
    int result = 0;
    Date now = DateTimeUtil.now();
    notes.setCreateTime(now);
    notes.setUpdateTime(now);
    if (StringUtils.isBlank(notes.getCreateUserId())) {
      notes.setCreateUserId(SessionUtil.getCurrentUserId());
    }
    if (StringUtils.isBlank(notes.getId())) {
      result = notesBusiness.create(notes);
    } else {
      notes.setUpdateUserId(SessionUtil.getCurrentUserId());
      result = notesBusiness.update(notes);
    }
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
