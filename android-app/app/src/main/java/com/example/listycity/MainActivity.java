package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton", "Paris", "London", "Grande Prairie", "Toronto"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        Button addButton = findViewById(R.id.add_button);
        Button removeButton = findViewById(R.id.remove_button);
        removeButton.setEnabled(false);

        LinearLayout addCityDialog = findViewById(R.id.add_city_dialog);
        Button confirmButton = findViewById(R.id.confirm_button);
        EditText addCityText = findViewById(R.id.add_city_text);
        addCityDialog.setEnabled(false);
        addCityDialog.setVisibility(View.INVISIBLE);
        addCityText.setEnabled(false);
        confirmButton.setEnabled(false);



        addButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                addCityDialog.setEnabled(true);
                addCityDialog.setVisibility(View.VISIBLE);
                addCityText.setEnabled(true);
                confirmButton.setEnabled(true);
            }});

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = addCityText.getText().toString();
                if (!input.isEmpty()) {
                    dataList.add(input);
                }
                addCityText.setText("");
                cityAdapter.notifyDataSetChanged();
                addCityDialog.setEnabled(false);
                addCityText.setEnabled(false);
                confirmButton.setEnabled(false);
                addCityDialog.setVisibility(View.INVISIBLE);
            }
        });



        // for remove button
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // sets the onclick listener for the remove button
                removeButton.setEnabled(true);
                removeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dataList.remove(i);
                        cityAdapter.notifyDataSetChanged();
                        removeButton.setEnabled(false);
                    }
                });
            }

        });

        //setOnClickListener() for buttons

        //cityAdapter.notifyDataSetChanged(); to update the listview

    }
}