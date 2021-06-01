package com.kennedy.tfi.models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import com.kennedy.tfi.constants.AI.*;

public class AI {

    private double x0 = 1.077879d;
    private double x1 = 0d;
    private double x2 = 0d;
    private double x3 = 0d;
    private double x4 = 0d;
    private double x5 = 0d;
    private double x6 = 0d;
    private double x7 = 0d;
    private double x8 = 0d;
    private double x9 = 0d;
    private double x10 = 0d;
    private double x11 = 0d;
    private double x12 = 0d;
    private double x13 = 0d;
    private double x14 = 0d;
    private double x15 = 0d;
    private double y = 0d;

    private double rate;

    private VisitType visitType;
    private LocalDate appointmentCreation;
    private LocalDate appointment;
    private Month apppointmentMonth;
    private DayOfWeek appointmentDay;

    public void AI(VisitType visitType, LocalDate appointmentCreation, LocalDate appointment) {
        this.visitType = visitType;
        this.appointmentCreation = appointmentCreation;
        this.appointment = appointment;
    }

    private void analizeVisitType() {

        if (visitType == VisitType.NEWPATIENT) {
            x1 = -0.98053d;
        } else if (this.visitType == VisitType.RETURN) {
            x1 = -1.17604d;
        } else if (this.visitType == VisitType.NEWPATIENTCHILD) {
            x1 = -0.98053d;
        } else if (this.visitType == VisitType.RETURNCHILD) {
            x1 = -0.45971d;
        } else {
            x1 = 0f;
        }
    }

    private void analizeTimeToAppointment() {

        long timeToAppointmet = appointmentCreation.until(appointment, ChronoUnit.DAYS);

        if (timeToAppointmet == 1) {
            x2 = 1.38889d;
        } else if (timeToAppointmet > 14) {
            x2 = -0.41509d;
        } else {
            x2 = 0d;
        }
    }

    private void analizeAppointmentMonth() {
        apppointmentMonth = appointment.getMonth();

        if (apppointmentMonth == Month.JANUARY) {
            x3 = 0.1169d;
        } else if (apppointmentMonth == Month.FEBRUARY) {
            x3 = 0.13061d;
        } else if (apppointmentMonth == Month.MARCH) {
            x3 = -0.30591d;
        } else if (apppointmentMonth == Month.APRIL) {
            x3 = 0.06925d;
        } else if (apppointmentMonth == Month.MAY) {
            x3 = 0.06925d;
        } else if (apppointmentMonth == Month.JUNE) {
            x3 = 0.06681d;
        } else if (apppointmentMonth == Month.JULY) {
            x3 = 0.21308d;
        } else if (apppointmentMonth == Month.AUGUST) {
            x3 = 0.24301d;
        } else if (apppointmentMonth == Month.SEPTEMBER) {
            x3 = 0.06525d;
        } else if (apppointmentMonth == Month.OCTOBER) {
            x3 = 0.16892d;
        } else if (apppointmentMonth == Month.NOVEMBER) {
            x3 = 0.31265d;
        } else if (apppointmentMonth == Month.DECEMBER) {
            x3 = 0.01766d;
        } else {
            x3 = 0d;
        }
        ;
    }

    private void analizeAppointmentDay() {
        appointmentDay = appointment.getDayOfWeek();

        if (appointmentDay == DayOfWeek.MONDAY) {
            x4 = 0.10703d;
        } else if (appointmentDay == DayOfWeek.TUESDAY) {
            x4 = 0.09383d;
        } else if (appointmentDay == DayOfWeek.WEDNESDAY) {
            x4 = 0.05991d;
        } else if (appointmentDay == DayOfWeek.THURSDAY) {
            x4 = 0.11382d;
        } else if (appointmentDay == DayOfWeek.FRIDAY) {
            x4 = 0;
        } else if (appointmentDay == DayOfWeek.SATURDAY) {
            x4 = 0.24379;
        } else if (appointmentDay == DayOfWeek.SUNDAY) {
            x4 = 0d;
        } else {
            x4 = 0d;
        }
    }

    public double calculateShowRate() {
        analizeVisitType();
        analizeTimeToAppointment();
        analizeAppointmentMonth();
        analizeAppointmentDay();

        rate = Math.exp(x0 + x1 + x2 + x3 + x4 + x5 + x6 + x7 + x8 + x9 + x10 + x11 + x12 + x13 + x14 + x15)
                / (Math.exp(x0 + x1 + x2 + x3 + x4 + x5 + x6 + x7 + x8 + x9 + x10 + x11 + x12 + x13 + x14 + x15) + 1);
        return rate;
    }
}
