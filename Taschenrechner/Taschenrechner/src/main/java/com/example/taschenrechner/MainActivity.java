package com.example.taschenrechner;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView mTextViewFirstNum;
    TextView mTextViewAction;
    TextView mTextViewSecondNum;
    TextView mTextViewEqual;
    TextView mTextViewResult;

    private Integer firstNum=0;
    private Integer secondNum=0;
    private Integer action=0; //+=1, -=2, *=3, /=4, CE=0, %=5
    private Integer result=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewFirstNum = (TextView) findViewById(R.id.mTextViewFirstNum);
        mTextViewAction = (TextView) findViewById(R.id.mTextViewAction);
        mTextViewSecondNum = (TextView) findViewById(R.id.mTextViewSecondNum);
        mTextViewEqual = (TextView) findViewById(R.id.mTextViewEqual);
        mTextViewResult = (TextView) findViewById(R.id.mTextViewResult);
    }


    public void onClickNumberButton(View v) {
        String text="";
        if(action==0){
            if(firstNum == 0)
                text=v.getTag().toString();
            else
                text=firstNum.toString()+v.getTag().toString();
            mTextViewSecondNum.setText("");
            mTextViewAction.setText("");
            mTextViewEqual.setText("");
            mTextViewFirstNum.setText(text);
            firstNum=Integer.parseInt(text);
        }
        else{
            if(secondNum == 0)
                text=v.getTag().toString();
            else
                text=secondNum.toString()+v.getTag().toString();
            mTextViewSecondNum.setText(text);
            secondNum=Integer.parseInt(text);
        }
        mTextViewResult.setText("");
        result=0;
    }

    public void onClickActionButton(View v) {
        action=Integer.parseInt(v.getTag().toString());
        if(result==0){
            switch (action) {
                case 1:
                    mTextViewAction.setText("+");
                    break;
                case 2:
                    mTextViewAction.setText("-");
                    break;
                case 3:
                    mTextViewAction.setText("*");
                    break;
                case 4:
                    mTextViewAction.setText("/");
                    break;
                case 5: // Ganzzahlige Division
                    mTextViewAction.setText("%");
                    break;
                default:
                    firstNum=0;
                    secondNum=0;
                    result=0;
                    mTextViewResult.setText("");
                    mTextViewFirstNum.setText("0");
                    mTextViewSecondNum.setText("");
                    mTextViewAction.setText("");
                    mTextViewEqual.setText("");
                    break;
            }
        }
        else{
            if(action==0){
                firstNum=0;
                secondNum=0;
                result=0;
                mTextViewResult.setText("");
                mTextViewFirstNum.setText("0");
                mTextViewSecondNum.setText("");
                mTextViewAction.setText("");
                mTextViewEqual.setText("");
            }
        }
    }

    public void onClickEqualsButton(View v) {
        switch (action) {
            case 1:
                result=firstNum + secondNum;
                break;
            case 2:
                result=firstNum - secondNum;
                break;
            case 3:
                result=firstNum * secondNum;
                break;
            case 4:
                result=firstNum / secondNum;
                break;
            case 5:
                result=firstNum % secondNum;
                break;
            default:
                break;
        }
        if(action>0){
            firstNum=0;secondNum=0;
            mTextViewEqual.setText("=");
            mTextViewResult.setText(result.toString());
            action=0;
        }
    }

    /**
     * Mit dieser Funktion wird die Fakultät einer gegebenen Zahl berechnet
     * <br>
     * <b>Note:</b> Falls die Zahl negativ ist, wird -1 ausgegeben, da die Fakultät negativer Zahlen nicht definiert ist.
     */
    public void onClickFak(View v){
        if (firstNum < 0)
            result = -1;
        else{
            int res = 1; // 0! = 1
            for(int i = 1; i <= firstNum ; i++){
                res = res * i;
            }
            result = res;
        }
        firstNum=0;secondNum=0;
        mTextViewAction.setText("!");
        mTextViewSecondNum.setText(" ");
        mTextViewEqual.setText("=");
        mTextViewResult.setText(result.toString());
        action=0;
    }

    /**
     * Mit dieser Funktion wird das Quadrat einer gegebenen Zahl berechnet
     */
    public void onClickPower(View v){
        result = firstNum * firstNum;
        firstNum=0;secondNum=0;
        mTextViewAction.setText("^2");
        mTextViewSecondNum.setText(" ");
        mTextViewEqual.setText("=");
        mTextViewResult.setText(result.toString());
        action=0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
