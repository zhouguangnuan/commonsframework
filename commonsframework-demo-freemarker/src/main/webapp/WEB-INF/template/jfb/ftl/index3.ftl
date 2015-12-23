<#include "/jfb/ftl/base/basic.ftl">
	<#macro mhead>
		<!--[if lt IE 9]> 
<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script> 
<![endif]-->
	</#macro>
	<#macro mbody>
		<div class="top_bar">
			<div class="c_word"><span><em>&nbsp;</em>都会国际大厦</span></div>
			<div class="r_word">
				<div class="mine">&nbsp;</div>
			</div>
		</div>

		<div class="content" id="content">
			<!-- 左侧内容区 -->
			<div class="left" id="view_left">
				<div style="position: absolute;"></div>
			</div>

			<!-- 右侧内容区 -->
			<div class="right" id="view_right">
				<div style="position: absolute;"></div>
			</div>
		</div>

		<!-- 购物车为空是  加class kong   满足第一个条件时  为 ts1  全满足则不加任何东西-->
		<div class="bottom_btn">
			<div class="card_no"><em>4</em></div>
			<div class="price">
				<div class="p">
					<i>共</i>
					<b>¥133.6</b>
				</div>
				<div class="x">购物车是空的</div>
				<div class="word">
					<span class="op">满¥10起送</span>
					<span class="op1">满¥50免运费</span>
				</div>
			</div>
			<div class="btn">
				<input value="立即购买" type="button" class="js_btn">
				<p>还差¥10起送</p>
			</div>
		</div>

		<!-- 模版 START -->
		<script type="text/html" id="tmpl_left">
			<% $.each(this.list, function(index, item) { %>
				<dl class="<% index==1 ? 'open' : 'close' %>" style="background:none;">
					<dt class="tehuijingxuan"><% item.name %><i></i></dt>
					<% if(item.children && item.children.length>0) { %>
						<div class="dl_box">
							<div class="dd_top">&nbsp;</div>
							<% $.each(item.children, function(_index, _item) { %>
								<dd><em></em>
									<% _item.name %>
								</dd>
							<% }); %>
							<div class="dd_bottom">&nbsp;</div>
						</div>
					<% } %>
				</dl>
			<% }); %>
		</script>
		<script type="text/html" id="tmpl_right">
			<% $.each(this.content, function(index, item) { %>
				<div class="p_one_box">
					<div class="img"><img src="https://placeholdit.imgix.net/~text?txtsize=30&txt=%E5%B7%A8%E6%96%B9%E4%BE%BF&w=55&h=55" lazyload="<% $.rootImageUrlLink(item.images) %>"></div>
					<div class="name"><% item.name %></div>
					<div class="gg"><% item.unitVal %><% item.unit %></div>
					<div class="price">￥<% item.price %></div>
					<div class="ad">
						<input type="button" class="del" style="display:none">
						<input type="text" class="text">
						<input type="button" class="add">
					</div>
				</div>
			<% }); %>
		</script>
		<!-- 模版 END -->

		<script type="text/javascript" src="${mRoot}/static/js/jfb/base/iscroll.4.2.5.js?v=1.0"></script>
		<script type="text/javascript" src="${mRoot}/static/js/jfb/Shop.js?v=1.0"></script>
		<script type="text/javascript" src="${mRoot}/static/js/jfb/Product.js?v=1.0"></script>
		<script type="text/javascript">
			$(function() {
				var scroll_left = new iScroll('view_left', {
					click: true,
					checkDOMChanges: true,
					hScrollbar: false,
					vScrollbar: false
				});
				var scroll_right = new iScroll('view_right', {
					click: true,
					checkDOMChanges: true,
					fadeScrollbar: false,
					onScrollEnd: function() {
						$.loadImage();
						if (this.y == this.maxScrollY && this.current_page + 1 < this.totalPages) {
							Product.search({
								'number': this.current_page + 1
							}, function(page) {
								scroll_right.current_page = page.number;
								scroll_right.totalPages = page.totalPages;
								controller.tmpl({
									dom: controller.view_right,
									v: $('#tmpl_right').html(),
									datas: page,
									type: "append"
								});
							});
						}
					}, onRefresh: function() {
						$.loadImage();
					}
				});
				scroll_right.current_page = 0;
				scroll_right.totalPages = 0;
				
				var controller =  $('#content').CreateController({
					// dom对象映射
					// 这里选择器必须是已存在的dom
					elements: {
						'#view_left>div': "view_left",
						'#view_right>div': "view_right",
					},
					// dom事件
					// 这里的selector可以是模版的
					events: {
						'#view_left dt click': 'showChildrenCategorys',
						'#view_left dd click': 'showProdut',
					},
					// 事件处理函数
					showChildrenCategorys: function(el, event) {
						$(el).parent().toggleClass("open");
						$(el).parent().toggleClass("close");
					},
					showProdut: function(el, event) {
						Product.search({
							'number': 0
						}, function(page) {
							scroll_right.current_page = page.number;
							scroll_right.totalPages = page.totalPages;
							controller.tmpl({
								dom: controller.view_right,
								v: $('#tmpl_right').html(),
								datas: page
							});
							scroll_right.scrollTo(0, scroll_right.y, 200, true);
						});
					},
					// 控制器初始化函数
					init: function() {
						// view_left
						Shop.productCategorys(function(data) {
							controller.tmpl({
								dom: controller.view_left,
								v: $('#tmpl_left').html(),
								datas: data
							});
						});
						// view_right
						Product.search(function(page) {
							scroll_right.current_page = page.number;
							scroll_right.totalPages = page.totalPages;
							controller.tmpl({
								dom: controller.view_right,
								v: $('#tmpl_right').html(),
								datas: page
							});
						});
					}
				});
				
				$.initLazyLoad();
			});
		</script>
	</#macro>