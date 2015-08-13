package com.example.allench.twitterclient.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.allench.twitterclient.R;
import com.example.allench.twitterclient.models.User;
import com.squareup.picasso.Picasso;

public class ComposeDialogFragment extends DialogFragment {

    private OnButtonClickListener mListener;
    private AlertDialog mDialog;
    private View mView;
    private ImageView ivComposeLogo;
    private TextView tvComposeUserName;
    private TextView tvComposeUserId;
    private TextView tvComposeCount;
    private EditText etComposeDescription;
    private int COMPOSE_TEXT_MAX_LENGTH = 140;

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        mListener = listener;
    }

    public interface OnButtonClickListener {
        public void onButtonApplyClick(String description);
    }

    public static ComposeDialogFragment newInstance(User user) {
        ComposeDialogFragment dialog = new ComposeDialogFragment();
        Bundle args = new Bundle();
        args.putString("userid", user.userid);
        args.putString("username", user.username);
        args.putString("profile_image_url", user.profile_image_url);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.fragment_compose_dialog, null);
        etComposeDescription = (EditText) mView.findViewById(R.id.etComposeDescription);
        tvComposeUserName = (TextView) mView.findViewById(R.id.tvComposeUserName);
        tvComposeUserId = (TextView) mView.findViewById(R.id.tvComposeUserId);
        tvComposeCount = (TextView) mView.findViewById(R.id.tvComposeCount);
        ivComposeLogo = (ImageView) mView.findViewById(R.id.ivComposeLogo);

        // init fields
        Bundle args = getArguments();
        tvComposeUserName.setText(args.getString("username"));
        tvComposeUserId.setText("@" + args.getString("userid"));
        tvComposeCount.setText(String.valueOf(COMPOSE_TEXT_MAX_LENGTH));
        ivComposeLogo.setImageResource(android.R.color.transparent);
        Picasso.with(builder.getContext()).load(args.getString("profile_image_url")).placeholder(R.drawable.loading).into(ivComposeLogo);

        // setup View
        builder.setView(mView);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("New Tweet...");

        // bind buttons
        builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if (mListener != null) {
                    String description = etComposeDescription.getText().toString();
                    mListener.onButtonApplyClick(description);
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        mDialog = builder.create();

        // while press Enter on EditText, focus on APPLY button
        etComposeDescription.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                int length = etComposeDescription.getText().length();
                int remain = COMPOSE_TEXT_MAX_LENGTH - length;
                tvComposeCount.setText(String.valueOf(remain));
                return false;
            }
        });

        return mDialog;
    }

}
