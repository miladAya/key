package com.example.keymystery.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.keymystery.Adapters.LevelFragmentAdapter;
import com.example.keymystery.database.Question;
import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.ActivityLevelBinding;
import com.example.keymystery.model.SendID;
import com.example.keymystery.model.SendScore;

import java.util.ArrayList;
import java.util.List;

public class LevelActivity extends AppCompatActivity implements SendScore , SendID {
ActivityLevelBinding binding;
ViewModel viewModel;
    int qustionID;
    int scoreQ ;
    Intent  intent;

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        intent=new Intent(LevelActivity.this,SoundService.class);
        startService(intent);
        if (SettingsActivity.isNotChecked(false)==false){
            stopService(intent);
        }
//        if (((SettingsActivity)getActivity()).binding.soundSw.isChecked()==false){
//            stopService(intent);
//        }
            Intent intent2=getIntent();
        int levelsNum=  intent2.getIntExtra("levelsNum",0);
        binding.levelNum.setText("level"+ " " + levelsNum);
         scoreQ=Integer.parseInt( binding.scoreTv.getText().toString());
        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(TorF_Fragment.newInstance(levelsNum,"True of False",scoreQ));
        fragments.add(ChoseFragment.newInstance(levelsNum,"Choose the correct answer",scoreQ));
        fragments.add(CompleteFragment.newInstance(levelsNum,"Fill the answer",scoreQ));
        LevelFragmentAdapter levelFragmentAdapter=new LevelFragmentAdapter(this,fragments);
        binding.VP.setAdapter(levelFragmentAdapter);
                binding.VP.setUserInputEnabled(false);
                binding.VP.setCurrentItem(0);

        Toast.makeText(this, String.valueOf(qustionID), Toast.LENGTH_SHORT).show();

        viewModel=new ViewModelProvider(LevelActivity.this).get(ViewModel.class);
viewModel.getAllQuestionsData().observe(LevelActivity.this, new Observer<List<Question>>() {
    @Override
    public void onChanged(List<Question> questions) {
        Question question = null;
        for (int i = 0; i < questions.size(); i++) {
            question = questions.get(i);
//            for (int j = 0; j < question.getId(); j++) {
//                Question question1= questions.get(j);
//            if (question.getLevel_no() == levelsNum && question.getId() == qustionID) {
//                Log.d("plk", "onChanged: " + question.getDuration());
//
//
//                // }
//            }                Log.d("plk", "onChanged: " + question.getDuration());

        }
                
          //  }

//            if (levelsNum==question.getLevel_no()  &&  questions.get(i).getIdQ() == idQ){
            
           



       // }
    }
});
//        Log.d("mnmn", "onCreate: "  + qustionID  );
;

        binding.skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              scoreQ-=3;
                binding.VP.setCurrentItem(getItem(+1), true);
                binding.scoreTv.setText(String.valueOf(scoreQ));

            }
        });
        binding.continueTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.VP.setCurrentItem(getItem(+1), true);
            }
        });


    }
    private int getItem(int i) {
        return binding.VP.getCurrentItem() + i;

    }

@Override
public void sendID(int id) {
        id  =id+0;
        qustionID=id;

}


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
    }

    @Override
    public void sendScore(int score) {
        scoreQ=scoreQ+score;
       binding.scoreTv.setText(String.valueOf(scoreQ));

//        numQustion=numQ;
    }




    //        new TabLayoutMediator(binding.TL, binding.VP, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
////                tab.set(tb.get(position));
//            }
//        }).attach();

    }




