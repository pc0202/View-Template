package io.opensaber;

import java.util.List;

public class FunctionEvaluator {
    
    private Function function;
    
    public FunctionEvaluator(Function function){
        this.function = function;
    }
    
    public String getResult(){
        String result = "";
        
        switch(function.getOperator()){
        case "concat":
            result = concat(function.getArgumentList());
            break;
        }
        
        return result;
    }
    
   public String concat(List<String> args){
       String res ="";
       for(String arg: args){
           res = res.isEmpty()? arg : (res +":"+ arg);
       }
       return res;
   }

}
