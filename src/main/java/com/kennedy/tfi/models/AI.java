package com.kennedy.tfi.models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import com.kennedy.tfi.constants.AI.*;

public class AI {

    private long idPatient;
    private long idAppointment;
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
    private double y = 0d;

    private double rate;

    private VisitType visitType;
    private LocalDate appointmentCreation;
    private LocalDate appointment;
    private LocalTime appointmentTime;
    private LocalDate patientBirthday;
    private String neighborhood;
    private boolean firstVisit;
    private int priorNoShows;

    public AI(VisitType visitType, LocalDate appointmentCreation, LocalDate appointment, LocalTime appointmentTime,
            LocalDate patientBirthday, String neighborhood, boolean firstVisit, int priorNoShows, long idPatient,
            long idAppointment) {
        this.visitType = visitType;
        this.appointmentCreation = appointmentCreation;
        this.appointment = appointment;
        this.appointmentTime = appointmentTime;
        this.patientBirthday = patientBirthday;
        this.neighborhood = neighborhood;
        this.firstVisit = firstVisit;
        this.priorNoShows = priorNoShows;
        this.idPatient = idPatient;
        this.idAppointment = idAppointment;
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
        Month apppointmentMonth = appointment.getMonth();

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
        DayOfWeek appointmentDay = appointment.getDayOfWeek();

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

    private void analizeAppointmentTime() {
        if (appointmentTime.isBefore(LocalTime.of(13, 0, 0))) {
            x5 = -0.14898d;
        } else {
            x5 = 0d;
        }
    }

    private void analizeAge() {

        // long yearsOld = patientBirthday.until(LocalTime.now(), ChronoUnit.YEARS);
        LocalDate today = LocalDate.now();
        long yearsOld = java.time.temporal.ChronoUnit.YEARS.between(patientBirthday, today);
        if (yearsOld < 2) {
            x6 = 0.24324d;
        } else if (yearsOld < 5) {
            x6 = 0.19334d;
        } else if (yearsOld < 10) {
            x6 = 0.19334d;
        } else {
            x6 = 0d;
        }
    }

    private void analizeEtnic() {
        x7 = 0d;
    }

    private void analizeCountyToClinic() {
        if (neighborhood.equals("Lanus")) { // Inside
            x8 = 0.03018d;
        } else if (neighborhood.equals("Lomas") || neighborhood.equals("CABA")) { // Adjacent
            x8 = 0d;
        } else { // Other
            x8 = -0.00358d;
        }
    }

    private void analizeDistanceToClinic() {
        x9 = 0d;
    }

    private void analizeHouseHolds() {
        x10 = 0d;
    }

    private void analizePrevNoShows() {
        if (firstVisit == true) {
            x11 = 0.74934d;
        } else if (priorNoShows == 0) {
            x11 = 0.56375d;
        } else if (priorNoShows == 1) {
            x11 = 0.2191d;
        } else if (priorNoShows == 2) {
            x11 = 0.14005d;
        } else if (priorNoShows < 5) {
            x11 = 0d;
        } else if (priorNoShows >= 5) {
            x11 = -0.3325d;
        }
    }

    public void analizeMedicalInsurance() {
        x12 = 0d;
    }

    public void analizeInsuranceHolder() {
        x13 = 0d;
    }

    public void analizeTotalInsuranceCarriers() {
        x14 = 0d;
    }

    public long getIdPatient() {
        return idPatient;
    }

    public long getIdAppointment() {
        return idAppointment;
    }

    public double calculateShowRate() {
        analizeVisitType();
        analizeTimeToAppointment();
        analizeAppointmentMonth();
        analizeAppointmentDay();
        analizeAppointmentTime();
        analizeAge();
        analizeEtnic();
        analizeCountyToClinic();
        analizeDistanceToClinic();
        analizeHouseHolds();
        analizePrevNoShows();
        analizeMedicalInsurance();

        rate = Math.exp(x0 + x1 + x2 + x3 + x4 + x5 + x6 + x7 + x8 + x9 + x10 + x11 + x12 + x13 + x14)
                / (Math.exp(x0 + x1 + x2 + x3 + x4 + x5 + x6 + x7 + x8 + x9 + x10 + x11 + x12 + x13 + x14) + 1);
        return rate;
    }
}
