package io.opensaber.template.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Field {
       
    private String name;
    private String display;
    private String function;
    private String viewTemplateName;
    private String fetchType;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDisplay() {
        return display;
    }
    public void setDisplay(String display) {
        this.display = display;
    }
    public String getFunction() {
        return function;
    }
    public void setFunction(String function) {
        this.function = function;
    }
    public String getViewTemplateName() {
        return viewTemplateName;
    }
    public void setViewTemplateName(String viewTemplateName) {
        this.viewTemplateName = viewTemplateName;
    }
    public String getFetchType() {
        return fetchType;
    }
    public void setFetchType(String fetchType) {
        this.fetchType = fetchType;
    }
    
    
    
    
    
    
    
    
}
