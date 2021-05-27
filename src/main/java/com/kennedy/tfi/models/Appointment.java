package com.kennedy.tfi.models;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "idApptPatient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "idApptMedicalDoctor")
    private MedicalDoctor medicalDoctor;

    public Appointment() {
    }

    public Appointment(LocalDate date, LocalTime time, MedicalDoctor medicalDoctor, Patient patient) {
        this.date = date;
        this.time = time;
        this.medicalDoctor = medicalDoctor;
        this.patient = patient;
        this.available = true;
        this.smsSent = false;
        this.noShow = false;
        this.duration = 15;
        this.status = "Creado";
    };

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

}
