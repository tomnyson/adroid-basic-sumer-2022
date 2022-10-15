package com.example.android_summer_2022;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.android_summer_2022.DTO.PassData;

public class FragmentTwo extends Fragment{
    PassData listener;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String username;

        View view = inflater.inflate(R.layout.fragment_two, container, false);
        Bundle bundle = getArguments();
        if(bundle != null ) {
            username = bundle.getString("username");
            EditText edUserFragment = view.findViewById(
                    R.id.edUserFragment);
            edUserFragment.setText(username);
        }
        EditText edusername = view.findViewById(R.id.edUserFragment);
        TextView tv = view.findViewById(R.id.txtShowText);
        edusername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.i("TEST", edusername.getText().toString());
                listener.sendMessage(edusername.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try
        {
            if(context instanceof PassData)
            listener = (PassData) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()+ " must implement OnImageClickListener");
        }
    }

}
