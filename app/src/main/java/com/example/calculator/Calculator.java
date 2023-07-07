package com.example.calculator;

import android.widget.Toast;


public class Calculator {
    private static Double numberFirst;
    private static Double numberTwo;
    private static CalculatorOperator operator;
    private static String currentNumberText;
    private boolean pressedDotInLast = false;

    public Calculator() {
        clear();
    }

    public String clickCode(CalculatorCode code) {

        Double number = null; // 定义一个 Double 类型的变量 number，并赋初值为 null
        if (this.operator == null) // 如果操作符为空，则 number 的值为 numberFirst
            number = numberFirst;
        else // 否则 number 的值为 numberTwo
            number = numberTwo;

        String numberText = currentNumberText + code.getNumber(); // 将当前数字文本和输入的数字拼接成一个字符串
        if (numberText.length() > 1) { // 如果拼接后的字符串长度大于1
            if (numberText.charAt(0) == '0' && numberText.charAt(1) != '.' && numberText.charAt(numberText.length() - 1) != '0') { // 如果字符串第一个字符为0并且第二个字符不为小数点并且末尾字符不为0
                numberText = ""; // 将字符串赋值为空字符串
                Toast.makeText(MainActivity.mainactivity, "非法输入！", Toast.LENGTH_SHORT).show(); // 弹出“非法输入”提示框
            }
        }
        try { // 尝试将字符型数字转换为双精度浮点型数字
            number = Double.parseDouble(numberText);
        } catch (NumberFormatException e) { // 如果转换失败，则当前数字文本保持不变
            numberText = currentNumberText;
        }
// 根据操作符号(+-x÷)来判断当前数字是第一个数字还是第二个数字
        if (operator == null) // 如果操作符为空，则当前数字为第一个数字
            numberFirst = number;
        else // 否则当前数字为第二个数字
            numberTwo = number;

        StringBuilder builder = new StringBuilder(); // 创建一个 StringBuilder 类型的变量 builder 来拼接字符串

        if (operator == null) { // 如果当前没有操作符
            builder.append(numberText); // 则将拼接后的字符串添加到 builder 中
        }
// 如果当前存在操作符，则将第一个数字、操作符和第二个数字拼接成字符串并添加到 builder 中
        else {
            builder.append(doubleWithInt(numberFirst)); // 将第一个数字加入 builder 中
            builder.append(operator.getOperator()); // 将操作符加入 builder 中
            builder.append(numberText); // 将第二个数字加入 builder 中
        }
        currentNumberText = numberText; // 将当前数字文本赋值为拼接后的字符串
        // 返回需要显示的字符串
        return builder.toString();
    }

    public static void clear() {
        numberFirst = 0.0;
        numberTwo = 0.0;
        operator = null;
        currentNumberText = "";
    }

    public String clickOperator(CalculatorOperator operator, String numberText) {
        String result = "";
        switch (operator) {
            case COS:
                result = cos();
                break;
            case SIN:
                result = sin();
                break;
            case Factorial: //阶乘
                if (currentNumberText.contains(".")|| numberFirst <= 1) {  //如果是小数
                    Toast.makeText(MainActivity.mainactivity, "请输入整数！", Toast.LENGTH_SHORT).show();
                    clear();
                    break;
                }
                long n = Long.parseLong(currentNumberText);
                result = factorial(n);
                break;
            case ROOT: //开平方根
                if (currentNumberText.contains("-") || numberFirst <= 0) { //如果是非正数
                    Toast.makeText(MainActivity.mainactivity, "请输入正数！", Toast.LENGTH_SHORT).show();
                    clear();
                    break;
                }
                result = rooting();
                break;
            case CLEAR:
                clear();
                result = "";
                break;
            case NEGATE:
                result = negate();
                break;
            case POINTER:
                result = pointer();
                break;

            case DIVISION:
            case MULTIPLICATION:
            case SUBTRACTION:
            case ADDITION:
                char inputOperator = operator.getOperator();  // 获取 operator 的操作符
                if (this.operator == null) {  // 如果当前对象的 operator 为空
                    result = numberText + inputOperator;  // 将字符串 numberText 和操作符拼接成新的字符串赋值给 result
                } else {  // 如果当前对象的 operator 不为空
                    char lastOperator = numberText.charAt(numberText.length() - 1);  // 获取 numberText 的最后一个字符作为操作符
                    result = equals(numberText);  // 计算当前对象中 numberText 的结果赋值给 result
                    if (this.operator == null) {  // 如果当前对象的 operator 为空
                        result += inputOperator;  //  将操作符拼接到 result 中
                    } else {  // 如果当前对象的 operator 不为空
                        result = result.replace(lastOperator, inputOperator);  // 将 result 中的最后一个操作符替换成输入的操作符
                    }
                }
                currentNumberText = "";
                this.operator = operator;
                break;
            case EQUAlS:
                result = equals(numberText);
                break;
        }

        return result;
    }

    private String factorial(long n) {
        long num = recusion(n);
        operator = null;
        return doubleWithInt((double) num);
    }

    private long recusion(long n) {
        if (n == 0 || n == 1)
            return 1;
        else {
            return n * recusion(n - 1);
        }
    }

    //保留七位小数
    public static double formatDouble1(double d) {
        return (double) Math.round(d * 10000000) / 10000000;
    }

    private String sin() {
        double num = Math.sin(numberFirst * Math.PI /(double) 180);//默认计算角度值
        numberFirst = formatDouble1(num);
        operator = null;

        return doubleWithInt(numberFirst);
    }

    private String cos() {
        double num = Math.cos(numberFirst * Math.PI/ (double)180);//默认计算角度值
       numberFirst = formatDouble1(num);
        operator = null;
        return doubleWithInt(numberFirst);
    }

    private String rooting() {
        double num = Math.sqrt(numberFirst);
        numberFirst = num;
        operator = null;
        return doubleWithInt(num);
    }

    private String negate() {
        numberFirst = -numberFirst;
        operator = null;
        return doubleWithInt(numberFirst);
    }

    private String pointer() {
        numberFirst /= 100;
        operator = null;
        return doubleWithInt(numberFirst);
    }

    private String equals(String numberText) {
        //  if (this.operator == null || numberTwo.equals(0.0)) {
        if (this.operator == null) {
            return numberText;
        }
        double result = 0.0;
        switch (operator) {
            case DIVISION:
                if (numberTwo.equals(0.0)) { //如果0为除数
                    Toast.makeText(MainActivity.mainactivity, "0不能为除数！", Toast.LENGTH_SHORT).show();
                    break;
                }
                result = numberFirst / numberTwo;
                break;
            case MULTIPLICATION:
                result = numberFirst * numberTwo;
                break;
            case SUBTRACTION:
                result = numberFirst - numberTwo;
                break;
            case ADDITION:
                result = numberFirst + numberTwo;
                break;
        }
        numberFirst = result;
        operator = null;
        numberTwo = 0.0;
        currentNumberText = doubleWithInt(result);
        return doubleWithInt(numberFirst);
    }

    private String doubleWithInt(Double number) {
        //声明一个boolean类型的变量flag，比较number通过longValue()转换后的值是否等于number本身，结果赋值给flag
        boolean flag = number.longValue() == number;
        //如果flag为true，即number的小数部分为0，则执行下一行代码
        if (flag) {
            //返回number通过longValue()转换后的值，并将它转换成字符串类型后返回
            return String.valueOf(number.longValue());
        }
        //number的小数部分不为0
        return number.toString();
    }

}
