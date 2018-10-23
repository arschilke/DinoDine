package mad.dinodine;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AllocationDao {
    @Insert
    public void insertAllocation(Allocation a);

    @Update
    public void updateAllocation(Allocation a);
    @Update
    public void updateAllocations(Allocation... a);

    @Delete
    public void deleteAllocation(Allocation a);
    @Delete
    public void deleteAllocations(Allocation... a);

    @Query("DELETE FROM allocation")
    void deleteAll();
    @Query("DELETE FROM allocation WHERE booking=:b")
    void deleteAllocationWithBookingID(String b);
    @Query("DELETE FROM allocation WHERE table_id=:t")
    void deleteAllocationWithTableID(String t);
    @Query("DELETE FROM allocation WHERE booking=:b AND table_id=:t")
    void deleteAllocation(String b, String t);

    @Query("SELECT * FROM allocation ORDER BY booking")
    List<Allocation> getAllAllocations();
    @Query("SELECT * FROM allocation where booking = :b ORDER BY table_id")
    List<Allocation> getAllAllocations(String b);
    @Query("SELECT * FROM allocation where table_id = :t")
    List<Allocation> getAllocationsForTable(String t);
    //@Query("SELECT * FROM `table` where table_id = :t")
    //List<Table> getTabFromAll(String t);




}
