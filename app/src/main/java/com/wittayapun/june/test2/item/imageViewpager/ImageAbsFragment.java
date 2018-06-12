package com.wittayapun.june.test2.item.imageViewpager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wittayapun.june.test2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageAbsFragment extends Fragment {


    public ImageAbsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_abs, container, false);
    }

}
