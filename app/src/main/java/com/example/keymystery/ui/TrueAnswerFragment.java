package com.example.keymystery.ui;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.keymystery.R;
import com.example.keymystery.databinding.FragmentTrueBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrueAnswerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrueAnswerFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrueAnswerFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static TrueAnswerFragment newInstance() {
        TrueAnswerFragment fragment = new TrueAnswerFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTrueBinding binding=FragmentTrueBinding.inflate(inflater,container,false);
        binding.closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

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