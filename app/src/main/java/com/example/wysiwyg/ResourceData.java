package com.example.wysiwyg;

public enum ResourceData {

    BLACK("black"),
    WHITE("white"),
    RED("red"),
    BLUE("blue"),
    GREEN("green"),
    NINEFONTSIZE("1"),
    TENFONTSIZE("2"),
    ELEVENFONTSIZE("3"),
    TWENTEENFONTSIZE("5"),
    FOURTEENFONTSIZE("7");

    private final String resource;

    private ResourceData(String resource){
        this.resource = resource;
    }

    @Override
    public String toString(){
        return this.resource;
    }

}
