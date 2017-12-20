package com.cucumbertests.stepdefs.salaryreport;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java8.En;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SalaryDatesReportStepDefs implements En {

    private int month;
    private int year;
    private Scenario scenario;

    private static Calendar calendar = null;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    public SalaryDatesReportStepDefs() {

        Given("^I have month '(.+)' and year '(.+)'$", (String month, String year) -> {
            this.month = Integer.parseInt(month);
            this.year = Integer.parseInt(year);
        });

        Then("^I should see base salary pay date$", () -> {
            scenario.write(month + "/" + year + " Basic salary to be paid on: " + lastWorkingDayOfMonth(month, year));
        });

        Then("^I should see bonus salary pay date$", () -> {
            try {
                scenario.write(month + "/" + year + " Bonus salary to be paid on: " + midMonthWorkingDay(month, year));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @param month integer value of month
     * @param year integer value of month
     * @return last day of month in int value
     */
    private static int getLastDayValueOfMonth(int month, int year) {
        calendar = Calendar.getInstance();
        // passing month-1 because 0-->jan, 1-->feb... 11-->dec
        calendar.set(year, month - 1, 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        Date date = calendar.getTime();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);

    }

    private static String getLastDayDateOfMonth(int month, int year) {
        calendar = Calendar.getInstance();
        // passing month-1 because 0-->jan, 1-->feb... 11-->dec
        calendar.set(year, month - 1, 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        Date date = calendar.getTime();
        DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
        return DATE_FORMAT.format(date);
    }

    private static String lastWorkingDayOfMonth(int month, int year) {

        int lastDayValue = getLastDayValueOfMonth(month, year);
        String lastDayDate = getLastDayDateOfMonth(month, year);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Get a Date object from the date string
        Date myDate = null;
        try {
            myDate = dateFormat.parse(lastDayDate);
            calendar.setTime(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Use the date formatter to produce a formatted date string
        Date lastWorkingDay = null;

        // if the Date->time xform always places the time as YYYYMMDD 00:00:00
        //   this will be safer.
        if(lastDayValue == 7) {
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            lastWorkingDay = calendar.getTime();
        } else if (lastDayValue == 1) {
            calendar.add(Calendar.DAY_OF_YEAR, -2);
            lastWorkingDay = calendar.getTime();
        }

        return dateFormat.format(lastWorkingDay);
    }

    private static int GetDay15ValueOfMonth(int month, int year) throws ParseException {
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = newDateFormat.parse("15/" + month + "/" + year);
        //     System.out.println(date);
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK); // the day of the week in numerical format

    }

    private static String GetDay15DateOfMonth(int month, int year) throws ParseException {
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = newDateFormat.parse("15/" + month + "/" + year);
        DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
        return DATE_FORMAT.format(date);

    }

    private static String midMonthWorkingDay(int month, int year) throws ParseException {
        int Day15Value = GetDay15ValueOfMonth(month, year);
        String Day15Date = GetDay15DateOfMonth(month, year);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Get a Date object from the date string
        Date myDate = dateFormat.parse(Day15Date);
        calendar.setTime(myDate);

        // Use the date formatter to produce a formatted date string
        Date midMonthWorkingDay = null;

        // if the Date->time xform always places the time as YYYYMMDD 00:00:00
        //   this will be safer.
        if(Day15Value == 7) {
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            midMonthWorkingDay = calendar.getTime();
        } else if (Day15Value == 1) {
            calendar.add(Calendar.DAY_OF_YEAR, -2);
            midMonthWorkingDay = calendar.getTime();
        }

        return dateFormat.format(midMonthWorkingDay);

    }
}
