package com.kennedy.tfi.models;

import java.time.LocalTime;

public class AppointmentETA {

    private Appointment appointment;
    private LocalTime startETA;
    private LocalTime endETA;
    private String status;

    public AppointmentETA(Appointment app) {
        this.appointment = app;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void calculateStatus() {
        // if (startETA.compareTo(super.getTime()){}
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
