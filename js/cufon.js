/*
 * Copyright (c) 2009 Simo Kinnunen.
 * Licensed under the MIT license.
 *
 * @version 1.09i
 */
var Cufon = (function() {
	var m = function() {
		return m.replace.apply(null, arguments)
	};
	var x = m.DOM = {
		ready : (function() {
			var C = false, E = {
				loaded : 1,
				complete : 1
			};
			var B = [], D = function() {
				if (C) {
					return
				}
				C = true;
				for ( var F; F = B.shift(); F()) {
				}
			};
			if (document.addEventListener) {
				document.addEventListener("DOMContentLoaded", D, false);
				window.addEventListener("pageshow", D, false)
			}
			if (!window.opera && document.readyState) {
				(function() {
					E[document.readyState] ? D() : setTimeout(arguments.callee,
							10)
				})()
			}
			if (document.readyState && document.createStyleSheet) {
				(function() {
					try {
						document.body.doScroll("left");
						D()
					} catch (F) {
						setTimeout(arguments.callee, 1)
					}
				})()
			}
			q(window, "load", D);
			return function(F) {
				if (!arguments.length) {
					D()
				} else {
					C ? F() : B.push(F)
				}
			}
		})(),
		root : function() {
			return document.documentElement || document.body
		}
	};
	var n = m.CSS = {
		Size : function(C, B) {
			this.value = parseFloat(C);
			this.unit = String(C).match(/[a-z%]*$/)[0] || "px";
			this.convert = function(D) {
				return D / B * this.value
			};
			this.convertFrom = function(D) {
				return D / this.value * B
			};
			this.toString = function() {
				return this.value + this.unit
			}
		},
		addClass : function(C, B) {
			var D = C.className;
			C.className = D + (D && " ") + B;
			return C
		},
		color : j(function(C) {
			var B = {};
			B.color = C.replace(/^rgba\((.*?),\s*([\d.]+)\)/,
					function(E, D, F) {
						B.opacity = parseFloat(F);
						return "rgb(" + D + ")"
					});
			return B
		}),
		fontStretch : j(function(B) {
			if (typeof B == "number") {
				return B
			}
			if (/%$/.test(B)) {
				return parseFloat(B) / 100
			}
			return {
				"ultra-condensed" : 0.5,
				"extra-condensed" : 0.625,
				condensed : 0.75,
				"semi-condensed" : 0.875,
				"semi-expanded" : 1.125,
				expanded : 1.25,
				"extra-expanded" : 1.5,
				"ultra-expanded" : 2
			}[B] || 1
		}),
		getStyle : function(C) {
			var B = document.defaultView;
			if (B && B.getComputedStyle) {
				return new a(B.getComputedStyle(C, null))
			}
			if (C.currentStyle) {
				return new a(C.currentStyle)
			}
			return new a(C.style)
		},
		gradient : j(function(F) {
			var G = {
				id : F,
				type : F.match(/^-([a-z]+)-gradient\(/)[1],
				stops : []
			}, C = F.substr(F.indexOf("(")).match(
					/([\d.]+=)?(#[a-f0-9]+|[a-z]+\(.*?\)|[a-z]+)/ig);
			for ( var E = 0, B = C.length, D; E < B; ++E) {
				D = C[E].split("=", 2).reverse();
				G.stops.push([ D[1] || E / (B - 1), D[0] ])
			}
			return G
		}),
		quotedList : j(function(E) {
			var D = [], C = /\s*((["'])([\s\S]*?[^\\])\2|[^,]+)\s*/g, B;
			while (B = C.exec(E)) {
				D.push(B[3] || B[1])
			}
			return D
		}),
		recognizesMedia : j(function(G) {
			var E = document.createElement("style"), D, C, B;
			E.type = "text/css";
			E.media = G;
			try {
				E.appendChild(document.createTextNode("/**/"))
			} catch (F) {
			}
			C = g("head")[0];
			C.insertBefore(E, C.firstChild);
			D = (E.sheet || E.styleSheet);
			B = D && !D.disabled;
			C.removeChild(E);
			return B
		}),
		removeClass : function(D, C) {
			var B = RegExp("(?:^|\\s+)" + C + "(?=\\s|$)", "g");
			D.className = D.className.replace(B, "");
			return D
		},
		supports : function(D, C) {
			var B = document.createElement("span").style;
			if (B[D] === undefined) {
				return false
			}
			B[D] = C;
			return B[D] === C
		},
		textAlign : function(E, D, B, C) {
			if (D.get("textAlign") == "right") {
				if (B > 0) {
					E = " " + E
				}
			} else {
				if (B < C - 1) {
					E += " "
				}
			}
			return E
		},
		textShadow : j(function(F) {
			if (F == "none") {
				return null
			}
			var E = [], G = {}, B, C = 0;
			var D = /(#[a-f0-9]+|[a-z]+\(.*?\)|[a-z]+)|(-?[\d.]+[a-z%]*)|,/ig;
			while (B = D.exec(F)) {
				if (B[0] == ",") {
					E.push(G);
					G = {};
					C = 0
				} else {
					if (B[1]) {
						G.color = B[1]
					} else {
						G[[ "offX", "offY", "blur" ][C++]] = B[2]
					}
				}
			}
			E.push(G);
			return E
		}),
		textTransform : (function() {
			var B = {
				uppercase : function(C) {
					return C.toUpperCase()
				},
				lowercase : function(C) {
					return C.toLowerCase()
				},
				capitalize : function(C) {
					return C.replace(/\b./g, function(D) {
						return D.toUpperCase()
					})
				}
			};
			return function(E, D) {
				var C = B[D.get("textTransform")];
				return C ? C(E) : E
			}
		})(),
		whiteSpace : (function() {
			var D = {
				inline : 1,
				"inline-block" : 1,
				"run-in" : 1
			};
			var C = /^\s+/, B = /\s+$/;
			return function(H, F, G, E) {
				if (E) {
					if (E.nodeName.toLowerCase() == "br") {
						H = H.replace(C, "")
					}
				}
				if (D[F.get("display")]) {
					return H
				}
				if (!G.previousSibling) {
					H = H.replace(C, "")
				}
				if (!G.nextSibling) {
					H = H.replace(B, "")
				}
				return H
			}
		})()
	};
	n.ready = (function() {
		var B = !n.recognizesMedia("all"), E = false;
		var D = [], H = function() {
			B = true;
			for ( var K; K = D.shift(); K()) {
			}
		};
		var I = g("link"), J = g("style");
		function C(K) {
			return K.disabled || G(K.sheet, K.media || "screen")
		}
		function G(M, P) {
			if (!n.recognizesMedia(P || "all")) {
				return true
			}
			if (!M || M.disabled) {
				return false
			}
			try {
				var Q = M.cssRules, O;
				if (Q) {
					search: for ( var L = 0, K = Q.length; O = Q[L], L < K; ++L) {
						switch (O.type) {
						case 2:
							break;
						case 3:
							if (!G(O.styleSheet, O.media.mediaText)) {
								return false
							}
							break;
						default:
							break search
						}
					}
				}
			} catch (N) {
			}
			return true
		}
		function F() {
			if (document.createStyleSheet) {
				return true
			}
			var L, K;
			for (K = 0; L = I[K]; ++K) {
				if (L.rel.toLowerCase() == "stylesheet" && !C(L)) {
					return false
				}
			}
			for (K = 0; L = J[K]; ++K) {
				if (!C(L)) {
					return false
				}
			}
			return true
		}
		x.ready(function() {
			if (!E) {
				E = n.getStyle(document.body).isUsable()
			}
			if (B || (E && F())) {
				H()
			} else {
				setTimeout(arguments.callee, 10)
			}
		});
		return function(K) {
			if (B) {
				K()
			} else {
				D.push(K)
			}
		}
	})();
	function s(D) {
		var C = this.face = D.face, B = {
			"\u0020" : 1,
			"\u00a0" : 1,
			"\u3000" : 1
		};
		this.glyphs = D.glyphs;
		this.w = D.w;
		this.baseSize = parseInt(C["units-per-em"], 10);
		this.family = C["font-family"].toLowerCase();
		this.weight = C["font-weight"];
		this.style = C["font-style"] || "normal";
		this.viewBox = (function() {
			var F = C.bbox.split(/\s+/);
			var E = {
				minX : parseInt(F[0], 10),
				minY : parseInt(F[1], 10),
				maxX : parseInt(F[2], 10),
				maxY : parseInt(F[3], 10)
			};
			E.width = E.maxX - E.minX;
			E.height = E.maxY - E.minY;
			E.toString = function() {
				return [ this.minX, this.minY, this.width, this.height ]
						.join(" ")
			};
			return E
		})();
		this.ascent = -parseInt(C.ascent, 10);
		this.descent = -parseInt(C.descent, 10);
		this.height = -this.ascent + this.descent;
		this.spacing = function(L, N, E) {
			var O = this.glyphs, M, K, G, P = [], F = 0, J = -1, I = -1, H;
			while (H = L[++J]) {
				M = O[H] || this.missingGlyph;
				if (!M) {
					continue
				}
				if (K) {
					F -= G = K[H] || 0;
					P[I] -= G
				}
				F += P[++I] = ~~(M.w || this.w) + N + (B[H] ? E : 0);
				K = M.k
			}
			P.total = F;
			return P
		}
	}
	function f() {
		var C = {}, B = {
			oblique : "italic",
			italic : "oblique"
		};
		this.add = function(D) {
			(C[D.style] || (C[D.style] = {}))[D.weight] = D
		};
		this.get = function(H, I) {
			var G = C[H] || C[B[H]] || C.normal || C.italic || C.oblique;
			if (!G) {
				return null
			}
			I = {
				normal : 400,
				bold : 700
			}[I] || parseInt(I, 10);
			if (G[I]) {
				return G[I]
			}
			var E = {
				1 : 1,
				99 : 0
			}[I % 100], K = [], F, D;
			if (E === undefined) {
				E = I > 400
			}
			if (I == 500) {
				I = 400
			}
			for ( var J in G) {
				if (!k(G, J)) {
					continue
				}
				J = parseInt(J, 10);
				if (!F || J < F) {
					F = J
				}
				if (!D || J > D) {
					D = J
				}
				K.push(J)
			}
			if (I < F) {
				I = F
			}
			if (I > D) {
				I = D
			}
			K.sort(function(M, L) {
				return (E ? (M >= I && L >= I) ? M < L : M > L
						: (M <= I && L <= I) ? M > L : M < L) ? -1 : 1
			});
			return G[K[0]]
		}
	}
	function r() {
		function D(F, G) {
			if (F.contains) {
				return F.contains(G)
			}
			return F.compareDocumentPosition(G) & 16
		}
		function B(G) {
			var F = G.relatedTarget;
			if (!F || D(this, F)) {
				return
			}
			C(this, G.type == "mouseover")
		}
		function E(F) {
			C(this, F.type == "mouseenter")
		}
		function C(F, G) {
			setTimeout(function() {
				var H = d.get(F).options;
				m.replace(F, G ? h(H, H.hover) : H, true)
			}, 10)
		}
		this.attach = function(F) {
			if (F.onmouseenter === undefined) {
				q(F, "mouseover", B);
				q(F, "mouseout", B)
			} else {
				q(F, "mouseenter", E);
				q(F, "mouseleave", E)
			}
		}
	}
	function u() {
		var C = [], D = {};
		function B(H) {
			var E = [], G;
			for ( var F = 0; G = H[F]; ++F) {
				E[F] = C[D[G]]
			}
			return E
		}
		this.add = function(F, E) {
			D[F] = C.push(E) - 1
		};
		this.repeat = function() {
			var E = arguments.length ? B(arguments) : C, F;
			for ( var G = 0; F = E[G++];) {
				m.replace(F[0], F[1], true)
			}
		}
	}
	function A() {
		var D = {}, B = 0;
		function C(E) {
			return E.cufid || (E.cufid = ++B)
		}
		this.get = function(E) {
			var F = C(E);
			return D[F] || (D[F] = {})
		}
	}
	function a(B) {
		var D = {}, C = {};
		this.extend = function(E) {
			for ( var F in E) {
				if (k(E, F)) {
					D[F] = E[F]
				}
			}
			return this
		};
		this.get = function(E) {
			return D[E] != undefined ? D[E] : B[E]
		};
		this.getSize = function(F, E) {
			return C[F] || (C[F] = new n.Size(this.get(F), E))
		};
		this.isUsable = function() {
			return !!B
		}
	}
	function q(C, B, D) {
		if (C.addEventListener) {
			C.addEventListener(B, D, false)
		} else {
			if (C.attachEvent) {
				C.attachEvent("on" + B, function() {
					return D.call(C, window.event)
				})
			}
		}
	}
	function v(C, B) {
		var D = d.get(C);
		if (D.options) {
			return C
		}
		if (B.hover && B.hoverables[C.nodeName.toLowerCase()]) {
			b.attach(C)
		}
		D.options = B;
		return C
	}
	function j(B) {
		var C = {};
		return function(D) {
			if (!k(C, D)) {
				C[D] = B.apply(null, arguments)
			}
			return C[D]
		}
	}
	function c(F, E) {
		var B = n.quotedList(E.get("fontFamily").toLowerCase()), D;
		for ( var C = 0; D = B[C]; ++C) {
			if (i[D]) {
				return i[D].get(E.get("fontStyle"), E.get("fontWeight"))
			}
		}
		return null
	}
	function g(B) {
		return document.getElementsByTagName(B)
	}
	function k(C, B) {
		return C.hasOwnProperty(B)
	}
	function h() {
		var C = {}, B, F;
		for ( var E = 0, D = arguments.length; B = arguments[E], E < D; ++E) {
			for (F in B) {
				if (k(B, F)) {
					C[F] = B[F]
				}
			}
		}
		return C
	}
	function o(E, M, C, N, F, D) {
		var K = document.createDocumentFragment(), H;
		if (M === "") {
			return K
		}
		var L = N.separate;
		var I = M.split(p[L]), B = (L == "words");
		if (B && t) {
			if (/^\s/.test(M)) {
				I.unshift("")
			}
			if (/\s$/.test(M)) {
				I.push("")
			}
		}
		for ( var J = 0, G = I.length; J < G; ++J) {
			H = z[N.engine](E, B ? n.textAlign(I[J], C, J, G) : I[J], C, N, F,
					D, J < G - 1);
			if (H) {
				K.appendChild(H)
			}
		}
		return K
	}
	function l(D, M) {
		var C = D.nodeName.toLowerCase();
		if (M.ignore[C]) {
			return
		}
		var E = !M.textless[C];
		var B = n.getStyle(v(D, M)).extend(M);
		var F = c(D, B), G, K, I, H, L, J;
		if (!F) {
			return
		}
		for (G = D.firstChild; G; G = I) {
			K = G.nodeType;
			I = G.nextSibling;
			if (E && K == 3) {
				if (H) {
					H.appendData(G.data);
					D.removeChild(G)
				} else {
					H = G
				}
				if (I) {
					continue
				}
			}
			if (H) {
				D.replaceChild(o(F, n.whiteSpace(H.data, B, H, J), B, M, G, D),
						H);
				H = null
			}
			if (K == 1) {
				if (G.firstChild) {
					if (G.nodeName.toLowerCase() == "cufon") {
						z[M.engine](F, null, B, M, G, D)
					} else {
						arguments.callee(G, M)
					}
				}
				J = G
			}
		}
	}
	var t = " ".split(/\s+/).length == 0;
	var d = new A();
	var b = new r();
	var y = new u();
	var e = false;
	var z = {}, i = {}, w = {
		autoDetect : false,
		engine : null,
		forceHitArea : false,
		hover : false,
		hoverables : {
			a : true
		},
		ignore : {
			applet : 1,
			canvas : 1,
			col : 1,
			colgroup : 1,
			head : 1,
			iframe : 1,
			map : 1,
			optgroup : 1,
			option : 1,
			script : 1,
			select : 1,
			style : 1,
			textarea : 1,
			title : 1,
			pre : 1
		},
		printable : true,
		selector : (window.Sizzle
				|| (window.jQuery && function(B) {
					return jQuery(B)
				})
				|| (window.dojo && dojo.query)
				|| (window.Ext && Ext.query)
				|| (window.YAHOO && YAHOO.util && YAHOO.util.Selector && YAHOO.util.Selector.query)
				|| (window.$$ && function(B) {
					return $$(B)
				}) || (window.$ && function(B) {
					return $(B)
				}) || (document.querySelectorAll && function(B) {
					return document.querySelectorAll(B)
				}) || g),
		separate : "words",
		textless : {
			dl : 1,
			html : 1,
			ol : 1,
			table : 1,
			tbody : 1,
			thead : 1,
			tfoot : 1,
			tr : 1,
			ul : 1
		},
		textShadow : "none"
	};
	var p = {
		words : /\s/.test("\u00a0") ? /[^\S\u00a0]+/ : /\s+/,
		characters : "",
		none : /^/
	};
	m.now = function() {
		x.ready();
		return m
	};
	m.refresh = function() {
		y.repeat.apply(y, arguments);
		return m
	};
	m.registerEngine = function(C, B) {
		if (!B) {
			return m
		}
		z[C] = B;
		return m.set("engine", C)
	};
	m.registerFont = function(D) {
		if (!D) {
			return m
		}
		var B = new s(D), C = B.family;
		if (!i[C]) {
			i[C] = new f()
		}
		i[C].add(B);
		return m.set("fontFamily", '"' + C + '"')
	};
	m.replace = function(D, C, B) {
		C = h(w, C);
		if (!C.engine) {
			return m
		}
		if (!e) {
			n.addClass(x.root(), "cufon-active cufon-loading");
			n.ready(function() {
				n.addClass(n.removeClass(x.root(), "cufon-loading"),
						"cufon-ready")
			});
			e = true
		}
		if (C.hover) {
			C.forceHitArea = true
		}
		if (C.autoDetect) {
			delete C.fontFamily
		}
		if (typeof C.textShadow == "string") {
			C.textShadow = n.textShadow(C.textShadow)
		}
		if (typeof C.color == "string" && /^-/.test(C.color)) {
			C.textGradient = n.gradient(C.color)
		} else {
			delete C.textGradient
		}
		if (!B) {
			y.add(D, arguments)
		}
		if (D.nodeType || typeof D == "string") {
			D = [ D ]
		}
		n.ready(function() {
			for ( var F = 0, E = D.length; F < E; ++F) {
				var G = D[F];
				if (typeof G == "string") {
					m.replace(C.selector(G), C, true)
				} else {
					l(G, C)
				}
			}
		});
		return m
	};
	m.set = function(B, C) {
		w[B] = C;
		return m
	};
	return m
})();
Cufon
		.registerEngine(
				"vml",
				(function() {
					var e = document.namespaces;
					if (!e) {
						return
					}
					e.add("cvml", "urn:schemas-microsoft-com:vml");
					e = null;
					var b = document.createElement("cvml:shape");
					b.style.behavior = "url(#default#VML)";
					if (!b.coordsize) {
						return
					}
					b = null;
					var h = (document.documentMode || 0) < 8;
					document
							.write(('<style type="text/css">cufoncanvas{text-indent:0;}@media screen{cvml\\:shape,cvml\\:rect,cvml\\:fill,cvml\\:shadow{behavior:url(#default#VML);display:block;antialias:true;position:absolute;}cufoncanvas{position:absolute;text-align:left;}cufon{display:inline-block;position:relative;vertical-align:'
									+ (h ? "middle" : "text-bottom") + ";}cufon cufontext{position:absolute;left:-10000in;font-size:1px;}a cufon{cursor:pointer}}@media print{cufon cufoncanvas{display:none;}}</style>")
									.replace(/;/g, "!important;"));
					function c(i, j) {
						return a(i, /(?:em|ex|%)$|^[a-z-]+$/i.test(j) ? "1em"
								: j)
					}
					function a(l, m) {
						if (m === "0") {
							return 0
						}
						if (/px$/i.test(m)) {
							return parseFloat(m)
						}
						var k = l.style.left, j = l.runtimeStyle.left;
						l.runtimeStyle.left = l.currentStyle.left;
						l.style.left = m.replace("%", "em");
						var i = l.style.pixelLeft;
						l.style.left = k;
						l.runtimeStyle.left = j;
						return i
					}
					function f(l, k, j, n) {
						var i = "computed" + n, m = k[i];
						if (isNaN(m)) {
							m = k.get(n);
							k[i] = m = (m == "normal") ? 0 : ~~j.convertFrom(a(
									l, m))
						}
						return m
					}
					var g = {};
					function d(p) {
						var q = p.id;
						if (!g[q]) {
							var n = p.stops, o = document
									.createElement("cvml:fill"), i = [];
							o.type = "gradient";
							o.angle = 180;
							o.focus = "0";
							o.method = "sigma";
							o.color = n[0][1];
							for ( var m = 1, l = n.length - 1; m < l; ++m) {
								i.push(n[m][0] * 100 + "% " + n[m][1])
							}
							o.colors = i.join(",");
							o.color2 = n[l][1];
							g[q] = o
						}
						return g[q]
					}
					return function(ac, G, Y, C, K, ad, W) {
						var n = (G === null);
						if (n) {
							G = K.alt
						}
						var I = ac.viewBox;
						var p = Y.computedFontSize
								|| (Y.computedFontSize = new Cufon.CSS.Size(c(
										ad, Y.get("fontSize"))
										+ "px", ac.baseSize));
						var y, q;
						if (n) {
							y = K;
							q = K.firstChild
						} else {
							y = document.createElement("cufon");
							y.className = "cufon cufon-vml";
							y.alt = G;
							q = document.createElement("cufoncanvas");
							y.appendChild(q);
							if (C.printable) {
								var Z = document.createElement("cufontext");
								Z.appendChild(document.createTextNode(G));
								y.appendChild(Z)
							}
							if (!W) {
								y.appendChild(document
										.createElement("cvml:shape"))
							}
						}
						var ai = y.style;
						var R = q.style;
						var l = p.convert(I.height), af = Math.ceil(l);
						var V = af / l;
						var P = V * Cufon.CSS.fontStretch(Y.get("fontStretch"));
						var U = I.minX, T = I.minY;
						R.height = af;
						R.top = Math.round(p.convert(T - ac.ascent));
						R.left = Math.round(p.convert(U));
						ai.height = p.convert(ac.height) + "px";
						var F = Y.get("color");
						var ag = Cufon.CSS.textTransform(G, Y).split("");
						var L = ac.spacing(ag, f(ad, Y, p, "letterSpacing"), f(
								ad, Y, p, "wordSpacing"));
						if (!L.length) {
							return null
						}
						var k = L.total;
						var x = -U + k + (I.width - L[L.length - 1]);
						var ah = p.convert(x * P), X = Math.round(ah);
						var O = x + "," + I.height, m;
						var J = "r" + O + "ns";
						var u = C.textGradient && d(C.textGradient);
						var o = ac.glyphs, S = 0;
						var H = C.textShadow;
						var ab = -1, aa = 0, w;
						while (w = ag[++ab]) {
							var D = o[ag[ab]] || ac.missingGlyph, v;
							if (!D) {
								continue
							}
							if (n) {
								v = q.childNodes[aa];
								while (v.firstChild) {
									v.removeChild(v.firstChild)
								}
							} else {
								v = document.createElement("cvml:shape");
								q.appendChild(v)
							}
							v.stroked = "f";
							v.coordsize = O;
							v.coordorigin = m = (U - S) + "," + T;
							v.path = (D.d ? "m" + D.d + "xe" : "") + "m" + m
									+ J;
							v.fillcolor = F;
							if (u) {
								v.appendChild(u.cloneNode(false))
							}
							var ae = v.style;
							ae.width = X;
							ae.height = af;
							if (H) {
								var s = H[0], r = H[1];
								var B = Cufon.CSS.color(s.color), z;
								var N = document.createElement("cvml:shadow");
								N.on = "t";
								N.color = B.color;
								N.offset = s.offX + "," + s.offY;
								if (r) {
									z = Cufon.CSS.color(r.color);
									N.type = "double";
									N.color2 = z.color;
									N.offset2 = r.offX + "," + r.offY
								}
								N.opacity = B.opacity || (z && z.opacity) || 1;
								v.appendChild(N)
							}
							S += L[aa++]
						}
						var M = v.nextSibling, t, A;
						if (C.forceHitArea) {
							if (!M) {
								M = document.createElement("cvml:rect");
								M.stroked = "f";
								M.className = "cufon-vml-cover";
								t = document.createElement("cvml:fill");
								t.opacity = 0;
								M.appendChild(t);
								q.appendChild(M)
							}
							A = M.style;
							A.width = X;
							A.height = af
						} else {
							if (M) {
								q.removeChild(M)
							}
						}
						ai.width = Math.max(Math.ceil(p.convert(k * P)), 0);
						if (h) {
							var Q = Y.computedYAdjust;
							if (Q === undefined) {
								var E = Y.get("lineHeight");
								if (E == "normal") {
									E = "1em"
								} else {
									if (!isNaN(E)) {
										E += "em"
									}
								}
								Y.computedYAdjust = Q = 0.5 * (a(ad, E) - parseFloat(ai.height))
							}
							if (Q) {
								ai.marginTop = Math.ceil(Q) + "px";
								ai.marginBottom = Q + "px"
							}
						}
						return y
					}
				})());
Cufon
		.registerEngine(
				"canvas",
				(function() {
					var b = document.createElement("canvas");
					if (!b || !b.getContext || !b.getContext.apply) {
						return
					}
					b = null;
					var a = Cufon.CSS.supports("display", "inline-block");
					var e = !a
							&& (document.compatMode == "BackCompat" || /frameset|transitional/i
									.test(document.doctype.publicId));
					var f = document.createElement("style");
					f.type = "text/css";
					f
							.appendChild(document
									.createTextNode(("cufon{text-indent:0;}@media screen,projection{cufon{display:inline;display:inline-block;position:relative;vertical-align:middle;"
											+ (e ? ""
													: "font-size:1px;line-height:1px;")
											+ "}cufon cufontext{display:-moz-inline-box;display:inline-block;width:0;height:0;overflow:hidden;text-indent:-10000in;}"
											+ (a ? "cufon canvas{position:relative;}"
													: "cufon canvas{position:absolute;}") + "}@media print{cufon{padding:0;}cufon canvas{display:none;}}")
											.replace(/;/g, "!important;")));
					document.getElementsByTagName("head")[0].appendChild(f);
					function d(p, h) {
						var n = 0, m = 0;
						var g = [], o = /([mrvxe])([^a-z]*)/g, k;
						generate: for ( var j = 0; k = o.exec(p); ++j) {
							var l = k[2].split(",");
							switch (k[1]) {
							case "v":
								g[j] = {
									m : "bezierCurveTo",
									a : [ n + ~~l[0], m + ~~l[1], n + ~~l[2],
											m + ~~l[3], n += ~~l[4],
											m += ~~l[5] ]
								};
								break;
							case "r":
								g[j] = {
									m : "lineTo",
									a : [ n += ~~l[0], m += ~~l[1] ]
								};
								break;
							case "m":
								g[j] = {
									m : "moveTo",
									a : [ n = ~~l[0], m = ~~l[1] ]
								};
								break;
							case "x":
								g[j] = {
									m : "closePath"
								};
								break;
							case "e":
								break generate
							}
							h[g[j].m].apply(h, g[j].a)
						}
						return g
					}
					function c(m, k) {
						for ( var j = 0, h = m.length; j < h; ++j) {
							var g = m[j];
							k[g.m].apply(k, g.a)
						}
					}
					return function(V, w, P, t, C, W) {
						var k = (w === null);
						if (k) {
							w = C.getAttribute("alt")
						}
						var A = V.viewBox;
						var m = P.getSize("fontSize", V.baseSize);
						var B = 0, O = 0, N = 0, u = 0;
						var z = t.textShadow, L = [];
						if (z) {
							for ( var U = z.length; U--;) {
								var F = z[U];
								var K = m.convertFrom(parseFloat(F.offX));
								var I = m.convertFrom(parseFloat(F.offY));
								L[U] = [ K, I ];
								if (I < B) {
									B = I
								}
								if (K > O) {
									O = K
								}
								if (I > N) {
									N = I
								}
								if (K < u) {
									u = K
								}
							}
						}
						var Z = Cufon.CSS.textTransform(w, P).split("");
						var E = V.spacing(Z, ~~m.convertFrom(parseFloat(P
								.get("letterSpacing")) || 0),
								~~m
										.convertFrom(parseFloat(P
												.get("wordSpacing")) || 0));
						if (!E.length) {
							return null
						}
						var h = E.total;
						O += A.width - E[E.length - 1];
						u += A.minX;
						var s, n;
						if (k) {
							s = C;
							n = C.firstChild
						} else {
							s = document.createElement("cufon");
							s.className = "cufon cufon-canvas";
							s.setAttribute("alt", w);
							n = document.createElement("canvas");
							s.appendChild(n);
							if (t.printable) {
								var S = document.createElement("cufontext");
								S.appendChild(document.createTextNode(w));
								s.appendChild(S)
							}
						}
						var aa = s.style;
						var H = n.style;
						var j = m.convert(A.height);
						var Y = Math.ceil(j);
						var M = Y / j;
						var G = M * Cufon.CSS.fontStretch(P.get("fontStretch"));
						var J = h * G;
						var Q = Math.ceil(m.convert(J + O - u));
						var o = Math.ceil(m.convert(A.height - B + N));
						n.width = Q;
						n.height = o;
						H.width = Q + "px";
						H.height = o + "px";
						B += A.minY;
						H.top = Math.round(m.convert(B - V.ascent)) + "px";
						H.left = Math.round(m.convert(u)) + "px";
						var r = Math.max(Math.ceil(m.convert(J)), 0) + "px";
						if (a) {
							aa.width = r;
							aa.height = m.convert(V.height) + "px"
						} else {
							aa.paddingLeft = r;
							aa.paddingBottom = (m.convert(V.height) - 1) + "px"
						}
						var X = n.getContext("2d"), D = j / A.height;
						X.scale(D, D * M);
						X.translate(-u, -B);
						X.save();
						function T() {
							var x = V.glyphs, ab, l = -1, g = -1, y;
							X.scale(G, 1);
							while (y = Z[++l]) {
								var ab = x[Z[l]] || V.missingGlyph;
								if (!ab) {
									continue
								}
								if (ab.d) {
									X.beginPath();
									if (ab.code) {
										c(ab.code, X)
									} else {
										ab.code = d("m" + ab.d, X)
									}
									X.fill()
								}
								X.translate(E[++g], 0)
							}
							X.restore()
						}
						if (z) {
							for ( var U = z.length; U--;) {
								var F = z[U];
								X.save();
								X.fillStyle = F.color;
								X.translate.apply(X, L[U]);
								T()
							}
						}
						var q = t.textGradient;
						if (q) {
							var v = q.stops, p = X.createLinearGradient(0,
									A.minY, 0, A.maxY);
							for ( var U = 0, R = v.length; U < R; ++U) {
								p.addColorStop.apply(p, v[U])
							}
							X.fillStyle = p
						} else {
							X.fillStyle = P.get("color")
						}
						T();
						return s
					}
				})()); /*
						 * ! The following copyright notice may not be removed
						 * under any circumstances.
						 * 
						 * Copyright: The digitally encoded machine readable
						 * outline data for producing the Typefaces provided as
						 * part of your laser printer is copyrighted (c) 1981
						 * Linotype All Rights Reserved. This data is the
						 * property of Linotype and may not be reproduced, used,
						 * displayed, modified, disclosed or transferred without
						 * the express written approval of Linotype.
						 */
Cufon
		.registerFont({
			"w" : 200,
			"face" : {
				"font-family" : "Glypha",
				"font-weight" : 700,
				"font-stretch" : "normal",
				"units-per-em" : "360",
				"panose-1" : "2 0 8 3 0 0 0 0 0 0",
				"ascent" : "288",
				"descent" : "-72",
				"x-height" : "4",
				"bbox" : "-4 -294 359 63.6287",
				"underline-thickness" : "19.44",
				"underline-position" : "-35.28",
				"stemv" : "48",
				"unicode-range" : "U+0020-U+007E"
			},
			"glyphs" : {
				" " : {
					"w" : 100
				},
				"!" : {
					"d" : "42,-71r-10,-187r57,0r-10,187r-37,0xm36,0r0,-48r49,0r0,48r-49,0",
					"w" : 119
				},
				"\"" : {
					"d" : "118,-154r-26,0r-9,-104r45,0xm49,-154r-27,0r-9,-104r45,0",
					"w" : 144
				},
				"#" : {
					"d" : "141,-86r-12,86r-28,0r12,-86r-37,0r-12,86r-27,0r12,-86r-44,0r0,-27r48,0r6,-37r-43,0r0,-27r47,0r11,-81r27,0r-11,81r37,0r12,-81r27,0r-12,81r41,0r0,27r-45,0r-5,37r40,0r0,27r-44,0xm123,-150r-37,0r-5,37r36,0"
				},
				"$" : {
					"d" : "185,-173r-42,0v-1,-31,-11,-50,-30,-55r0,76v37,11,77,31,77,80v0,49,-31,75,-77,76r0,31r-27,0r0,-33v-15,-4,-29,-12,-36,-27r0,25r-40,0r0,-90r42,0v0,26,8,54,34,60r0,-82v-38,-14,-76,-28,-76,-76v0,-46,29,-75,76,-75r0,-31r27,0r0,35v10,3,21,8,32,22r0,-21r40,0r0,85xm86,-229v-15,2,-27,17,-27,33v0,19,12,28,27,34r0,-67xm113,-102r0,71v15,-4,27,-16,27,-35v0,-21,-11,-30,-27,-36"
				},
				"%" : {
					"d" : "214,-127v73,-1,69,129,3,131v-74,1,-67,-134,-3,-131xm215,-104v-19,0,-20,28,-20,42v0,14,1,43,21,43v19,0,20,-27,20,-41v0,-15,0,-44,-21,-44xm93,4r-30,0r143,-266r30,0xm33,-194v0,-32,16,-68,49,-68v39,0,53,30,53,65v0,32,-12,67,-50,67v-36,0,-52,-32,-52,-64xm84,-239v-19,0,-20,29,-20,42v0,13,2,44,20,44v19,0,21,-30,21,-44v0,-14,-2,-42,-21,-42",
					"w" : 299
				},
				"&" : {
					"d" : "104,-168v14,-8,31,-23,31,-40v0,-17,-9,-29,-27,-29v-41,2,-29,53,-4,69xm175,-208v0,34,-23,54,-45,69r52,57v8,-13,12,-28,13,-43r-22,0r0,-30r87,0r0,30r-28,0v-2,25,-10,50,-24,71r19,22r37,0r0,32r-67,0v-7,-5,-14,-24,-22,-18v-57,44,-158,22,-158,-56v0,-36,21,-58,51,-74v-18,-13,-31,-35,-31,-59v0,-40,35,-59,71,-59v37,0,67,18,67,58xm95,-117v-41,19,-40,92,16,90v14,0,34,-9,43,-20",
					"w" : 280
				},
				"(" : {
					"d" : "69,-269r36,0v-60,98,-58,225,0,322r-36,0v-29,-50,-51,-106,-51,-165v0,-56,24,-109,51,-157",
					"w" : 119
				},
				")" : {
					"d" : "98,-112v0,61,-22,114,-52,165r-36,0v60,-97,59,-226,0,-322r36,0v28,46,52,102,52,157",
					"w" : 119
				},
				"*" : {
					"d" : "63,-188r-44,-6r11,-39r39,23r-6,-48r35,0r-7,49r40,-22r11,37r-44,6r31,35r-29,23r-20,-43r-20,43r-30,-23",
					"w" : 159
				},
				"+" : {
					"d" : "27,-145r75,0r0,-75r33,0r0,75r75,0r0,33r-75,0r0,75r-33,0r0,-75r-75,0r0,-33",
					"w" : 237
				},
				"," : {
					"d" : "26,-48r49,0v3,41,-6,69,-17,96r-33,0r19,-48r-18,0r0,-48",
					"w" : 100
				},
				"-" : {
					"d" : "23,-84r0,-36r88,0r0,36r-88,0",
					"w" : 133
				},
				"." : {
					"d" : "75,0r-49,0r0,-48r49,0r0,48",
					"w" : 100
				},
				"\/" : {
					"d" : "38,0r-33,0r95,-258r33,0",
					"w" : 140
				},
				"0" : {
					"d" : "101,-29v51,-10,37,-88,35,-144v-1,-23,-8,-60,-36,-57v-42,5,-38,65,-38,108v0,41,-1,89,39,93xm187,-127v-1,64,-20,129,-88,131v-64,2,-86,-71,-86,-137v0,-58,16,-129,88,-129v68,0,87,70,86,135"
				},
				"1" : {
					"d" : "31,-168r0,-44v21,-14,41,-29,60,-46r47,0r0,226r33,0r0,32r-118,0r0,-32r35,0r0,-178v-17,15,-38,28,-57,42"
				},
				"2" : {
					"d" : "180,-192v-8,75,-78,114,-120,160r83,0r0,-38r40,0r0,70r-170,0r0,-47r93,-96v25,-22,40,-86,-8,-86v-26,0,-36,24,-36,47r-49,0v2,-52,33,-80,85,-80v43,0,86,28,82,70"
				},
				"3" : {
					"d" : "185,-70v2,48,-37,75,-86,74v-49,0,-86,-25,-86,-77r50,0v1,24,11,43,38,43v20,0,34,-21,34,-42v0,-28,-19,-52,-53,-44r0,-32v30,1,50,-11,50,-43v0,-20,-11,-39,-33,-39v-25,0,-35,22,-35,44r-49,0v-4,-98,162,-103,166,-9v1,35,-20,56,-52,63v32,1,55,26,56,62"
				},
				"4" : {
					"d" : "114,-214r-71,117r71,0r0,-117xm114,-66r-106,0r0,-45r95,-147r59,0r0,161r28,0r0,31r-28,0r0,35r26,0r0,31r-108,0r0,-31r34,0r0,-35"
				},
				"5" : {
					"d" : "18,-69r50,0v0,21,10,39,33,39v29,0,38,-24,38,-49v0,-24,-6,-55,-37,-55v-19,0,-31,12,-34,30r-47,0r3,-154r152,0r0,35r-107,0r-1,80v38,-56,120,-10,120,57v0,55,-32,90,-88,90v-46,0,-82,-25,-82,-73"
				},
				"6" : {
					"d" : "188,-83v0,48,-36,87,-84,87v-75,0,-90,-65,-90,-127v0,-66,20,-139,99,-139v41,0,71,25,72,67r-47,0v0,-17,-10,-36,-29,-36v-44,0,-48,68,-48,101v10,-21,28,-37,52,-37v51,0,75,36,75,84xm105,-26v47,-2,52,-106,-1,-108v-53,3,-56,106,1,108"
				},
				"7" : {
					"d" : "56,-179r-42,0r0,-79r175,0v4,66,-29,97,-47,147r-40,111r-53,0r46,-122r46,-101r-85,0r0,44"
				},
				"8" : {
					"d" : "135,-134v34,5,50,33,50,65v0,51,-36,73,-84,73v-50,0,-86,-21,-86,-75v0,-32,20,-58,53,-63v-31,-7,-50,-27,-50,-59v0,-49,37,-69,82,-69v46,0,83,20,83,70v0,29,-20,53,-48,58xm100,-232v-24,0,-36,17,-36,40v0,23,11,42,36,42v26,0,36,-19,36,-43v0,-22,-13,-39,-36,-39xm101,-27v24,0,38,-23,38,-48v0,-23,-13,-45,-39,-45v-28,0,-38,23,-38,48v0,24,13,45,39,45"
				},
				"9" : {
					"d" : "186,-134v0,67,-29,138,-99,138v-41,0,-70,-24,-72,-66r48,0v1,19,9,36,30,36v43,0,45,-61,47,-102v-10,24,-29,38,-55,38v-44,0,-72,-41,-72,-85v0,-49,35,-89,84,-87v67,3,89,58,89,128xm98,-231v-27,-1,-38,27,-38,55v0,24,8,53,37,53v55,0,52,-107,1,-108"
				},
				":" : {
					"d" : "84,-48r0,48r-49,0r0,-48r49,0xm35,-134r0,-47r49,0r0,47r-49,0",
					"w" : 119
				},
				";" : {
					"d" : "36,0r0,-48r49,0v3,41,-6,69,-17,96r-33,0r20,-48r-19,0xm36,-134r0,-47r49,0r0,47r-49,0",
					"w" : 119
				},
				"<" : {
					"d" : "214,-57r0,33r-194,-89r0,-32r194,-89r0,33r-162,72",
					"w" : 237
				},
				"=" : {
					"d" : "27,-174r183,0r0,34r-183,0r0,-34xm27,-117r183,0r0,33r-183,0r0,-33",
					"w" : 237
				},
				">" : {
					"d" : "20,-57r162,-72r-162,-72r0,-33r194,89r0,32r-194,89r0,-33",
					"w" : 237
				},
				"?" : {
					"d" : "89,-230v-20,0,-32,15,-31,37r-48,0v1,-44,33,-69,79,-69v83,0,99,103,40,141v-20,20,-24,23,-24,54r-48,0v-12,-61,60,-77,60,-128v0,-18,-7,-35,-28,-35xm105,-48r0,48r-48,0r0,-48r48,0",
					"w" : 180
				},
				"@" : {
					"d" : "168,-241v-61,0,-115,51,-115,113v0,109,144,148,211,74r7,16v-76,88,-240,29,-240,-88v0,-75,63,-136,137,-136v63,0,118,43,118,108v0,48,-32,95,-77,96v-16,0,-23,-7,-25,-22v-27,41,-97,15,-91,-36v-5,-53,77,-120,113,-63r4,-11r25,0r-29,101v0,7,3,11,10,11v30,-4,50,-40,50,-72v0,-57,-43,-91,-98,-91xm148,-84v33,-1,46,-37,46,-69v0,-14,-8,-24,-23,-24v-30,0,-50,36,-50,62v0,17,9,31,27,31",
					"w" : 316
				},
				"A" : {
					"d" : "101,-111r71,0r-36,-105xm104,0r-98,0r0,-32r20,0r85,-226r59,0r84,226r20,0r0,32r-104,0r0,-32r31,0r-16,-47r-96,0r-17,47r32,0r0,32",
					"w" : 280
				},
				"B" : {
					"d" : "222,-68v0,47,-48,68,-100,68r-114,0r0,-33r30,0r0,-192r-30,0r0,-33r130,0v42,-5,79,26,79,66v0,33,-18,54,-51,59v35,1,56,32,56,65xm167,-188v0,-39,-39,-40,-80,-37r0,77v43,3,80,-1,80,-40xm173,-75v0,-39,-41,-45,-86,-41r0,84v45,3,86,-2,86,-43",
					"w" : 240
				},
				"C" : {
					"d" : "241,-165r-43,0v-2,-37,-20,-64,-60,-64v-54,0,-68,54,-68,99v0,43,12,101,66,101v34,0,58,-23,58,-57r50,0v-1,62,-50,90,-107,90v-84,0,-120,-58,-120,-136v0,-52,20,-86,36,-102v32,-33,114,-41,148,0r0,-24r40,0r0,93",
					"w" : 259
				},
				"D" : {
					"d" : "188,-73v29,-67,2,-160,-74,-152r-25,0r0,193v48,0,79,4,99,-41xm123,-258v72,-6,126,48,126,127v0,37,-7,76,-36,102v-50,45,-123,24,-204,29r0,-32r30,0r0,-193r-30,0r0,-33r114,0",
					"w" : 266
				},
				"E" : {
					"d" : "222,0r-213,0r0,-32r30,0r0,-193r-30,0r0,-33r213,0r0,79r-40,0r0,-46r-94,0r0,78r38,0r0,-33r34,0r0,98r-34,0r0,-32r-38,0r0,82r94,0r0,-50r40,0r0,82",
					"w" : 240
				},
				"F" : {
					"d" : "126,0r-116,0r0,-32r30,0r0,-193r-30,0r0,-33r205,0r0,83r-39,0r0,-50r-87,0r0,83r34,0r0,-33r34,0r0,100r-34,0r0,-34r-34,0r0,77r37,0r0,32",
					"w" : 226
				},
				"G" : {
					"d" : "16,-126v3,-77,28,-136,110,-136v31,0,57,7,76,32r0,-28r41,0r0,92r-44,0v-1,-37,-24,-60,-61,-60v-92,2,-90,195,2,195v39,0,62,-29,62,-66r-34,0r0,-32r104,0r0,32r-26,0r0,97r-42,0r0,-30v-15,24,-45,34,-73,34v-72,0,-117,-66,-115,-130",
					"w" : 280
				},
				"H" : {
					"d" : "119,0r-110,0r0,-32r29,0r0,-193r-29,0r0,-33r110,0r0,33r-32,0r0,78r105,0r0,-78r-31,0r0,-33r110,0r0,33r-30,0r0,193r30,0r0,32r-110,0r0,-32r31,0r0,-82r-105,0r0,82r32,0r0,32",
					"w" : 280
				},
				"I" : {
					"d" : "39,-225r-32,0r0,-33r113,0r0,33r-32,0r0,193r32,0r0,32r-113,0r0,-32r32,0r0,-193",
					"w" : 126
				},
				"J" : {
					"d" : "157,-86v9,57,-25,90,-75,90v-48,0,-79,-33,-74,-87r50,0v1,21,-3,54,27,54v26,0,23,-35,23,-52r0,-144r-35,0r0,-33r108,0r0,33r-24,0r0,139",
					"w" : 186
				},
				"K" : {
					"d" : "116,0r-108,0r0,-32r29,0r0,-193r-29,0r0,-33r108,0r0,33r-29,0r0,86r75,-86r-24,0r0,-33r100,0r0,33r-20,0r-78,86r83,107r20,0r0,32r-103,0r0,-32r26,0r-79,-102r0,102r29,0r0,32",
					"w" : 246
				},
				"L" : {
					"d" : "208,0r-199,0r0,-32r30,0r0,-193r-30,0r0,-33r118,0r0,33r-39,0r0,193r77,0r0,-59r43,0r0,91",
					"w" : 219
				},
				"M" : {
					"d" : "331,-32r0,32r-103,0r0,-32r27,0r0,-193r-66,225r-45,0r-64,-225r0,193r25,0r0,32r-96,0r0,-32r29,0r0,-193r-29,0r0,-33r106,0r54,200r54,-200r108,0r0,33r-29,0r0,193r29,0",
					"w" : 339
				},
				"N" : {
					"d" : "111,0r-102,0r0,-32r30,0r0,-193r-30,0r0,-33r96,0r96,203r0,-170r-30,0r0,-33r102,0r0,33r-29,0r0,225r-63,0r-99,-208r0,176r29,0r0,32",
					"w" : 280
				},
				"O" : {
					"d" : "210,-131v0,-49,-21,-99,-70,-99v-48,0,-71,50,-71,99v0,45,14,103,71,103v50,0,70,-48,70,-103xm16,-124v0,-74,50,-138,123,-138v81,0,125,57,125,132v0,80,-51,134,-124,134v-72,0,-124,-59,-124,-128",
					"w" : 280
				},
				"P" : {
					"d" : "215,-179v0,49,-39,88,-92,80r-33,0r0,67r37,0r0,32r-117,0r0,-32r30,0r0,-194r-30,0r0,-32r122,0v46,-6,83,34,83,79xm164,-179v0,-38,-31,-52,-74,-46r0,93v44,5,74,-8,74,-47",
					"w" : 226
				},
				"Q" : {
					"d" : "135,-262v125,0,162,172,72,234v20,-4,41,-4,64,-4r0,34r-139,2v-75,2,-116,-61,-116,-131v0,-71,48,-135,119,-135xm204,-125v0,-48,-18,-105,-67,-105v-49,0,-70,55,-69,103v0,44,14,99,68,99v50,0,68,-56,68,-97",
					"w" : 286
				},
				"R" : {
					"d" : "167,-186v0,-37,-36,-44,-79,-39r0,84v44,4,79,-6,79,-45xm174,0v-15,-31,-10,-115,-61,-109r-25,0r0,77r30,0r0,32r-110,0r0,-32r31,0r0,-194r-31,0r0,-32r124,0v45,-6,85,26,85,70v0,35,-21,61,-56,65v48,2,41,55,56,91r22,0r0,32r-65,0",
					"w" : 246
				},
				"S" : {
					"d" : "74,-118v-80,-12,-79,-144,16,-144v22,0,47,5,60,26r0,-22r40,0r0,85r-42,0v1,-33,-13,-55,-45,-57v-22,-2,-44,15,-39,37v15,66,132,26,132,123v0,76,-102,100,-142,46r0,24r-40,0r0,-90r42,0v0,31,12,61,47,61v23,0,42,-12,42,-37v-1,-38,-44,-39,-71,-52",
					"w" : 206
				},
				"T" : {
					"d" : "55,-170r-42,0r0,-88r214,0r0,88r-42,0r0,-54r-40,0r0,192r34,0r0,32r-119,0r0,-32r35,0r0,-192r-40,0r0,54",
					"w" : 240
				},
				"U" : {
					"d" : "128,4v-62,0,-97,-42,-97,-108r0,-121r-26,0r0,-33r108,0r0,33r-32,0v6,80,-26,195,52,195v78,0,44,-119,51,-195r-32,0r0,-33r103,0r0,33r-26,0r0,114v7,70,-34,115,-101,115",
					"w" : 259
				},
				"V" : {
					"d" : "6,-225r0,-33r104,0r0,33r-33,0r59,172r59,-172r-32,0r0,-33r99,0r0,33r-19,0r-82,225r-55,0r-81,-225r-19,0",
					"w" : 266
				},
				"W" : {
					"d" : "10,-225r0,-33r98,0r0,33r-32,0r37,172r46,-205r58,0r42,205r41,-172r-33,0r0,-33r92,0r0,33r-16,0r-54,225r-59,0r-43,-205r-48,205r-61,0r-51,-225r-17,0",
					"w" : 360
				},
				"X" : {
					"d" : "103,0r-99,0r0,-32r19,0r80,-100r-76,-93r-18,0r0,-33r104,0r0,33r-29,0r54,69r52,-69r-29,0r0,-33r96,0r0,33r-18,0r-76,93r81,100r18,0r0,32r-106,0r0,-32r31,0r-59,-76r-57,76r32,0r0,32",
					"w" : 266
				},
				"Y" : {
					"d" : "5,-225r0,-33r103,0r0,33r-28,0r46,82r48,-82r-26,0r0,-33r94,0r0,33r-17,0r-77,126r0,67r36,0r0,32r-121,0r0,-32r35,0r0,-67r-76,-126r-17,0",
					"w" : 246
				},
				"Z" : {
					"d" : "215,0r-204,0r0,-40r143,-186r-99,0r0,51r-40,0r0,-83r196,0r0,40r-145,187r108,0r0,-54r41,0r0,85",
					"w" : 226
				},
				"[" : {
					"d" : "92,53r-69,0r0,-322r69,0r0,30r-28,0r0,262r28,0r0,30",
					"w" : 119
				},
				"\\" : {
					"d" : "149,0r-110,-258r36,0r110,258r-36,0",
					"w" : 223
				},
				"]" : {
					"d" : "97,53r-69,0r0,-30r28,0r0,-262r-28,0r0,-30r69,0r0,322",
					"w" : 119
				},
				"^" : {
					"d" : "46,-63r-32,0r89,-195r31,0r90,195r-33,0r-73,-160",
					"w" : 237
				},
				"_" : {
					"d" : "180,47r-180,0r0,-24r180,0r0,24",
					"w" : 180
				},
				"a" : {
					"d" : "98,-196v45,0,77,27,77,80r0,86r24,0r0,30r-66,0r0,-31v-11,24,-34,35,-60,35v-38,1,-61,-27,-61,-59v0,-53,58,-68,116,-61v3,-23,0,-51,-30,-51v-21,0,-31,14,-31,34r-48,0v1,-47,37,-63,79,-63xm130,-89v-35,0,-69,-3,-70,35v0,15,10,27,25,27v35,0,44,-34,45,-62",
					"w" : 206
				},
				"b" : {
					"d" : "215,-100v0,51,-4,104,-77,104v-19,0,-45,-6,-60,-36r0,32r-70,0r0,-31r25,0r0,-200r-25,0r0,-31r73,0r0,99v12,-24,31,-33,57,-33v56,0,77,46,77,96xm123,-164v-29,1,-43,34,-43,66v0,28,6,70,43,70v36,0,42,-39,42,-67v0,-28,-6,-69,-42,-69",
					"w" : 226
				},
				"c" : {
					"d" : "11,-98v1,-46,23,-99,79,-98v23,0,43,5,53,27r0,-23r37,0r0,72r-39,0v-1,-22,-10,-44,-36,-44v-38,0,-43,44,-43,73v0,28,9,64,43,64v21,0,33,-18,35,-37r41,0v-2,47,-35,68,-80,68v-62,0,-90,-45,-90,-102",
					"w" : 186
				},
				"d" : {
					"d" : "89,4v-60,-11,-78,-28,-78,-104v0,-49,23,-96,78,-96v23,0,44,8,57,28r0,-63r-25,0r0,-31r73,0r0,231r25,0r0,31r-70,0r0,-31v-15,25,-36,34,-60,35xm147,-98v0,-31,-13,-66,-43,-66v-35,0,-42,42,-42,69v0,28,6,67,41,67v37,0,44,-41,44,-70",
					"w" : 226
				},
				"e" : {
					"d" : "102,4v-58,0,-91,-47,-90,-102v0,-53,30,-98,88,-98v26,0,50,5,67,25v19,22,23,52,22,85r-128,0v0,27,11,59,44,59v19,0,32,-13,35,-31r46,0v-3,42,-45,62,-84,62xm62,-114r78,0v0,-22,-9,-52,-36,-52v-30,0,-40,27,-42,52"
				},
				"f" : {
					"d" : "132,-231v-31,-10,-54,5,-48,39r33,0r0,31r-33,0r0,130r32,0r0,31r-106,0r0,-31r26,0r0,-130r-28,0r0,-31r28,0v-10,-61,42,-89,96,-72r0,33",
					"w" : 126
				},
				"g" : {
					"d" : "12,-102v1,-51,26,-94,77,-94v24,0,46,9,60,30r0,-26r71,0r0,31r-25,0r0,147v6,49,-42,77,-90,77v-38,0,-81,-11,-82,-51r48,0v9,31,62,27,73,-2v5,-15,3,-30,3,-45v-13,24,-31,33,-57,33v-51,0,-80,-46,-78,-100xm103,-33v63,-4,58,-132,2,-132v-36,0,-43,41,-43,69v1,28,12,65,41,63",
					"w" : 226
				},
				"h" : {
					"d" : "120,-163v-52,0,-35,78,-38,132r24,0r0,31r-96,0r0,-31r25,0r0,-200r-25,0r0,-31r72,0r0,95v29,-52,114,-31,114,33r0,103r24,0r0,31r-95,0r0,-31r23,0v-6,-48,19,-132,-28,-132",
					"w" : 226
				},
				"i" : {
					"d" : "109,0r-96,0r0,-31r26,0r0,-130r-26,0r0,-31r74,0r0,161r22,0r0,31xm39,-265r48,0r0,44r-48,0r0,-44",
					"w" : 119
				},
				"j" : {
					"d" : "6,-192r74,0r0,200v0,13,0,26,-6,38v-18,21,-48,18,-76,14r0,-31v24,5,34,-7,34,-29r0,-161r-26,0r0,-31xm80,-265r0,44r-48,0r0,-44r48,0",
					"w" : 106
				},
				"k" : {
					"d" : "102,0r-94,0r0,-31r25,0r0,-200r-25,0r0,-31r73,0r0,156r63,-56r-28,0r0,-30r94,0r0,31r-22,0r-45,39r51,91r19,0r0,31r-88,0r0,-29r21,0r-35,-62r-30,25r0,37r21,0r0,29",
					"w" : 219
				},
				"l" : {
					"d" : "109,0r-96,0r0,-31r25,0r0,-200r-25,0r0,-31r73,0r0,231r23,0r0,31",
					"w" : 119
				},
				"m" : {
					"d" : "123,-161v-54,0,-36,76,-39,130r23,0r0,31r-96,0r0,-31r25,0r0,-130r-25,0r0,-31r70,0r0,32v18,-45,98,-52,113,0v23,-61,115,-40,115,30r0,99r24,0r0,31r-94,0r0,-31r22,0r0,-85v0,-19,-2,-45,-28,-45v-50,0,-34,77,-36,130r22,0r0,31r-92,0r0,-31r22,0v-5,-48,18,-130,-26,-130",
					"w" : 339
				},
				"n" : {
					"d" : "148,-31v-6,-49,19,-127,-28,-132v-58,5,-31,79,-37,132r23,0r0,31r-96,0r0,-31r25,0r0,-130r-25,0r0,-31r70,0r0,32v12,-24,31,-36,58,-36v81,-1,54,90,58,165r24,0r0,31r-94,0r0,-31r22,0",
					"w" : 226
				},
				"o" : {
					"d" : "104,-27v59,-3,58,-135,0,-138v-64,7,-58,135,0,138xm10,-97v0,-53,40,-99,92,-99v62,0,95,43,95,101v0,55,-34,99,-93,99v-53,0,-94,-46,-94,-101",
					"w" : 206
				},
				"p" : {
					"d" : "215,-98v13,79,-87,144,-134,69r0,57r27,0r0,30r-100,0r0,-30r25,0r0,-189r-25,0r0,-31r70,0r0,33v40,-73,146,-30,137,61xm124,-165v-32,0,-46,42,-46,70v0,27,11,67,45,67v35,0,43,-38,43,-65v0,-27,-6,-72,-42,-72",
					"w" : 226
				},
				"q" : {
					"d" : "12,-98v3,-52,17,-98,75,-98v30,0,48,10,61,37r0,-33r70,0r0,31r-25,0r0,189r25,0r0,30r-100,0r0,-30r27,0r0,-57v-12,22,-30,33,-54,33v-51,0,-82,-50,-79,-102xm147,-95v0,-28,-8,-70,-44,-70v-36,0,-42,44,-42,71v0,27,8,66,43,66v34,0,43,-40,43,-67",
					"w" : 226
				},
				"r" : {
					"d" : "144,-148v-62,-17,-65,51,-61,117r31,0r0,31r-104,0r0,-31r25,0r0,-130r-25,0r0,-31r70,0r0,41v8,-33,31,-47,64,-44r0,47",
					"w" : 146
				},
				"s" : {
					"d" : "153,-96v32,41,6,100,-55,100v-18,0,-37,-8,-47,-23r0,19r-34,0r0,-67r37,0v-7,41,58,58,64,17v-4,-36,-54,-23,-77,-41v-47,-37,-22,-105,41,-105v17,0,35,7,45,20r0,-16r34,0r0,62r-35,0v-2,-21,-10,-37,-33,-37v-27,-6,-44,36,-13,44v21,5,59,9,73,27",
					"w" : 180
				},
				"t" : {
					"d" : "87,4v-76,4,-46,-95,-52,-165r-29,0r0,-31r29,0r0,-32r47,-16r0,48r48,0r0,31r-48,0r0,120v0,4,2,11,6,12v22,9,20,-21,20,-34r30,0v4,39,-11,66,-51,67",
					"w" : 146
				},
				"u" : {
					"d" : "108,-31v49,-4,32,-78,35,-130r-26,0r0,-31r74,0r0,161r25,0r0,31r-70,0r0,-32v-10,24,-31,36,-57,36v-77,1,-55,-90,-58,-165r-24,0r0,-31r72,0r0,115v-2,22,6,48,29,46",
					"w" : 226
				},
				"v" : {
					"d" : "5,-161r0,-31r90,0r0,31r-23,0r36,122r40,-122r-24,0r0,-31r84,0r0,31r-15,0r-56,161r-60,0r-56,-161r-16,0",
					"w" : 213
				},
				"w" : {
					"d" : "6,-192r92,0r0,31r-26,0r28,124r40,-155r59,0r36,155r32,-124r-24,0r0,-31r85,0r0,31r-18,0r-46,161r-61,0r-34,-144r-39,144r-61,0r-46,-161r-17,0r0,-31",
					"w" : 333
				},
				"x" : {
					"d" : "122,0r0,-29r21,0r-40,-53r-41,53r21,0r0,29r-79,0r0,-31r15,0r55,-68r-48,-62r-18,0r0,-31r86,0r0,29r-20,0r37,47r36,-47r-20,0r0,-29r79,0r0,31r-17,0r-51,62r55,68r15,0r0,31r-86,0",
					"w" : 213
				},
				"y" : {
					"d" : "5,-161r0,-31r92,0r0,31r-25,0r39,106r37,-106r-24,0r0,-31r85,0r0,31r-15,0r-69,173v-12,40,-37,59,-87,49r0,-33v27,4,43,-2,49,-25r-66,-164r-16,0",
					"w" : 213
				},
				"z" : {
					"d" : "170,0r-154,0r0,-37r101,-126r-62,0r0,31r-36,0r0,-60r151,0r0,36r-102,127r66,0r0,-35r36,0r0,64",
					"w" : 186
				},
				"{" : {
					"d" : "13,-109v55,-8,7,-107,39,-147v12,-15,35,-13,53,-13v-64,13,11,141,-59,160v70,9,-8,138,59,162v-33,5,-71,-14,-64,-50v-3,-47,13,-105,-28,-112",
					"w" : 115
				},
				"|" : {
					"d" : "102,0r0,-258r33,0r0,258r-33,0",
					"w" : 237
				},
				"}" : {
					"d" : "105,-109v-68,12,25,175,-92,162v65,-12,-12,-143,59,-162v-69,-9,7,-136,-59,-159v17,0,39,-2,53,12v32,35,-18,134,39,147",
					"w" : 115
				},
				"~" : {
					"d" : "195,-161r26,16v-19,43,-69,53,-113,27v-27,-16,-56,-6,-66,22r-26,-17v14,-22,31,-45,61,-45v31,0,65,24,86,24v16,0,24,-16,32,-27",
					"w" : 237
				},
				"'" : {
					"d" : "49,-154r-27,0r-9,-104r45,0",
					"w" : 72
				},
				"`" : {
					"d" : "-4,-272r49,0r32,52r-29,0",
					"w" : 100
				},
				"\u00a0" : {
					"w" : 100
				}
			}
		});
