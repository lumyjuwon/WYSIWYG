package com.example.wysiwyg;

public enum EvalCommand{
    FONTNAME("fontName"),
    FONTSIZE("fontSize"),
    BOLD("bold"),
    UNDERLINE("underline"),
    ITALIC("italic"),
    STRIKETHROUGH("strikeThrough"),
    SUPERSCRIPT("superscript"),
    SUBSCRIPT("subscript"),
    FORECOLOR("foreColor"),
    BACKGROUNDCOLOR("backColor"),
    JUSTIFYLEFT("justifyLeft"),
    JUSTIFYCENTER("justifyCenter"),
    JUSTIFYRIGHT("justifyRight");

    private final String cmd;

    private EvalCommand(String cmd){
        this.cmd = cmd;
    }
    @Override
    public String toString(){
        return this.cmd;
    }
}