package com.example.yum.rudefarm;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CheckListAcitivity extends Activity {

    RecyclerView firstRecylerView;
    RecyclerView secondRecylerView;
    RecyclerView thirdRecylerView;
    LinearLayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    List firstAnswerList = new ArrayList();
    List secondAnswerList = new ArrayList();
    List thirdAnswerList = new ArrayList();
    String question;

    String[] answer;

    public void initFirstanswerData(String... strings) {

        for (String string : strings) {
            firstAnswerList.add(string);
        }
    }

    public void initSecondanswerData(String... strings) {

        for (String string : strings) {
            secondAnswerList.add(string);
        }
    }

    public void initThirdanswerData(String... strings) {

        for (String string : strings) {
            thirdAnswerList.add(string);
        }
    }

    public void initQuestion(String question) {
        this.question = new String();
        this.question = question;
    }

    public void initFirst() {
        initFirstanswerData(
                "예, 저는 어쩔 수 없는 담배의 노예입니다.",
                "아니요, 제가 그렇게 꼴초는 아닙니다."
        );
        initQuestion("아침에 일어나자마자 담배 생각이 절실했나요?");
        firstRecylerView = (RecyclerView) findViewById(R.id.first);
        firstRecylerView.setHasFixedSize(true);
        adapter = new ChecklistListAdapter(question, firstAnswerList);
        firstRecylerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        firstRecylerView.setLayoutManager(layoutManager);
    }

    public void initSecond() {
        initSecondanswerData(
                "예.. 죄송합니다",
                "아니요, 유혹을 견뎌냈습니다!"
        );

        initQuestion("오늘 담배를 한 개피라도 피셨나요?");
        secondRecylerView = (RecyclerView) findViewById(R.id.second);
        secondRecylerView.setHasFixedSize(true);
        adapter = new ChecklistListAdapter(question, secondAnswerList);
        secondRecylerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        secondRecylerView.setLayoutManager(layoutManager);
    }

    public void initThird() {
        initThirdanswerData();
        initQuestion("에잉 쯧쯧... 몇개피나 피셨나요?");
        thirdRecylerView = (RecyclerView) findViewById(R.id.third);
        thirdRecylerView.setHasFixedSize(true);
        adapter = new ChecklistListAdapter(question, thirdAnswerList);
        thirdRecylerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        thirdRecylerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

        initFirst();
        initSecond();
        initThird();

    }
}
