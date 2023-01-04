package com.example.keymystery.ui;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.keymystery.databinding.FragmentFalseAnswerBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FalseAnswerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FalseAnswerFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CORRECT_ANSWER = "correct";

    // TODO: Rename and change types of parameters
    private String mCorrectAnswer;

    public FalseAnswerFragment() {
        // Required empty public constructor
    }




    // TODO: Rename and change types and number of parameters
    public static FalseAnswerFragment newInstance(String param1) {
        FalseAnswerFragment fragment = new FalseAnswerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CORRECT_ANSWER, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCorrectAnswer = getArguments().getString(ARG_CORRECT_ANSWER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentFalseAnswerBinding binding=FragmentFalseAnswerBinding.inflate(inflater,container,false);
        binding.closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.correctTv.setText(mCorrectAnswer);
        return binding.getRoot();
    }
    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params=getDialog().getWindow().getAttributes();
        params.width=ViewGroup.LayoutParams.MATCH_PARENT;
        params.height=ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }
}