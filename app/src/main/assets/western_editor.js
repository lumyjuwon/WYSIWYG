"use strict";

var WE = {};
var callback;	// 테스트용

WE.editor = document.getElementById('editor');

WE.selectionchange = function () {
	var items = {};

	items.fontName = document.queryCommandValue('fontName');
	items.fontSize = document.queryCommandValue('fontSize');
	items.bold = document.queryCommandState('bold');
	items.underline = document.queryCommandState('underline');
	items.italic = document.queryCommandState('italic');
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

	// for test
	/*state = '';
	for (var i in items) state += i + ': ' + items[i] + ', ';*/

	// location.href = 'we-state://' + encodeURI(JSON.stringify(items));
};

document.addEventListener('selectionchange', WE.selectionchange);   // 커서 이동 시 이벤트

WE.exec = function (cmd, val) { // execCommand로 동작하는 기능 처리
	val = (typeof(val) !== 'undefined') ? val : null;
	document.execCommand("styleWithCSS", null, (val !== null));
	document.execCommand(cmd, false, val);
    WE.editor.focus();
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