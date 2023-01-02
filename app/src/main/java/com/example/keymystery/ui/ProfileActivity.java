package com.example.keymystery.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.keymystery.R;
import com.example.keymystery.database.Question;
import com.example.keymystery.database.User;
import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.ActivityProfileBinding;
import com.example.keymystery.model.ActionListener;
import com.hbb20.CountryCodePicker;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;

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
        viewModel=new ViewModelProvider(this).get(ViewModel.class);

        setSupportActionBar(binding.Tb);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.your_account);

        }
        if (viewModel.getAllUsersData()!=null){
           viewModel.getAllUsersData().observe(this, new Observer<List<User>>() {
               @Override
               public void onChanged(List<User> users) {
                   for (int i = 0; i < users.size(); i++) {
                       User user=users.get(i);
                       binding.nameUser.setText(user.getUserName());
                       binding.emailUser.setText(user.getEmail());
                       binding.birthDateUser.setText(user.getBirthDate());

                   }
               }
           });
        }






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
           String email=binding.emailUser.getText().toString();
          validateEmail(email);
       }
   });
   binding.Tb.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           insertData();
       }
   });


    }




    public boolean validateEmail(String emil){
        if (Patterns.EMAIL_ADDRESS.matcher(emil).matches()){
            return true;
        }else {
            binding.emailUser.setError("Invalid Email");
            return false;
        }}


    private void insertData() {
        String userName =binding.nameUser.getText().toString();
        String userEmail =binding.emailUser.getText().toString();
        Boolean userGender =Boolean.parseBoolean(binding.nameUser.getText().toString());
        String userCountry =binding.countryUser.getSelectedItem().toString();
        String userBirth =binding.birthDateUser.getText().toString();
        User user=new User(userName,userEmail,userGender,userCountry,userBirth);
            viewModel.insertUsers(user);
    }




    @Override
    public void sendName(String name) {
        binding.nameUser.setText(name);

    }

    @Override
    public void onBackPressed() {
        if (binding.nameUser!=null&& binding.emailUser!=null &&binding.emailUser!=null &&
                binding.countryUser!=null &&binding.birthDateUser!=null & binding.group!=null){
            insertData();
            finish();
        }
        else{
            Toast.makeText(this, "There is an empty field", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void sendEmail(String email) {
        binding.emailUser.setText(email);

    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                insertData();
                finish();

                return true;
        }
        return super.onOptionsItemSelected(item);}

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}