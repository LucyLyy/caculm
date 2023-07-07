package com.example.calculator;

import androidx.annotation.Nullable;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int[] btnIds = {
            R.id.btn_square_root,
            R.id.btn_cos,
            R.id.btn_sin,
            R.id.btn_factorial,
            R.id.btn_clear,
            R.id.btn_negate,
            R.id.btn_pointer,
            R.id.btn_division,
            R.id.btn_one,
            R.id.btn_two,
            R.id.btn_three,
            R.id.btn_multiplication,
            R.id.btn_four,
            R.id.btn_five,
            R.id.btn_six,
            R.id.btn_subtraction,
            R.id.btn_seven,
            R.id.btn_eight,
            R.id.btn_nine,
            R.id.btn_addition,
            R.id.btn_zero,
            R.id.btn_dot,
            R.id.btn_equals,
    };
    private Button[] btns;
    private TextView calContentTextView;
    private Calculator calculator;
    //private ScaleTouchListener touchListener;
    public static MainActivity mainactivity;  //方便非activity类使用toast

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mainactivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @SuppressLint("ClickableViewAccessibility")
    //初始化
    private void init() {
        /*// 创建一个缩放触摸监听器
        touchListener = new ScaleTouchListener();*/
        // 创建一个计算器
        calculator = new Calculator();
        // 获取布局中的用于显示计算结果的 TextView
        calContentTextView = (TextView) findViewById(R.id.tv_cal_content);
        // 遍历按钮 ID 数组
        Button btn;
        for (int btnId : btnIds) {
            // 获取当前 ID 对应的按钮
            btn = (Button) findViewById(btnId);
            // 为按钮添加点击事件监听器
            btn.setOnClickListener(this);
//            // 为按钮添加触摸事件监听器
//            btn.setOnTouchListener(touchListener);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        String numberText = calContentTextView.getText().toString();
        Button button = (Button) view;
        String buttonString = button.getText().toString();//获取按键的文字
        switch (buttonString) {
            case "SIN":
                numberText = calculator.clickOperator(CalculatorOperator.SIN, numberText);
                break;
            case "COS":
                numberText = calculator.clickOperator(CalculatorOperator.COS, numberText);
                break;
            case "√":
                numberText = calculator.clickOperator(CalculatorOperator.ROOT, numberText);
                break;
            case "!":
                numberText = calculator.clickOperator(CalculatorOperator.Factorial, numberText);
                break;
            case "C":
                numberText = calculator.clickOperator(CalculatorOperator.CLEAR, numberText);
                break;
            case "+/-":
                numberText = calculator.clickOperator(CalculatorOperator.NEGATE, numberText);
                break;
            case "%":
                numberText = calculator.clickOperator(CalculatorOperator.POINTER, numberText);
                break;
            case "÷":
                numberText = calculator.clickOperator(CalculatorOperator.DIVISION, numberText);
                break;
            case "1":
                numberText = calculator.clickCode(CalculatorCode.ONE);
                break;
            case "2":
                numberText = calculator.clickCode(CalculatorCode.TWO);
                break;
            case "3":
                numberText = calculator.clickCode(CalculatorCode.THREE);
                break;
            case "×":
                numberText = calculator.clickOperator(CalculatorOperator.MULTIPLICATION, numberText);
                break;
            case "4":
                numberText = calculator.clickCode(CalculatorCode.FOUR);
                break;
            case "5":
                numberText = calculator.clickCode(CalculatorCode.FIVE);
                break;
            case "6":
                numberText = calculator.clickCode(CalculatorCode.SIX);
                break;
            case "-":
                numberText = calculator.clickOperator(CalculatorOperator.SUBTRACTION, numberText);
                break;
            case "7":
                numberText = calculator.clickCode(CalculatorCode.SEVEN);
                break;
            case "8":
                numberText = calculator.clickCode(CalculatorCode.EIGHT);
                break;
            case "9":
                numberText = calculator.clickCode(CalculatorCode.NINE);
                break;
            case "+":
                numberText = calculator.clickOperator(CalculatorOperator.ADDITION, numberText);
                break;
            case "0":
                numberText = calculator.clickCode(CalculatorCode.ZERO);
                break;
            case ".":
                numberText = calculator.clickCode(CalculatorCode.DOT);
                break;
            case "=":
                numberText = calculator.clickOperator(CalculatorOperator.EQUAlS, numberText);
                break;
        }
        // 设置calContentTextView的文本内容为numberText
        calContentTextView.setText(numberText);
    }
}
