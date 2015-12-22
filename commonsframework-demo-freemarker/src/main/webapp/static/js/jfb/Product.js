/**
 * 封装商品相关的方法
 * v=1.0
 */
(function(window){
	var Product = {};
	
	/** 分页查询商品
	 * filter：查询过滤条件
	 * 		number：页码（0开始）
	 * 		size：每页条目数（默认10）
	 * 		categoryId：分类Id
	 * callback：回调函数
	 */
	Product.search = function()
	{
		var filter;
		var callback;
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
	
		var url = Config.apiRoot + "/jfb/products";
		$.getApi(url, filter, function(json) {
			callback(json.page);
		}, "json");
	}
	
	window.Product = Product;
})(window);