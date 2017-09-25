package com.technocracy.app.aavartan;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.technocracy.app.aavartan.activity.RegisterActivity;
import com.technocracy.app.aavartan.gallery.Model.Data.Image;
import com.technocracy.app.aavartan.helper.SessionManager;


public class SignupFragment extends Fragment {

    private ProgressDialog pDialog;
    private SessionManager session;
    private EditText phoneEditText;
    private EditText collegeEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText password0EditText;
    private EditText department;
    private EditText semester;
    private EditText captcha;
    private EditText password1EditText;
    private EditText emailEditText;
    private Button btnRegister;


    public SignupFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phoneEditText = (EditText) getView().findViewById(R.id.phone);
        collegeEditText = (EditText) getView().findViewById(R.id.college);
        firstNameEditText = (EditText) getView().findViewById(R.id.first_name);
        lastNameEditText = (EditText) getView().findViewById(R.id.last_name);
        password0EditText = (EditText) getView().findViewById(R.id.password0);
        password1EditText = (EditText) getView().findViewById(R.id.password1);
        emailEditText = (EditText) getView().findViewById(R.id.email);
        department = (EditText) getView().findViewById(R.id.department);
        semester = (EditText) getView().findViewById(R.id.semester);
        captcha = (EditText) getView().findViewById(R.id.captcha);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_signup, container, false);
        return v;
    }

}

