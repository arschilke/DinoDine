package mad.dinodine;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Table.class, Guest.class, Booking.class, Allocation.class}, version = 1,exportSchema=false)
public abstract class AppRoomDB extends RoomDatabase {

    private static AppRoomDB INSTANCE;

    public abstract TableDao tableModel();
    public abstract GuestDao guestModel();
    public abstract BookingDao bookingModel();
    public abstract AllocationDao allocationModel();

    public static AppRoomDB getInMemoryDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppRoomDB.class)
                    //allowing queries on mainthread to get it up and running....
                    //remove and use Async tasks and background tasks.
                    .allowMainThreadQueries()
                    .build();
            //RoomDatabase.Builder saved = Room.databaseBuilder(context.getApplicationContext(), AppRoomDB.class,"myDB");

        }
        INSTANCE.toString();
        return INSTANCE;
    }
    public String getName(){
        return INSTANCE.toString();
    }
    public static void destroyInstance(){INSTANCE = null;}
}
