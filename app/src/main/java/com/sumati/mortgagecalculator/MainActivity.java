package com.sumati.mortgagecalculator;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private EditText amount;
    private TextView interestRate;
    private SeekBar seekbar;
    private TextView loan_term;
    private RadioGroup radioGroup;
    private RadioButton radioloanYears;
    private CheckBox taxes;
    private Button button;
    private TextView paymentResult;

    private double loanAmount;
    private double currentRate;
    private double extra_taxes;
    private int loanTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariables();


        // Initialize the textview with '5'.
        //interestRate.setText(Integer.toString(seekbar.getProgress()));
        seekbar.setProgress(50);
        //interestRate.setText(R.string.value_of_interest_rate);
        interestRate.setText(getString(R.string.rate_of_interest, 5.0));
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float value = 5.0f;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                value = ((float)progressValue / 10);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                interestRate.setText(getString(R.string.rate_of_interest, value));
               // interestRate.setText(R.string.rate_of_interest + Float.toString(value));
                currentRate = value;
            }
        });


        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.radioButton1) {
                    loanTime = 7;
                } else if (checkedId == R.id.radioButton2) {
                    loanTime = 15;
                } else {
                    loanTime = 30;
                }
            }

        });

        button = (Button) findViewById(R.id.calculate_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioloanYears = (RadioButton) findViewById(selectedId);
                calculatePayment();
            }
        });

    }


    // calculate monthly payment
    private void calculatePayment() {

        //Toast.makeText(getApplicationContext(), "Hello toast!", Toast.LENGTH_SHORT).show();
        double result = 0.0;
        double T = 0.0;

        if (amount.getText().toString().length() > 0 ) {

            loanAmount = Double.parseDouble(amount.getText().toString());
            loanTime = Integer.parseInt(radioloanYears.getText().toString());
            T = calculateTaxes();

            if (currentRate == 0) {
                result = (loanAmount / (loanTime *12)) + T;
            } else {
                double J = currentRate / 1200;
                double n = loanTime * 12;
                result = (loanAmount * (J / (1 - Math.pow(1 + J, -n)))) + T;
            }
        } else {
            //alert
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);

            // If the amount is missing
            builder.setTitle(R.string.missingEntries);

            // provide an OK button that simply dismisses the dialog
            builder.setPositiveButton(R.string.ok, null);

            // set the message to display
            builder.setMessage(R.string.provideEntries);

            // create AlertDialog from the AlertDialog.Builder
            AlertDialog errorDialog = builder.create();
            errorDialog.show();
        }

        //paymentResult.setText(R.string.monthly_payment + Double.toString(result));
        paymentResult.setText(getString(R.string.monthly_payment, result));

    }


    // A private method to help us initialize our variables.
    private void initializeVariables() {
        seekbar = (SeekBar) findViewById(R.id.interest_rate);
        interestRate = (TextView) findViewById(R.id.rate_textview);
        amount = (EditText) findViewById(R.id.borrowAmount);
        loan_term = (TextView) findViewById(R.id.loan_textview);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        taxes = (CheckBox) findViewById(R.id.taxes);
        button = (Button) findViewById(R.id.calculate_button);
        paymentResult = (TextView) findViewById(R.id.result);
        loanAmount = 0;
        currentRate = 5;
        extra_taxes = 0.0;
        paymentResult.setText(getString(R.string.monthly_payment, 0.0));
    }

    private double calculateTaxes() {
        // Toast.makeText(getApplicationContext(), "Loanamount:" + loanAmount, Toast.LENGTH_SHORT).show();
        if (taxes.isChecked()) {
            extra_taxes = 0.001 * loanAmount;
        } else {
            extra_taxes = 0.0;
        }
        return extra_taxes;
    }

}
