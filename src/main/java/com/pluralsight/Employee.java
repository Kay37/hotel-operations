
package com.pluralsight;

import java.time.LocalDateTime;

public class Employee {

    // ! backing variables.
    private int employeeId;
    private String name;
    private String department;
    private double payRate;
    private double hoursWorked;
    // ! fields for punchIn/punchOut/punchTimeCard. startTime is a double so we can get hours & minutes.
    private double startTime;
    // ! is an employee punchedIn?
    private boolean punchedIn;

    //! Our public constructor. No startTime or punchedIn b/c a new employee hasn't punched in yet and created
    //! a start time.
    public Employee(int employeeId, String name, String department, double payRate, double hoursWorked) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.payRate = payRate;
        this.hoursWorked = hoursWorked;
    }

    //! our plain getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getPayRate() {
        return payRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getStartTime() {
        return startTime;
    }

    public boolean isPunchedIn() {
        return punchedIn;
    }

    // ! our derived getters. Derived getters have no backing variables
    public double getRegularHours() {
        //? if the employee's hours exceed 40 then they have some overtime hours. We only want the regular hours,
        //? so we'll check if they have overtime and if they do that means they at least have 40 so we can just return
        //? 40.
        if (hoursWorked > 40) {
            return 40;
        }
        return hoursWorked;
    }

    public double getOvertimeHours() {
        // ? if the employee's hours are over 40, then they have overtime hours. We'll take their total hours worked
        // ? and subtract the regular hours (40) to get how many hours were overtime.
        if (hoursWorked > 40) {
            return hoursWorked - 40;
        }
        return 0;
    }

    //! We'll get their total pay here. their regular hours * their payrate and then we'll add that amount to
    //! their overtime hours * (their payRate * 1.5)
    public double getTotalPay() {
        return getRegularHours() * payRate + getOvertimeHours() * (payRate * 1.5);
    }

    //! method for punchIn where Main has to supply the time.
    public void punchIn(double time) {
        startTime = time;
    }

    //! method for punchIn where it reads the clock on its own so main can Call this without passing a time.
    public void punchIn() {
        LocalDateTime now = LocalDateTime.now();
        startTime = now.getHour() + now.getMinute() / 60.0;
    }

    //! method for punchOut where Main has to supply the time.
    public void punchOut(double time) {
        //? creating a variable for how long the shift was. It takes the current time (when clocking out time) and
        //? subtracts what the time was when they started (clocked in)
        double shiftDuration = time - startTime;
        //? This takes their amount of hours worked for the week and adds the shift duration to it.
        hoursWorked += shiftDuration;
    }

    //! method for punchOut where it reads the clock on its own so main can Call this without passing a time.
    public void punchOut() {
        LocalDateTime punchOutTime = LocalDateTime.now();
        //? a variable for how long the shift was. Before this, we create a LDT object that holds the current LDT.
        //? then, we grab the hour from that LDT, add it to the .getMinute from that LDT, and divide by 60.0
        //? we have to do this b/c hours are stored as one decimal #, and minutes have to become a fraction of
        //? an hour before they can be added to it.
        double shiftDuration = punchOutTime.getHour() + punchOutTime.getMinute() / 60.0 - startTime;
        hoursWorked += shiftDuration;
    }

    //! method that combines punchIn & punchOut and figures out if it's in or out on its own (don't have to specify
    //! when calling it)
    //! this method needs Main to supply the time.
    public void punchTimeCard(int time) {
        if (punchedIn) {
            punchOut(time);
            punchedIn = false;
        } else {
            punchIn(time);
            punchedIn = true;
        }
    }
    //! this method reads the clock on its own so main can call w/out passing a time.
    public void punchTimeCard() {
        if (punchedIn) {
            punchOut();
            punchedIn = false;
        } else {
            punchIn();
            punchedIn = true;
        }
    }
}
