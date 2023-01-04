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
import android.widget.Toast;

import com.example.keymystery.R;
import com.example.keymystery.database.Question;
import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.FragmentTorFBinding;
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
    MediaPlayer mediaPlayer;

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_LEVEL_NUM = "levelsNum";
    private static final String ARG_PATTERN_NAME = "patternName";
    private static final String ARG_SCORE = "score";


    private int mLevelsNum;
    private String mPatternName;
    private int mScore;


    public TorF_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sendScore= (SendScore) context;
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
                        //binding.questionTv.setText(question.getTitle());
                        binding.questionTv.setText(String.valueOf(question.getId()) +  ".  "  +  question.getTitle());

                        binding.no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (question.getTrue_answer().toString().contains(getString(R.string.no))){
                                  trueAnswer(question.getTrue_answer());
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
                                 trueAnswer(question.getTrue_answer());
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
      mediaPlayer=  MediaPlayer.create(getActivity(),R.raw.false_answer);
        mediaPlayer.start();
    }

    private void trueAnswer(String trueAnswer) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(new TrueAnswerFragment(), "a").commit();
        mScore = mScore + 1;
        sendScore.sendScore(mScore,trueAnswer);
        mediaPlayer=  MediaPlayer.create(getActivity(),R.raw.true_answer);
        mediaPlayer.start();

    }}