package mad.dinodine;

//import android.os.Parcel;
//import android.os.Parcelable;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Booking implements Serializable{

    int bookingID;
    int numOfPeople;
    Guest guest;
    Date date;
    Time startTime;
    Time endTime; //use start and endTime or just start with duration? pros,cons?
    double duration; //1.0-1.5-2.0 enum? short avg long?
    Table table;

    public Booking(){
        this.bookingID = -1;
        this.guest = null;
        this.numOfPeople = -1;
        this.date = null;
        this.table = new Table();
    }
    public Booking(Guest guest, int NumOfPeople, long date, Table t){
        generateBookingId();
        this.guest = guest;
        this.numOfPeople = NumOfPeople;
        this.date = new Date(date);
        this.table = t;
    }
    public Booking(Guest guest, int NumOfPeople, Date date, Table t){
        generateBookingId();
        this.guest = guest;
        this.numOfPeople = NumOfPeople;
        this.date = date;
        this.table = t;
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
    public int getBookingID() {
        return bookingID;
    }
    public int getNumOfPeople() {
        return numOfPeople;
    }
    public Guest getGuest() {
        return guest;
    }
    public Date getDate() {
        return date;
    }
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
    public Table getTable() {
        return table;
    }

    //Setters -----------------------------------------------------
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }
    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setDate(long date) {this.date = new Date(date);}
    public void setTable(Table t) {table = t;}
    public void setStartTime(Time startTime) { this.startTime = startTime; }
    public void setEndTime(Time endTime) { this.endTime = endTime; }

    @Override
    public String toString() {
        return "Booking[ " +
                "bookingID: " + bookingID +
                ", guest: " + guest +
                ", numOfPeople: " + numOfPeople +
                ", date: " + date +
                " ]";
    }


    private void generateBookingId() {
        //TODO code to generate booking id num
        //if(bookingID == -1){}
        bookingID = 0;
    }
    private Date generateDateObj(int day, int month, int year, int hour, int min){
        Calendar cal = Calendar.getInstance();
        cal.set(year,month,day,hour,min);
        return cal.getTime();
    }


}
