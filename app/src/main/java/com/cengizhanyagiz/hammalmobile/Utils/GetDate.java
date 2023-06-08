package com.cengizhanyagiz.hammalmobile.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDate {
    public static String getDate() {
        DateFormat date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        final String reportDate = date.format(today);
        return reportDate;
    }
}
