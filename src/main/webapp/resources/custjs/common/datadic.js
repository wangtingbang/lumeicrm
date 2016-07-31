var datadic = {
		productType:{
			"1":"速",
			"2":"享",
			"3":"安",
			"4":"盈",
			"5":"专"
		},
		productStatus:{
			"1":"未提交",
			"2":"退回",
			"3":"未审批",
			"4":"审批通过",
			"5": "即将开始",
			"6": "立即抢购",
		    "7": "抢购结束",
		    "8": "收益中",
		    "9": "还款中",
		    "10": "还款结束"
		},
		incomeDistributionMode:{
			"1":"一次性还本付息"
		},
		postsalepayIncomeDistributionMode:{
			"1":"余额计息"
		},
		orderStatus:{
			"1":"未支付",
			"2":"支付中",
			"3":"支付失败",
			"4":"支付超时",
			"5":"购买成功",
			"11":"已提交",
			"12":"退回",
			"13":"审核中",
			"14":"审核失败",
			"15":"还款中",
			"16":"还款成功",
			"17":"还款失败"
		},
		institution:{
		},
		status : {
			"1":"未确认",
			"3":"失败",
			"4":"已确认"
		}
}

function datadicArray(obj){
	var array = new Array();
	for(p in obj){
		if(typeof obj[p] == "function"){
			array.push({key:p, value:obj[p]()});
		}
		array.push({key:p, value:obj[p]});
	}
	return array;
}
