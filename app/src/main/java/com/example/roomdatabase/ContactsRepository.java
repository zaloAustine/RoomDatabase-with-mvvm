package com.example.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ContactsRepository  {
    Dao dao;
    LiveData<List<Contacts>> Allcontacts;

    public ContactsRepository(Application application){

        Database database = Database.getInstance(application);
        dao = database.ContactDao();
        Allcontacts = dao.getAllContacts();
    }

    public void insert(Contacts contacts){
        new InsertAsytask(dao).execute(contacts);
    }

    public void  delete(Contacts contacts){
        new DeletetAsytask(dao).doInBackground(contacts);

    }

    public LiveData<List<Contacts>> getAllcontacts() {
        return Allcontacts;
    }

    private static class InsertAsytask extends AsyncTask<Contacts,Void,Void>{
        private Dao dao;

        private InsertAsytask(Dao dao){
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Contacts... contacts) {
            dao.insert(contacts[0]);
            return null;
        }
    }



    private static class DeletetAsytask extends AsyncTask<Contacts,Void,Void>{
        private Dao dao;

        private DeletetAsytask(Dao dao){
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Contacts... contacts) {
            dao.delete(contacts[0]);
            return null;
        }
    }

}
