package com.quickblox.sample.chat.java.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.quickblox.auth.session.QBSettings;
import com.quickblox.sample.chat.java.BuildConfig;
import com.quickblox.sample.chat.java.R;


public class AppInfoActivity1 extends BaseActivity1 {

    private TextView appVersionTextView;
    private TextView sdkVersionTextView;
    private TextView apiDomainTextView;
    private TextView chatDomainTextView;
    private TextView appQAVersionTextView;

    public static void start(Context context) {
        Intent intent = new Intent(context, AppInfoActivity1.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinfo);

        initUI();
        fillUI();
    }

    private void initUI() {
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.appinfo_title);
        appVersionTextView = findViewById(R.id.tv_app_version);
        sdkVersionTextView = findViewById(R.id.tv_sdk_version);
        apiDomainTextView = findViewById(R.id.tv_api_domain);
        chatDomainTextView = findViewById(R.id.tv_chat_domain);
        appQAVersionTextView = findViewById(R.id.tv_qa_version);
    }

    public void fillUI() {
        appVersionTextView.setText(BuildConfig.VERSION_NAME);
        sdkVersionTextView.setText(com.quickblox.BuildConfig.VERSION_NAME);
        apiDomainTextView.setText(QBSettings.getInstance().getServerApiDomain());
        chatDomainTextView.setText(QBSettings.getInstance().getChatEndpoint());

        if (BuildConfig.IS_QA) {
            String appVersion = BuildConfig.VERSION_NAME;
            String versionQACode = String.valueOf(BuildConfig.VERSION_QA_CODE);
            String qaVersion = appVersion + "." + versionQACode;
            Spannable spannable = new SpannableString(qaVersion);
            spannable.setSpan(new ForegroundColorSpan(Color.RED), appVersion.length() + 1,
                    qaVersion.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            appQAVersionTextView.setText(spannable, TextView.BufferType.SPANNABLE);
            appQAVersionTextView.setVisibility(View.VISIBLE);

            findViewById(R.id.text_qa_version_title).setVisibility(View.VISIBLE);
        }
    }
}