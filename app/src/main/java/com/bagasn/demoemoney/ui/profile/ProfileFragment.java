package com.bagasn.demoemoney.ui.profile;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bagasn.demoemoney.LoginActivity;
import com.bagasn.demoemoney.R;
import com.bagasn.demoemoney.util.SPSession;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private TextView textID;
    private TextView textUserName;
    private TextView textUserEmail;
    private TextView textUserMember;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textID = view.findViewById(R.id.text_no_rek);
        textUserName = view.findViewById(R.id.text_name);
        textUserEmail = view.findViewById(R.id.text_email);
        textUserMember = view.findViewById(R.id.text_member_type);

        setProfileValue();

        textID.setOnClickListener(this);

        view.findViewById(R.id.btn_setting_profile)
                .setOnClickListener(this);
        view.findViewById(R.id.btn_setting_password)
                .setOnClickListener(this);
        view.findViewById(R.id.btn_setting_terms)
                .setOnClickListener(this);
        view.findViewById(R.id.btn_setting_about)
                .setOnClickListener(this);
        view.findViewById(R.id.btn_setting_help)
                .setOnClickListener(this);
        view.findViewById(R.id.btn_logout)
                .setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_no_rek:
                copyIdUserToClipboard();
                break;
            case R.id.btn_logout:
                actionLogout();
                break;
            default:
                toast("This feature is coming soon.");
                break;
        }
    }

    private void setProfileValue() {
        if (getActivity() == null)
            return;

        SPSession ss = SPSession.init(getActivity());
        textID.setText(ss.getString(SPSession.keyUserNoId));
        textUserName.setText(ss.getString(SPSession.keyUserName));
        textUserEmail.setText(ss.getString(SPSession.keyUserEmail));
        textUserMember.setText(ss.getString(SPSession.keyUserRank));
    }

    private void copyIdUserToClipboard() {
        if (getContext() == null) {
            toast("Failed to copy your ID.");
            return;
        }

        String userId = SPSession.init(getContext())
                .getString(SPSession.keyUserNoId);

        ClipboardManager cm = (ClipboardManager) getContext()
                .getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("id_demo_emoney_app", userId);
        cm.setPrimaryClip(data);

        toast("Your ID has been copied.");
    }

    private void actionLogout() {
        if (getActivity() == null) {
            toast("Failed to logout.");
            return;
        }

        SPSession.init(getActivity())
                .clearSession();

        getActivity().finishAffinity();
        startActivity( new Intent(getActivity(), LoginActivity.class));
    }

    private void toast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT)
                .show();
    }

}