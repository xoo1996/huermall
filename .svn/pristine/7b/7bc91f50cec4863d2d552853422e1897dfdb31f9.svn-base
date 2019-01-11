var LoadingMask, LoadingMsg, loading = {
	addMsk: function(t) {
		var t = t || "努力加载中，请稍候。。。";
		LoadingMask = $('<div class="datagrid-mask"></div>').css({
			display: "block",
			"z-index": "99999",
			width: "100%",
			height: $(window).height()
		}).appendTo("body"), LoadingMsg = $('<div class="datagrid-mask-msg"></div>').html(t).appendTo("body").css({
			display: "block",
			"z-index": "100000",
			left: ($(document.body).outerWidth(!0) - 190) / 2,
			top: ($(window).height() - 45) / 2
		})
	},
	removeMsk: function() {
		LoadingMask.remove(), LoadingMsg.remove()
	}
},
	App = function() {
		var t, e = ".J_grid",
			a = !! navigator.userAgent.match(/MSIE 8.0/),//判断浏览器类型
			n = !! navigator.userAgent.match(/MSIE 9.0/),
			i = !! navigator.userAgent.match(/MSIE 10/),
			r = function() {
				$.ajaxSetup({
					complete: function(t, e) {
						var a = t.getResponseHeader("Access-Status");
						"-1" == a && $.messager.alert("提示", "登录超时，请重新登录!", "warning", function() {
							window.location.reload()
						})
					}
				})
			},//window.location.hash:地址栏#后面的内容
			s = function() {
				$("#nav-menu li").click(function(t) {
					$(this).addClass("active").siblings().removeClass("active");
					var e = $(this).find("a").attr("href");//e为当前li的href值
					$("#crumb .c1").html($(this).find("a").html());
					 "#" != e && $("#left").load(e, function() {
						$.parser && $.parser.parse("#left");
						var t = window.location.hash,
							e = t.indexOf("/"),
							a = t.substring(1, e) - 1,
							n = t.substring(e);
						a != $("#nav-menu li.active").index() ? $(".main-menu:first .sub-menu li:first")
						.click() : (n.indexOf("?") > -1 && (n = n.split("?")[0]),
						 $(".main-menu .sub-menu li").each(function() {
							if (n == $(this).data("hash")) {
								var t = $(this).parent().parent().prev();
								return t.hasClass("accordion-header-selected") || t.click(), $(this).click(), !1
							}
						}))
					});
					t.preventDefault();
					
				})
			},
			o = function() {//delegate事件：指定的元素（属于被选元素的子元素）添加一个或多个事件处理程序。元素可以是未创建的
				$("#left").delegate(".main-menu .sub-menu li", "click", function(t) {
					$(".main-menu .sub-menu li").removeClass("active"), $(this).addClass("active");
					var e = $(this).find("a").attr("href"),
						a = $("#nav-menu li.active").index() + 1;
				
					window.location.href = "#" + a + $(this).data("hash"); 
					$("#crumb .c2").html("/ " + $(this).parent().data("group"));
					$("#crumb .c3").html("/ " + $(this).find("a").html());
					 $("#crumb .c4").html("");
					 "#" != e && $("#content").load(e, function() {
						$.parser && $.parser.parse("#content")
					});
					 t.preventDefault();
				})
			},
			d = function() {
				$(window).hashchange(function() {
					var t = window.location.hash,
						e = t.indexOf("/"),
						a = t.substring(1, e) - 1,
						n = t.substring(e);
					if (a != $("#nav-menu li.active").index() && $("#nav-menu li").eq(a).click(), $(".main-menu .sub-menu li").each(function() {
						return n == $(this).data("hash") ? $(this).hasClass("active") ? !1 : ($(this).click(), !1) : void 0
					}), n.indexOf("?") > -1) {
						var i = ZLZ.ROOT + n,
							r = n.split("?")[0];
						$("#crumb .c4").html("/ 编辑"), $(".main-menu .sub-menu li").removeClass("active"), $(".main-menu .sub-menu li").each(function() {
							r == $(this).data("hash") && $(this).addClass("active")
						}), $("#content").load(i, function() {
							$.parser && $.parser.parse("#content")
						})
					}
				}), $(window).hashchange()
			},
			l = function() {
				(a || n) && $("input[placeholder]:not(.placeholder-no-fix), textarea[placeholder]:not(.placeholder-no-fix)").each(function() {
					var t = $(this);
					"" == t.val() && "" != t.attr("placeholder") && t.addClass("placeholder").val(t.attr("placeholder")), t.focus(function() {
						t.val() == t.attr("placeholder") && t.val("")
					}), t.blur(function() {
						("" == t.val() || t.val() == t.attr("placeholder")) && t.val(t.attr("placeholder"))
					})
				})
			},
			u = function(t, e) {
				if (e) $("#crumb .c4").html("/ " + e), $("#content").load(t, function() {
					$.parser && $.parser.parse("#content")
				});
				else {
					var a = t.split(".")[0],
						n = new RegExp(ZLZ.ROOT, "gi"),
						i = a.replace(n, "");
					$(".main-menu .sub-menu li").each(function() {
						i == $(this).data("hash") && $(this).click()
					})
				}
			},
			c = function(t) {//reload
				if (t) {
					var t = t || e;
					$(t).hasClass("jq-datagrid") ? $(t).datagrid("reload") : $(t).treegrid("reload")
				} else $(".jq-datagrid").datagrid("reload"), $(".jq-treegrid").treegrid("reload")
			},
			m = function(t, e) {//message
				var a, n = 1e3;
			    if(t.status==0||t.status==1){
				1 == t.status ? a = '<i class="messager-icon-tip messager-right fl"></i>' : (a = '<i class="messager-icon-tip messager-error fl"></i>', n = 4e3),
				 $.messager.show({
					title: "提示",
					msg: a + '<div style="margin: 5px 0 0 40px;">' + t.info + "</div>",
					height: "auto",
					showType: "fade",
					timeout: n,
					style: {
						right: "",
						top: document.body.scrollTop + document.documentElement.scrollTop+300,
						bottom: "",
						fontSize: 14
					}
				}), c(e)}
			},
			g = function(e, a) {//ajaxForm表格提交
				var n = "";
				if ("object" == typeof arguments[0]) var i = arguments[0],
					a = i.gridId,
					r = i.success,
					e = i.formId;
				$(e).form({
					onSubmit: function() {
						var t = $(this).form("validate");
						return n = $(this).find("button[type='submit']").text(),
						 t ? void $(this).find("button[type='submit']").attr("disabled", "disabled").text("保存中...") : !1
					},
					success: function(t) {
						var t = new Function("return" + t)();
						$(e).find("button[type='submit']").removeAttr("disabled", "disabled").text(n), r && r(t), m(t, a)
					}
				}), $(".J_close").on("click", function() {
					t.dialog("destroy")
				})
			},
			f = function(e, a, n) {//popup 弹窗效果
				var i = {
					title: "信息处理",
					formId: "#form",
					gridId: "",
					width: 600,
					height: "auto",
					isClose: !0
				};
				if ("object" == typeof arguments[1]) var r = $.extend({}, i, arguments[1]),
					a = r.title,
					n = r.onLoad;
				else var r = $.extend({}, i),
					a = a || r.title;
				var s = r.formId,
					o = r.gridId,
					d = r.width,
					l = r.height,
					u = r.top,
					c = r.isClose,
					m = e.split("id=")[1];
				t = $.dialog({
					title: a,//弹出框的标题
					width: d,//宽度
					height: l,//高度
					top: u,//距离顶部距离
					href: e,//链接
					shadow: !1,
					onLoad: function() {
						var a = $(s).attr("action");//表单提交地址
						a || $(s).attr("action", e), m && $(s).append("<input type='hidden' name='id' value='" + m + "' />"),
						 g({
							formId: s,
							gridId: o,
							success: function() {
								t && c && (t.dialog("destroy"), t = "")
							}
						}), n && n()
					}
				})
			},
			pjl_lunbo = function(e, a, n) {//popup 弹窗效果
				var i = {
					title: "信息处理",
					formId: "#form",
					gridId: "",
					width: 600,
					height: "auto",
					isClose: !0
				};
				if ("object" == typeof arguments[1]) var r = $.extend({}, i, arguments[1]),
					a = r.title,
					n = r.onLoad;
				else var r = $.extend({}, i),
					a = a || r.title;
				var s = r.formId,
					o = r.gridId,
					d = r.width,
					l = r.height,
					u = r.top,
					c = r.isClose,
					m = e.split("id=")[1];
				t = $.dialog({
					title: a,//弹出框的标题
					width: d,//宽度
					height: l,//高度
					top: u,//距离顶部距离
					href: e,//链接
					shadow: !1,
					onLoad: function() {
						var a = $(s).attr("action");//表单提交地址
						a || $(s).attr("action", e), m && $(s).append("<input type='hidden' name='id' value='" + m + "' />"),
						 g({
							formId: s,
							gridId: o,
							success: function() {
								t && c && (t.dialog("destroy"), t = "")
								$("li.active").click();
							}
						}), n && n()
					}
				})
			},
			h = function(t) {
				var t = t || e;
				$(t).datagrid("loadData", {
					total: 0,
					rows: []
				})
			},
			p = function(t, e) {//ajax
				var a = {
					msg: "确定要删除选中的记录？",
					type: "POST"
				};
				if ("object" == typeof arguments[1]) var n = $.extend({}, a, arguments[1]),
					e = n.msg,
					i = n.success,
					r = n.ok;
				else var n = $.extend({}, a),
					e = e || n.msg;
				var s = n.type,
					o = n.gridId,
					d = n.param;
				$.confirm(e, function() {
					r && r(), $.ajax({
						url: t,
						type: s,
						dataType: "json",
						data: d,
						success: function(t) {
							m(t, o), i && i(t)
						}
					})
				})
			},
			ajax_lunbo = function(t, e) {//ajax
				var a = {
					msg: "确定要删除选中的记录？",
					type: "POST"
				};
				if ("object" == typeof arguments[1]) var n = $.extend({}, a, arguments[1]),
					e = n.msg,
					i = n.success,
					r = n.ok;
				else var n = $.extend({}, a),
					e = e || n.msg;
				var s = n.type,
					o = n.gridId,
					d = n.param;
				$.confirm(e, function() {
					r && r(), $.ajax({
						url: t,
						type: s,
						dataType: "json",
						data: d,
						success: function(t) {
							m(t, o), i && i(t),
							$("li.active").click();
						}
					})
				})
			},
			release = function(t, e) {//活动发布
				var a = {
					msg: "确定发布？",
					type: "POST"
				};
				if ("object" == typeof arguments[1]) var n = $.extend({}, a, arguments[1]),
					e = n.msg,
					i = n.success,
					r = n.ok;
				else var n = $.extend({}, a),
					e = e || n.msg;
				var s = n.type,
					o = n.gridId,
					d = n.param;
				$.confirm(e, function() {
					r && r(), $.ajax({
						url: t,
						type: s,
						dataType: "json",
						data: d,
						success: function(t) {
							m(t, o), i && i(t)
						}
					})
				})
			},
			v = function(t, a) {
				var a = a || e,
					n = $(a).hasClass("jq-treegrid"),
					i = t.split("wd=")[1];
				"" == i ? $.alert("请输入搜索内容", "info") : $(a).datagrid(n ? {
					url: t
				} : {
					url: t
				})
			},
			b = function() {//handleWord
				var t, e = function(e, a, n) {
						if (t = e, e.length > a) {
							var i = e.substring(0, a);
							return i + '<a href="javascript:;" class="txt-info J_openDetails">(明细)</a>'
						}
						return e
					},
					a = function(e) {
						var e = e || ".J_grid";
						$(".J_openDetails").on("click", function() {
							App.dialog({
								title: "查看明细",
								width: 600,
								content: t
							})
						})
					};
				return {
					cut: e,
					look: a
				}
			}(),
			w = function(t) {//statusFmt
				var e;
				return 0 == t ? e = "icon-cancel" : 1 == t && (e = "icon-accept"), '<i class="icon ' + e + '"></i>'
			},
			x = function(t) {//getUrlParam
				var e = new RegExp("(^|&)" + t + "=([^&]*)(&|$)"),
					a = window.location.search.substr(1).match(e);
				return null != a ? unescape(a[2]) : null
			},
			y = function(t, e) {//getString
				var a = $.map(t, function(t) {
					return t[e]
				}).join(",");
				return a
			},
			pp = function(t, e) {//ajax
				var a = {
					type: "POST"
				};
				if ("object" == typeof arguments[1]) var n = $.extend({}, a, arguments[1]),
					//e = n.msg,
					i = n.success,
					r = n.ok;
				else var n = $.extend({}, a);
					//e = e || n.msg;
				var s = n.type,
					o = n.gridId,
					d = n.param;
			   $.ajax({
						url: t,
						type: s,
						dataType: "json",
						data: d,
						success: function(t) {
							m(t, o), i && i(t)
						}
					})
			},
			activity_create = function(t, e) {//ajax
				if ("object" == typeof arguments[1]) var r = $.extend({}, arguments[1]);
				var s = r.formId,
				o = r.gridId,
				m = e.split("id=")[1];;  //id
				var a = $(s).attr("action");//表单提交地址
				alert(a);
				g({
					formId: s,
					gridId: o,
					success: function() {
						t && c && (t.dialog("destroy"), t = "")
					}
				})
			},
			warnDel=function(t, e) {//ajax
				var a = {
						msg: "该客户机类型存在绑定的客户机，是否强行删除？",
						type: "POST"
					};
					if ("object" == typeof arguments[1]) var n = $.extend({}, a, arguments[1]),
						e = n.msg,
						i = n.success,
						r = n.ok;
					else var n = $.extend({}, a),
						e = e || n.msg;
					var s = n.type,
						o = n.gridId,
						d = n.param;
					$.confirm(e, function() {
						r && r(), $.ajax({
							url: t,
							type: s,
							dataType: "json",
							data: d,
							success: function(t) {
								m(t, o), i && i(t)
							}
						})
					})
				};
		return {
			init: function() {
				r(), s(), o(), d(), l()
			},
			loadPage: u,
			message: m,
			popup: f,
			popup_lunbo: pjl_lunbo,
			ajaxForm: g,
			ajax: p,
			ajaxx: pp,
			ajax1: activity_create,
			ajax_lunbo: ajax_lunbo,
			release: release,
			warnDel:warnDel,
			search: v,
			reload: c,
			statusFmt: w,
			handleWord: b,
			getUrlParam: x,
			getString: y,
			loading: function(t) {
				"remove" == t ? loading.removeMsk() : loading.addMsk(t)
			}
		}
	}();
