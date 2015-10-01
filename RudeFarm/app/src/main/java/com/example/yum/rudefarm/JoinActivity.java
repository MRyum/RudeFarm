package com.example.yum.rudefarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class JoinActivity extends Activity {

    EditText name_et;
    EditText email_et;
    EditText pw_et;

    String name;
    String email;
    String pw;

    private Button nextButton;
    private Spinner year;
    private Spinner month;
    private Spinner day;
    private Spinner sex_sp;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> list1;
    private ArrayList<String> list2;
    private ArrayList<String> list3;
    private ArrayList<String> list4;

    int dates;

    // 생년월일 성별 데이터
    String y;
    String m;
    String d;
    String ymd;

    String sex;

    private AlertDialog mDialog = null;

    public AlertDialog createDialog() {
        AlertDialog.Builder ab = new AlertDialog.Builder(this)
                .setTitle("오류")
                .setMessage("정보를 다 입력하지 않으셨습니다.")
                .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setDismiss(mDialog);
                    }
                });
        return ab.create();
    }

    private void setDismiss(Dialog dialog) {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    public void initEditText() {
        name_et = (EditText) findViewById(R.id.name_input);
        email_et = (EditText) findViewById(R.id.email_input);
        pw_et = (EditText) findViewById(R.id.pw_input);
    }

    public boolean etCheck() {
        if (name_et.getText().length() == 0 || email_et.getText().length() == 0
                || pw_et.getText().length() == 0)
            return true;
        return false;
    }

    public void initDate() {
        SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        dates = Integer.parseInt(CurYearFormat.format(date));

    }
    public void initSpinner() {
        year = (Spinner) findViewById(R.id.y_sp);
        month = (Spinner) findViewById(R.id.m_sp);
        day = (Spinner) findViewById(R.id.d_sp);
        sex_sp = (Spinner) findViewById(R.id.sex_sp);
        list1 = new ArrayList<>();
        int yearData = 1940;
        int i = 0;
        while (true) {
            list1.add(String.valueOf(yearData + i));
            if ((yearData + i) == dates)
                break;
            i++;
        }
        Collections.reverse(list1);
        adapter = new ArrayAdapter<String>(this, R.layout.ymd_item, list1);
        year.setAdapter(adapter);


        list2 = new ArrayList<>();
        for (int j = 1; j <= 12; j++)
            list2.add(String.valueOf(j));
        adapter = new ArrayAdapter<String>(this, R.layout.ymd_item, list2);
        month.setAdapter(adapter);

        list3 = new ArrayList<>();
        for (int j = 1; j <= 31; j++ ){
            list3.add(String.valueOf(j));
        }
        adapter = new ArrayAdapter<String>(this, R.layout.ymd_item, list3);
        day.setAdapter(adapter);

        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#ffffff"));
                y = ((TextView) parent.getChildAt(0)).getText().toString();
                ymd = y;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#ffffff"));
                m = ((TextView) parent.getChildAt(0)).getText().toString();
                ymd += m;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#ffffff"));
                d = ((TextView) parent.getChildAt(0)).getText().toString();
                ymd += d;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        list4 = new ArrayList<>();
        list4.add("남자");
        list4.add("여자");
        adapter = new ArrayAdapter<String>(this, R.layout.ymd_item, list4);
        sex_sp.setAdapter(adapter);
        sex_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#ffffff"));
                sex = ((TextView) parent.getChildAt(0)).getText().toString();
                ymd += sex;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        initDate();
        initSpinner();
        initEditText();

        nextButton = (Button) findViewById(R.id.Joinbtn);
        nextButton.setAlpha(0.7f);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCheck()) {
                    mDialog = createDialog();
                    mDialog.show();
                } else {
                    Intent i = new Intent(JoinActivity.this, BaseInfoActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);
                    finish();
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("test", ymd);
    }
}
