package com.pict.journalApp.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

    private Current current;

    @Getter
    @Setter
    public static class Current{
        public double temp_c;
    }
}
