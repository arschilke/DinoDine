package mad.dinodine;
//End up using this instead?? Storing dates/times is a pain.
//https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html

//import android.os.Parcel;
//import android.os.Parcelable;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

//Can't have foreign key, and an object.. Foreign key would work if I was only storing the guestID, which I'm not.
@Entity(foreignKeys = @ForeignKey(entity = Guest.class, parentColumns = "guestID", childColumns = "guest"),
        indices = @Index("guest"))
@TypeConverters({Converters.class})
public class Booking implements Serializable, Comparable{
    //guest key - foreign key index..
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "booking_id")
    private String bookingID="";
    private int numOfPeople;
    private Date date;
    private Time startTime;
    private Time endTime;
    private String guest;


    public Booking(String bookingID, int numOfPeople, long date, Time startTime, Time endTime, String guest){
        if(bookingID.equals("")){bookingID = UUID.randomUUID().toString();}else{this.bookingID=bookingID;}
        this.numOfPeople = numOfPeople;
        this.date = new Date(date);
        this.startTime = startTime;
        this.endTime = endTime;
        this.guest = guest;
    }

    public Booking(int numOfPeople, long date, Time startTime, Time endTime, String guest) {
        this.bookingID = UUID.randomUUID().toString();
        this.numOfPeople = numOfPeople;
        this.date = new Date(date);
        this.startTime = startTime;
        this.endTime = endTime;
        this.guest = guest;
    }

    //@Ignore
    public Booking(){
        this.bookingID = UUID.randomUUID().toString();
        this.guest = "";
        this.numOfPeople = -1;
        this.date = new Date();
        this.startTime = new Time(date.getTime());
        this.endTime = new Time(date.getTime());
    }

    @Ignore
    public Booking(String guest, int NumOfPeople, Date date, Table t){
        this.guest = guest;
        this.numOfPeople = NumOfPeople;
        this.date = date;
    }
    //Unnecessary constructors? Just pass in milliseconds whenever we interact with Date object.
    /*public Booking(Guest guest, int NumOfPeople, int day, int month, int year, int hour, int min){
        generateBookingId();
        this.guest = guest;
        this.numOfPeople = NumOfPeople;
        this.date = generateDateObj(day, month, year, hour, min);
    }
    public Booking(int bookingID, Guest guest, int numOfPeople, Date date) {
        this.bookingID = bookingID;
        this.guest = guest;
        this.numOfPeople = numOfPeople;
        this.date = date;
    }
    public Booking(int bookingID, Guest guest, int NumOfPeople, int day, int month, int year, int hour, int min){
        this.bookingID = bookingID;
        this.guest = guest;
        this.numOfPeople = NumOfPeople;
        this.date = generateDateObj(day, month, year, hour, min);
    }*/

    //Getters -----------------------------------------------------
    @NonNull public String getBookingID() { return this.bookingID;}
    public int getNumOfPeople() { return this.numOfPeople;}
    public String getGuest()     { return this.guest;}
    public Date getDate()       { return this.date;}
    public Time getStartTime()  { return this.startTime;}
    public Time getEndTime()    { return this.endTime;}
    public String getDateString() { //Converted to display as dd/mm/yyyy
        String result = "";
        if (date != null) {
            Calendar dmy = new GregorianCalendar();
            dmy.setTime(date);
            String day = "", month= "", year="";
            int d = dmy.get(Calendar.DAY_OF_MONTH);
            int m = dmy.get(Calendar.MONTH)+1; //is an index, so add one to represent month properly.
            int y = dmy.get(Calendar.YEAR);
            if(d < 10){ day = "0"+d;}else{day+=d;}
            if(m< 10) {month = "0"+m;}else{month+=m;}
            year += y;
            //result = date.toString();
            result = day+"/"+month+"/"+year;
        }
        return result;
    }
    public String getTime() { //gets time string in hh:mm:ss from Booking.Date object. To get HH:MM substring(0,5)
        String result = "";
        if(date!=null) {
            Time time = new Time(date.getTime());
            result = time.toString().substring(0,5);
        }
        return result;
    }


    //Setters -----------------------------------------------------
    public void setBookingID(@NonNull String bookingID) {this.bookingID = bookingID;}
    public void setNumOfPeople(int numOfPeople){
        if (numOfPeople <= 0 ){
            this.numOfPeople = 0;
        }
            this.numOfPeople = numOfPeople;
    }
    public void setGuest(String guest) {this.guest = guest;}
    public void setDate(Date date) {
        this.date = date;

    }
    public void setDate(long date) {
        this.date = new Date(date);

    }
    public void setStartTime(Time x){
        this.startTime = x;
    }
    public void setEndTime(Time x){
        this.endTime = x;
    }

    @Override
    public int compareTo(Object other){
        Booking other2 = (Booking) other;
        Date d = getDate();
        d.setHours(0);
        d.setMinutes(0);
        d.setSeconds(0);
        Date d2 = other2.getDate();
        d2.setHours(0);
        d2.setMinutes(0);
        d2.setSeconds(0);
        if(d.compareTo(d2) != 0){
            return d.compareTo(d2);
        }
        String aStr = getStartTime().toString();
        String bStr = other2.getStartTime().toString();
        //array = {hour, min, sec}
        int aTimeArray[] = {Integer.parseInt(aStr.substring(0,2)), Integer.parseInt(aStr.substring(3,5)), Integer.parseInt(aStr.substring(6))};
        int bTimeArray[] = {Integer.parseInt(bStr.substring(0,2)), Integer.parseInt(bStr.substring(3,5)), Integer.parseInt(bStr.substring(6))};
        for (int x = 0; x < aTimeArray.length; x++){
            if(aTimeArray[x] > bTimeArray[x] ){
                return 1;
            }
            else if (aTimeArray[x] < bTimeArray[x]) {
                return -1;
            }
        }
        return 0;
    }


    @Override
    public String toString() {
        return "Booking[ " +
                "bookingID: " + bookingID +
                ", numOfPeople: " + numOfPeople +
                ", date: " + date +
                ", startTime: " + startTime +
                ", endTime: "+ endTime+
                ", guestID: " + guest +
                " ]";
    }



    /*private Date generateDateObj(int day, int month, int year, int hour, int min){
        Calendar cal = Calendar.getInstance();
        cal.set(year,month,day,hour,min);
        return cal.getTime();
    }*/
}
