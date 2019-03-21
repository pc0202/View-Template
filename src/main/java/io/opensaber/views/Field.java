package io.opensaber.views;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Field {

    private String name;
    private String title;
    private boolean display = true;
    private String function;
    private String viewTemplateName;
    private String fetchType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        if (null == title) {
            return this.name;
        } else {
            return this.title;
        }
    }

}
