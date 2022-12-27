package com.example.keymystery.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.FragmentChoseBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChoseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChoseFragment extends Fragment {
ViewModel viewModel;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChoseFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ChoseFragment newInstance(String param1, String param2) {
        ChoseFragment fragment = new ChoseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        FragmentChoseBinding binding=FragmentChoseBinding.inflate(inflater,container,false);
//        viewModel=new ViewModelProvider(getActivity()).get(ViewModel.class);
//        viewModel.getAllLevelData(1).observe(getActivity(), new Observer<List<Level>>() {
//            @Override
//            public void onChanged(List<Level> levelData) {
//                for (int i = 0; i < levelData.size(); i++) {
//                    binding.questionTv.setText(levelData.get(i).getQuestion());
//
//                }
//
//            }
//        });

        return  binding.getRoot();    }
}