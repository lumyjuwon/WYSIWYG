"use strict";

var WE = {};
var callback;	// 테스트용

WE.editor = document.getElementById('editor');

WE.selectionchange = function () {
	let items = {};

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

	// location.href = 'we-state://' + encodeURI(JSON.stringify(items));

	// 테스트용
	callback = '';
	for (let i in items) {
		callback += i + ': ' + items[i] + ', ';
	}
};

document.addEventListener('selectionchange', WE.selectionchange);   // 커서 이동 시 이벤트

WE.exec = function (cmd, val) { // 기능 실행
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
