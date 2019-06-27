# Android Editor

### DEMO

***

움짤 넣든지 동영상 넣든지



### AndroidX

***

This editor uses AndroidX. 

You can migrate your project to AndroidX. (How to migrate :  [Here](https://developer.android.com/jetpack/androidx/migrate) )



### Functions

***

-[x] Text Size

-[x] Text Color

-[x] Text Background Color

-[x] Text Bold

-[x] Text Italic

-[x] Subscript

-[x] Superscript

-[x] Text Underline

-[x] Text Strike Through

-[x] Content Align (left, middle, right)

-[x] Insert Image

-[x] Insert Youtube

-[x] Undo

-[x] Redo

-[x] Indent

-[x] Outdent

-[x] InsertOrderedList

-[x] InsertUnorderedList

-[x] styleWithCSS



### Setup

***

```
repositories {
    maven { url "https://jitpack.io" }
}
```

Add this maven to your ```project's``` build.gradle.



```
implementation 'com.github.lumyjuwon:Android-Rich-Wysiwyg-Editor:1.0.0'
```

And add this to your ``` module's``` build gradle.



### Methods

***

```javascript
WE.exec = function(cmd, val) { // 
    val = (typeof(val) !== 'undefined') ? val : null;
    document.execCommand("styleWithCSS", null, (val !== null));
    document.execCommand(cmd, false, val);
    WE.editor.focus();
    console.log('hello world');
    WE.backuprange();
    WE.restorerange();
};
```

Performs execCommand basic methods. You can add more funtions. ( [한국어](https://developer.mozilla.org/ko/docs/Web/API/Document/execCommand) , [ENG](https://developer.mozilla.org/en/docs/Web/API/Document/execCommand) )



```javascript
WE.insertCss = function (property, value) {	
	let select = getSelection();
	let tag = select.getRangeAt(0).startContainer.parentNode;
	if (select.toString() == tag.textContent) {	
		if (tag.style[property] != value) {
			tag.style[property] = value;
		} else {
			tag.style.removeProperty(property);
		}
		if (tag.style.length == 0) {	
			tag.removeAttribute('style');
			if (tag.tagName == 'SPAN') {	
				WE.editor.innerHTML = WE.editor.innerHTML.replace(/<span>(.*?)<\/span>/g, '$1');
			}
		}
	} else {	
		document.execCommand("styleWithCSS", null, false);
		document.execCommand('foreColor', false, 'rgb(1, 1, 1)');	
		WE.editor.innerHTML = WE.editor.innerHTML.replace(/<font .+?>(.*?)<\/font>/g, '<span style="' + property + ': ' + value + '">$1</span>');	
	}
	WE.editor.focus();
};
```

Performs adding the css property to the dragged area.



### Requirements

***

Android 5+



### Credits

***

* [RichEditor for Android](https://github.com/wasabeef/richeditor-android) by wasabeef
* [ImagePicker]( https://github.com/esafirm/android-image-picker)



### License

***

```
Copyright 2019 lumyjuwon

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

