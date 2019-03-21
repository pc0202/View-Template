package io.opensaber.views;

import java.util.List;

public class Function {

    private String expression;
    private List<Object> argValues;

    public Function(String expression) {
        this.expression = expression;
    }

    public void setArgValues(List<Object> args) {
        this.argValues = args;
    }

    public String getExpression() {
        return this.expression;
    }

    public List<Object> getArgValues() {
        return this.argValues;
    }
}
