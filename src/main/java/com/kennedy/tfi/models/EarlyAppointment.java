// package com.kennedy.tfi.models;

// import java.time.LocalDate;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.Table;

// import org.hibernate.annotations.GenericGenerator;

// @Entity
// @Table(name = "early_appointments")
// public class EarlyAppointment {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//     @GenericGenerator(name = "native", strategy = "native")
//     private long id;

//     private LocalDate movingToDay;
//     private boolean morning;
//     private boolean afternoom;

//     @ManyToOne
//     @JoinColumn(name = "idEarlyApp")
//     private Appointment appointment;

//     public EarlyAppointment() {
//     }

//     public EarlyAppointment(LocalDate movingToDay, boolean morning, boolean afternoom) {
//         this.movingToDay = movingToDay;
//         this.morning = morning;
//         this.afternoom = afternoom;
//     }

//     public LocalDate getMovingToDay() {
//         return movingToDay;
//     }

//     public void setMovingToDay(LocalDate movingToDay) {
//         this.movingToDay = movingToDay;
//     }

//     public boolean isMorning() {
//         return morning;
//     }

//     public void setMorning(boolean morning) {
//         this.morning = morning;
//     }

//     public boolean isAfternoom() {
//         return afternoom;
//     }

//     public void setAfternoom(boolean afternoom) {
//         this.afternoom = afternoom;
//     }

// }
