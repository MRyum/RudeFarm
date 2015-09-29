package com.example.yum.rudefarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

public class BaseInfoActivity extends Activity implements View.OnClickListener {

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private Uri mImageCaptureUri;

    ImageView profileImg;
    EditText nickname_eT;
    EditText farmname_eT;

    Bitmap circlePhoto;
    String nickname;
    String farmname;

    Button button;

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
        nickname_eT = (EditText) findViewById(R.id.nickname);
        farmname_eT = (EditText) findViewById(R.id.farmname);
        nickname = nickname_eT.getText().toString();
        //이게 안먹힙니다. 왜인지 모르겠는데, 변수에 스트링값이 안들어가면
        //nickname_eT.getText().toString()으로 서버에 보내는게 좋을듯 합니다.
        farmname = farmname_eT.getText().toString();
        //마찬가지임다 ㅠㅠ 다른 액티비티나 프래그먼트에도 그러니까 주의하시길..

    }
    //이게 안먹힙니다. 왜인지 모르겠는데, 변수에 스트링값이 안들어가면
    //nickname_eT.getText().toString()으로 서버에 보내는게 좋을듯 합니다

    public boolean etCheck() {
        if (nickname_eT.getText().length() == 0 || farmname_eT.getText().length() == 0)
            return true;
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_info);

        initEditText();

        profileImg = (ImageView) findViewById(R.id.profile_img);

        button = (Button) findViewById(R.id.Nextbtn);
        button.setAlpha(0.7f);

        profileImg.setOnClickListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCheck()) {
                    mDialog = createDialog();
                    mDialog.show();
                } else {
                    //Intent i = new Intent(BaseInfoActivity.this, LoginActivity.class);
                    finish();
                    //startActivity(i);
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakePhotoAction();
            }
        };

        DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakeAlbumAction();
            }
        };

        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        new AlertDialog.Builder(this)
                .setTitle("업로드할 이미지 선택")
                .setPositiveButton("사진촬영", cameraListener)
                .setNeutralButton("앨범선택", albumListener)
                .setNegativeButton("취소", cancelListener)
                .show();
    }
    public Bitmap cropCircle(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        int size = (bitmap.getWidth() / 2);
        canvas.drawCircle(size, size, size, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
    private void doTakePhotoAction() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        //intent.putExtra("return-data", true);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }
    private void doTakeAlbumAction() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case CROP_FROM_CAMERA: {
                final Bundle extras = data.getExtras();

                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    circlePhoto = cropCircle(photo);
                    profileImg.setImageBitmap(circlePhoto);
                }
                File f = new File(mImageCaptureUri.getPath());
                if (f.exists()) {
                    f.delete();
                }

                break;
            }

            case PICK_FROM_ALBUM: {
                mImageCaptureUri = data.getData();
            }

            case PICK_FROM_CAMERA: {
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                intent.putExtra("outputX", 100);
                intent.putExtra("outputY", 100);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_CAMERA);

                break;
            }
        }
    }

}
