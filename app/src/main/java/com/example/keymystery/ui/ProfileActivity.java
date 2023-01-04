package com.example.keymystery.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
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
    String  gender;
    String email;

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



email=binding.emailUser.getText().toString();


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
       int selectedId= binding.group.getCheckedRadioButtonId();
            binding.group.setTextAlignment(selectedId);
        Log.d("TAG", "onCreate: " );

    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)
                    gender="male";

                    break;
            case R.id.radioButton4:
                if (checked)
                    gender="female";
                    break;
        }
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
        Boolean userGender =Boolean.parseBoolean(gender);
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
            saveData();

    }

    private void saveData() {
        String emailUser=  binding.emailUser.getText().toString();
        String nameUser=  binding.nameUser.getText().toString();
        String birthDataUser=  binding.birthDateUser.getText().toString();

       if (emailUser.isEmpty()){
            binding.emailUser.setError("you should enter the Email");

        }
        else if (nameUser.isEmpty()){
            binding.nameUser.setError("you should enter the  Name");

        }
        else if (!validateEmail(emailUser)){
            binding.emailUser.setError("Invalid Email");
        }
        else {
            insertData();
            finish();
        }
    }

    @Override
    public void sendEmail(String email) {
        binding.emailUser.setText(email);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                saveData();


            case  R.id.playerRecord:
                Intent intent2=new Intent(ProfileActivity.this,PlayerRecordActivity.class);
                startActivity(intent2);

            return true;

        }return false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

}