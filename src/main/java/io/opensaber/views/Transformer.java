package io.opensaber.views;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;

public class Transformer {

    public JsonNode transform(ViewTemplate viewTemplate, ObjectNode node) {
        ObjectNode result = JsonNodeFactory.instance.objectNode();
        String subjectType = node.fieldNames().next();

        ObjectNode nodeAttrs = (ObjectNode) node.get(subjectType);
        // for each field array item
        for (Field field : viewTemplate.getFields()) {
            // - if function is specified
            /// - call inline function expression evaluation
            if (field.getFunction() != null) {
                // TODO - remove hardcoded function reference
                String expression = viewTemplate.getFunctionDefinitions().get(0).getResult();
                String functionStr = field.getFunction();
                String argNames = field.getFunction().substring(functionStr.indexOf("(") + 1,
                                                            functionStr.lastIndexOf(")"));
                String[] functionArgs = argNames.split(", ");

                FieldFunction function = new FieldFunction(expression);
                List<Object> actualValues = new ArrayList<>();
                for(String oneArg: functionArgs) {
                    // Cut off the $
                    //System.out.println("Added value "+ oneArg.substring(1) + " -- " + nodeAttrs.get(oneArg.substring(1)));
                    actualValues.add(ValueType.getValue(nodeAttrs.get(oneArg.substring(1))));
                }
                function.setArgValues(actualValues);

                FunctionEvaluator<String> evaluator = new FunctionEvaluator(function);

                if (field.getDisplay()) {
                    result.put(field.getTitle(), evaluator.evaluate());
                }
            } else if (field.getDisplay()) {
                result.set(field.getTitle(), nodeAttrs.get(field.getName()));
            }

            //TODO:
            // - if provider is specified
            // - instantiate the provider and do an invoke on provider.doOperation
            // - else: nothing
        }

        return JsonNodeFactory.instance.objectNode().set(subjectType, result);
    }

}
