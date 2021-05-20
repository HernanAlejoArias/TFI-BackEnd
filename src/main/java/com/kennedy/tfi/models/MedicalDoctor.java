package com.kennedy.tfi.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class MedicalDoctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String name;
    private String lastName;
    private LocalDate birthday;
    private int dni;
    private int medicalRegistration;
    private String specialism;

    @OneToMany(mappedBy = "medicalDoctor")
    private Set<Appointment> appointments = new HashSet<>();

    public MedicalDoctor() {
    }

    public MedicalDoctor(String name, String lastName, LocalDate birthday, int dni, int medicalRegistration,
            String specialism) {
        this.setName(name);
        this.setLastName(lastName);
        this.setBirthday(birthday);
        this.setDni(dni);
        this.setMedicalRegistration(medicalRegistration);
        this.setSpecialism(specialism);
    }

    public int getMedicalRegistration() {
        return medicalRegistration;
    }

    public void setMedicalRegistration(int medicalRegistration) {
        this.medicalRegistration = medicalRegistration;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getSpecialism() {
        return specialism;
    }

    public void setSpecialism(String specialism) {
        this.specialism = specialism;
    }
}
