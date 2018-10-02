package mad.dinodine;

public class Guest {
    String firstName, lastName, phoneNum, email; //phoneExt, titleName, diet;
    int guestID;

    public Guest() {
        this.firstName = "";
        this.lastName = "";
        //this.phoneExt = "";
        this.phoneNum = "";
        //this.titleName = "";
        this.email = "";
        //this.diet = "";
        this.guestID = -1;
    }

    public Guest(String fname, String lName, String phoneNum, String phoneExt, String title, String email, String diet){
        this.firstName = fname;
        this.lastName = lName;
        //this.phoneExt = phoneExt;
        this.phoneNum = phoneNum;
        //this.titleName = title;
        this.email = email;
        //this.diet = diet;
        generateGuestID();
    }
    public Guest(String fname, String lName, String phoneNum, String phoneExt, String title, String email,String diet, int guestID){
        this.firstName = fname;
        this.lastName = lName;
        //this.phoneExt = phoneExt;
        this.phoneNum = phoneNum;
        //this.titleName = title;
        this.email = email;
        //this.diet = diet;
        this.guestID = guestID;
    }

    public void generateGuestID(){
        //TODO code generate Guest ID
        if(guestID == -1)
            guestID = 0;
    }
    //Useful methods
    public Guest lookupByID(int guestID){
        if(guestID == this.guestID){
            return this;
        }
        return null;
    }
    public Guest lookupByLastName(String lName){
        if (this.lastName == lName){
            return this;
        }
        return null;
    }
    public int lookupIDByLastName(String lName){
        if (this.lastName == lName){
            return guestID;
        }
        return 0;
    }

    //Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /*public String getPhoneExt() {
        return phoneExt;
    }

    public void setPhoneExt(String phoneExt) {
        this.phoneExt = phoneExt;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
    */

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Guest{" +
                " firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +/*
                ", phoneExt='" + phoneExt + '\'' +
                ", titleName='" + titleName + '\'' +*/
                ", email='" + email + '\'' +/*
                ", diet='" + diet + '\'' +*/
                ", guestID=" + guestID +
                '}';
    }
}
