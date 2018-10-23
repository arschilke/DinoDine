package mad.dinodine;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

@Entity
public class Table implements Serializable {
    //min max 'globals' for table capacity (Should max up with spinner in select people)
    private static final int capMIN = 0;
    private static final int capMAX = 10;
    private static final int mCapMIN = 0;
    private static final int mCapMAX = 14;

    @PrimaryKey
    @ColumnInfo(name = "table_id")
    @NonNull
    private String tableID = "";
    private int capacity;
    @ColumnInfo(name = "max_capacity")
    private int maxCapacity;
    @Ignore
    private ArrayList<String> neighbours; //hold tableID of other tables that i can be joined with

    //Constructors - add tables in via json file? Let sql handle ID parameter, or have them set in something like a json file
    @Ignore
    public Table() {
        tableID = UUID.randomUUID().toString();
        capacity = 0;
        maxCapacity = 0;
        neighbours = new ArrayList<String>();
    }

    @Ignore
    public Table(int cap, int max, ArrayList<String> n) {
        tableID = UUID.randomUUID().toString();
        capacity = cap;
        maxCapacity = max;
        //neighbours = new ArrayList<String>();
        neighbours = n;
    }
    //USING THIS ONE FOR ROOM LIBRARY
    public Table(String tableID, int capacity, int maxCapacity) {
        if(tableID.equals("")){tableID = UUID.randomUUID().toString();}
        else{this.tableID = tableID;}
        this.capacity = capacity;
        this.maxCapacity = maxCapacity;
        this.neighbours = new ArrayList<String>();
    }
    @Ignore
    public Table(@NonNull String tableID, int capacity, int maxCapacity, ArrayList<String> neighbours) {
        this.tableID = tableID;
        this.capacity = capacity;
        this.maxCapacity = maxCapacity; //or do we set maxCap to be capacity + a constant. eg tables can always have at least 2 chairs added..
        //neighbours = new ArrayList<String>();
        this.neighbours = neighbours;
    }

    //Getters
    @NonNull public String getTableID() {
        return tableID;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public ArrayList<String> getNeighbours() {
        return neighbours;
    }

    //Setters
    public void setCapacity(int cap) {
        if (cap > capMIN && cap <= capMAX) {
            capacity = cap;
        }
    }

    public void setMaxCapacity(int maxcap) {
        if (maxcap > mCapMIN && maxcap <= mCapMAX && maxcap >= capacity) {
            maxCapacity = maxcap;
        }
    }

    public void setNeighbours(ArrayList<String> n) {
        neighbours = n;
    }

    //toString/display methods
    public String toString() {
        return "Table[ tableID:" + tableID + ", capacity:" + capacity + ", maxCapacity:" + maxCapacity + ", neighbours:" +neighbours+ " ]";
    }
}
