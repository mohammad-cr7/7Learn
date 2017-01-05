package com.example.sshahini.myapplication.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sshahini.myapplication.MyApplication;
import com.example.sshahini.myapplication.R;
import com.example.sshahini.myapplication.adapter.ClothesViewPagerAdapter;
import com.example.sshahini.myapplication.view.Util;

public class BotickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botick);
        final TabLayout tabLayout=(TabLayout) findViewById(R.id.tablayout);
        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);
        ClothesViewPagerAdapter adapter=new ClothesViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Util.setTypefaceToolbar(this,toolbar);

        ViewGroup tabs= (ViewGroup) tabLayout.getChildAt(0);
        for (int i = 0; i < tabs.getChildCount(); i++) {
            ViewGroup tab= (ViewGroup) tabs.getChildAt(i);
            for (int j = 0; j < tab.getChildCount(); j++) {
                if (tab.getChildAt(j) instanceof TextView){
                    ((TextView)tab.getChildAt(j)).setTypeface(MyApplication.getIranianSansFont(this));
                }
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
