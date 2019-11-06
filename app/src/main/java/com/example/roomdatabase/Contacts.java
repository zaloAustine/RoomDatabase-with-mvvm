package com.example.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Contacts {

    public Contacts() {
    }

    @PrimaryKey(autoGenerate = true)
       private int id;

        @ColumnInfo(name = "Name")
        private String Name;


        @ColumnInfo(name = "Contact")
        private String Contact;

    public Contacts(String name, String contact) {
        Name = name;
        Contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}
