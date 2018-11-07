package com.tmzdh.monitor;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.tmauto.argus.mapi.model.TMAccount;
import com.tmzdh.monitor.common.BaseActivity;
import com.tmzdh.monitor.common.DisplayUtil;
import com.tmzdh.monitor.common.EncryptUtil;
import com.tmzdh.monitor.common.SpringScaleInterpolator;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by chris on 10/8/18.
 */
public class LoginFragment extends DialogFragment implements OnClickListener {

    View dialogView;

    AccountService accountService;

    Button btnLogin;
    TextView tvMsg;
    EditText etPhone, etPwd;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onStart() {
        super.onStart();

        Point size = DisplayUtil.getScreenSize(getActivity());
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = size.x * 95 / 100;
        params.height = size.y * 4 / 7;
        window.setAttributes(params);

        setCancelable(false);

        int color = ContextCompat.getColor(getActivity(), android.R.color.transparent);
        window.setBackgroundDrawable(new ColorDrawable(color));

        accountService = AccountService.instance();
    }

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); // 不显示标题栏

        dialogView = inflater.inflate(R.layout.login, container, false);

        startUpAnimation(dialogView);


        etPhone = dialogView.findViewById(R.id.et_name);
        etPwd = dialogView.findViewById(R.id.et_pwd);
        tvMsg = dialogView.findViewById(R.id.tv_msg);
        btnLogin = dialogView.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        dialogView.findViewById(R.id.iv_close).setOnClickListener(this);

        RxTextView.textChanges(etPhone).subscribe((text) -> {
                    if (text.length() == 11) {
                        etPwd.setFocusable(true);
                        etPwd.requestFocus();
                    }
                }
        );

        return dialogView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_login:
                String phone = etPhone.getText().toString();
                String pwd = etPwd.getText().toString();

                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
                    return;
                }

                tvMsg.setVisibility(View.GONE);
                etPwd.setEnabled(false);
                etPwd.setEnabled(false);
                view.setEnabled(false);

                accountService.login(phone, EncryptUtil.md5(pwd, ""))
                        .subscribe(new Observer<TMAccount>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(TMAccount account) {

                                switch (account.status.code) {
                                    case 200:
                                        tvMsg.setVisibility(View.GONE);
                                        startDownAnimation(dialogView);
                                        MainActivity activity = (MainActivity) getActivity();
                                        activity.injectToken(account.token);
                                        break;
                                    default:
                                        etPwd.setEnabled(true);
                                        etPwd.setEnabled(true);
                                        btnLogin.setEnabled(true);
                                        tvMsg.setVisibility(View.VISIBLE);
                                        tvMsg.setText(account.status.msg);
                                        break;
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case R.id.iv_close:
                startDownAnimation(dialogView);
        }
    }

    private void startUpAnimation(View view) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        slide.setDuration(400);
        slide.setInterpolator(new SpringScaleInterpolator(.8f));
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        view.startAnimation(slide);
    }

    private void startDownAnimation(View view) {

        ((BaseActivity) getActivity()).expandBackgroundAnimation();

        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(slide);
    }


}
