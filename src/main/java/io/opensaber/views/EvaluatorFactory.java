package io.opensaber.views;

import java.util.List;

public class EvaluatorFactory {
    
    /**
     * returns the instance of IEvaluator implementations (like:FunctionEvaluator, ProviderEvaluator 
     * 
     * @param actualValues
     * @param functiondef
     * @return
     */
    public static IEvaluator<Object> getInstance(List<Object> actualValues, FunctionDefinition functiondef) {
        IEvaluator<Object> evaluator = null;
        FieldFunction function = null;

        if (functiondef.getResult() != null) {
            function = new FieldFunction(functiondef.getResult());
            function.setArgValues(actualValues);
            evaluator = new FunctionEvaluator(function);

        } else if (functiondef.getProvider() != null) {
            function = new FieldFunction(functiondef.getProvider());
            function.setArgValues(actualValues);
            evaluator = new ProviderEvaluator(function);
        }

        return evaluator;
    }
    
}
