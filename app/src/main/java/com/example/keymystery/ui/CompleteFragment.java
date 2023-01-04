package com.example.keymystery.ui;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.keymystery.R;
import com.example.keymystery.database.Question;
import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.FragmentChoseBinding;
import com.example.keymystery.databinding.FragmentCompleteBinding;
import com.example.keymystery.model.SendScore;

import java.util.List;


public class CompleteFragment extends Fragment {
    ViewModel viewModel;
    Question question;
    SendScore sendScore;
    MediaPlayer mediaPlayer;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LEVEL_NUM = "levelsNum";
    private static final String ARG_PATTERN_NAME = "patternName";
    private static final String ARG_SCORE = "score";


    private int mLevelsNum;
    private String mPatternName;
    private int mScore;

    public CompleteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sendScore= (SendScore) context;
    }

    // TODO: Rename and change types and number of parameters
    public static CompleteFragment newInstance(int  levelsNo ,String pattern_name,int score) {
        CompleteFragment fragment = new CompleteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_LEVEL_NUM, levelsNo);
        args.putString(ARG_PATTERN_NAME, pattern_name);
        args.putInt(ARG_SCORE, score);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLevelsNum = getArguments().getInt(ARG_LEVEL_NUM);
            mPatternName = getArguments().getString(ARG_PATTERN_NAME);
            mScore = getArguments().getInt(ARG_SCORE);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentCompleteBinding binding=FragmentCompleteBinding.inflate(inflater,container,false);
        viewModel=new ViewModelProvider(getActivity()).get(ViewModel.class);
        viewModel.getAllQuestionsData().observe(getActivity(), new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                for (int i = 0; i < questions.size(); i++) {
                    if (questions.get(i).getLevel_no() == mLevelsNum && (questions.get(i).getPattern_name()
                            .equals(mPatternName))) {
                        Log.d("aha", String.valueOf(mLevelsNum));
                        Question question = questions.get(i);
                        //binding.questionTv.setText(question.getTitle());
                        binding.questionTv.setText(String.valueOf(question.getId()) +  ".  "  +  question.getTitle());

                    binding.saveBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.saveBtn.setVisibility(View.INVISIBLE);
                            if (question.getTrue_answer().toString().contains(binding.answer.getText().toString())){
                                trueAnswer(question.getTrue_answer());

                            }

                            else{
                               falseAnswer();
                            }
                        }
                    });
                    }

                }


            }
        });
        return binding.getRoot();
    }
    private void falseAnswer() {
        FalseAnswerFragment fragment= FalseAnswerFragment.newInstance(question.getHint());
        fragment.show(getActivity().getSupportFragmentManager(), "f");
        mediaPlayer=  MediaPlayer.create(getActivity(),R.raw.false_answer);
        mediaPlayer.start();
    }

    private void trueAnswer(String trueAnswer) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(new TrueAnswerFragment(),"a").commit();
        mScore=mScore+5;
        sendScore.sendScore(mScore,trueAnswer);
        mediaPlayer=  MediaPlayer.create(getActivity(),R.raw.true_answer);
        mediaPlayer.start();

    }
}