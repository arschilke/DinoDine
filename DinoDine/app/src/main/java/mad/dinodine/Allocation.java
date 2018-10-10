package mad.dinodine;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"booking","table"},
        foreignKeys = {@ForeignKey(entity = Booking.class, parentColumns = "booking_id", childColumns = "booking"),
                       @ForeignKey(entity = Table.class, parentColumns = "table_id", childColumns = "table")},
        indices = @Index(value = {"table"}))
public class Allocation {

//todo add index for foreign keys...
// 'table column references a foreign key but it is not part of an index'
    @NonNull
    private String booking;
    @NonNull
    private String table;

    @Ignore
    public Allocation(){
        this.booking = "empty";
        this.table = "empty";
    }

    public Allocation(String booking, String table){this.booking = booking;this.table=table;}

    public String getBooking(){return booking;}
    public String getTable(){return table;}

    public void setBooking(String booking){this.booking = booking;}
    public void setTable(String table){this.table = table;}

    public String toString(){
        return "Allocation[ booking:"+booking+", table:"+table+" ]";
    }
}
