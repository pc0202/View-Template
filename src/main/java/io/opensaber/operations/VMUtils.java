package io.opensaber.operations;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.opensaber.template.pojo.Field;
import io.opensaber.template.pojo.ViewTemplate;

public class VMUtils {

    public static void transform(ViewTemplate viewTemplate, ObjectNode node) {
        // for each field array item
        for (Field field : viewTemplate.getFields()) {
            // - if function is specified
            /// - call inline function expression evaluation
            if (field.getFunction() != null) {
                String expression = field.getFunction();
                Function function = new Function(expression);
                FunctionEvaluator evaluator = new FunctionEvaluator(function);
                // modifying the node
                node.put(field.getName(), evaluator.getResult());

            }
            //TODO:
            // - if provider is specified
            // - instantiate the provider and do an invoke on provider.doOperation
            // - else: nothing
        }

    }

}
