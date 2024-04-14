package com.mtb.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Screen {
    private String screenId;
    @NotNull
    @Size(min = 3)
    private String theatreCode;
    @NotNull
    private int capacity;


    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getTheatreCode() {
        return theatreCode;
    }

    public void setTheatreCode(String theatreCode) {
        this.theatreCode = theatreCode;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "screenId='" + screenId + '\'' +
                ", theatreCode='" + theatreCode + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
