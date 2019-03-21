package io.opensaber;

import java.util.List;

public interface IViewFunctionProvider<T> {
    
    public abstract T doAction(List<Object> values);

}
