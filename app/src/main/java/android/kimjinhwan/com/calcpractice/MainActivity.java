package android.kimjinhwan.com.calcpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //1. 변수 선언
    TextView txtPreview, txtView;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPlus, btnMinus, btnMultiply, btnDivide, btnClear, btnResult;
    String preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //2. id 찾아 연결
     //아직 우리가 쓰기에는 높은 수준
        txtPreview = (TextView) findViewById(R.id.txtPreview);
        txtView = (TextView) findViewById(R.id.txtView);


        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnResult = (Button) findViewById(R.id.btnResult);

    //3. setOnClickListener 할당

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnResult.setOnClickListener(this);
    }
    //버튼을 클릭한 것이 확인되면 무엇을 할 것인가?
    @Override
    public void onClick(View v) {
       switch(v.getId()){
           case R.id.btn0:
               txtPreview.setText(txtPreview.getText().toString()+"0");
               break;
           case R.id.btn1:
               txtPreview.setText(txtPreview.getText().toString()+"1");
               break;
           case R.id.btn2:
               txtPreview.setText(txtPreview.getText().toString()+"2");
               break;
           case R.id.btn3:
               txtPreview.setText(txtPreview.getText().toString()+"3");
               break;
           case R.id.btn4:
               txtPreview.setText(txtPreview.getText().toString()+"4");
               break;
           case R.id.btn5:
               txtPreview.setText(txtPreview.getText().toString()+"5");
               break;
           case R.id.btn6:
               txtPreview.setText(txtPreview.getText().toString()+"6");
               break;
           case R.id.btn7:
               txtPreview.setText(txtPreview.getText().toString()+"7");
               break;
           case R.id.btn8:
               txtPreview.setText(txtPreview.getText().toString()+"8");
               break;
           case R.id.btn9:
               txtPreview.setText(txtPreview.getText().toString()+"9");
               break;
           case R.id.btnPlus:
               txtPreview.setText(txtPreview.getText().toString()+"+");
               break;
           case R.id.btnMinus:
               txtPreview.setText(txtPreview.getText().toString()+"-");
               break;
           case R.id.btnMultiply:
               txtPreview.setText(txtPreview.getText().toString()+"*");
               break;
           case R.id.btnDivide:
               txtPreview.setText(txtPreview.getText().toString()+"/");
               break;
           case R.id.btnClear:
               clear();
               break;
           case R.id.btnResult:
               result();
               break;

       }
    }
    private void result(){
        String current = txtPreview.getText().toString();
        txtView.setText(calculate(current));
    }

    private String calculate(String preview){

        //1.split으로 쪼개기
        String splitted[] = preview.split("(?<=[*/+-])|(?=[*/+-])");

        //2. 쪼갠 배열을 컬렉션에 옮기기
        //2.1. ArrayList 생성
        ArrayList<String> list = new ArrayList<>();

        //2.2. 생성된 컬렉션에 값 넣기
        for(String temp : splitted){
           list.add(temp);
        }

        //3. 곱셈과 나눗셈 먼저 하기

        for(int i = 0; i<list.size(); i++){
            String temp = list.get(i);
            int tempResult = 0;
            if(temp.equals("*")||temp.equals("/")){
                int before = Integer.parseInt(list.get(i-1));
                int after = Integer.parseInt(list.get(i+1));
                if(temp.equals("*")){
                    tempResult = before * after ;
                } else {
                    tempResult = before / after;
                }
                list.set(i, tempResult+"");
                list.remove(i+1);
                list.remove(i-1);
                i--;
            }
        }
        //4. 덧셈과 뺄셈 하기
        for(int i = 0; i<list.size(); i++){
            String temp = list.get(i);
            int tempResult = 0;
            if(temp.equals("+")||temp.equals("-")){
                int before = Integer.parseInt(list.get(i-1));
                int after = Integer.parseInt(list.get(i+1));
                if(temp.equals("+")){
                    tempResult = before + after;
                } else {
                    tempResult = before - after;
                }
                list.set(i, tempResult+"");
                list.remove(i+1);
                list.remove(i-1);
                i--;
            }
        }
        //5. 결과값 리턴
        return list.get(0);
    }



    private void clear(){
        txtPreview.setText("0");
        txtView.setText("0");
    }
}
