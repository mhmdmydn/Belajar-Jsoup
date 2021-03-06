package com.del.app.jsoup.util;

import android.app.Activity;
import android.view.View;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.CheckBox;
import android.widget.Button;
import android.content.Context;
import android.widget.Toast;
import android.view.Gravity;
import android.graphics.drawable.GradientDrawable;
import android.view.inputmethod.InputMethodManager;
import android.util.TypedValue;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AlertDialog;

public class MainUtil {
    
    public static int TOP = 1;
    public static int CENTER = 2;
    public static int BOTTOM = 3;
    
    /**
     * Method change font activity
     */
    public static void changeActivityFont(Activity con, String fontNameAsset){
        String fontName = fontNameAsset.trim();
        if(fontName.contains(".ttf")){
            fontName = fontName.replaceAll(".ttf", "");
        }
        overrideFonts(con, con.getWindow().getDecorView(), fontNameAsset);
    }

    private static void overrideFonts(final Activity con, final View v, final String fontName){
        try{
            Typeface activityTypeFace = Typeface.createFromAsset(con.getAssets(), "fonts/" + fontName + ".ttf");
            if((v instanceof ViewGroup)){
                ViewGroup activityFontGroup = (ViewGroup) v;
                for (int i = 0; i < activityFontGroup.getChildCount(); i++) {
                    View child = activityFontGroup.getChildAt(i);
                    overrideFonts(con, child, fontName);
                }
            }
            else {
                if ((v instanceof TextView)) {
                    ((TextView) v).setTypeface(activityTypeFace);
                }
                else {
                    if ((v instanceof EditText )) {
                        ((EditText) v).setTypeface(activityTypeFace);
                    }
                    else {
                        if ((v instanceof Switch )) {
                            ((Switch) v).setTypeface(activityTypeFace);
                        }
                        else {
                            if ((v instanceof CheckBox )) {
                                ((CheckBox) v).setTypeface(activityTypeFace);
                            }
                            else {
                                if ((v instanceof Button)) {
                                    ((Button) v).setTypeface(activityTypeFace);
                                }
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            showToast(con, e.toString());
        }
    } 
    
    public static void customToast(Context context, String message, int textColor, int textSize, int bgColor, int radius, int gravity) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        View view = toast.getView(); 
        TextView msg = view.findViewById(android.R.id.message); 
        msg.setTextSize(textSize); 
        msg.setTextColor(textColor); 
        msg.setGravity(Gravity.CENTER); 
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(bgColor);
        shape.setCornerRadius(radius); 
        view.setBackgroundDrawable(shape); 
        view.setPadding(15,10,15,10);
        view.setElevation(10);
        switch(gravity){
            case 1:
                toast.setGravity(Gravity.TOP, 0,150);
                break;
            case 2:
                toast.setGravity(Gravity.CENTER, 0,0);
                break;
            case 3:
                toast.setGravity(Gravity.BOTTOM, 0,150);
                break;
        }
        toast.show();
    }
    
    public static void showDialog(Context context, String title, String msg, DialogInterface.OnClickListener ok, DialogInterface.OnClickListener close){
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("OK", ok)
                .setNegativeButton("CLOSE", close);
                
        AlertDialog alert = builder.create();
        alert.show();
    }
    
    public static void showToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT);
    }
    
    public static void hideKeyboard(Context c){
        InputMethodManager imm = (InputMethodManager) 
            c.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public static void showKeyboard(Context c){
        InputMethodManager imm = (InputMethodManager)
            c.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
	}
    
    public static float getDip(Context context, int input) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, input, context.getResources().getDisplayMetrics());
    }
    
    
    
}