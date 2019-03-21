package io.opensaber.template.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewTemplate {

    private String id;
    private String subject;    
    private List<io.opensaber.template.pojo.Field> fields;
    private List<FunctionDefinition> functionDefinitions;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public List<FunctionDefinition> getFunctionDefinitions() {
        return functionDefinitions;
    }
    public void setFunctionDefinitions(List<FunctionDefinition> functionDefinitions) {
        this.functionDefinitions = functionDefinitions;
    }
    public List<io.opensaber.template.pojo.Field> getFields() {
        return fields;
    }
    public void setFields(List<io.opensaber.template.pojo.Field> fields) {
        this.fields = fields;
    }
}
