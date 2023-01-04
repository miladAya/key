package com.example.keymystery.ui;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.keymystery.R;
import com.example.keymystery.database.Question;
import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.FragmentChoseBinding;
import com.example.keymystery.model.SendScore;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChoseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChoseFragment extends Fragment {
    ViewModel viewModel;
    Question question;
    SendScore sendScore;
    MediaPlayer mediaPlayer;


    private static final String ARG_LEVEL_NUM = "levelsNum";
    private static final String ARG_PATTERN_NAME = "patternName";
    private static final String ARG_SCORE = "score";
    private static final String QUESTION = "question";

    private int mLevelsNum;
    private int question_id;
    private String mPatternName;
    private int mScore;


    public ChoseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sendScore= (SendScore) context;
    }

    // TODO: Rename and change types and number of parameters
    public static ChoseFragment newInstance(int levelsNo, String pattern_name, int score, int question_id) {
        ChoseFragment fragment = new ChoseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_LEVEL_NUM, levelsNo);
        args.putString(ARG_PATTERN_NAME, pattern_name);
        args.putInt(ARG_SCORE, score);
        args.putInt(QUESTION, question_id);
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
            question_id = getArguments().getInt(QUESTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentChoseBinding binding=FragmentChoseBinding.inflate(inflater,container,false);
        viewModel=new ViewModelProvider(getActivity()).get(ViewModel.class);
        viewModel.getAllQuestionsData().observe(getActivity(), new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                for (int i = 0; i < questions.size(); i++) {
                    if (questions.get(i).getLevel_no() == mLevelsNum && (questions.get(i).getPattern_name()
                            .equals(mPatternName))) {
                        Log.d("aha", String.valueOf(mLevelsNum));

                        Log.d("TAG", "onChangedqqwe: " + questions.get(i).getId());
                        Log.d("TAG", "onChangedasdas: " + question_id);

                        if (questions.get(i).getId() == question_id) {
                            question = questions.get(i);
                            binding.questionTv.setText(String.valueOf(question.getId()) + ".  " + question.getTitle());
                            binding.answer1.setText(question.getAnswer_1());
                            binding.answer2.setText(question.getAnswer_2());
                            binding.answer3.setText(question.getAnswer_3());
                            binding.answer4.setText(question.getAnswer_4());
                        }

//                        String bbb =question.getAnswer_1().toString();
//                        String cc =question.getHint().toString();
//                        Log.d("bar","bbb" +   "           " +  bbb);
//                        Log.d("car","cc" +    "           "  +    cc);
                        binding.answer1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (question.getTrue_answer().toString().contains(question.getAnswer_1().toString())){
                                    trueAnswer(question.getTrue_answer());
                                }

                                else{
                                    falseAnswer();
                                }



                            }
                        });

                        binding.answer2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (question.getTrue_answer().toString().contains(question.getAnswer_2().toString())){
                                    trueAnswer(question.getTrue_answer());
                                }
                                else{

                                    falseAnswer();                                }
                            }
                        });

                        binding.answer3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (question.getTrue_answer().toString().contains(question.getAnswer_3().toString())){
                                    trueAnswer(question.getTrue_answer());
                                }
                                else{
                                    falseAnswer();
                                }
                            }
                        });

                        binding.answer4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (question.getTrue_answer().toString().contains(question.getAnswer_4().toString())){
                                    trueAnswer(question.getTrue_answer());
                                }
                                else{
                                    falseAnswer();
                                }
                            }
                        });
                    }
                        };
                    }});

        return  binding.getRoot();
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
     mScore=mScore+2;
        Log.d("nunu", "trueAnswer:  " +mScore);
        sendScore.sendScore(mScore,trueAnswer);
        mediaPlayer=  MediaPlayer.create(getActivity(), R.raw.true_answer);
        mediaPlayer.start();
    }

}