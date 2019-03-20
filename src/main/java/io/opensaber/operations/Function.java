package io.opensaber.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class Function {
    
    private String expression = "#/functionDefinitions/concat($name, $nationalIdentifier)";
    //private List<Object> args;
    private String operator;
    private List<String> argumentList = new ArrayList<>(); 
   
    
    public Function(String expression){       
        this.expression = expression;
        parseExpression();
    }
    
/*    public void setArgs(List<Object> args) {
        this.args = args;
    }
    */
    public List<String> getArgumentList() {
        return this.argumentList;
    }
    public String getOperator(){
        return this.operator;
    }
    
    private void parseExpression(){
        String subString = StringUtils.substringBetween(expression, "(", ")");
        String[] commaSeparatedArr = subString.split("\\s*,\\s*");
        argumentList = Arrays.stream(commaSeparatedArr).collect(Collectors.toList());
        operator = StringUtils.substring(expression, expression.lastIndexOf("/")+1, expression.indexOf("("));//(expression, "/", ")");

    }

    
}
