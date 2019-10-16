package com.pslyp.quailsmartfarm_home_client.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pslyp.quailsmartfarm_home_client.R;
import com.pslyp.quailsmartfarm_home_client.activities.SigninActivity;
import com.pslyp.quailsmartfarm_home_client.services.Prefs;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements View.OnClickListener {

    private Button logOutButton;

    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);

        setHasOptionsMenu(true);

        initInstance(view);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.me_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.log_out_button :
                Prefs prefs = new Prefs.Builder(getContext())
                        .name("Authen")
                        .mode(getContext().MODE_PRIVATE)
                        .build();

                prefs.clear();

                startActivity(new Intent(getContext(), SigninActivity.class));
                getActivity().finish();
                break;
        }
    }

    private void initInstance(View view) {
        logOutButton = view.findViewById(R.id.log_out_button);
        logOutButton.setOnClickListener(this);
    }

}
