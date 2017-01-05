package com.example.sshahini.myapplication.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sshahini.myapplication.R;

/**
 * Created by Saeed shahini on 7/26/2016.
 */
public class SampleFragment2 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_sample_fragment2,container,false);
    }
}
