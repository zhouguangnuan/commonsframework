/**
 * 封装商品相关的方法
 * v=1.0
 */
(function(window) {
	var Shop = {};

	/** 列表所售商品类型
	 * filter：查询过滤条件
	 * 		shopId	
	 * 		parentId
	 * callback：回调函数
	 */
	Shop.productCategorys = function() {
	    	var filter = null;
		var callback = null;
		if(arguments.length==0){
			alert("callback not be no");
		}
		if(arguments.length==1 && typeof arguments[0] != "function")
		{
			alert("callback not be no");
		} else {
			callback = arguments[0];
		}
		if(arguments.length>1){
			filter = arguments[0];
			callback = arguments[1];
		}
		
		var url = Config.apiRoot + "/jfb/product/categorys";
		$.getApi(url, filter, function(json) {
			callback(json.data);
		}, "json");
	}
	
	window.Shop = Shop;
})(window);