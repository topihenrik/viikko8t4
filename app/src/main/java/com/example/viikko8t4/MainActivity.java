package com.example.viikko8t4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    BottleDispenser bottle = BottleDispenser.getInstance();
    DecimalFormat format = new DecimalFormat("0.#");
    TextView etCurrentMoney;
    TextView etMessageConsole;
    SeekBar seekBar;
    TextView seekBarNumber;
    Spinner spinnerName;
    Spinner spinnerSize;

    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCurrentMoney = findViewById(R.id.currentMoney);
        etMessageConsole = findViewById(R.id.messageConsole);
        seekBar = findViewById(R.id.seekBar);
        seekBarNumber = findViewById(R.id.seekBarNumber);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarNumber.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        spinnerName = findViewById(R.id.spinnerName);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerName.setAdapter(adapter1);
        spinnerName.setOnItemSelectedListener(this);

        spinnerSize = findViewById(R.id.spinnerSize);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.sizes, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(adapter2);
        spinnerSize.setOnItemSelectedListener(this);

        context = MainActivity.this;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    public void addMoney(View view) {
        bottle.addMoney(Integer.parseInt(seekBarNumber.getText().toString()));
        seekBar.setProgress(0);
        etCurrentMoney.setText(format.format(bottle.getMoney()));
        etMessageConsole.setText("Klink! Added more money!");
        return;
    }

    public void buyBottle(View view) {
        int errCode = bottle.buyBottle(spinnerName.getSelectedItem().toString(), Double.valueOf(spinnerSize.getSelectedItem().toString()));
        if (errCode == 0) {
            etCurrentMoney.setText(format.format(bottle.getMoney()));
            etMessageConsole.setText("KACHUNK! " + spinnerName.getSelectedItem() + " came out of the dispenser!");
        } else if (errCode == 1) {
            etMessageConsole.setText("Not enough bottles!");
        } else if (errCode == 2) {
            etMessageConsole.setText("Add money first!");
        }
        return;
    }

    public void returnMoney(View view) {
        etMessageConsole.setText("Klink klink. Money came out! You got " + String.format("%.2f", bottle.getMoney()) + "€ back");
        bottle.returnMoney();
        etCurrentMoney.setText(String.valueOf(bottle.getMoney()));
        return;
    }

    public void fileSaveReceipt(View view) {
        if (bottle.lastPurchase == null) {
            etMessageConsole.setText("Buy something first!");
            return;
        }

        FileOutputStream fos = null;
        try {
            fos = openFileOutput("receipt.txt", MODE_PRIVATE);
            fos.write(("RECEIPT\n").getBytes());
            fos.write(bottle.lastPurchase.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        etMessageConsole.setText("Receipt saved into a file named: 'receipt.txt'.");
        return;
    }

}