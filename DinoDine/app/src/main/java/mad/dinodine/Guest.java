package mad.dinodine;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

@Entity
public class Guest {
    @PrimaryKey
    @NonNull
    private String guestID="";
    private String titleName;
    private String firstName;
    private String lastName;

    private String phoneExt;
    private String phoneNum;
    private String email;

    private String diet;


    public Guest() {
        //this.guestID=0; //autoGenerate??
        this.guestID = UUID.randomUUID().toString();
        this.titleName = "";
        this.firstName = "";
        this.lastName = "";

        this.phoneExt = "";
        this.phoneNum = "";
        this.email = "";

        this.diet = "";
    }

    public Guest(String fname, String lName, String phoneNum, String email){
        this.guestID = UUID.randomUUID().toString();
        this.firstName = fname;
        this.lastName = lName;
        //this.phoneExt = phoneExt;
        this.phoneNum = phoneNum;
        //this.titleName = title;
        this.email = email;
        //this.diet = diet;
    }

    public Guest(String guestID, String fname, String lName, String phoneNum, String email){
        this.guestID = guestID;
        this.firstName = fname;
        this.lastName = lName;
        //this.phoneExt = phoneExt;
        this.phoneNum = phoneNum;
        //this.titleName = title;
        this.email = email;
        //this.diet = diet;
    }


    //Getters-------------------------------------------------------------------
    public String getGuestID() { return this.guestID; }
    public String getDiet() { return this.diet; }
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getPhoneNum() {return phoneNum;}
    public String getPhoneExt() {return phoneExt;}
    public String getTitleName() {return titleName;}
    public String getEmail() {return email;}

    //Setters--------------------------------------------------------------------
    public void setGuestID(String x){this.guestID = x;}
    public void setDiet(String x){this.diet = x;}
    public void setFirstName(String firstName) { this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setPhoneNum(String phoneNum) {this.phoneNum = phoneNum;}
    public void setPhoneExt(String phoneExt) {this.phoneExt = phoneExt;}
    public void setTitleName(String titleName) {this.titleName = titleName;}
    public void setEmail(String email) {this.email = email;}

    @Override
    public String toString() {
        return "Guest[ " +
                " guestID:" + guestID +
                ", firstName:" + firstName +
                ", lastName:" + lastName +
                ", phoneNum:" + phoneNum +
                //", phoneExt:" + phoneExt +
                //", titleName:" + titleName +
                ", email:" + email +
                //", diet:" + diet +
                " ]";
    }
}
