package io.opensaber.views;



import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.util.List;

public class FunctionEvaluator<T> {
    private static final JexlEngine jexl = new JexlEngine();
    private JexlContext jexlContext = new MapContext();
    private FieldFunction function;
    private Expression jexlExpression;

    public FunctionEvaluator(FieldFunction function) {
        this.function = function;
    }

    public void setContextArgs() {
        int itr = 1;
        for(Object val : function.getArgValues()) {
            String arg = "arg" + itr++;
            jexlContext.set(arg, val);
        }
    }

    private void prepare() {
        jexlExpression = jexl.createExpression(function.getExpression());
        setContextArgs();
    }

    public T evaluate() {
        prepare();
        T result = (T) jexlExpression.evaluate(jexlContext);

        return result;
    }

    public String concat(List<String> args) {
        String res = "";
        for (String arg : args) {
            res = res.isEmpty() ? arg : (res + ":" + arg);
        }
        return res;
    }

}
