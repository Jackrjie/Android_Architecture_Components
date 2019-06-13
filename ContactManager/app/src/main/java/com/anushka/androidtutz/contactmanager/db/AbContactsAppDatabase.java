package com.anushka.androidtutz.contactmanager.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.anushka.androidtutz.contactmanager.db.entity.Contact;

/**
 * create by Tim on 6/2/2019
 * email: jackrjie@gmail.com
 */
@Database(entities = {Contact.class},version = 1)
public abstract class AbContactsAppDatabase extends RoomDatabase {
    public abstract IContactDAO getContactDAO();

}
