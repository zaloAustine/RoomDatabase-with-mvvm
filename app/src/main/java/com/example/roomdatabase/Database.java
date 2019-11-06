package com.example.roomdatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {Contacts.class}, version = 1,exportSchema = false)
    public abstract class Database extends RoomDatabase {
        public static Database instance;

        public abstract Dao ContactDao();


        public static synchronized Database getInstance(Context context){

            if(instance==null){
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        Database.class,"Contacts").fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build();
            }

            return instance;
        }

        public static RoomDatabase.Callback callback = new RoomDatabase.Callback(){

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                new populateDb(instance).execute();
            }
        };


        private static  class  populateDb extends AsyncTask<Void,Void,Void>{
            Dao dao;

            private  populateDb(Database database){
                dao = database.ContactDao();

            }

            @Override
            protected Void doInBackground(Void... voids) {
                dao.insert(new Contacts("Zalo","0779292074"));
                dao.insert(new Contacts("Zalo","0779292074"));
                dao.insert(new Contacts("Zalo","0779292074"));
                dao.insert(new Contacts("Zalo","0779292074"));
                return null;
            }
        }

    }
