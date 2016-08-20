var datadic = {
	usState:{
		"AL":"AL",
		"AK":"AK",
		"AZ":"AZ",
		"AR":"AR",
		"CA":"CA",
		"CO":"CO",
		"CT":"CT",
		"DE":"DE",
		"FL":"FL",
		"GA":"GA",
		"HI":"HI",
		"ID":"ID",
		"IL":"IL",
		"IN":"IN",
		"IA":"IA",
		"KS":"KS",
		"KY":"KY",
		"LA":"LA",
		"ME":"ME",
		"MD":"MD",
		"MA":"MA",
		"MI":"MI",
		"MN":"MN",
		"MS":"MS",
		"MO":"MO",
		"MT":"MT",
		"NE":"NE",
		"NV":"NV",
		"NH":"NH",
		"NJ":"NJ",
		"NM":"NM",
		"NY":"NY",
		"NC":"NC",
		"ND":"ND",
		"OH":"OH",
		"OK":"OK",
		"OR":"OR",
		"PA":"PA",
		"RI":"RI",
		"SC":"SC",
		"SD":"SD",
		"TN":"TN",
		"TX":"TX",
		"UT":"UT",
		"VT":"VT",
		"VA":"VA",
		"WA":"WA",
		"WV":"WV",
		"WI":"WI",
		"WY":"WY"
	},
	serviceType:{
		"1":"Car Deals",
		"2":"Emergency Contact",
		"3":"Car Buying",
		"4":"Car Rental",
		"5":"Cellphone",
		"6":"Airport Pickup",
		"7":"Temporary House",
		"8":"AIM"
	},
	carSaleStatus:{
		"1":"Potential to Buy",
		"2":"Appointment Made",
		"3":"Sold by Lumei",
		"4":"Bought from Others",
		"5":"No Response"
	},
	customerRating:{
		"1":"A",
		"2":"B",
		"3":"C"
	},
	usedNew:{
		"1":"New",
		"2":"Used"
	},
	method:{
		"1":"Cash Deal",
		"2":"Finance",
		"3":"Lease"
	},
	deposit:{
		"1":"N/A",
		"2":"Received",
		"3":"To Refund",
		"4":"To Refunded",
		"5":"Non-refundable"
	},
	carDealSource:{
		"1":"Friends Refer ",
		"2":"WeChat Group",
		"3":"WeChat Public Account",
		"4":"Website",
		"5":"Citic Bank",
		"6":"Flyer/DM",
		"7":"University Ambassador",
		"8":"University Event",
		"9":"LONE Club",
		"10":"Airport Pickup",
		"11":"Emergency Contact",
		"12":"Car Rental",
		"13":"Cellphone",
		"14":"Temporary Accommodation",
		"15":"AiM",
		"16":"Customer Refer"
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
