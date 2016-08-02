var datadic = {
		serviceType:{
			"1":"Car Buying",
			"2":"Emergency Contact",
			"3":"Car Selling",
			"4":"Car Rental",
			"5":"Cellphone",
			"6":"Airport Pickup",
			"7":"Temporary House",
			"8":"AIM"
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
