package com.example.yum.rudefarm;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CheckListAcitivity extends Activity {

    RecyclerView firstRecylerView;
    RecyclerView secondRecylerView;
    RecyclerView thirdRecylerView;
    LinearLayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    List answerList = new ArrayList();
    String question;

    String[] answer;

    @Override
    protected void onPause() {
        super.onPause();
        View container = (View) findViewById(R.id.container);
        Bitmap bitmap = ((BitmapDrawable) container.getBackground()).getBitmap();
        bitmap.recycle();
    }

    public void initData(String question, String[] answer) {
        answerList = new ArrayList<String>();
        this.question = new String();

        this.question = question;
        for (int i = 0; i < answer.length; i++)
            answerList.add(answer[i]);
    }
    public void initanswerData(String ... strings) {

        for (String string:strings) {
            answerList.add(string);
        }
    }
    public void initQuestion(String question) {
        question = new String();
        question = this.question;
    }
    public void clearEmpty() {
        if (!answerList.isEmpty())
            answerList.clear();
    }
    public void initRecyclerView(RecyclerView recyclerView) {
        recyclerView = (RecyclerView) findViewById(R.id.first);
        recyclerView.setHasFixedSize(true);
        adapter = new ChecklistListAdapter(question, answerList);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void initFirst() {

        clearEmpty();

        initanswerData();
        initQuestion("");
        firstRecylerView = (RecyclerView) findViewById(R.id.first);
        firstRecylerView.setHasFixedSize(true);
        adapter = new ChecklistListAdapter(question, answerList);
        firstRecylerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        firstRecylerView.setLayoutManager(layoutManager);
    }
    public void initSecond() {

        clearEmpty();
        initanswerData("", "", "");
        initQuestion("");
        secondRecylerView = (RecyclerView) findViewById(R.id.second);
        secondRecylerView.setHasFixedSize(true);
        adapter = new ChecklistListAdapter(question, answerList);
        secondRecylerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        secondRecylerView.setLayoutManager(layoutManager);
    }
    public void initThird() {

        clearEmpty();
        initanswerData("", "", "");
        initQuestion("");
        thirdRecylerView = (RecyclerView) findViewById(R.id.third);
        thirdRecylerView.setHasFixedSize(true);
        adapter = new ChecklistListAdapter(question, answerList);
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
