package mad.dinodine;

public class Guest {
    String firstName, lastName, phoneNum, phoneExt, titleName, email;
    int guestID;

    Guest(String fname, String lName, String phoneNum, String phoneExt, String title, String email){
        this.firstName = fname;
        this.lastName = lName;
        this.phoneExt = phoneExt;
        this.phoneNum = phoneNum;
        this.titleName = title;
        this.email = email;
        generateGuestID();
    }
    Guest(String fname, String lName, String phoneNum, String phoneExt, String title, String email, int guestID){
        this.firstName = fname;
        this.lastName = lName;
        this.phoneExt = phoneExt;
        this.phoneNum = phoneNum;
        this.titleName = title;
        this.email = email;
        this.guestID = guestID;
    }

    public void generateGuestID(){
        //TODO code generate Guest ID
        if(guestID == 0)
            guestID = 1;
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

    public String getPhoneExt() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
