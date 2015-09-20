package com.example.yum.rudefarm;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.marshalchen.ultimaterecyclerview.ObservableScrollState;
import com.marshalchen.ultimaterecyclerview.ObservableScrollViewCallbacks;
import com.marshalchen.ultimaterecyclerview.URLogs;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private String farm_id;
    private int level;

    int i = 100;
    private String TITLES[];
    private int ICONS[];
    private String NAME;
    private String EMAIL;
    private int PROFILE;

    //카드 안에 쓰일 데이터들 이걸로 사용

    // 카드들 안에 들어갈 텍스트 데이터의 리스트
    private List<String> Text;
    // 카드들 안에 들어갈 이미지 데이터의 리스트
    private List<Integer> Image;

    private Toolbar toolbar;
    private UltimateRecyclerView content_list;

    //네비게이션 어뎁터

    private RecyclerView.Adapter mAdapter;

    RecyclerView mRecyclerView; //메인 카드 리스트 어뎁터
    private MainAdapter mainAdapter;

    //리싸이클러뷰 레이아웃매니저 = 리사이클러뷰의 레이아웃 형태를 정해주고 속성 설정 가능.
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager MainLayoutManager;

    // 네비게이션 관련 변수들.
    private DrawerLayout Drawer;
    private ActionBarDrawerToggle mDrawerToggle;

    //보기 편하라고 만듦. 이곳은 데이터의 초기화하는 곳, 서버에서 데이터를 받아와 저장할때 이곳을 사용하기 바람.
    public void init() {

        farm_id = "다리꼬지마 다다리";

        level = 7;

        TITLES = new String[]{"내 목장", "21일 프로젝트", "버릇 캐스트",
                "상점", "함께해요", "랭킹", "버릇 검색", "설정"};

        ICONS = new int[]{R.drawable.menu_home,
                R.drawable.menu_project, R.drawable.menu_cast, R.drawable.menu_store,
                R.drawable.menu_group, R.drawable.menu_rank, R.drawable.menu_search, R.drawable.menu_setting};

        NAME = "유예권";
        EMAIL = "yyg8291@naver.com";


        Text = new ArrayList<String>();
        Image = new ArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            Text.add("example" + i);
        }
        for (int i = 0; i < 10; i++) {
            Image.add(R.drawable.splash);
        }

    }

    int getScreenHeight() {
        return findViewById(R.id.content).getHeight();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();

        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);
        int noOfChild = toolbar.getChildCount();
        View view;
        toolbar.setAlpha(0);
        toolbar.setTranslationY(-300);
        toolbar.animate().setDuration(1000).translationY(0).alpha(1);
        for (int i = 0; i < noOfChild; i++) {
            view = toolbar.getChildAt(i);
            view.setTranslationY(-300);
            view.animate().setStartDelay(900).setDuration(1000).translationY(0);
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //네비게이션 리싸이클러뷰
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mAdapter = new MyAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        //메인 리스트 리싸이클러뷰
        content_list = (UltimateRecyclerView) findViewById(R.id.content_list);

        //데이터를 어뎁터에 넣는 부분.
        mainAdapter = new MainAdapter(Text, Image, farm_id, level);

        MainLayoutManager = new GridLayoutManager(this, 2);
        ((GridLayoutManager) MainLayoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 || position == 1 ? 2 : 1;
            }
        });
        content_list.setLayoutManager(MainLayoutManager);
        content_list.setAdapter(mainAdapter);

        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(2, 40, 50, true);
        content_list.addItemDecoration(spacesItemDecoration);

        //content_list.setParallaxHeader(getLayoutInflater().inflate(R.layout.status, content_list.mRecyclerView, false));
//        content_list.setNormalHeader(getLayoutInflater().inflate(R.layout.status, content_list.mRecyclerView, false));
        content_list.setOnParallaxScroll(new UltimateRecyclerView.OnParallaxScroll() {
            @Override
            public void onParallaxScroll(float percentage, float offset, View parallax) {
                Drawable c = toolbar.getBackground();

                c.setAlpha(Math.round(127 + percentage * 128));

                if (percentage == 0)
                    c.setAlpha(0);

                toolbar.setBackgroundDrawable(c);
            }
        });

        content_list.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

//                Drawable c = toolbar.getBackground();
//
//
//                if (dy == 0)
//                    c.setAlpha(0);

                //toolbar.setBackgroundDrawable(c);
            }
        });

        content_list.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            @Override
            public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
            }

            @Override
            public void onDownMotionEvent() {

            }

            @Override
            public void onUpOrCancelMotionEvent(ObservableScrollState observableScrollState) {
//                if (observableScrollState == ObservableScrollState.DOWN) {
//                    ultimateRecyclerView.hideToolbar(toolbar, ultimateRecyclerView, getScreenHeight());
//                    ultimateRecyclerView.hideFloatingActionMenu();
//                } else if (observableScrollState == ObservableScrollState.UP) {
//                    ultimateRecyclerView.showToolbar(toolbar, ultimateRecyclerView, getScreenHeight());
//                    ultimateRecyclerView.showFloatingActionMenu();
//                }
            }
        });

        content_list.setRecylerViewBackgroundColor(Color.parseColor("#d2d7db"));

//        content_list.enableLoadmore();
//        mainAdapter.setCustomLoadMoreView(LayoutInflater.from(this)
//                .inflate(R.layout.custom_bottom_progressbar, null));

        //밑으로 드래그 했을때 로딩하는 리스너. 여기에 백그라운드로 그 다음 내용 받아올때 사용하면 된다.
//        content_list.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
//            //테스트용, 일종의 예제
//            @Override
//            public void loadMore(int itemsCount, final int maxLastVisiblePosition) {
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    public void run() {
//                        mainAdapter.insert("More " + moreNum++, R.drawable.splash, mainAdapter.getAdapterItemCount());
//                        mainAdapter.insert("More " + moreNum++, R.drawable.splash, mainAdapter.getAdapterItemCount());
//                        mainAdapter.insert("More " + moreNum++, R.drawable.splash, mainAdapter.getAdapterItemCount());
//
////                         linearLayoutManager.scrollToPositionWithOffset(maxLastVisiblePosition,-1);
////                           linearLayoutManager.scrollToPosition(maxLastVisiblePosition);
//
//                    }
//                }, 1000);
//            }
//        });

//        content_list.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mainAdapter.insert(moreNum++ + "  Refresh things", 0);
//                        content_list.setRefreshing(false);
//                        //   content_list.scrollBy(0, -50);
//                        MainLayoutManager.scrollToPosition(0);

//
////                        content_list.setAdapter(mainAdapter);
////                        mainAdapter.notifyDataSetChanged();
//                    }
//                }, 1000);
//            }
//        });


        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}