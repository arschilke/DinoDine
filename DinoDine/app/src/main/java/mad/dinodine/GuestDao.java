package mad.dinodine;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface GuestDao {
    @Insert
    public void insertGuest(Guest g);

    @Update
    public void updateGuest(Guest g);
    @Update
    public void updateGuests(Guest... g);

    @Delete
    public void deleteGuest(Guest g);
    @Delete
    public void deleteGuest(Guest... g);

    @Query("DELETE FROM guest")
    void deleteAll();

    @Query("SELECT * FROM guest ORDER BY guestID")
    List<Guest> getAllGuests();

    @Query("SELECT * FROM guest WHERE guestID = :id")
    Guest getGuest(String id);

}
