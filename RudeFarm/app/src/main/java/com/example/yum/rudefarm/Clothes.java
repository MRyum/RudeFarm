
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

public class Clothes extends Fragment {

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
                "꾸미지 않은 내추럴룩",
                "누구나 패션왕! 모나미룩",
                "아청아청한 교복",
                "디자이너가 KCM 팬임",
                "떼인 돈 받으러가는 정장룩",
        };
        maleGoods = new int[]{
                R.mipmap.male_clothes1,
                R.mipmap.male_clothes2,
                R.mipmap.male_clothes3,
                R.mipmap.male_clothes4,
                R.mipmap.male_clothes5,
        };
        malePrize = new String[]{
                "Free",
                "400",
                "500",
                "900",
                "300",
        };

        femaleTitle = new String[]{
                "꾸미지 않은 내추럴룩",
                "봄처녀 느낌 플라워패턴",
                "아청아청한 교복",
                "상체 짧아보이는 하이웨스트",
                "1도 안 섹시한 원피스",
        };
        femaleGoods = new int[]{
                R.mipmap.female_clothes1,
                R.mipmap.female_clothes2,
                R.mipmap.female_clothes3,
                R.mipmap.female_clothes4,
                R.mipmap.female_clothes5,
        };
        femalePrize = new String[]{
                "Free",
                "800",
                "500",
                "400",
                "300",
        };

        //조건문으로 사용자가 남자일 때, male이 붙은 데이터를 집어넣고,
        //여자일때 female이 붙은 데이터를 넣는다.
        //addDataInList() 함수 만들어뒀으니 이걸 사용하기 바랍니다.
        addDataInList(maleTitle, titleList);
        addDataInList(maleGoods, goodsList);
        addDataInList(malePrize, prizeList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.clothes, container, false);

        initData();

        RecyclerView.ItemDecoration decoration = new StoreCardSpacesItemDecoration(3, 10, 20, false);
        list = (RecyclerView) rootView.findViewById(R.id.storeList);
        manager = new GridLayoutManager(getActivity(), 3);
        adapter = new StoreAdapter(titleList, goodsList, prizeList);
        list.addItemDecoration(decoration);
        list.setLayoutManager(manager);
        list.setAdapter(adapter);

        return rootView;
    }
}