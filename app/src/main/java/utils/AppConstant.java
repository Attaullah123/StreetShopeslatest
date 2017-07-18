package utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ListPopupWindow;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import adapter.DropDownAdapter;

/**
 * Created by hardik on 07/01/17.
 */

public class AppConstant {



    /*List*/
    public static ArrayList<String> priceFilter = new ArrayList<>(
            Arrays.asList("Price: High to Low", "Price: Low to High"));

    public static  String user_id="";
    public  static String cart_id="";


    public static final String deviceType = "Android";
    public static final String USER_TYPE_NORMAL = "normal";
    public static final String USER_TYPE_TWITTER = "twitter";
    public static final String USER_TYPE_FACEBOOK = "facebook";
    public static final String USER_TYPE_LINKEDIN = "linkedin";

    public static final String TYPE_CUSTOMER_DETAILS = "customerDetail";
    public static final String TYPE_LOGIN_CUSTOMER = "loginCustomer";
    public static final String TYPE_STORE_LIST = "storesList";
    public static final String TYPE_PRODUCT_LIST = "productList";
    public static final String TYPE_SELLER_LIST = "sellersList";
    public static final String TYPE_BANNER_LIST = "bannersList";
    public static final String TYPE_CATEGORY_LIST = "categoryList";
    public static final String TYPE_CREATE_CUSTOMER = "createCustomer";
    public static final String TYPE_CUSTOMER_LIST = "categoryList";

    /* preference constrant */
    public static final String PRF_SIGNUP_USERID = "userid";
    public static final String PRF_SIGNUP_SHAREURL = "shareurl";

    public static final String EXTRA_SEARCH = "extra_search";
    public static final String EXTRA_SUB_CATEGORY = "extra_sub_category";
    public static final String EXTRA_PRODUCT = "extra_product";

    public static final String DATE_FORMAT1 = "yyyy-MM-dd";




    public static ProgressDialog progressDialog;

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public final static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public final static void showAlertDialog(Activity activity, String message) {
        new AlertDialog.Builder(activity)
                .setTitle("")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .show();
    }

    public final static void showProgressDialog(Activity activity) {
       // if(progressDialog == null) {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage("Loading");
        //}
        if(progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public final static void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    public static void setDate(final Context context, final TextView mDobEt) {

        DatePickerCustomDialog datePickerCustomDialog = DatePickerCustomDialog.getInstance(context, new DatePickerCallback() {
            @Override
            public void onDateSetCallBack(String date) {


                if( compareDates(date)){
                    Toast.makeText(context,"Please select proper date",Toast.LENGTH_LONG).show();
                }else {
                    mDobEt.setText(date);
                   // Toast.makeText(context,"bbb",Toast.LENGTH_LONG).show();
                }

            }

        });
        Calendar c = Calendar.getInstance();
        //int pYear = c.get(Calendar.YEAR);
        //int pMonth = c.get(Calendar.MONTH);
        //int pDay = c.get(Calendar.DAY_OF_MONTH);
        //c.set(pYear, pMonth, pDay);
        c.add(Calendar.YEAR, -17);
        datePickerCustomDialog.setMaxDateCustom(c.getTimeInMillis());
    }



    public static boolean compareDates(String d2)
    {
        try{
            // If you already have date objects then skip 1

            //1
            // Create 2 dates starts
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse("2016-01-01");
            Date date2 = sdf.parse(d2);

            System.out.println("Date1"+sdf.format(date1));
            System.out.println("Date2"+sdf.format(date2));System.out.println();

            // Create 2 dates ends
            //1

            // Date object is having 3 methods namely after,before and equals for comparing
            // after() will return true if and only if date1 is after date 2
            if(date1.after(date2)){
                System.out.println("Date1 is after Date2");
                return false;
            }
            // before() will return true if and only if date1 is before date2
            if(date1.before(date2)){
                System.out.println("Date1 is before Date2");
                return true;
            }

            //equals() returns true if both the dates are equal
            if(date1.equals(date2)){
                System.out.println("Date1 is equal Date2");
            }

            System.out.println();
        }
        catch(ParseException ex){
            ex.printStackTrace();
        }
        return false;
    }


    public static void showListPopup(Activity activity, View v, final ArrayList<String> genderList, final TextView genderTv) {
        DropDownAdapter mListAdapter = new DropDownAdapter(activity, genderList);
        final ListPopupWindow listPopupWindow = new ListPopupWindow(activity);
        listPopupWindow.setAdapter(mListAdapter);

        listPopupWindow.setAnchorView(v);
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.9);

        listPopupWindow.setWidth(width);

        listPopupWindow.setModal(true);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                genderTv.setText(genderList.get(position));
                listPopupWindow.dismiss();
            }
        });
        listPopupWindow.show();
    }


}
