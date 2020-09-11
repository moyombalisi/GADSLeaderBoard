package com.innovm.gadsleaderboard.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.innovm.gadsleaderboard.R;
import com.innovm.gadsleaderboard.adapter.LearningLeadersAdapter;
import com.innovm.gadsleaderboard.model.LearningLeaders;
import com.innovm.gadsleaderboard.service.RetrofitApiCalls;
import com.innovm.gadsleaderboard.client.RetrofitClient;
import com.innovm.gadsleaderboard.databinding.FragmentLearningLeadersBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeadersFragment extends Fragment {
    LearningLeadersAdapter adapter;
    RetrofitApiCalls service;
    private FragmentLearningLeadersBinding binding;

    public LearningLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_learning_leaders, container, false);
        service = RetrofitClient.getRetrofitInstance().create(RetrofitApiCalls.class);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.shimmerLayout.startShimmer();
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.shimmerLayout.stopShimmer();
    }

    public void initAdapter() {
        Call<List<LearningLeaders>> call = service.getLearningLeaders();
        call.enqueue(new Callback<List<LearningLeaders>>() {
            @Override
            public void onResponse(Call<List<LearningLeaders>> call, Response<List<LearningLeaders>> response) {
                if (response.isSuccessful()) {
                    List<LearningLeaders> res = response.body();

                    binding.shimmerLayout.stopShimmer();
                    binding.shimmerLayout.setVisibility(View.GONE);
                    binding.rcvLearningLeaders.setVisibility(View.VISIBLE);

                    adapter = new LearningLeadersAdapter(getActivity(), res, leader -> {
                        //Do Nothing
                    });
                    binding.rcvLearningLeaders.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                    binding.rcvLearningLeaders.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<LearningLeaders>> call, Throwable t) {
                binding.shimmerLayout.stopShimmer();
            }
        });
    }
}