package com.cartera.masterkey.cartera.util;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.views.dialogs.ConfigurarImpresoraDialogFragment;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class Utilidades {

    public static String obtenerIMEICelular(Context actividad) {
        String pIMEI = null;
        TelephonyManager tm = (TelephonyManager) actividad.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            return pIMEI = tm.getDeviceId();
        } else if (pIMEI == null || pIMEI.length() == 0) {
            return pIMEI = Settings.Secure.getString(actividad.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return pIMEI;
    }

    public static String obtenerSerialSimCard(Context actividad) throws Exception {
        TelephonyManager tm = (TelephonyManager) actividad.getSystemService(Context.TELEPHONY_SERVICE);
        String simSerial = null;
        if (tm != null) {
            return simSerial = tm.getSimSerialNumber();
        }
        return simSerial;
    }

    public static Animator prepareUnrevealAnimator(View view, float cx, float cy) {
        int radius = getEnclosingCircleRadius(view, (int) cx, (int) cy);
        Animator anim = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(view, (int) cx, (int) cy, radius, 0);
            anim.setInterpolator(new AccelerateInterpolator(2f));
            anim.setDuration(500);
        }

        return anim;
    }

    private static int getEnclosingCircleRadius(View v, int cx, int cy) {
        int realCenterX = cx + v.getLeft();
        int realCenterY = cy + v.getTop();
        int distanceTopLeft = (int) Math.hypot(realCenterX - v.getLeft(), realCenterY - v.getTop());
        int distanceTopRight = (int) Math.hypot(v.getRight() - realCenterX, realCenterY - v.getTop());
        int distanceBottomLeft = (int) Math.hypot(realCenterX - v.getLeft(), v.getBottom() - realCenterY);
        int distanceBottomRight = (int) Math.hypot(v.getRight() - realCenterX, v.getBottom() - realCenterY);

        Integer[] distances = new Integer[]{distanceTopLeft, distanceTopRight, distanceBottomLeft,
                distanceBottomRight};

        return Collections.max(Arrays.asList(distances));
    }

    public static void onFragmentTouched(final Fragment fragment, float x, float y) {
        Animator unreveal = Utilidades.prepareUnrevealAnimator(fragment.getView(), x, y);

        unreveal.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                fragment.getFragmentManager().popBackStackImmediate();
                //    fragment.getS().executePendingTransactions();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        unreveal.start();
    }

    public static void onDialogTouched(final DialogFragment dialog, float x, float y) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animator unreveal = Utilidades.prepareUnrevealAnimator(dialog.getView(), x, y);

            unreveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    dialog.dismiss();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
            unreveal.start();
        } else {
            dialog.dismiss();
        }
    }

    public static void alertDialogInformacion(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setTitle(R.string.informacion);
        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.show();
    }

    public static void alertDialogConfigurarImpresora(FragmentManager fragmentManager, Context context) {
        ConfigurarImpresoraDialogFragment dialogFragment = ConfigurarImpresoraDialogFragment.newInstance(context);
        //  dialogFragment.setTargetFragment(activity.getFragmentManager(), requestCode);
        dialogFragment.show(fragmentManager, "configurarImpresora");

        Fragment frag = fragmentManager.findFragmentByTag("configurarImpresora");
        if (frag != null) {
            fragmentManager.beginTransaction().remove(frag).commit();
        }
    }

    public static void formatearValor(final EditText edtValor) {
        final NumberFormat nf;
        nf = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        edtValor.addTextChangedListener(new TextWatcher() {

            private boolean isUpdating = false;

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int after) {
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                isUpdating = true;

                String str = s.toString();
                boolean hasMask = ((str.indexOf("R$") > -1 || str.indexOf("$") > -1));
                if (hasMask) {
                    str = str.replaceAll("[R$]", "").replaceAll("[.]", "");
                }

                if (!edtValor.getText().toString().equals("")) {
                    try {
                        String valor = edtValor.getText().toString();
                        Log.i("valor", valor);
                        int valor2 = Integer.parseInt(valor.replaceAll("[R$]", "").replaceAll("[.]", ""));
                       // double valor2 = Double.parseDouble(valor.replace("$", ""));
                        Log.i("valor2 int", "" + valor2);
                        str = nf.format(valor2);
                        Log.i("valor2 int", str);

                        //edtValor.setText(str.replaceAll("[R$]", ""));
                        edtValor.setText(str);
                        edtValor.setSelection(edtValor.getText().length());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no utlizado
            }

            @Override
            public void afterTextChanged(Editable s) {
                // no utilizado
            }
        });
    }

    public static void formatearValor(final TextView txtValor) {
        final NumberFormat nf;
        nf = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));


        if (!txtValor.getText().toString().equals("")) {
            try {
                String valor = txtValor.getText().toString();
                double valor2 = Double.parseDouble(valor.replace("$", ""));
                valor = nf.format(valor2);
                valor = valor.replace("$", "");
                valor = "$" + valor;
                txtValor.setText(valor);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

    }

    public static String formatearValor(Double valor) {
        final NumberFormat nf;
        nf = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        String txtValor = "";

        try {
            txtValor = nf.format(valor);
            txtValor = txtValor.replace("$", "");
            txtValor = "$" + txtValor;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return txtValor;
    }

    public static String convertirPrimeraMayuscula(String texto) {
        texto = texto.trim();
        texto = texto.toLowerCase();
        if (texto.contains("<html>") || texto.contains("</html>")) {
            texto = texto.replace("<html>", "");
            texto = texto.replace("</html>", "");
            texto = texto.trim();
        }

        if (texto.length() > 2) {
            //texto = texto.replaceFirst(texto.substring(0, 1), texto.substring(0, 1).toUpperCase()) + ".";
            texto = texto.replaceFirst(texto.substring(0, 1), texto.substring(0, 1).toUpperCase());
        }
        return texto;
    }

    public static void ocultarTeclado(Activity activity, EditText editText){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static void ocultarTeclado(Activity activity, SearchView searchView){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
    }
}
