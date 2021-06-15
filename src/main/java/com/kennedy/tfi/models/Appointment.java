package com.kennedy.tfi.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private LocalDate date;
    private LocalTime time;
    private boolean available;
    private LocalTime endTime;
    private boolean smsSent;
    private boolean noShow;
    private int duration;
    private String status;
    private boolean early_app_same_day;
    private int early_app_same_day_notice;
    private boolean early_app;
    private boolean early_app_morning;
    private boolean early_app_afternoom;

    /*
     * @OneToMany(mappedBy = "appointment") private Set<EarlyAppointment>
     * earlyAppointment = new HashSet<>();
     */

    @ManyToOne
    @JoinColumn(name = "idApptPatient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "idApptMedicalDoctor")
    private MedicalDoctor medicalDoctor;

    public Appointment() {
    }

    // Basic appointment creation
    public Appointment(LocalDate date, LocalTime time, MedicalDoctor medicalDoctor, Patient patient) {
        this.date = date;
        this.time = time;
        this.medicalDoctor = medicalDoctor;
        this.patient = patient;
        this.available = false;
        this.smsSent = false;
        this.noShow = false;
        this.duration = 15;
        this.status = "Creado";
    };

    // Appointment creation with Early-Appointment
    public Appointment(LocalDate date, LocalTime time, MedicalDoctor medicalDoctor, Patient patient,
            boolean early_app_same_day, int early_app_same_day_notice, boolean early_app, boolean early_app_morning,
            boolean early_app_afternoom) {
        this.date = date;
        this.time = time;
        this.medicalDoctor = medicalDoctor;
        this.patient = patient;
        this.available = false;
        this.smsSent = false;
        this.noShow = false;
        this.duration = 15;
        this.status = "Creado";
        this.early_app_same_day = early_app_same_day;
        this.early_app_same_day_notice = early_app_same_day_notice;
        this.early_app = early_app;
        this.early_app_morning = early_app_morning;
        this.early_app_afternoom = early_app_afternoom;
    };

    // For Test appointment on the commandLineRunner
    public Appointment(LocalDate date, LocalTime time, boolean available, LocalTime endTime,
            MedicalDoctor medicalDoctor, Patient patient, boolean smsSent, boolean noShow) {
        this.date = date;
        this.time = time;
        this.endTime = endTime;
        this.medicalDoctor = medicalDoctor;
        this.patient = patient;
        this.available = available;
        this.smsSent = smsSent;
        this.noShow = noShow;
        this.duration = 15;
        this.status = "Creado";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isSMSSent() {
        return smsSent;
    }

    public void setSMSSent(boolean smsSent) {
        this.smsSent = smsSent;
    }

    public boolean isNoShow() {
        return noShow;
    }

    public void setNoShow(boolean noShow) {
        this.noShow = noShow;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public MedicalDoctor getMedicalDoctor() {
        return medicalDoctor;
    }

    public void setMedicalDoctor(MedicalDoctor medicalDoctor) {
        this.medicalDoctor = medicalDoctor;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isEarly_app_same_day() {
        return early_app_same_day;
    }

    public void setEarly_app_same_day(boolean early_app_same_day) {
        this.early_app_same_day = early_app_same_day;
    }

    public int getEarly_app_same_day_notice() {
        return early_app_same_day_notice;
    }

    public void setEarly_app_same_day_notice(int early_app_same_day_notice) {
        this.early_app_same_day_notice = early_app_same_day_notice;
    }

    public boolean isEarly_app() {
        return early_app;
    }

    public void setEarly_app(boolean early_app) {
        this.early_app = early_app;
    }

    public boolean isEarly_app_morning() {
        return early_app_morning;
    }

    public void setEarly_app_morning(boolean early_app_morning) {
        this.early_app_morning = early_app_morning;
    }

    public boolean isEarly_app_afternoom() {
        return early_app_afternoom;
    }

    public void setEarly_app_afternoom(boolean early_app_afternoom) {
        this.early_app_afternoom = early_app_afternoom;
    }

}
