package com.makemyaccounting.accounting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makemyaccounting.dashboard.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageCustomerFragment extends Fragment {


    public ManageCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_customer, container, false);
    }

}
