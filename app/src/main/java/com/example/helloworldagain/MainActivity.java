package com.example.helloworldagain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements HelloFragment.OnSelectedItemListener {

    public static final String HELLO_FRAGMENT = "HELLO_TAG";

    HelloFragment helloFragment;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        button = findViewById(R.id.replace);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right);

                fragmentTransaction.replace(R.id.main_frame, new DetailsFragment());
                fragmentTransaction.addToBackStack("BACK");

                fragmentTransaction.commit();

            }
        });

//        button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HelloFragment helloFragment = (HelloFragment) getSupportFragmentManager()
//                        .findFragmentByTag(HELLO_FRAGMENT);
//                if (helloFragment != null) {
//                    helloFragment.sayHello();
//                }
//            }
//        });

        if (savedInstanceState == null) {


            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            helloFragment = new HelloFragment();
            fragmentTransaction.add(R.id.main_frame, helloFragment, HELLO_FRAGMENT);

            fragmentTransaction.commit();
        } else {
            helloFragment = (HelloFragment) fragmentManager.findFragmentById(Integer.parseInt(HELLO_FRAGMENT));
        }
    }

    @Override
    public void onUpdatedSelected(String message) {

        HelloFragment helloFragment = (HelloFragment) getSupportFragmentManager()
                .findFragmentByTag(HELLO_FRAGMENT);

        if (helloFragment != null) {
            helloFragment.sayHello(message);
        }
    }
}
