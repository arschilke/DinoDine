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

    @Query("SELECT * FROM booking ORDER BY booking_id")
    List<Booking> getAllBookings();

    @Query("SELECT * FROM booking WHERE booking_id = :id")
    Booking getBooking(int id);
}
