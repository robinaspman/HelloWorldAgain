package com.example.helloworldagain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;

import java.util.Objects;


public class HelloFragment extends Fragment {
    private EditText editText;
    private TextView textView;
    private Button button;

    private OnSelectedItemListener listener;

    public HelloFragment() {
    }

    public interface OnSelectedItemListener {
        void onUpdatedSelected(String message);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        // The first to be fired!
        // Called when the Fragment instance is associated with an Activity.
        // This does not mean the Activity is fully initialized
        super.onAttach(context);

        if (context instanceof OnSelectedItemListener) {
            listener = (OnSelectedItemListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement HelloFragment.onSelectedItemListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Is called when Fragment should create its view object hierarchy, either dynamically or via XML layout inflation.
        return inflater.inflate(R.layout.fragment_hello, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Called right after onCreateView is called.
        //Note: onViewCreated is only called if the view returned from onCreateView() is non-null
        // Any vew setup should occur. For example: view lookups and attaching view listeners.
        super.onViewCreated(view, savedInstanceState);

        button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSayHello("Updating...");

            }
        });
        editText = view.findViewById(R.id.editText);
        textView = view.findViewById(R.id.textView);

    }

    private void updateSayHello(String message) {

        String newTime = (System.currentTimeMillis() + message + editText.getText().toString());

        listener.onUpdatedSelected(newTime);
    }

    @Override
    public void onDestroyView() {
        // The fragment is about to be destroyed - cleanup!
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        // Is called when the fragment is no longer connected to the Activity
        // Get rid of references from onAttache() - null them out - to prevent memory leaks
        super.onDetach();

        //Clean up!
        this.listener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // This method is called after the parent Activities onCreate() method had completed.
        super.onActivityCreated(savedInstanceState);
    }

    void sayHello(String text) {
        textView.setText(text);

    }

    void sayHello() {

        textView.setText(editText.getText().toString());
    }
}
