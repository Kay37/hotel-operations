package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    //! punchIn() tests
    //! ----------------------------------------------------------------------------------------------------------------
    @Test
    public void punchOut_fourHourShift_should_addFourHours() {
        //* Arrange
        Employee employee = new Employee(12347, "Raymond Melly",
                "Clerk", 25.00, 41);
        //? Punched in at 10 o clock
        employee.punchIn(10);

        //* Act
        //? Punched out at 2 o clock (four hours later)
        employee.punchOut(14);

        //* Assert
        //? 0.001 is our tolerance (third parameter). We don't want an exact comparison with assertEquals, so 0.001
        //? means "pass if it's within 0.001 of 45". Close enough for government work.
        assertEquals(45, employee.getHoursWorked(), 0.001);
    }

    @Test
    public void punchIn_storesStartTime() {
        //* Arrange
        Employee employee = new Employee(12347, "Raymond Melly",
                "Clerk", 25.00, 41);
        //* Act
        employee.punchIn(10);

        //* Assert
        assertEquals(10, employee.getStartTime(), 0.001);
        assertEquals(41,  employee.getHoursWorked(), 0.01);
    }


    //! ----------------------------------------------------------------------------------------------------------------

    //! punchOut() tests
    //! ----------------------------------------------------------------------------------------------------------------
    @Test
    public void punchOut_shiftWithMinutes_addsPartialHours() {
        //* Arrange
        Employee employee = new Employee(12347, "Raymond Melly",
                "Clerk", 25.00, 41);
        employee.punchIn(9.5);

        //* Act
        employee.punchOut(14.25);

        //* Assert
        assertEquals(45.75,  employee.getHoursWorked(), 0.001);
    }

    @Test
    public void punchOut_hoursOverForty_createsOvertime() {
        //* Arrange
        Employee employee = new Employee(12347, "Raymond Melly",
                "Clerk", 25.00, 38);
        employee.punchIn(9);

        //* Act
        employee.punchOut(14);

        //* Assert
        assertEquals(40, employee.getRegularHours(), 0.001);
        assertEquals(3, employee.getOvertimeHours(), 0.001);
    }

    //! ----------------------------------------------------------------------------------------------------------------
}