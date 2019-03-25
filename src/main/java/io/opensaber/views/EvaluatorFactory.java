package io.opensaber.views;

public class EvaluatorFactory {
    
    public static final String concat = "concat";
    public static final String userDefinedConcat = "userDefinedConcat";
    public static final String customDefinedConcat = "customDefinedConcat";
    /**
     * returns the instance of IEvaluator implementations (like:FunctionEvaluator, ProviderEvaluator 
     * @param functionName
     * @param function
     * @return
     */
    public static IEvaluator<Object> getInstance(String functionName, FieldFunction function){
        IEvaluator<Object> evaluator = null;
        switch(functionName){ 
        
        case concat :
            evaluator = new FunctionEvaluator(function);
            break;
        case userDefinedConcat :
            evaluator = new ProviderEvaluator<Object>(function);
            break;
        case customDefinedConcat :
            break;
        default :
            evaluator = new FunctionEvaluator(function);
            break;
        }  
        
        return evaluator;
    }
    
}
