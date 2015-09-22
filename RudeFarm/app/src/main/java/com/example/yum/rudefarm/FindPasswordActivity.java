package com.example.yum.rudefarm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FindPasswordActivity extends AppCompatActivity {



    FragmentManager fm;
    FragmentTransaction fragmentTransaction;
    Button NextBtn;

    AlertDialog mDialog = null;
    EditText findname_et;
    EditText findemail_et;

    String findname;
    String findemail;

    InputInfoFragment inputInfoFragment;
    NewPasswordFragment newPasswordFragment;

    //생년월일 데이터 y는 연도, m은 월 뭐 아시죠?
    String y;
    String m;
    String d;
    String ymd; //합쳐져 있는거

    private ArrayAdapter<String> adapter;
    private ArrayList<String> list1;
    private ArrayList<String> list2;
    private ArrayList<String> list3;

    private Spinner year;
    private Spinner month;
    private Spinner day;

    int dates;

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
        list1 = new ArrayList<>();
        int yearData = 1940;
        int i = 1;
        while (true) {
            list1.add(String.valueOf(yearData + i));
            if ((yearData + i) == dates)
                break;
            i++;
        }
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpassword);

        initDate();
        initSpinner();


        findname_et = (EditText) findViewById(R.id.findname);
        findemail_et = (EditText) findViewById(R.id.findemail);

        findname = findname_et.getText().toString();
        findemail = findemail_et.getText().toString();

        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();

        inputInfoFragment = new InputInfoFragment();
        newPasswordFragment = new NewPasswordFragment();
        NextBtn = (Button) findViewById(R.id.nextbtn);
        NextBtn.setAlpha(0.7f);

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("test", "파인드패스워드액티비티" +  findname + findemail);
                if (findname_et.getText().length() == 0 || findemail_et.getText().length() == 0) {
                    mDialog = createDialog();
                    mDialog.show();
                } else {
                    NextBtn.setVisibility(View.GONE);
                    fragmentTransaction.replace(R.id.findpassword_fm, newPasswordFragment);
                    fragmentTransaction.commit();
                }
            }
        });
    }
    private AlertDialog createDialog() {
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
}
