package com.kennedy.tfi.models;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private LocalDate date;
    private LocalTime time;
    private boolean available;
    private LocalTime endTime;
    private long idmedicalDoctor;
    private long idPatient;
    private boolean smsSent;
    private boolean noShow;

    @ManyToOne
    @JoinColumn(name = "idApptPatient")
    private Patient paciente;

    @ManyToOne
    @JoinColumn(name = "idApptMedicalDoctor")
    private MedicalDoctor medicalDoctor;

    public Appointment() {
    }

    public Appointment(LocalDate date, LocalTime time, boolean available, LocalTime endTime, long idmedicalDoctor,
            long idPatient, boolean smsSent, boolean noShow) {
        this.date = date;
        this.time = time;
        this.available = available;
        this.endTime = endTime;
        this.idmedicalDoctor = idmedicalDoctor;
        this.idPatient = idPatient;
        this.smsSent = smsSent;
        this.noShow = noShow;
    }

    public long getIdmedicalDoctor() {
        return idmedicalDoctor;
    }

    public void setIdmedicalDoctor(Long idmedicalDoctor) {
        this.idmedicalDoctor = idmedicalDoctor;
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

    public Long getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Long idPatient) {
        this.idPatient = idPatient;
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

}
