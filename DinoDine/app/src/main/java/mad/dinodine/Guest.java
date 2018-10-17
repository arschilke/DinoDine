package mad.dinodine;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.UUID;

@Entity
public class Guest {
    @PrimaryKey
    @NonNull
    private String guestID="";
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String email;



    public Guest() {
        this.guestID = UUID.randomUUID().toString();
        this.firstName = " ";
        this.lastName = " ";
        this.phoneNum = "";
        this.email = "";

    }

    public Guest(String fname, String lName, String phoneNum, String email){
        this.guestID = UUID.randomUUID().toString();
        this.firstName = fname;
        this.lastName = lName;
        if (fname.trim().equals("") && lName.trim().equals("") ){
            throw new NullPointerException("Both Names are empty");
        }
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public Guest(String guestID, String fname, String lName, String phoneNum, String email){
        this.guestID = guestID;
        this.firstName = fname;
        this.lastName = lName;
        this.phoneNum = phoneNum;
        this.email = email;
    }


    //Getters-------------------------------------------------------------------
    public String getGuestID() { return this.guestID; }
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getPhoneNum() {return phoneNum;}
    public String getEmail() {return email;}

    //Setters--------------------------------------------------------------------
    public void setGuestID(String x){this.guestID = x;}
    /* NEEDS TRY AND CATCH
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
        if (firstName.equals("") && lastName.equals("")) {
                throw new NullPointerException("Input Error");
        }
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
        if (firstName.equals("") && lastName.equals("")) {
            throw new NullPointerException("Input Error");
        }
        }
    public void setPhoneNum(String phoneNum) {this.phoneNum = phoneNum;}
    public void setEmail(String email) {this.email = email;}

    @Override
    public String toString() {
        return "Guest[ " +
                " guestID:" + guestID +
                ", firstName:" + firstName +
                ", lastName:" + lastName +
                ", phoneNum:" + phoneNum +
                ", email:" + email +
                " ]";
    }
}
