(function($) {
	if (typeof Function.prototype.method != 'function') {
		Function.prototype.method = function(funcName, func) {
			this[funcName] = func;
			return this
		}
	}

	var LazyLoad = {
		isShow: function($el) {
			return $(window).height() + $(window).scrollTop() > $el.offset().top
		},
		loadImage: function() {
			var _self = this;
			var $imgs = $("img[LazyLoad]");
			$imgs.each(function() {
				var $this = $(this);
				if (_self.isShow($this)) {
					var src = $this.attr("LazyLoad");
					if(src && src.trim()){
						$this.removeAttr("LazyLoad");
						$this.attr("src", src);
						$this.show()
					}
				}
			})
		},
		run: function() {
			var _self = this;
			_self.loadImage();
			if (_self.bind)
				return;
			$(window).bind("touchmove touchend scroll", function() {
				_self.loadImage()
			});
			_self.bind = true
		}
	};
	
	var Controllers = {
		create: function(includes) {
			var mvc = function() {
				this.initializer.apply(this, arguments);
				this.inits.apply(this, arguments)
			};
			mvc.proxy = function(func) {
				return $.proxy(func, this)
			};
			mvc.fn = mvc.prototype;
			mvc.fn.inits = function() {};
			mvc.fn.proxy = mvc.proxy;
			mvc.include = function(ob) {
				$.extend(this.fn, ob)
			};
			mvc.extend = function(ob) {
				$.extend(this, ob)
			};
			mvc.updateView = function(func, callback) {
				if (func) {
					func();
					var that = this;
					setTimeout(function() {
						that.refreshElements();
						if (callback) {
							callback.apply(that, arguments)
						}
					}, 100)
				}
			};
			mvc.fn.updateView = mvc.updateView;
			mvc.include(Commons);
			mvc.include({
				initializer: function(options) {
					this.options = options;
					for (var o in this.options) {
						this[o] = this.options[o]
					}
					if (this.elements)
						this.refreshElements();
					if (this.events)
						this.delegateEvents();
					if (this.subscribe)
						this.subscribeEvents();
					if (this.mouseHandler)
						this.bindMouseEvents();
					if (!this.back) {
						if (this.init)
							this.init()
					}
				},
				$: function(key) {
					if (key) {
						return $(key, this.el)
					}
					return null
				},
				refreshElements: function() {
					var that = this;
					var eles = this.elements;
					for (var key in eles) {
						var val = eles[key];
						if (!val) {
							continue
						}
						if (typeof(val) != 'object') {
							that[val] = that.$(key);
						} else {
							for (var a in val) {
								that[val[a]] = that.$(a);
							}
						}
					}
				},
				delegateEvents: function() {
					for (var key in this.events) {
						(function(obj) {
							try {
								var methodName = obj.events[key];
								var method = obj.proxy(obj[methodName]);
								var match = key.split(/\s+/g);
								var match_length = match.length;
								if (match_length > 2) {
									var select = match.shift();
									for (i = 1; i < match_length - 1; i++) {
										select = select + ' ' + match.shift();
									}
									match.unshift(select)
								}
								match.push(function(e) {
									(function(k, event) {
										method.call(obj, k, (event || window.event))
									})(this, e)
								});
								obj.el.delegate.apply(obj.el, match)
							} catch (e) {
								console.log(e.message)
							}
						})(this)
					}
				},
				subscribeEvents: function() {
					for (var key in this.subscribe) {
						var method = this.subscribe[key];
						var proxy = this.proxy(this[method]);
						Messager.subscribe(key, proxy);
					}
				}
			});
			if (includes)
				mvc.include(includes);
			return mvc
		}
	};

	var Messager = {
		subscribe: function(ev, callback) {
			var _callbacks = this._callbacks || (this._callbacks = {});
			(this._callbacks[ev] || (this._callbacks[ev] = [])).push(callback);
			return this
		},
		publish: function() {
			var args = Array.prototype.slice.call(arguments, 0);
			var ev = args.shift();
			var that = this;
			var calls, list;
			if (!(calls = this._callbacks))
				return this;
			if (!(list = this._callbacks[ev]))
				return this;
			$.each(list, function(k, v) {
				v.apply(that, args)
			});
			return this
		}
	}

	var Commons = {
		cssLink: function(link) {
			return Config.Path.cssPath + link + '?v=' + Config.Path.version
		},
		scriptLink: function(link) {
			return Config.Path.scriptPath + link + '?v=' + Config.Path.version
		},
		imageUrlLink: function(link) {
			return Config.Path.imagePath + link + '?v=' + Config.Path.version
		},
		toPrice: function(price, precision) {
			var a = price;
			if (a >= 0) {
				a = parseFloat(a);
				return '¥' + a.toFixed(precision || 2)
			}
			return '¥' + price
		},
		regExp: {
			isNumber: /^(([1-9])(\d+)?)$/,
			chinese: /[\u4E00-\u9FA5]/g,
			numbers: /^[0-9]+$/,
			email: /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/,
			isNumberOrLetter: /^(?![a-z]+$)(?![A-Z]+$)(?![0-9]+$)[a-zA-Z0-9]+$/,
			tirmAll: /\s/g,
			tirmStart: /^\s*/,
			trimEnd: /\s*$/,
			trim: /(^\s*)|(\s*$)/g,
			zipCode: /^[1-9]{1}\d{5}$/,
			cellPhone: /^1[34578][0-9]{9}$/,
			phone: /^0\d{2,3}-[2-9]\d{6,7}(-\d{0,4})?$/,
			phonereg: /^0?\d{3,4}$/,
			phone1reg: /^\d{7,8}$/,
			phone2reg: /^\d{1,4}$/,
			nickName: /^[0-9A-Za-z_]{4,20}$/,
			time: /^\d{4}-\d{2}-\d{2}$/
		},
		requestQueue: [],
		ajaxApi: function(data) {
			(function(_utils, _data) {
				function AjaxStart() {
					new AjaxRequest()
				}

				function AjaxRequest() {
					this.init()
				}
				AjaxRequest.prototype = {
					init: function() {
						var queryString = $.param(_data.data || {});
						
						this.url = _data.url + (queryString.trim()==="" ? "" : "?"+queryString);
						if (this.validateRequest()) {
							this.beginRequest()
						}
					},
					validateRequest: function() {
						for (var urls in _utils.requestQueue) {
							if (_utils.requestQueue[urls] == this.url) {
								return false
							}
						}
						_utils.requestQueue.push(this.url);
						return true
					},
					beginRequest: function() {
						var _this = this;
						var timeout = _data.timeout;
						_data.timeout = timeout || 5000;
						
						// 按接口约定处理参数 ... 
						
						// 成功处理
						var sucessFn = _data.success;
						_data.success = function(resultData) {
							if (sucessFn) {
								// 业务错误处理
								try{
									if(resultData.code != 1){
										if(resultData.code === 120001){
											// 弹出提示登录等操作
											alert(resultData.message);
										} else {
											alert(resultData.message);
										}
										_this.requestDispose();
										return false;
									}
								}catch(e){
									_this.requestDispose();
									alert("服务器异常，请稍后重试");
									return false;
								}
								
								sucessFn(resultData);
							}
							
							// 控制重复请求（0.5秒内只对相同url请求1次）
							setTimeout(function() {
								_this.requestDispose();
							}, 500)
						};
						
						// 错误处理
						var errorFn = _data.error;
						_data.error = function(xhr, type) {
							if (errorFn) {
								errorFn(xhr, type);
							} else {
								console.log(xhr);
								console.log(type);
								
								switch (type){
									case "timeout":
										alert("网络请求超时，请稍后重试");
										break;
									default:
										alert("服务器异常，请稍后重试");
										break;
								}
							}
							
							_this.requestDispose();
						};
						
						$.ajax(_data);
					},
					requestDispose: function() {
						for (var i = 0, j = _utils.requestQueue.length; i < j; i++) {
							if (_utils.requestQueue[i] == this.url) {
								_utils.requestQueue.splice(i, 1);
								return
							}
						}
					}
				};
				AjaxStart()
			})(this, data)
		},
		parseArguments : function (url, data, success, dataType) {
			if ($.isFunction(data)) dataType = success, success = data, data = undefined
			if (!$.isFunction(success)) dataType = success, success = undefined
			return {
				url: url,
				data: data,
				success: success,
				dataType: dataType
			}
		},
		format: function(template) {
			return this.replace(template, [].slice.call(arguments, 1))
		},
		enter: function(event) {
			return (window.event && window.event.keyCode || event.which) == 13
		},
		replace: function(template, data) {
			return template.replace(/{([^{]*?)}/g, function(match, key) {
				return data[key] == null ? match : data[key]
			})
		},
		countDown: function(param) {
			var e = {
				g: param.end ? param.end.getTime() : new Date().getTime(),
				e: param.date.getTime(),
				d: 1000 * 60 * 60 * 24,
				h: 1000 * 60 * 60,
				m: 1000 * 60,
				s: 1000,
				ms: 100
			};
			e.n = e.e - e.g;
			var r = {
				day: '0',
				hour: '0',
				minute: '00',
				second: '00',
				millisecond: '0',
				timeend: true
			};
			if (e.n > 0) {
				r.day = Math.floor(e.n / e.d);
				e.d -= r.day * e.d;
				r.hour = Math.floor(e.n / e.h);
				e.n -= r.hour * e.h;
				r.minute = Math.floor(e.n / e.m);
				e.n -= r.minute * e.m;
				r.second = Math.floor(e.n / e.s);
				e.n -= r.second * e.s;
				r.millisecond = Math.floor(e.n / e.ms);
				for (var a in r) {
					if (a == 'timeend') {
						continue
					}
					if (r[a] > 0) {
						r.timeend = false
					}
					if (r[a] < 10 && a != 'millisecond') {
						r[a] = '0' + r[a]
					} else {
						r[a] = '' + r[a]
					}
				}
			}
			console.log(r);
			param.data = r;
			param.dom.html(this.replace(param.temp, param.data));
			if (Math.floor((e.e - e.g) / 1000 <= 0) && param.callback) {
				param.callback();
				return
			}
			
			this.countDown.Timer = setTimeout($.proxy(function() {
				this.countDown(param)
			}, this), param.time || 1000)
		},
		tmpl: function(data) {
				if (data.v.indexOf("url:") == 0) {
					var link = $.trim(data.v.substring(4));
					if (link && link.indexOf("/") == 0) {
						link = link.substring(1)
					}
					data.v = this.ScriptLink("template/" + link);
					var cache = this.tmpl.getCache(data.v);
					if (cache) {
						data.v = cache;
						this.tmpl.render(data)
					} else {
						this.tmpl.loadTmpl(data)
					}
				} else {
					this.tmpl.render(data)
				}
			}
			.method("loadTmpl", function(data) {
				var xhr = $.ajaxSettings.xhr();
				xhr.open("GET", data.v, true);
				xhr.send();
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4) {
						this.tmpl.setCache(data.v, xhr.responseText);
						data.v = xhr.responseText;
						this.tmpl.render(data)
					}
				}
			}).method("getCache", function(key) {
				this.tmpl.cache = this.tmpl.cache || {};
				return this.tmpl.cache[key]
			}).method("setCache", function(key, value) {
				this.tmpl.cache = this.tmpl.cache || {};
				this.tmpl.cache[key] = value
			}).method("render", function(data) {
				var regJs = /<%([\w\W]*?)%>/g,
					regJsExpression = /\bbreak\b|\bcase\b|\bcatch\b|\bcontinue\b|\bdefault\b|\bdelete\b|\bdo\b|\belse\b|\bfinally\b|\bfor\b|\bfunction\b|\bif\b|\breturn\b|\bswitch\b|\bthrow\b|\btry\b|\bvar\b|\bwhile\b|\bwith\b|{|}/g,
					newval = ' var r=[];\n',
					len = 0;
				var append = function(val, script) {
					return script ? (newval += val.match(regJsExpression) ? val + '\n' : 'r.push(' + val + ');\n') : (newval += val != '' ? 'r.push("' + val.replace(/"/g, '\\"') + '");\n' : ''),
						append
				};
				while (match = regJs.exec(data.v)) {
					append(data.v.slice(len, match.index))(match[1], true);
					len = match.index + match[0].length
				}
				append(data.v.slice(len));
				newval += ' return r.join("");';
				if(data.type && data.type=="append"){
				    data.dom.append(new Function(newval.replace(/[\n\r\t]/g, "")).apply(data.datas));
				} else {
				    data.dom.html(new Function(newval.replace(/[\n\r\t]/g, "")).apply(data.datas));
				}
				data.callback && data.callback()
			})
	}




	/** 
	 * 扩展 $ 静态方法
	 */
	$.extend($, {
		loadImage: $.proxy(LazyLoad.loadImage, LazyLoad),
		initLazyLoad: $.proxy(LazyLoad.run, LazyLoad),
		subscribe : $.proxy(Messager.subscribe, Messager),
		publish : $.proxy(Messager.publish, Messager),
		rootCssLink : $.proxy(Commons.cssLink, Commons),
		rootScriptLink : $.proxy(Commons.scriptLink, Commons),
		rootImageUrlLink : $.proxy(Commons.imageUrlLink, Commons),
		toPrice : $.proxy(Commons.toPrice, Commons),
		format : $.proxy(Commons.format, Commons),
		replace : $.proxy(Commons.replace, Commons),
		countDown : $.proxy(Commons.countDown, Commons),
		ajaxApi : $.proxy(Commons.ajaxApi, Commons),
		getApi : function( /* url, data, success, dataType */ ) {
			return $.ajaxApi(Commons.parseArguments.apply(null, arguments));
		},
		postApi : function( /* url, data, success, dataType */ ) {
			var options = Commons.parseArguments.apply(null, arguments);
			options.type = 'POST';
			return $.ajaxApi(options);
		},
	});

	/** 
	 * 扩展 $ 实例方法
	 */
	$.fn.CreateController = function(options) {
		var controller = Controllers.create(options);
		return new controller({el: this});
	};
	$.fn.enterEvent = function(callback) {
		this.on("keydown", function(event){
			if(Commons.enter(event)){
				callback.apply(this, arguments);
			}
		});
	};
})($);