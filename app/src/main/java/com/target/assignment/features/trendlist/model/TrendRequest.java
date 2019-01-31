package com.target.assignment.features.trendlist.model;

public class TrendRequest {
    private final String since;
    private final String language;

    public TrendRequest(String language, String since) {
        this.language = language;
        this.since = since;

    }

    public String getSince() {
        return since;
    }

    public String getLanguage() {
        return language;
    }
}
