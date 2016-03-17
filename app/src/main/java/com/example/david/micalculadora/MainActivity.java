package com.example.david.micalculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import net.java.dev.eval.Expression;
import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView lblResult;
    private boolean isParenthesis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblResult = (TextView) findViewById(R.id.lblResult);
        isParenthesis =false;
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btnDot).setOnClickListener(this);
        findViewById(R.id.btnClear).setOnClickListener(this);
        findViewById(R.id.btnAddition).setOnClickListener(this);
        findViewById(R.id.btnSubstraction).setOnClickListener(this);
        findViewById(R.id.btnMultiplication).setOnClickListener(this);
        findViewById(R.id.btnDivision).setOnClickListener(this);
        findViewById(R.id.btnEqual).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
        findViewById(R.id.btnParenthesisStart).setOnClickListener(this);
        findViewById(R.id.btnParenthesisEnd).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMultiplication :
                updateResult("*");
                break;
            case R.id.btnClear :
                clearScreen();
                break;
            case R.id.btnEqual :
                getResult(lblResult.getText().toString());
                break;
            case R.id.btnDelete :
                deleteLastLetter();
                break;
            default:
                updateResult(((Button) findViewById(v.getId())).getText().toString());
                break;
        }
    }

    public void showMessage(String value){
        Toast.makeText(this,value,Toast.LENGTH_SHORT).show();
    }

    public void updateResult(String value){
        String newVal = "";
        String lastVal = lblResult.getText().toString();
        if(lastVal.equals("0")){
            newVal = value;
        }
        else {
            newVal = lastVal+ value;
        }
        lblResult.setText(newVal);
    }

    public void clearScreen(){
        lblResult.setText("0");
    }

    public void getResult(String value){
        BigDecimal operationResult;
        try{
            Expression exp = new Expression(value);
            operationResult = exp.eval();
        }
        catch (Exception e){
            operationResult=BigDecimal.ZERO;
            showMessage("The operation entered is not valid");
        }
        lblResult.setText(operationResult.toString());
    }

    public void deleteLastLetter(){
        String value ="";

            showMessage(lblResult.getText().toString().length()+"");
            if(lblResult.getText().toString().length()>=1){
                value= lblResult.getText().toString();

                lblResult.setText(value.substring(0,value.length()-1));
            }


    }
}
