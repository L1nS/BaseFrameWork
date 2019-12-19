package com.lins.baseframework.utils.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.lins.baseframework.R;


/**
 * Created by Admin on 2017/4/21.
 */

public class MessageDialog extends DialogFragment {

    private CharSequence message;
    private String positive;
    private DialogInterface.OnClickListener positiveListener;

    public static MessageDialog newInstance(CharSequence message, String positive, DialogInterface.OnClickListener positiveListener) {
        MessageDialog instance = new MessageDialog();
        instance.message = message;
        instance.positive = positive;
        instance.positiveListener = positiveListener;
        return instance;
    }

    public static MessageDialog newInstance(CharSequence message, String positive) {
        MessageDialog instance = new MessageDialog();
        instance.message = message;
        instance.positive = positive;
        instance.positiveListener = null;
        return instance;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_message, null);

        TextView idTvMsg = view.findViewById(R.id.id_tv_msg);
        TextView idBtnPositive = view.findViewById(R.id.id_tv_positive);

        idTvMsg.setText(message);
        idBtnPositive.setText(positive);

        idBtnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positiveListener != null)
                    positiveListener.onClick(getDialog(), DialogInterface.BUTTON_POSITIVE);
                else
                    dismiss();
            }
        });

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }
}