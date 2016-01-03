package com.example.teaspoon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import teaspoon.annotations.OnUi;

public class MainActivity extends Activity {

	TextView tvHello;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHello = (TextView) findViewById(R.id.main_tv_hello);

        test_hideHelloTextView();
    }

    @OnUi private void test_hideHelloTextView() {
    	tvHello.setVisibility(View.INVISIBLE);
    }
}
