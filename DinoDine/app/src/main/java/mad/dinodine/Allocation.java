package mad.dinodine;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"booking","table_id"},
        foreignKeys = {@ForeignKey(entity = Booking.class, parentColumns = "booking_id", childColumns = "booking"),
                @ForeignKey(entity = Table.class, parentColumns = "table_id", childColumns = "table_id")},
        indices = @Index(value = {"table_id"}))
public class Allocation {
    @NonNull
    private String booking;
    @NonNull
    private String table_id;

    @Ignore
    public Allocation(){
        this.booking = "empty";
        this.table_id = "empty";
    }

    public Allocation(@NonNull String booking, @NonNull String table_id){
        this.booking = booking;
        this.table_id=table_id;
    }

    public @NonNull String getBooking(){return booking;}
    public @NonNull String getTable_id(){return table_id;}

    public void setBooking(@NonNull String booking){this.booking = booking;}
    public void setTable(String table){this.table_id = table;}

    public String toString(){
        return "Allocation[ booking:"+booking+", table:"+table_id+" ]";
    }
}