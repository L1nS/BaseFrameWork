package com.lins.baseframework.utils.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.lins.baseframework.R;


/**
 * Created by Admin on 2017/4/21.
 */

public class SelectDialog extends DialogFragment {

    private CharSequence message;
    private String positive;
    private DialogInterface.OnClickListener positiveListener;
    private String negative;
    private DialogInterface.OnClickListener negativeListener;

    public static SelectDialog newInstance(CharSequence message, String positive, DialogInterface.OnClickListener positiveListener,
                                           String negative, DialogInterface.OnClickListener negativeListener) {
        SelectDialog instance = new SelectDialog();
        instance.message = message;
        instance.positive = positive;
        instance.positiveListener = positiveListener;
        instance.negative = negative;
        instance.negativeListener = negativeListener;
        return instance;
    }

    public static SelectDialog newInstance(CharSequence message, String positive, DialogInterface.OnClickListener positiveListener,
                                           String negative) {
        SelectDialog instance = new SelectDialog();
        instance.message = message;
        instance.positive = positive;
        instance.positiveListener = positiveListener;
        instance.negative = negative;
        instance.negativeListener = null;
        return instance;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_select, null);

        TextView idTvMsg = view.findViewById(R.id.id_tv_msg);
        TextView idBtnNegative = view.findViewById(R.id.id_tv_negative);
        TextView idBtnPositive = view.findViewById(R.id.id_tv_positive);

        idTvMsg.setText(message);
        idBtnNegative.setText(negative);
        idBtnPositive.setText(positive);

        idBtnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positiveListener != null)
                    positiveListener.onClick(getDialog(), DialogInterface.BUTTON_POSITIVE);
            }
        });
        idBtnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (negative != null)
                    negativeListener.onClick(getDialog(), DialogInterface.BUTTON_NEGATIVE);
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