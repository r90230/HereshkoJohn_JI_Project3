//John Hereshko - Java I - Project 3

package com.jeshko.android.hereshko_john_ji_project3;

public class Place{
    String name;
    String subName;

    public Place () {
        name = "";
        subName = "";
    }

    public Place (String name, String subName) {
        this.name = name;
        this.subName = subName;
    }

    public void setName(String enteredName) {
        this.name = enteredName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String NameToString () {

        return name;
    }

    public String SubNameToString () {
        return subName;
    }
}
