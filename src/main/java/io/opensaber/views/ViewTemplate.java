package io.opensaber.views;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewTemplate {

    private String id;
    private String subject;    
    private List<Field> fields;
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
    public List<Field> getFields() {
        return fields;
    }
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
