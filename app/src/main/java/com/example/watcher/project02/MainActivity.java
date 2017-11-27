package com.example.watcher.project02;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView resultView;
    double firstOperand;
    String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultView = ((TextView) findViewById(R.id.result));
    }

    @SuppressLint("SetTextI18n")
    public void onButtonClick(View v) {
        String old_result = resultView.getText().toString();
        if (old_result.equals("0")) {
            old_result = "";
        }

        if (((Button) v).getText().equals(".")) { //разрешена только одна точка
            findViewById(R.id.btnPoint).setEnabled(false);
        }

        if (old_result.length() < 9) {
            resultView.setText(old_result + ((Button) v).getText());
        }
    }

    public void clear(View v) {
        findViewById(R.id.btnPoint).setEnabled(true);
        resultView.setText("0");
    }

    @SuppressLint("SetTextI18n")
    public void setPlusMinus(View v) {
        String old_result = resultView.getText().toString();
        char[] chars = old_result.toCharArray();
        if (chars[0] == '-') {
            char[] copy = new char[chars.length - 1];
            System.arraycopy(chars, 1, copy, 0, chars.length - 1);
            resultView.setText(new String(copy));
        } else {
            resultView.setText("-" + old_result);
        }
    }

    public void onClickOperators(View v) {
        operator = ((Button) v).getText().toString();
        firstOperand = Double.parseDouble(resultView.getText().toString());
        clear(v);

        double res = 0;

        switch (operator) {
            case "X^2":
                res = firstOperand * firstOperand;
                break;
            case "Sqrt":
                res = Math.sqrt(firstOperand);
                break;
            case "X!":
                res = factorial((int) firstOperand);
                break;
            case "Log":
                res = Math.log(firstOperand);
                break;
            case "Ln":
                res = Math.log10(firstOperand);
                break;
            case "Sin":
                res = Math.sin(firstOperand);
                break;
            case "Cos":
                res = Math.cos(firstOperand);
                break;
            case "Tan":
                res = Math.tan(firstOperand);
                break;
            case "Pi":
                res = 3.141592;
                break;
            case "e":
                res = 2.71828;
                break;
        }

        if ((int) res == res)
            resultView.setText(String.valueOf((int) res));
        else
            resultView.setText(String.valueOf(res));

    }

    public long factorial(int n) {
        if (n > 20) return 0;
        else
            return (1 > n) ? 1 : n * factorial(n - 1);
    }

    public void equal(View view) {
        double secondOperand = Double.parseDouble(resultView.getText().toString());
        double res = 0;

        switch (operator) {
            case "+":
                res = firstOperand + secondOperand;
                break;
            case "-":
                res = firstOperand - secondOperand;
                break;
            case "X":
                res = firstOperand * secondOperand;
                break;
            case "/":
                if (secondOperand != 0)
                    res = firstOperand / secondOperand;
                break;
            case "%":
                res = firstOperand * secondOperand / 100;
                break;
        }

        if ((int) res == res)
            resultView.setText(String.valueOf((int) res));
        else
            resultView.setText(String.valueOf(res));
    }
}
