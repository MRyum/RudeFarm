package com.example.yum.rudefarm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Shoes extends Fragment {
    private GridLayoutManager manager;
    private RecyclerView list;
    private RecyclerView.Adapter adapter;

    String[] femaleTitle;
    int[] femaleGoods;
    String[] femalePrize;

    String[] maleTitle;
    int[] maleGoods;
    String[] malePrize;

    List<String> titleList;
    List<Integer> goodsList;
    List<String> prizeList;

    public void addDataInList(String[] data, List list) {
        for (int i = 0; i < data.length; i++) {
            list.add(data[i]);
        }
    }

    public void addDataInList(int[] data, List list) {
        for (int i = 0; i < data.length; i++) {
            list.add(data[i]);
        }
    }

    public void initData() {
        titleList = new ArrayList<>();
        goodsList = new ArrayList<>();
        prizeList = new ArrayList<>();

        maleTitle = new String[]{
                "그냥 운동화.. 그냥...",
                "국민 신발 기본템 개이득",
        };
        maleGoods = new int[]{
                R.mipmap.male_shoes_01,
                R.mipmap.male_shoes_02,
        };
        malePrize = new String[]{
                "Free",
                "800",
        };

        femaleTitle = new String[]{
                "10명 중에 8명은 신는 샌들",
                "발목 가려서 다리 짧아보임",
        };
        femaleGoods = new int[]{
                R.mipmap.female_shoes_01,
                R.mipmap.female_shoes_02,
        };
        femalePrize = new String[]{
                "Free",
                "800"
        };

        //조건문으로 사용자가 남자일 때, male이 붙은 데이터를 집어넣고,
        //여자일때 female이 붙은 데이터를 넣는다.
        //addDataInList() 함수 만들어뒀으니 이걸 사용하기 바랍니다.
        addDataInList(femaleTitle, titleList);
        addDataInList(femaleGoods, goodsList);
        addDataInList(femalePrize, prizeList);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hair, container, false);

        initData();

        list = (RecyclerView) rootView.findViewById(R.id.storeList);
        RecyclerView.ItemDecoration decoration = new StoreCardSpacesItemDecoration(3, 10, 20, false);
        manager = new GridLayoutManager(getActivity(), 3);
        adapter = new StoreAdapter(titleList, goodsList, prizeList);
        list.addItemDecoration(decoration);
        list.setLayoutManager(manager);
        list.setAdapter(adapter);


        return rootView;
    }
}