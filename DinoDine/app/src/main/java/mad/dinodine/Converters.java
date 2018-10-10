package mad.dinodine;

import android.arch.persistence.room.TypeConverter;

import java.sql.Time;
import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date longToDate(long value){
        return new Date(value);
    }
    @TypeConverter
    public static long dateToLong(Date value){
        if(value==null)
        {return -1;}
        else
        return value.getTime();
    }

    @TypeConverter
    public static Time longToTime(long value){
        return new Time(value);
    }
    @TypeConverter
    public static long timeToLong(Time value){
        if(value == null)
        {return -1;}
        else
        return value.getTime();
    }
}
