package com.example.roomdatabase;

import android.app.Application;

import com.example.roomdatabase.Contacts;
import com.example.roomdatabase.ContactsRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ContactViewModel extends AndroidViewModel {

    private ContactsRepository repository;
    private LiveData<List<Contacts>> allConacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactsRepository(application);
        allConacts = repository.getAllcontacts();

    }

    public void insert(Contacts contact){
        repository.insert(contact);
    }

    public void delete(Contacts contacts){
        repository.delete(contacts);
    }

    public LiveData<List<Contacts>> getAllConacts() {
        return allConacts ;
    }
}
