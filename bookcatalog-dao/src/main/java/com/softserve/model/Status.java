package com.softserve.model;

public enum Status {
	AVAILABLE("Available"), UNAVAILABLE("Unavailable");

	private String label;

    private Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }	
	
}