App.init(), function() {
	App.dialog = $.dialog = function(t) {
		var e = {
			minimizable: !1,
			modal: !0,
			collapsible: !1,
			maximizable: !1,
			onClose: function() {
				$(this).dialog("destroy")
			}
		};
		t = $.extend(e, t);
		var a = $(t.el || "<div style='padding: 5px;'></div>");
		return t.href ? (loading.addMsk(), $.get(t.href, function(e) {
			LoadingMask && loading.removeMsk(), t.href = null, a.dialog(t);
			var n = a.find("div.dialog-content");
			n.html($.fn.panel.defaults.extractor(e)), a.dialog(t), $.parser && $.parser.parse(n), t.onLoad && t.onLoad.call(a)
		}, "html")) : a.dialog(t), a
	}, App.alert = $.alert = function() {
		1 === arguments.length ? $.messager.alert("提示", arguments[0]) : 2 === arguments.length && "string" == typeof arguments[1] ? $.messager.alert("提示", arguments[0], arguments[1]) : 2 === arguments.length && "function" == typeof arguments[1] ? $.messager.alert("提示", arguments[0], "", arguments[1]) : 3 === arguments.length && $.messager.alert("提示", arguments[0], arguments[1], arguments[2])
	}, App.confirm = $.confirm = function(t, e) {
		$.messager.confirm("确认", t, function(t) {
			t && e()
		})
	}, App.prompt = $.prompt = function(t, e) {
		$.messager.prompt("提示", t, e)
	}
}(), $.extend($.fn.validatebox.defaults.rules, {
	idcard: {
		validator: function(t) {
			return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(t)
		},
		message: "身份证号码格式不正确"
	},
	safepass: {
		validator: function(t, e) {
			return /^[a-zA-Z0-9]{1}([a-zA-Z0-9]|[\~`!@#$%^&*?-_/+-=:;.,<>\'\"\\\|\[\]\(\)\{\}]){5,29}$/.test(t);
			/*return /^[A-Za-z0-9]{6,20}$/.test(t)*/
	},
		message: "密码由字母或数字或特殊字符组成，至少6位"
	},
	phone: {
		validator: function(t) {
			return /^0\d{2,3}(\-)?\d{7,8}$/.test(t)
		},
		message: "格式不正确,请使用下面格式:020-88888888"
	},
	mobile: {
		validator: function(t) {
			return /^1\d{10}$/.test(t)
		},
		message: "请输入正确的手机号码"
	},
	order: {
		validator: function(t) {
			return /^\d{6}$/.test(t)
		},
		message: "请输入正确的订单号"
	},
	allPhone: {
		validator: function(t) {
			return /^1\d{10}$/.test(t) || /^0\d{2,3}(\-)?\d{7,8}$/.test(t)
		},
		message: "请输入正确的手机号码"
	},
	currency: {
		validator: function(t) {
			return /^d+(.d+)?$/i.test(t)
		},
		message: "货币格式不正确"
	},
	qq: {
		validator: function(t) {
			return /^[1-9]\d{4,10}$/.test(t)
		},
		message: "QQ号码格式不正确"
	},
	integer: {
		validator: function(t) {
			return /^[0-9]*[1-9][0-9]*$/.test(t)
		},
		message: "只能输入正整数"
	},
	age: {
		validator: function(t) {
			return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(t)
		},
		message: "年龄必须是0到120之间的整数"
	},
	ipRange: {
		validator: function(t) {
			return /^(?:[1-9][0-9]?|1[01][0-9]|255)$/i.test(t)
		},
		message: "ip必须是0到255之间的整数"
	},
	chinese: {
		validator: function(t) {
			return /^[Α-￥]+$/i.test(t)
		},
		message: "请输入中文"
	},
	notChinese: {
		validator: function(t) {
			return /^\w+$/i.test(t);
		},
		message: "不能输入中文"
	},
	english: {
		validator: function(t) {
			return /^[A-Za-z]+$/i.test(t)
		},
		message: "请输入英文"
	},
	number: {
		validator: function(t, e) {
			return /^\d+$/.test(t)
		},
		message: "请输入数字"
	},
	unnormal: {
		validator: function(t) {
			return /.+/i.test(t)
		},
		message: "输入值不能为空和包含其他非法字符"
	},
	username: {
		validator: function(t) {
			return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(t)
		},
		message: "用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）"
	},
	zip: {
		validator: function(t) {
			return /^[1-9]\d{5}$/.test(t)
		},
		message: "邮政编码格式不正确"
	},
	ip: {
		validator: function(t) {
			return /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/.test(t)
		},
		message: "IP地址格式不正确,且不能输入大于256的数！"
	},
	
	
	name: {
		validator: function(t) {
			return /^[Α-￥]+$/i.test(t) | /^w+[ws]+w+$/i.test(t)
		},
		message: "请输入正确的姓名"
	},
	same: {
		validator: function(t, e) {
			return "" != $("#" + e[0]).val() && "" != t ? $("#" + e[0]).val() == t : !0
		},
		message: "两次输入的密码不一致！"
	},
	minLength: {
		validator: function(t, e) {
			return t.length >= e[0]
		},
		message: "内容长度至少为 {0} 位！"
	},
	onlyLength: {
		validator: function(t, e) {
			return t.length == e[0]
		},
		message: "内容长度只能为 {0} 位！"
	},
	numsize: {
		validator: function (value) {
            if (value>200 || value< 0) {
                return false;
            } else {
                return true;
            }
        },
		message: "一次新增台数不能超过200台！"
	},
	numberb: {
		validator: function (t) {
			return /^\+?[1-9][0-9]*$/.test(t)
			
        },
		message: "只能输入纯数字！"
	},
	numbera: {
		validator: function (t) {
			return /^[0-9a-zA-Z]*$/g.test(t)
			
        },
		message: "只能输入纯数字、纯字母或者数字字母的组合！"
	},
	numberc: {
		validator: function (t) {
			var num = t.substr(t.length-3);
			var letter = t.substr(0,t.length-3);
			var numflag = /^[0-9]*$/g.test(num); 
			var letterflag = /^[A-Za-z]+$/g.test(letter);
			return numflag & letterflag			
        },
		message: "后三位为数字，其余位数为字母！"
	},
	equalto: {  
        validator:function(value,param){  
            return $(param[0]).val() == value;  
        },  
        message:'两次输入的密码不一致！'  
    },
    
    selectRequired: {
        validator: function (value, param) {
            if (value == "" || value.indexOf('全部') >= 0) {
                return false;
            } else {
                return true;
            }
        },
        message: '该下拉框为必选项'
    },
});




$.extend($.fn.datagrid.methods, {  
    /**  
     * 开打提示功能  
     * @param {} jq  
     * @param {} params 提示消息框的样式  
     * @return {}  
     */  
    doCellTip: function(jq, params){  
        function showTip(data, td, e){  
            if ($(td).text() == ""||$(td).text().length<6)   
                return;  
            data.tooltip.text($(td).text()).css({  
                top: (e.pageY + 10) + 'px',  
                left: (e.pageX + 20) + 'px',  
                'z-index': $.fn.window.defaults.zIndex,  
                display: 'block'  
            });  
        };  
        return jq.each(function(){  
            var grid = $(this);  
            var options = $(this).data('datagrid');  
            if (!options.tooltip) {  
                var panel = grid.datagrid('getPanel').panel('panel');  
                var defaultCls = {  
                    'border': '1px solid #666',  
                    'padding': '2px',  
                    'color': '#666', 
                    'font-size':'12px',
                    'background': '#fff',  
                    'position': 'absolute',  
                    'max-width': '200px',  
                    'border-radius' : '4px',  
                    '-moz-border-radius' : '4px',  
                    '-webkit-border-radius' : '4px',  
                    'display': 'none'  
                }  
                var tooltip = $("<div id='celltip'></div>").appendTo('body');  
                tooltip.css($.extend({}, defaultCls, params.cls));  
                options.tooltip = tooltip;  
                panel.find('.datagrid-body').each(function(){  
                    var delegateEle = $(this).find('> div.datagrid-body-inner').length ? $(this).find('> div.datagrid-body-inner')[0] : this;  
                    $(delegateEle).undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove').delegate('td', {  
                        'mouseover': function(e){  
                            if (params.delay) {  
                                if (options.tipDelayTime)   
                                    clearTimeout(options.tipDelayTime);  
                                var that = this;  
                                options.tipDelayTime = setTimeout(function(){  
                                    showTip(options, that, e);  
                                }, params.delay);  
                            }  
                            else {  
                                showTip(options, this, e);  
                            }  
                              
                        },  
                        'mouseout': function(e){  
                            if (options.tipDelayTime)   
                                clearTimeout(options.tipDelayTime);  
                            options.tooltip.css({  
                                'display': 'none'  
                            });  
                        },  
                        'mousemove': function(e){  
                            var that = this;  
                            if (options.tipDelayTime)   
                                clearTimeout(options.tipDelayTime);  
                            //showTip(options, this, e);  
                            options.tipDelayTime = setTimeout(function(){  
                                    showTip(options, that, e);  
                                }, params.delay);  
                        }  
                    });  
                });  
                  
            }  
              
        });  
    },  
    /**  
     * 关闭消息提示功能  
     *  
     * @param {}  
     *            jq  
     * @return {}  
     */  
    cancelCellTip: function(jq){  
        return jq.each(function(){  
            var data = $(this).data('datagrid');  
            if (data.tooltip) {  
                data.tooltip.remove();  
                data.tooltip = null;  
                var panel = $(this).datagrid('getPanel').panel('panel');  
                panel.find('.datagrid-body').undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove')  
            }  
            if (data.tipDelayTime) {  
                clearTimeout(data.tipDelayTime);  
                data.tipDelayTime = null;  
            }  
        });  
    }  
});  
//rownumber自适应
$.extend($.fn.datagrid.methods, {
    fixRownumber : function (jq) {
        return jq.each(function () {
            var panel = $(this).datagrid("getPanel");
            //获取最后一行的number容器,并拷贝一份
            var clone = $(".datagrid-cell-rownumber", panel).last().clone();
            //由于在某些浏览器里面,是不支持获取隐藏元素的宽度,所以取巧一下
            clone.css({
                "position" : "absolute",
                left : -1000
            }).appendTo("body");
            var width = clone.width("auto").width();
            //默认宽度是25,所以只有大于25的时候才进行fix
            if (width > 25) {
                //多加5个像素,保持一点边距
                $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 5);
                //修改了宽度之后,需要对容器进行重新计算,所以调用resize
                $(this).datagrid("resize");
                //一些清理工作
                clone.remove();
                clone = null;
            } else {
                //还原成默认状态
                $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).removeAttr("style");
            }
        });
    }
});