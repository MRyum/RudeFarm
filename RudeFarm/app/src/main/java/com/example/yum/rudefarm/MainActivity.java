package com.example.yum.rudefarm;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView content_list;
    MainAdapter mainAdapter = null;
    LinearLayoutManager MainLayoutManager;
    Toolbar toolbar;
    private String TITLES[];
    private int ICONS[];
    private String NAME;
    private String EMAIL;
    private int PROFILE;

    //동물 모습이 나오는 뷰의 데이터들.
    private String farmName; // 농장이름
    private int level; // 레벨 데이터
    private int animal; // 동물 이미지 리소스 데이터 값 R.id.~ 이런 모습의 int형
    private int point;

    // 카드들 안에 들어갈 텍스트 데이터의 리스트
    private List<String> castText;
    // 카드들 안에 들어갈 이미지 데이터의 리스트
    private List<Integer> castImage;
    //네비게이션 어뎁터
    private RecyclerView.Adapter mAdapter;
    RecyclerView mRecyclerView; //메인 카드 리스트 어뎁터
    //리싸이클러뷰 레이아웃매니저 = 리사이클러뷰의 레이아웃 형태를 정해주고 속성 설정 가능.
    private RecyclerView.LayoutManager mLayoutManager;
    // 네비게이션 관련 변수들.
    private DrawerLayout Drawer;
    private ActionBarDrawerToggle mDrawerToggle;

    public void Animate(View v, int DIRECTION, float afterSize, float offset){
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) v.getLayoutParams();
        int value = -(int) ((v.getHeight() - afterSize) * offset);
        switch (DIRECTION){
            case 0:
                params.setMargins(value, 0, 0, 0);
                break;
            case 1:
                params.setMargins(0, value, 0, 0);
                break;
            case 2:
                params.setMargins(0, 0, value, 0);
                break;
            case 3:
                params.setMargins(0, 0, 0, value);
                break;
        }
        v.setLayoutParams(params);
    }

    //init이 앞에 붙은 함수는 데이터나 변수 초기화하는 함수이다 이름대로 할 테니 보기 편할것이다. 히히
    //보기 편하라고 만듦. 이곳은 데이터의 초기화하는 곳, 서버에서 데이터를 받아와 저장할때 이곳을 사용하기 바람.
    public void initData() {
        farmName = "다리꼬지마 다다리";
        level = 7;
        point = 4100;

        castText = new ArrayList<String>();
        castImage = new ArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            castText.add("example" + i);
        }
        for (int i = 0; i < 10; i++) {
            castImage.add(R.mipmap.splash);
        }


        TITLES = new String[]{"내 목장", "21일 프로젝트", "버릇 캐스트",
                "상점", "함께해요", "랭킹", "버릇 검색", "설정"};
        ICONS = new int[]{R.mipmap.menu_home,
                R.mipmap.menu_project, R.mipmap.menu_cast, R.mipmap.menu_store,
                R.mipmap.menu_group, R.mipmap.menu_rank, R.mipmap.menu_search, R.mipmap.menu_setting};
        NAME = "유예권";
        EMAIL = "yyg8291@naver.com";
        animal = R.mipmap.chicken;



    }
    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getBackgroundColor(toolbar));
        }
        int noOfChild = toolbar.getChildCount();
        View view;
//        toolbar.setTranslationY(-300);
//        toolbar.animate().setDuration(1000).translationY(0).alpha(1);
//        for (int i = 0; i < noOfChild; i++) {
//            view = toolbar.getChildAt(i);
//            view.setTranslationY(-300);
//            view.animate().setDuration(1000).translationY(0);
//        }
    }
    public void initNavigationList() {
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mAdapter = new MyAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }
    public void initMainList() {
        mainAdapter = new MainAdapter(castText, castImage, farmName, point, level, animal);
        content_list = (RecyclerView) findViewById(R.id.content_list);
        MainLayoutManager = new GridLayoutManager(this, 2);
        ((GridLayoutManager) MainLayoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? 2 : 1;
            }
        });
        content_list.setLayoutManager(MainLayoutManager);
        content_list.setHasFixedSize(true);
        content_list.setAdapter(mainAdapter);


        content_list.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && dy <= 500) {
                    float offset = (float)dy / 500.0f;
                    toolbar.setVisibility(View.VISIBLE);
                    Animate(toolbar, 1, getResources().getDimension(R.dimen.toolbarHeight), (1 - offset));
                    Log.v("scroll", String.valueOf("오프셋 : " + offset + " 디와이 : " + dy));
                }
                else if( dy < 0 ){
                    if(dy >= -500){
                        float offset = (float)dy/-500.0f;
                        toolbar.setVisibility(View.GONE);
//                        Animate(toolbar, 1, getResources().getDimension(R.dimen.marginToolbarHeight), offset);
                    }
                }
            }
        });
    }
    public void initNavigation() {
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
        toolbar.setNavigationIcon(R.mipmap.button_menu);

    }
    public static int getBackgroundColor(View view) {
        Drawable drawable = view.getBackground();
        if (drawable instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) drawable;
            if (Build.VERSION.SDK_INT >= 11) {
                return colorDrawable.getColor();
            }
            try {
                Field field = colorDrawable.getClass().getDeclaredField("mState");
                field.setAccessible(true);
                Object object = field.get(colorDrawable);
                field = object.getClass().getDeclaredField("mUseColor");
                field.setAccessible(true);
                return field.getInt(object);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initData();
        initToolbar();
        initNavigationList();
        initMainList();
        initNavigation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    public void DisplayMove() {


    }
}
