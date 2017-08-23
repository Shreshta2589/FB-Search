package com.example.alladishreshta.assignment9;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.alladishreshta.assignment9.MainActivity.EXTRA_MESSAGE;
import static com.example.alladishreshta.assignment9.R.id.button;
import static com.example.alladishreshta.assignment9.R.id.editText;


public class MainFragment extends android.support.v4.app.Fragment {
    private EditText input;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.content_main, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Search on FB");

        input = (EditText) v.findViewById(editText);

        Button buttonsearch=(Button)v.findViewById(R.id.button2);
        buttonsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DisplayMessageActivity.class);
                String message = input.getText().toString();
                if (message.matches("")) {
                    Toast.makeText(getActivity(), "Please enter a Keyword", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }

        });

        Button buttonclear=(Button)v.findViewById(R.id.button);
        buttonclear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                input.setText(" ");
            }
        });

        return v;

    }
}
