package com.example.keymystery.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.keymystery.R;
//import com.example.keymystery.database.Question;
import com.example.keymystery.database.Levels;
import com.example.keymystery.database.Question;
import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.ActivityHomeBinding;
import com.example.keymystery.model.AppUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {
ActivityHomeBinding binding;
ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate( getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel=new ViewModelProvider(HomeActivity.this).get(ViewModel.class);

        String jsonStr= AppUtility.readFromAssets(getApplicationContext(),"json/puzzleGameData.json");
        parseJsonFromAssets(jsonStr);

        binding.startPlayingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.startPlayingBtn.setBackgroundResource(R.drawable.background);
                startActivity(new Intent(HomeActivity.this,LevelsActivity.class));
            }
        });
        binding.settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.settingsBtn.setBackgroundResource(R.drawable.background);
                startActivity(new Intent(HomeActivity.this,SettingsActivity.class));

            }
        });
        binding.exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.exitBtn.setBackgroundResource(R.drawable.background);
                finish();
            }
        });


    }


    private void parseJsonFromAssets(String jsonStr) {
//        arrayLevel=new ArrayList<>();
        try {
            if (jsonStr!=null){
                JSONArray jsonArray=new JSONArray(jsonStr);
                for (int i = 0; i < jsonArray.length(); i++) {
                    String item = jsonArray.get(i).toString();
                    JSONObject level = new JSONObject(item);
                    int level_no =level.getInt("level_no");
                    int unlock_points =level.getInt("unlock_points");
                    Levels levels=new Levels(level_no,unlock_points);
//                    viewModel.insertLevels(levels);
                    JSONArray questionLevelOne =level.getJSONArray("questions");
                    for (int j = 0; j < questionLevelOne.length(); j++) {
                        String itemLevelOne = questionLevelOne.get(j).toString();
                        JSONObject jsonLevelOne = new JSONObject(itemLevelOne);
                        int id =jsonLevelOne.getInt("id");
                        Log.d("lkl", "parseJsonFromAssets: " +id);
                        String title=jsonLevelOne.getString("title");
                        String answer_1=jsonLevelOne.getString("answer_1");
                        String answer_2=jsonLevelOne.getString("answer_2");
                        String answer_3=jsonLevelOne.getString("answer_3");
                        String answer_4=jsonLevelOne.getString("answer_4");
                        String true_answer=jsonLevelOne.getString("true_answer");
                        int points=jsonLevelOne.getInt("points");
                        long duration=jsonLevelOne.getLong("duration");
                        JSONObject pattern=   jsonLevelOne.getJSONObject("pattern");
                        int pattern_id =  pattern.getInt("pattern_id");
                        String  pattern_name=pattern.getString("pattern_name");
                        String  hint =jsonLevelOne.getString("hint");
                        Question question=new Question(id,level_no,title,answer_1,answer_2,answer_3,answer_4,true_answer,points,duration,hint,pattern_id,pattern_name);
                        viewModel.insertLevels(levels);
                        viewModel.insertQuestion(question);
                    }

                }}
        } catch (JSONException e) {
            e.printStackTrace();
        }
}}