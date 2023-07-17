package log.dnn.geoloc.uiclasses;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;


import androidx.fragment.app.Fragment;

import log.dnn.geoloc.R;


public class LoadingDialog {
    private AlertDialog dialogAlert;
    public Fragment fragment;

    public LoadingDialog(Fragment fragment) {
        this.fragment = fragment;
    }

    public void startLoading(){
        LayoutInflater inflater  = this.fragment.getLayoutInflater();

        AlertDialog.Builder builder = new AlertDialog.Builder(this.fragment.getActivity());
        builder.setView(inflater.inflate(R.layout.loading_item, null));
        builder.setInverseBackgroundForced(true);
        builder.setCancelable(false);

        dialogAlert = builder.create();
        dialogAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT
        ));
        dialogAlert.show();
    }

    public void dismiss(){
        dialogAlert.dismiss();
    }

}
