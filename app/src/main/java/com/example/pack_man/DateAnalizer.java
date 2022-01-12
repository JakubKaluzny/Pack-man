package com.example.pack_man;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class DateAnalizer {

    public static Calendar calendar = Calendar.getInstance();


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static int getDurationInDays(LocalDate startDate, LocalDate endDate){
        return (int)ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getSeason(LocalDate ld){
        if((ld.getMonthValue()== 3 && ld.getDayOfMonth() >= 21) || (ld.getMonthValue() > 3 && ld.getMonthValue() < 6) || (ld.getMonthValue()== 6 && ld.getDayOfMonth() <= 21)){
            return "Spring";
        }
        if((ld.getMonthValue() == 6 && ld.getDayOfMonth() >= 22) || (ld.getMonthValue() > 6 && ld.getMonthValue() < 9) || (ld.getMonthValue() == 9 && ld.getDayOfMonth() <= 22)){
            return "Summer";
        }
        if((ld.getMonthValue()== 9 && ld.getDayOfMonth() >= 23) || (ld.getMonthValue() > 9 && ld.getMonthValue() < 12) || (ld.getMonthValue()== 12 && ld.getDayOfMonth() <= 21)){
            return "Fall";
        }
        if((ld.getMonthValue() == 12 && ld.getDayOfMonth() >= 22) || ( ld.getMonthValue() < 3) || (ld.getMonthValue() == 12 && ld.getDayOfMonth() <= 20)){
            return "Winter";
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static int getDurationInDays(int startDateDay, int startDateMonth, int startDateYear, int endDateDay, int endDateMonth, int endDateYear){
        return DateAnalizer.getDurationInDays(LocalDate.of(startDateYear,startDateMonth,startDateDay), LocalDate.of(endDateYear,endDateMonth,endDateDay));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getSeason(int day, int mnth, int year) {
        return getSeason(LocalDate.of(year,mnth,day));
    }
}