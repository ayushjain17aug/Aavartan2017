package com.technocracy.app.aavartan.Contacts.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.technocracy.app.aavartan.Contacts.Model.Data.Contact;
import com.technocracy.app.aavartan.Contacts.Model.RetrofitContactProvider;
import com.technocracy.app.aavartan.Contacts.Presenter.ContactPresenter;
import com.technocracy.app.aavartan.Contacts.Presenter.ContactPresenterImpl;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.activity.LoginActivity;
import com.technocracy.app.aavartan.activity.NotificationsActivity;
import com.technocracy.app.aavartan.activity.UserActivity;
import com.technocracy.app.aavartan.helper.DatabaseHandler;
import com.technocracy.app.aavartan.helper.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity implements ContactView {

    private GridView contactsGridView;
    private ArrayList<Contact> contactList;
    private ContactsAdapter contactsAdapter;
    private DatabaseHandler db;
    private ProgressBar progressBar;
    private ContactPresenter presenter;
    private String type, toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Bundle data = getIntent().getExtras();
        type = data.getString("contact_type");
        if (type.equals("1"))
            toolbarTitle = "Our Team";
        else
            toolbarTitle = "Team Android";
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(toolbarTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        contactList = new ArrayList<>();
        db = new DatabaseHandler(getApplicationContext());
        contactsGridView = (GridView) findViewById(R.id.contacts_gridView);

        presenter = new ContactPresenterImpl(this, new RetrofitContactProvider(), this);
        presenter.getContact(type);
    }


    @Override
    public void showProgressBar(boolean b) {
        if (b)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(findViewById(R.id.contact_relativeLayout), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void showContacts(List<Contact> contacts) {
        Log.d("Aavartan2k17", "On Show");
        db.deleteAllContacts();
        for (Contact i : contacts) {
            db.addContact(i);
            contactList.add(i);
        }
        contactsAdapter = new ContactsAdapter(contactList, this);
        contactsGridView.setAdapter(contactsAdapter);
    }

    @Override
    public void showContactsFromDB() {
        contactList = db.getAllContacts();
        if (contactList != null) {
            contactsAdapter = new ContactsAdapter(contactList, this);
            contactsGridView.setAdapter(contactsAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_Login:
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                boolean userLoggedIn = sessionManager.isLoggedIn();
                if (userLoggedIn) {
                    Intent intent = new Intent(ContactsActivity.this, UserActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ContactsActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.action_notification:
                Intent intent = new Intent(ContactsActivity.this, NotificationsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}