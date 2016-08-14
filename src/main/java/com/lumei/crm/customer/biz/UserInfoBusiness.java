package com.lumei.crm.customer.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lumei.crm.auth.biz.OpAuthUserBusiness;
import com.lumei.crm.auth.dto.OpAuthUser;
import com.lumei.crm.auth.entity.TOpAuthUser;
import com.lumei.crm.commons.mybatis.support.Example;

@Service
public class UserInfoBusiness {

	@Autowired
	OpAuthUserBusiness opAuthUserBusiness;

	public Map<String, OpAuthUser> getUserInfoById(List<String> userIds) {
		Example<TOpAuthUser> example1 = Example.newExample(TOpAuthUser.class);
		example1.paramIn("id", userIds);
		List<OpAuthUser> users = opAuthUserBusiness.list(example1);
		Map<String, OpAuthUser> userMap = new HashMap();
		for (OpAuthUser user : users) {
			userMap.put(user.getId(), user);
		}
		return userMap;
	}
	
}
