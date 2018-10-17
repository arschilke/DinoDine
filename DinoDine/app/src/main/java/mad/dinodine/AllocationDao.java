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
    @Query("SELECT * FROM allocation ORDER BY booking")
    List<Allocation> getAllAllocations();
    @Query("SELECT * FROM allocation where booking = :b")
    List<Allocation> getAllAllocations(int b);
    @Query("SELECT * FROM allocation where `table` = :t")
    List<Allocation> getAllocationsForTable(String t);
}