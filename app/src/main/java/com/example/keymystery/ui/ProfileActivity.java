package com.example.keymystery.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.keymystery.R;
import com.example.keymystery.database.User;
import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.ActivityProfileBinding;
import com.example.keymystery.model.ActionListener;
import com.hbb20.CountryCodePicker;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity implements ActionListener {
ActivityProfileBinding binding;
   ViewModel viewModel;

String  userName;
    private int years;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.Tb);
        setTitle("");

     binding.birthDateLout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Calendar now = Calendar.getInstance();
             com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                     new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                         @Override
                         public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                             years = year;
                             binding.birthDateUser.setText(String.valueOf(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year));
                         }
                     },
                     now.get(Calendar.YEAR),
                     now.get(Calendar.MONTH),
                     now.get(Calendar.DAY_OF_MONTH)
             );

             dpd.show(getSupportFragmentManager(), "datePicker");
             dpd.setAccentColor(Color.parseColor("#F6D45D"));
         }


     });
     binding.countryLout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
         CountryCodePicker countryCodePicker=new CountryCodePicker(ProfileActivity.this);
         countryCodePicker.getSelectedCountryName();

         }
     });

        binding.nameLout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileNameFragment profileFragment =new ProfileNameFragment();
                profileFragment.show(getSupportFragmentManager(),"name");

            }
        });
   binding.emailLout.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
          ProfileEmailFragment profileEmailFragment=new ProfileEmailFragment();
          profileEmailFragment.show(getSupportFragmentManager(),"email");
       }
   });

//    insertData();


    }
//
//    private void insertData() {
//        String userName =binding.nameUser.getText().toString();
//        String userEmail =binding.emailUser.getText().toString();
//        Boolean userGender =Boolean.parseBoolean(binding.nameUser.getText().toString());
//        String userCountry =binding.countryUser.getText().toString();
//        String userBirth =binding.birthDateUser.getText().toString();
//        User user=new User(userName,userEmail,userGender,userCountry,userBirth);
//        viewModel=new ViewModelProvider(this).get(ViewModel.class);
//        viewModel.insertUsers(user);
//    }




    @Override
    public void sendName(String name) {
        binding.nameUser.setText(name);

    }

    @Override
    public void sendEmail(String email) {
        binding.emailUser.setText(email);

    }

}