package mad.dinodine;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TableDao {
    @Insert
    public void insertTable(Table t);
    //Only need insert to use json file to insert tables into database..
    //Everytime? Or DB is persistent??

    /*
    @Update
    public void updateTable(Table... t);
    @Delete
    public void deleteTable(Table... t);
    */

    @Query ("DELETE FROM `table`")
    void deleteAll();
    @Query ("DELETE FROM `table` where table_id = :t")
    void deleteTable(String t);

    @Query("SELECT * FROM `table` ORDER BY table_id")
    List<Table> getAllTables();

    @Query ("SELECT * FROM `table` WHERE table_id = :table")
    Table getTable(String table);

    @Query("SELECT T.table_id,T.capacity,T.max_capacity FROM allocation A,`table` T WHERE A.booking = :b AND A.table_id = T.table_id")
    Table getTableFromBookingID(String b);
    //List<Table> getTablesFromBooking(String... b);

    //seperate query for max capacity so able to tell when table is more cramped? eg recommend table that has capacity first, rather than max capacity
    @Query ("SELECT * FROM `table` WHERE capacity >= :cap OR max_capacity >= :cap")
    List<Table> getTablesForCapacity(int cap);

    //Other queries? Get booked Tables? get free tables? get tablesForCapacity?
}
