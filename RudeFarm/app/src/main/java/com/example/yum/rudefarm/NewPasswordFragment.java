package com.example.yum.rudefarm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by YUM on 2015-09-20.
 */
public class NewPasswordFragment extends Fragment {

    Toolbar toolbar;
    Button perfectBtn;

    EditText newPassword_et;
    EditText confirm_et;

    String newPassword = new String();
    String confirm = new String();

    AlertDialog mDialog;

    public NewPasswordFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.newpassword, container, false);

        toolbar = (Toolbar) rootView.findViewById(R.id.fpw_toolbar);
        TextView toolbarText = (TextView) toolbar.getChildAt(0);
        toolbarText.setText("새 비밀번호 설정");

        perfectBtn = (Button) rootView.findViewById(R.id.perBtn);


        newPassword_et = (EditText) rootView.findViewById(R.id.newpassword);
        confirm_et = (EditText) rootView.findViewById(R.id.confirm);

        newPassword = newPassword_et.getText().toString();
        confirm = confirm_et.getText().toString();

        perfectBtn.setAlpha(0.7f);

        perfectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("test", "Asfd " + newPassword);
                if (newPassword_et.getText().toString().equals(confirm_et.getText().toString()) &&
                        newPassword_et.getText().length() != 0 && confirm_et.getText().length() != 0) {
                    getActivity().finish();
                } else {
                    mDialog = createDialog();
                    mDialog.show();
                }
            }
        });

        return rootView;
    }

    private AlertDialog createDialog() {
        AlertDialog.Builder ab = new AlertDialog.Builder(getActivity())
                .setTitle("오류")
                .setMessage("비밀번호가 일치하지 않습니다")
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
