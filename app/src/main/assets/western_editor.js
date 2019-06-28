"use strict";

var WE = {};

WE.editor = document.getElementById('editor');

WE.selectionchange = function () {
	let items = {};

	items.fontName = document.queryCommandValue('fontName');
	items.fontSize = document.queryCommandValue('fontSize');
	items.bold = document.queryCommandState('bold');
	items.italic = document.queryCommandState('italic');
	items.underline = document.queryCommandState('underline');
	items.strikeThrough = document.queryCommandState('strikeThrough');
	items.superscript = document.queryCommandState('superscript');
	items.subscript = document.queryCommandState('subscript');
	items.foreColor = document.queryCommandValue('foreColor');
	items.backColor = WE.rgb2rgba(document.queryCommandValue('backColor'));
	items.justifyLeft = document.queryCommandState('justifyLeft');
	items.justifyCenter = document.queryCommandState('justifyCenter');
	items.justifyRight = document.queryCommandState('justifyRight');
	items.justifyFull = document.queryCommandState('justifyFull');
	items.insertunorderedList = document.queryCommandState('insertUnorderedList');
	items.insertorderedList = document.queryCommandState('insertOrderedList');
	items.lineHeight = getComputedStyle(getSelection().getRangeAt(0).startContainer.parentNode, 'line-height').getPropertyValue('line-height');

	location.href = 'we-state://' + encodeURI(JSON.stringify(items));
};

document.addEventListener('selectionchange', WE.selectionchange);	// 커서 이동 시 이벤트

WE.exec = function (cmd, val) { // execCommand로 동작하는 기능 처리
	val = (typeof(val) !== 'undefined') ? val : null;
	document.execCommand("styleWithCSS", null, (val !== null));
	document.execCommand(cmd, false, val);
	WE.editor.blur();
	WE.editor.focus();
};

WE.insertCss = function (property, value) {	// 블록 지정한 영역에 css 속성을 추가하는 함수
	let select = getSelection();
	let tag = select.getRangeAt(0).startContainer;
	if (tag.parentNode.id != 'editor') {
		tag = tag.parentNode;
	}
	if (select.toString() == tag.textContent) {	// 선택한 영역과 부모 태그가 동일하면 부모 태그에 속성 추가
		if (tag.style[property] != value) {
			tag.style[property] = value;
		} else {
			tag.style.removeProperty(property);
		}
		if (tag.style.length == 0) {	// style 속성이 없으면 속성 삭제
			tag.removeAttribute('style');
			if (tag.tagName == 'SPAN') {	// 빈 span 태그 삭제
				WE.editor.innerHTML = WE.editor.innerHTML.replace(/<span>(.*?)<\/span>/g, '$1');
			}
		}
	} else {	// 선택한 영역과 부모 태그가 다르면 span 태그로 감싸고 속성 추가
		document.execCommand("styleWithCSS", null, false);
		document.execCommand('foreColor', false, 'rgb(1, 1, 1)');	// 이러면 font 태그가 붙음
		WE.editor.innerHTML = WE.editor.innerHTML.replace(/<font .+?>(.*?)<\/font>/g, '<span style="' + property + ': ' + value + '">$1</span>');	// font를 span로 바꿔치기...
	}
	WE.editor.blur();
	WE.editor.focus();
};

WE.lineHeight = function (height) {
	let tag = getSelection().getRangeAt(0).startContainer;
	let tagOrigin = tag;
	while (tag.tagName != 'DIV') {	// 해당 줄 선택
		tag = tag.parentNode;
	}
	if (tag.id == 'editor') {	// 만약 텍스트가 div로 안 묶여있다면
		let node = null;
		let childs = tag.childNodes;
		for (let i = 0; i < childs.length; i++) {
			if (childs[i].nodeType == 3 || (childs[i].nodeType == 1 && childs[i].tagName != 'DIV')) {
				if (node == null) {
					node = document.createElement('div');
					WE.editor.insertBefore(node, childs[i]);
					node.appendChild(childs[i + 1]);
				} else {
					node.appendChild(childs[i--]);
				}
			}
		}
	}
	tag = tagOrigin;
	while (tag.tagName != 'DIV') {	// 해당 줄 선택
		tag = tag.parentNode;
	}
	WE.selectElement(tag);
	WE.insertCss('line-height', height);
};

WE.rgb2rgba = function (rgba) { // rgb를 rgba로 변환
	if (rgba.substring(0, 4) == 'rgb(') {
		rgba = rgba.replace('rgb', 'rgba').replace(')', ', 1)');
	}
	return rgba;
};

WE.submit = function () {
	location.href = 'we-callback://' + encodeURI(wE.editor.innerHTML);
};

WE.selectElement = function (el) {	// 특정 엘리먼트 선택
	let range = document.createRange();
	range.selectNodeContents(el);
	let sel = getSelection();
	sel.removeAllRanges();
	sel.addRange(range);
};

//Input area settings, not tested 
WE.setPlaceholder = function(placeholder) {
    WE.editor.setAttribute("placeholder", placeholder);
}

WE.setBaseTextColor = function(color) {
    WE.editor.style.color  = color;
}

WE.setBaseFontSize = function(size) {
    WE.editor.style.fontSize = size;
}

WE.setPadding = function(left, top, right, bottom) {
  WE.editor.style.paddingLeft = left;
  WE.editor.style.paddingTop = top;
  WE.editor.style.paddingRight = right;
  WE.editor.style.paddingBottom = bottom;
}

WE.setBackgroundColor = function(color) {
    document.body.style.backgroundColor = color;
}

WE.setBackgroundImage = function(image) {
    WE.editor.style.backgroundImage = image;
}

WE.setWidth = function(size) {
    WE.editor.style.minWidth = size;
}

WE.setHeight = function(size) {
    WE.editor.style.height = size;
}

WE.setTextAlign = function(align) {
    WE.editor.style.textAlign = align;
}

WE.setVerticalAlign = function(align) {
    WE.editor.style.verticalAlign = align;
}
