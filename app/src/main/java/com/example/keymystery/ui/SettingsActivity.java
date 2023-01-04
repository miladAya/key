package com.example.keymystery.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;

import android.app.ActivityManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.keymystery.R;
import com.example.keymystery.database.Question;
import com.example.keymystery.database.User;
import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.ActivitySettingsBinding;
import com.example.keymystery.model.JobService;

import java.util.List;
import java.util.prefs.Preferences;

public class SettingsActivity extends AppCompatActivity {
ActivitySettingsBinding binding;
ViewModel viewModel;
MediaPlayer mediaPlayer;
    User user;
    Question question;
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    boolean switchStatus;
    public static final int jsonId=100;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        edit=getSharedPreferences("switch",MODE_PRIVATE).edit();

        setSupportActionBar(binding.Tb);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.settings);

            intent = new Intent(SettingsActivity.this, SoundService.class);
            JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            viewModel = new ViewModelProvider(this).get(ViewModel.class);
            if (viewModel.getAllUsersData() != null) {
                viewModel.getAllUsersData().observe(this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {

                        for (int i = 0; i < users.size(); i++) {
                            user = users.get(i);
                            binding.nameUser.setText(user.getUserName());


                        }
                    }
                });
                viewModel.getAllQuestionsData().observe(this, new Observer<List<Question>>() {
                    @Override
                    public void onChanged(List<Question> questions) {
                        for (int i = 0; i < questions.size(); i++) {
                            question = questions.get(i);

                        }
                    }
                });
            }




            binding.profileLout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(SettingsActivity.this, ProfileActivity.class));

                }
            });
            binding.reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewModel.deleteUser(user);
                    edit.clear();
//

                }
            });
            binding.soundSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (compoundButton.isChecked()) {

                        edit.putBoolean("switchStatus" ,true);
                        binding.soundSw.setChecked(true);

                        edit.commit();

                    } else  {
                        edit.putBoolean("switchStatus" ,false);
                        binding.soundSw.setChecked(false);
                        edit.commit();



                    }
                }
            });

                //binding.soundSw.setChecked(sp.getBoolean("switchStatus",true));





            binding.notificationSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (binding.notificationSw.isChecked()) {

                        ComponentName componentName = new ComponentName(getBaseContext(), JobService.class);
                        JobInfo jobInfo = new JobInfo.Builder(jsonId, componentName)
                                //After what time does the service run
                                .setPeriodic(24 * 60 * 60 , 5 * 60 )
                                .build();

                        jobScheduler.schedule(jobInfo);
                    } else {
                        jobScheduler.cancel(jsonId);

                    }
                }
            });

        }



    }

//

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }


}