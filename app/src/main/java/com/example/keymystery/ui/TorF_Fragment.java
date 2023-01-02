package com.example.keymystery.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.keymystery.R;
import com.example.keymystery.database.Question;
import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.FragmentTorFBinding;
import com.example.keymystery.model.SendID;
import com.example.keymystery.model.SendScore;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TorF_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TorF_Fragment extends Fragment {
    ViewModel viewModel;
    SendScore sendScore;
    SendID sendId;
    CountDownTimer countDownTimer;

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_LEVEL_NUM = "levelsNum";
    private static final String ARG_PATTERN_NAME = "patternName";
    private static final String ARG_SCORE = "score";


    private int mLevelsNum;
    private String mPatternName;
    private int mScore;
    //private int questionNum;


    public TorF_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sendScore= (SendScore) context;
        sendId= (SendID) context;

    }

    // TODO: Rename and change types and number of parameters
    public static TorF_Fragment newInstance(int  levelsNo ,String pattern_name ,int score) {
        TorF_Fragment fragment = new TorF_Fragment();
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
        FragmentTorFBinding binding=FragmentTorFBinding.inflate(inflater,container,false);
        viewModel=new ViewModelProvider(getActivity()).get(ViewModel.class);

        viewModel.getAllQuestionsData().observe(getActivity(), new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                for (int i = 0; i < questions.size(); i++) {
                    if (questions.get(i).getLevel_no() == mLevelsNum && (questions.get(i).getPattern_name()
                            .equals(mPatternName))) {
                        Question question = questions.get(i);
                        for (int j = 0; j < question.getIdQ(); j++) {
                            Question question1= questions.get(j);
                            //question1.getDuration();
                            Log.d("aaa","a"+ question1.getDuration());
                        }
                        binding.questionTv.setText(question.getTitle());
                        Log.d("aha", String.valueOf(question.getId()));
                        sendId.sendID((int) question.getId());

                        binding.no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (question.getTrue_answer().toString().contains(getString(R.string.no))){
                                  trueAnswer(question.getPoints());
                                }
                                else{
                            falseAnswer(question.getHint());
                                }
                            }
                        });


                        binding.yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (question.getTrue_answer().toString().contains(getString(R.string.yes))){
                                 trueAnswer(question.getPoints());
                                 Log.d("as","aa"+ question.getPoints());

                                }

                                else{
                                falseAnswer(question.getHint());
                                }
                            }
                        });

                    }
                    }


            }
        });
        return  binding.getRoot();
    }
    private void falseAnswer(String hint) {
        FalseAnswerFragment fragment= FalseAnswerFragment.newInstance(hint);
        fragment.show(getActivity().getSupportFragmentManager(), "f");
    }

    private void trueAnswer(int point) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(new TrueAnswerFragment(), "a").commit();
        mScore = mScore + point;
        sendScore.sendScore(mScore);

    }}