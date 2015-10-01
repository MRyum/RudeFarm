package com.example.yum.rudefarm;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by YUM on 2015-10-01.
 */
public class DetailDialog extends Dialog {

    String title_txt;
    String prize_txt;

    String[] explain_dum;

    ImageView closeButton;
    Button buyButton;
    TextView title;
    TextView prize;
    TextView explain;

    ImageView DetailImage;

    int order;

    Bitmap image;

    WindowManager.LayoutParams wm;

    public DetailDialog(Context context, String title, String prize, int order, Bitmap image) {
        super(context);

        this.title_txt = title;
        prize_txt = prize;
        this.order = order;
        this.image = image;

    }

    public void initFont() {
        Typefaces.setFont("KoPubDotumLight.ttf", title, title);
        Typefaces.setFont("KoPubDotumLight.ttf", explain, explain);
        //Typefaces.setFont("KoPubDotumLight.ttf", prize, prize);
    }

    public void initData() {
        explain_dum = new String[]{

        };

        closeButton = (ImageView) findViewById(R.id.closeButton);
        buyButton = (Button) findViewById(R.id.buyButton);
        title = (TextView) findViewById(R.id.title);
        explain = (TextView) findViewById(R.id.explain);
        prize = (TextView) findViewById(R.id.prize);
        DetailImage = (ImageView) findViewById(R.id.detail_image);

        DetailImage.setImageBitmap(image);

        initFont();

        title.setText(title_txt);
        prize.setText(prize_txt);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.detailexplain_store);


        wm = this.getWindow().getAttributes();
        wm.height = 740;
        wm.width = 793;

        initData();



        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //현재 포인트에서 물건 포인트 빼기 등등 처리 해야함
                dismiss(); //다이얼로그 종료
            }
        });

    }
}
