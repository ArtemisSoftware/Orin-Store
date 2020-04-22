package com.artemissoftware.orionstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.artemissoftware.orionstore.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //data binding
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }


}
