package mad.dinodine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Booking {

    int bookingID;
    Guest guest;
    int numOfPeople;
    Date date; //a java.util.date object that contains Date and time
    //TODO make Table.java
    // Table table;

    public Booking(){
        this.bookingID = -1;
        this.guest = null;
        this.numOfPeople = -1;
        this.date = null;
    }
    public Booking(Guest guest, int NumOfPeople, long date){
        generateBookingId();
        this.guest = guest;
        this.numOfPeople = NumOfPeople;
        this.date = new Date(date);
    }
    public Booking(Guest guest, int NumOfPeople, Date date){
        generateBookingId();
        this.guest = guest;
        this.numOfPeople = NumOfPeople;
        this.date = date;
    }
    public Booking(Guest guest, int NumOfPeople, int day, int month, int year, int hour, int min){
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
    }

    private void generateBookingId() {
        //TODO code to generate booking id num
        if(bookingID == -1){}
        bookingID = 0;
    }

    private Date generateDateObj(int day, int month, int year, int hour, int min){
        Calendar cal = Calendar.getInstance();
        cal.set(year,month,day,hour,min);


        return cal.getTime();

    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", guest=" + guest +
                ", numOfPeople=" + numOfPeople +
                ", date=" + date +
                '}';
    }
}
