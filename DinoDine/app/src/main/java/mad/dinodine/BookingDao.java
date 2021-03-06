package mad.dinodine;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BookingDao {
    @Insert
    public void insertBooking(Booking b);

    @Update
    public void updateBooking(Booking b);

    @Delete
    public void deleteBooking(Booking b);

    @Query("DELETE FROM booking")
    void deleteAll();
    @Query("DELETE FROM booking WHERE booking_id=:b")
    void deleteBooking(String b);

    @Query("SELECT * FROM booking ORDER BY date, startTime")//Order by booking_id
    List<Booking> getAllBookings();

    @Query("SELECT * FROM booking WHERE booking_id = :id")
    Booking getBooking(String id);

    @Query("SELECT * FROM booking WHERE guest = :guest")
    Booking getBookingForGuest(String guest);

    @Query("SELECT booking_id, numOfPeople, date, startTime, endTime, guest " +
           "FROM booking, allocation " +
           "WHERE allocation.table_id = :table " +
            "AND allocation.booking = booking.booking_id") //join statement with booking and allocation or just put it in allocation, or store table ID in booking object
    List<Booking> getBookingsForTable(String table);

    @Query("SELECT * FROM booking WHERE (SELECT DATE('now')) < endTime AND (SELECT DATE('now')) > startTime")
    List<Booking> getBookingsOnNow();
}
