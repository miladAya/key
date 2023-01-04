package com.example.keymystery.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.erif.CountDown.CountDown;
import com.erif.CountDown.CountDownListener;
import com.erif.CountDown.Times;
import com.example.keymystery.Adapters.LevelFragmentAdapter;
import com.example.keymystery.R;
import com.example.keymystery.database.Question;
import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.ActivityLevelBinding;
import com.example.keymystery.model.InterfaceTimer;
import com.example.keymystery.model.SendScore;

import java.util.ArrayList;
import java.util.List;

public class LevelActivity extends AppCompatActivity implements SendScore, InterfaceTimer {
    ActivityLevelBinding binding;
    ViewModel viewModel;
    int qustionID;
    int scoreQ;
    Intent intent;
    long duration;
    String hintQ = null;
    String correctAnswer = null;
    CountDownTimer timer;
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    int switchON;
    int levelsNum;
    int countQuestionAnswered = 0;
    private static CountDownTimer testTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sp = getSharedPreferences("Date_user", MODE_PRIVATE);
        edit = sp.edit();


        intent = new Intent(LevelActivity.this, SoundService.class);
        switchON = R.id.soundSw;
        Intent intent1 = getIntent();
        boolean switchChecked = intent1.getBooleanExtra("switch_on", true);
        if (switchChecked == true) {
            startService(intent);

        } else
            stopService(intent);


//        if (SettingsActivity.isNotChecked(false)==false){
//            stopService(intent);
//        }
//        if (((SettingsActivity,)).binding.soundSw.isChecked()==false){
//            stopService(intent);
//        }

        Intent intent2 = getIntent();
        levelsNum = intent2.getIntExtra("levelsNum", 0);
        Log.d("TAasdasdasdG", "onCreate: " + levelsNum);
        binding.levelNum.setText("level" + " " + levelsNum);
        scoreQ = Integer.parseInt(binding.scoreTv.getText().toString());


        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(TorF_Fragment.newInstance(levelsNum, "True of False", scoreQ));

        if (levelsNum == 1) {
            fragments.add(ChoseFragment.newInstance(levelsNum, "Choose the correct answer", scoreQ, 4));
            fragments.add(ChoseFragment.newInstance(levelsNum, "Choose the correct answer", scoreQ, 2));
        } else {
            fragments.add(ChoseFragment.newInstance(levelsNum, "Choose the correct answer", scoreQ, 2));
        }
        fragments.add(CompleteFragment.newInstance(levelsNum, "Fill the answer", scoreQ));


        LevelFragmentAdapter levelFragmentAdapter = new LevelFragmentAdapter(this, fragments);
        binding.VP.setAdapter(levelFragmentAdapter);
        int count = levelFragmentAdapter.getItemCount();
        Log.d("iu", "onCreate: " + count);
        binding.VP.setUserInputEnabled(false);
        binding.VP.setCurrentItem(0);
        //progresTimer();


        viewModel = new ViewModelProvider(LevelActivity.this).get(ViewModel.class);
        viewModel.getAllQuestionsData().observe(LevelActivity.this, new androidx.lifecycle.Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                for (int i = 0; i < questions.size(); i++) {
                    Question question = questions.get(i);
                    duration = question.getDuration();
                    progresTimer(duration * 30, false);
                    binding.continueTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (correctAnswer != null) {
                                if (levelsNum == 1) {
                                    if (binding.VP.getCurrentItem() == 3) {
                                        finish();
                                    }
                                }
                                if (levelsNum == 2) {
                                    if (binding.VP.getCurrentItem() == 2) {
                                        finish();
                                    }
                                }
                                binding.VP.setCurrentItem(getItem(+1), true);
                                progresTimer(duration * 30, true);

                                //  timer.cancel();
                                countQuestionAnswered += 1;
//                                if (binding.VP.getCurrentItem() <questions.size() ){
//                                    finish();
//
//                                }
                            } else
                                binding.VP.setCurrentItem(getItem(+0), true);
                        }
                    });


                }
            }
        });
        //Toast.makeText(this, String.valueOf(duration), Toast.LENGTH_SHORT).show();

        binding.skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreQ-=3;
                binding.VP.setCurrentItem(getItem(+1), true);
                binding.scoreTv.setText(String.valueOf(scoreQ));
                countQuestionAnswered+=1;
//                if (correctAnswer == null){
//                    binding.skip.setVisibility(View.VISIBLE);
//                }else
//                    binding.skip.setVisibility(View.INVISIBLE);

//                            if ( binding.VP.getCurrentItem() > binding.VP.getAdapter().getItemId(questions.size())){
//                                finish();
//
//                            }
            }
        });
//        edit.putInt("score",scoreQ);
//        edit.apply();

//if (correctAnswer!=null){
//    timer.cancel();
//    finish();
//
//}
        Log.d("iii", "onCreate: " +countQuestionAnswered);
    }


    private int getItem(int i) {
        return binding.VP.getCurrentItem() + i;

    }

    @Override
    protected void onDestroy() {
        stopService(intent);
        super.onDestroy();
    }

    private void progresTimer(long m, boolean isClear) {
        long msFuture = m;
        // Log.d("dd", "progresTimer: " + question.getDuration());

        binding.progressBar.setMax((int) msFuture);

        testTimer = new CountDownTimer(msFuture, 1000) {
            public void onTick(long msUntilFinished) {
                binding.progressBar.setProgress((int) msUntilFinished);
            }

            public void onFinish() {
                binding.skip.setVisibility(View.INVISIBLE);
                binding.continueTv.setVisibility(View.VISIBLE);
                binding.progressBar.setProgress(100);
                //binding.VP.setCurrentItem(getItem(+1), true);

            }
        }.start();
    }


    @Override
    public void sendScore(int score, String trueAnswer) {
        scoreQ = scoreQ + score;
        binding.scoreTv.setText(String.valueOf(scoreQ));
        correctAnswer = trueAnswer;

    }


    @Override
    public void endTime() {
        binding.skip.setVisibility(View.INVISIBLE);
        binding.continueTv.setVisibility(View.VISIBLE);
        binding.progressBar.setProgress(100);
    }

    @Override
    public void onUpdate(int timer, int millis) {
        binding.progressBar.setProgress(millis);

    }
}




