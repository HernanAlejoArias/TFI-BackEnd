package com.kennedy.tfi.Controller;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentController {

    private long md;
    private LocalDate date;
    private LocalTime time;
    private int earlyDayAppointment;
    private boolean earlyMonday;
    private boolean earlyTuesday;
    private boolean earlyWednesday;
    private boolean earlyThrusday;
    private boolean earlyFriday;
    private boolean earlyMorning;
    private boolean earlyAfternoon;

    public AppointmentController(boolean earlyMonday, boolean earlyTuesday, boolean earlyWednesday,
            boolean earlyThrusday, boolean earlyFriday, boolean earlyMorning, boolean earlyAfternoon, long md,
            LocalDate date, LocalTime time, int earlyDayAppointment) {
        this.md = md;
        this.date = date;
        this.time = time;
        this.earlyDayAppointment = earlyDayAppointment;
        this.earlyMonday = earlyMonday;
        this.earlyTuesday = earlyTuesday;
        this.earlyWednesday = earlyWednesday;
        this.earlyThrusday = earlyThrusday;
        this.earlyFriday = earlyFriday;
        this.earlyMorning = earlyMorning;
        this.earlyAfternoon = earlyAfternoon;
    }

    public AppointmentController(long md) {
        this.md = md;
    }

    public long getMd() {
        return md;
    }

    public void setMd(long md) {
        this.md = md;
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

    public int getEarlyDayAppointment() {
        return earlyDayAppointment;
    }

    public void setEarlyDayAppointment(int earlyDayAppointment) {
        this.earlyDayAppointment = earlyDayAppointment;
    }

    public boolean isEarlyMonday() {
        return earlyMonday;
    }

    public void setEarlyMonday(boolean earlyMonday) {
        this.earlyMonday = earlyMonday;
    }

    public boolean isEarlyTuesday() {
        return earlyTuesday;
    }

    public void setEarlyTuesday(boolean earlyTuesday) {
        this.earlyTuesday = earlyTuesday;
    }

    public boolean isEarlyWednesday() {
        return earlyWednesday;
    }

    public void setEarlyWednesday(boolean earlyWednesday) {
        this.earlyWednesday = earlyWednesday;
    }

    public boolean isEarlyThrusday() {
        return earlyThrusday;
    }

    public void setEarlyThrusday(boolean earlyThrusday) {
        this.earlyThrusday = earlyThrusday;
    }

    public boolean isEarlyFriday() {
        return earlyFriday;
    }

    public void setEarlyFriday(boolean earlyFriday) {
        this.earlyFriday = earlyFriday;
    }

    public boolean isEarlyMorning() {
        return earlyMorning;
    }

    public void setEarlyMorning(boolean earlyMorning) {
        this.earlyMorning = earlyMorning;
    }

    public boolean isEarly_app_afternoom() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(boolean earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public boolean isEarlyAfternoon() {
        return false;
    }

}
