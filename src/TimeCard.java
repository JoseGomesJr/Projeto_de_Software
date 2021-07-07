import java.text.DateFormat;
import java.util.*;
import java.time.Duration;
import java.time.LocalTime;
public class TimeCard {
    private LocalTime entraDate;
    private LocalTime exiDate;
    private Date date;
    private Date exitdate;
    public void EntTimeCard(){
        Calendar calendar= Calendar.getInstance();
        this.entraDate = LocalTime.now();
        this.date= calendar.getTime();
    }
    public void ExiTimeCard(){
        Calendar calendar= Calendar.getInstance();
        this.exiDate = LocalTime.now();
        this.exitdate= calendar.getTime();
    }
    public void getDateInfo(){
        DateFormat dtHora = DateFormat.getDateTimeInstance();
		System.out.println("Entry data: "+dtHora.format(date));
        System.out.println("Departure data: "+dtHora.format(exitdate));
    }
    public double InforHoras(){
        Duration duration= Duration.between(entraDate, exiDate);
        return duration.toHours();
    }
    
}
