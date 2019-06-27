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

	location.href = 'we-state://' + encodeURI(JSON.stringify(items));

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

WE.insertCss = function (property, value) {
	let select = getSelection();
	let tag = select.getRangeAt(0).startContainer.parentNode;
	if (select.toString() == tag.textContent) {	// 선택한 영역과 부모 태그가 동일하면 부모 태그에 속성 추가
		if (tag.style[property] != value) {
			tag.style[property] = value;
		} else {
			tag.style.removeProperty(property)
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
