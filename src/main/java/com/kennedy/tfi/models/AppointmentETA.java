package com.kennedy.tfi.models;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class AppointmentETA {

    private LocalTime startETA;
    private LocalTime endETA;
    private String status;

    public AppointmentETA() {
    }

    public AppointmentETA(LocalTime startETA, LocalTime endETA) {
        this.startETA = startETA;
        this.endETA = endETA;
    }

    public void calculateStatus(LocalTime reservedTime, LocalTime endTime, String status) {
        this.status = status;

        if (!status.equals("Cancelado")) {
            if (endTime != null) {
                this.status = "Finalizado";
            } else if (reservedTime.until(this.startETA, ChronoUnit.MINUTES) > 30) {
                this.status = "Retrasado";
            } else {
                this.status = "En hora";
            }
        }
    }

    public LocalTime getStartETA() {
        return startETA;
    }

    public void setStartETA(LocalTime startETA) {
        this.startETA = startETA;
    }

    public LocalTime getEndETA() {
        return endETA;
    }

    public void setEndETA(LocalTime endETA) {
        this.endETA = endETA;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
