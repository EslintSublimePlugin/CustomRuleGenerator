package sample;

/**
 * Created by sachin on 30/4/17.
 */
public class TemplateBuilderEngine {

    public static String generateJSarray(String name){
        return name+": [ ]";
    }
    public static String generateJSONObject(String name){
        return name+": {";
    }

    public static String addChild(String parent,String child){
        return parent+"\n"+child;
    }

    public static String generateJSONObject(String name,String value){
        return name+":\""+value+"\"";
    }
    public static String generateJSONObject(String name,Boolean value){
        return name+":"+value;
    }

    public static String finalizeObjectorFunction(String name){
        return name+"}";
    }

    public static String addComment(String name,String comment){
        return name+"//"+comment;
    }

    public static String createJSFunction(String functionName,String args){
       return functionName +": function( "+args+"){\n";
    }

    public static String returnJS(){
        return "return {\n";
    }

    public static String generateTemplate(String root){
        return "\"use strict\";\n\n\n"+root;
    }
}
