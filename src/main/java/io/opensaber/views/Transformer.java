package io.opensaber.views;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transformer {
    
    private static Logger logger = LoggerFactory.getLogger(Transformer.class);

    /**
     * transforms a given JsonNode to representation of view templates
     * view template indicates any new field or mask fields for transformation
     * 
     * @param viewTemplate
     * @param node
     * @return
     */
    public JsonNode transform(ViewTemplate viewTemplate, ObjectNode node) {
        logger.debug("transformation on input node " + node);
        ObjectNode result = JsonNodeFactory.instance.objectNode();
        String subjectType = node.fieldNames().next();
        ObjectNode nodeAttrs = (ObjectNode) node.get(subjectType);

        for (Field field : viewTemplate.getFields()) {

            String functionStr = field.getFunction();
            if (functionStr != null) {

                String fdName = field.getFunctioName();
                String expression = viewTemplate.getExpression(fdName);

                FieldFunction function = new FieldFunction(expression);
                List<Object> actualValues = new ArrayList<>();
                for (String oneArg : field.getArgNames()) {
                    // Cut off the $
                    actualValues.add(ValueType.getValue(nodeAttrs.get(oneArg.substring(1))));
                }
                function.setArgValues(actualValues);

                FunctionEvaluator evaluator = new FunctionEvaluator(function);

                if (field.getDisplay()) {
                    result.put(field.getTitle(), evaluator.evaluate().toString());
                }
            } else if (field.getDisplay()) {
                result.set(field.getTitle(), nodeAttrs.get(field.getName()));
            }

        }
        logger.debug("Node transformation result: " + result);
        return JsonNodeFactory.instance.objectNode().set(subjectType, result);
    }

}
