package de.danoeh.antennapod.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import de.danoeh.antennapod.R;
import de.danoeh.antennapod.core.gpoddernet.GpodnetService;
import de.danoeh.antennapod.core.preferences.GpodnetPreferences;

/**
 * Creates a dialog that lets the user change the hostname for the gpodder.net service.
 */
public class GpodnetSetHostnameDialog {
    private static final String TAG = "GpodnetSetHostnameDialog";

    public static AlertDialog createDialog(final Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        final EditText et = new EditText(context);
        et.setText(GpodnetPreferences.getHostname());
        et.setInputType(InputType.TYPE_TEXT_VARIATION_URI);
        dialog.setTitle(R.string.pref_gpodnet_sethostname_title)
                .setView(setupContentView(context, et))
                .setPositiveButton(R.string.confirm_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Editable e = et.getText();
                        if (e != null) {
                            GpodnetPreferences.setHostname(e.toString());
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNeutralButton(R.string.pref_gpodnet_sethostname_use_default_host, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GpodnetPreferences.setHostname(GpodnetService.DEFAULT_BASE_HOST);
                        dialog.dismiss();
                    }
                })
                .setCancelable(true);
        return dialog.show();
    }

    private static View setupContentView(Context context, EditText et) {
        LinearLayout ll = new LinearLayout(context);
        ll.addView(et);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) et.getLayoutParams();
        if (params != null) {
            params.setMargins(8, 8, 8, 8);
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        return ll;
    }
}