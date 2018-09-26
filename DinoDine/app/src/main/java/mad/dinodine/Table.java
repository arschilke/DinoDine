package mad.dinodine;

import java.io.Serializable;

public class Table implements Serializable {
    //min max 'globals' for table capacity (Should max up with spinner in select people)
    static final int capMIN = 0;
    static final int capMAX = 10;
    static final int mCapMIN = 0;
    static final int mCapMAX = 14;

    static int _TID=0;
    int tableID;
    int capacity;
    int maxCapacity;

    //Constructors - add tables in via json file? Let sql handle ID parameter, or have them set in something like a json file
    public Table() {
        tableID = _TID++;
        capacity = 0;
        maxCapacity = 0;
    }
    public Table(int cap, int max)
    {
        tableID = _TID++;
        capacity = cap;
        maxCapacity = max;
    }
    public Table(int id, int cap, int max)
    {
        tableID=id;
        capacity=cap;
        maxCapacity=max; //or do we set maxCap to be capacity + a constant. eg tables can always have at least 2 chairs added..
    }

    //Getters
    public int getTableID()
    {return tableID;}
    public int getCapacity()
    {return capacity;}
    public int getMaxCapacity()
    {return maxCapacity;}

    //Setters
    public void setCapacity(int cap){
        if(cap > capMIN && cap <= capMAX) {
            capacity = cap;
        }
    }

    public void setMaxCapacity(int maxcap){
        if(maxcap > mCapMIN  && maxcap <= mCapMAX && maxcap >= capacity){
            maxCapacity = maxcap;
        }
    }

    //toString/display methods
    public String toString() {
        return "Table[ tableID: "+tableID+", capacity: "+capacity+", maxCapacity: "+maxCapacity+" ]";
    }
}
