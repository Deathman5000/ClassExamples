package com.example.ra412063.tbill;
/*
    This app computes the market value given
    par value, yield, and time as .25, .5 .75 or 1 year
    author: rajan alex
    varsion 01/31/18

 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TBillActivity extends AppCompatActivity {

    EditText f_value, period, m_value, apr;
    Button compute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tbill);
        f_value = findViewById(R.id.faceValue);
        period = findViewById(R.id.timePeriod);
        apr = findViewById(R.id.yield);
        m_value = findViewById(R.id.marketValue);
        compute = findViewById(R.id.computeButton);

        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (f_value.getText().toString().equals("")){
                    Toast.makeText(TBillActivity.this, "No value entered",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    double fv = Double.parseDouble(f_value.getText().toString());
                    double y = Double.parseDouble(period.getText().toString());
                    double r = Double.parseDouble(apr.getText().toString());
                    double result = fv * (1 - r / 100 * y);
                    m_value.setText(String.format("$%,.2f", result));
                    f_value.setText(String.format("$%,.2f", fv));
                    period.setText(String.format("%4.2f",y ));
                    apr.setText(String.format("%5.3f", r));
                }
            }
        });
    }
}
