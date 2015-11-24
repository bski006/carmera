package carmera.io.carmera.utils;

/*
 * Copyright (c) 2015 52inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.content.Context;
import android.graphics.PointF;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.TextView;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import carmera.io.carmera.models.Listing;

public class Util {

    private static Socket uploadSocket;
    private static Random random = new Random();
    private static final String TAG = "Util";
    public static Comparator<Listing> PRICE_COMPARATOR, MILEAGE_COMPARATOR, DATE_COMPARATOR;
    /**
     * Get the Random Number Generator
     * @return  the static random class
     */
    public static Random getRandom(){ return random; }

    /**
     * Get the time from epoch in seconds
     *
     * @return      epoch seconds
     */
    public static long time(){
        return System.currentTimeMillis()/1000;
    }

    /**
     * Return whether or not a given string is a valid email address according to
     * the {@link Patterns#EMAIL_ADDRESS}
     *
     * @param email     the string to validate
     * @return          true if string is a valid email
     */
    public static boolean isValidEmail(CharSequence email){
        return Patterns.EMAIL_ADDRESS.matcher(email).find();
    }

    /**
     * Generate a unique device identifier that can be replicated
     * on the device
     * <p>
     * CAVEAT This method requires the android.Manifest.permission#READ_PHONE_STATE permission
     * </p>
     * @param ctx       the application context
     * @return          the device unique id
     */
    public static String generateUniqueDeviceId(Context ctx){
        final TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);


        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(ctx.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);


        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return deviceUuid.toString();
    }

    /**
     * Generate a new UUID if one is not stored. This id is session/install based so every time
     * you install/re-install or wipe data for this application, this value will update and
     * change.
     *
     * @param uuidPref      the uuid preference
     * @return              the unique device id
     */
//    public static String generateUniqueDeviceId(StringPreference uuidPref){
//        // Check if uuid is set
//        if(uuidPref.isSet()){
//            return uuidPref.get();
//        }else{
//            String uuid = UUID.randomUUID().toString();
//            uuidPref.set(uuid);
//            return uuid;
//        }
//    }

    /**
     * Get the Device's GMT Offset
     * @return	the gmt offset in hours
     */
    public static int getGMTOffset(){
        Calendar now = Calendar.getInstance();
        return (now.get(Calendar.ZONE_OFFSET) + now.get(Calendar.DST_OFFSET))  / 3600000;
    }

    /**
     * Get the MIME type of a file
     * @param url
     * @return
     */
    public static String getMimeType(String url)
    {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            type = mime.getMimeTypeFromExtension(extension);
        }
        return type;
    }


    public static String getMimeTypeFromExt(String ext){
        String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);
        if(mime != null)
            return mime;
        else
            return "";
    }

    /**
     * Parse a file's mime type from the file extension
     *
     * @param filename
     * @return
     */
    public static String parseMimeType(String filename){
        return URLConnection.guessContentTypeFromName(filename);
    }


    /**
     * Compute the distance between two points
     *
     * @param p1		the first point
     * @param p2		the second point
     * @return			the distance between the two points
     */
    public static float distance(PointF p1, PointF p2){
        return (float) Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow(p2.y - p1.y,2));
    }


    /**
     * Convert Density-Independent Pixels to actual pixels
     *
     * @param ctx       the application context
     * @param dpSize    the size in DP units
     * @return          the size in Pixel units
     */
    public static float dpToPx(Context ctx, float dpSize) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, ctx.getResources().getDisplayMetrics());
    }


    /**
     * Convert Scale-Dependent Pixels to actual pixels
     *
     * @param ctx       the application context
     * @param spSize    the size in SP units
     * @return          the size in Pixel units
     */
    public static float spToPx(Context ctx, float spSize){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spSize, ctx.getResources().getDisplayMetrics());
    }


    /**
     * Clamp Integer values to a given range
     *
     * @param value     the value to clamp
     * @param min       the minimum value
     * @param max       the maximum value
     * @return          the clamped value
     */
    public static int clamp(int value, int min, int max){
        return Math.max(min, Math.min(max, value));
    }


    /**
     * Clamp Float values to a given range
     *
     * @param value     the value to clamp
     * @param min       the minimum value
     * @param max       the maximum value
     * @return          the clamped value
     */
    public static float clamp(float value, float min, float max){
        return Math.max(min, Math.min(max, value));
    }


    /**
     * Clamp Long values to a given range
     *
     * @param value     the value to clamp
     * @param min       the minimum value
     * @param max       the maximum value
     * @return          the clamped value
     */
    public static long clamp(long value, long min, long max){
        return Math.max(min, Math.min(max, value));
    }


    /**
     * Clamp Double values to a given range
     *
     * @param value     the value to clamp
     * @param min       the minimum value
     * @param max       the maximum value
     * @return          the clamped value
     */
    public static double clamp(double value, double min, double max){
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Compares two {@code long} values.
     * @return 0 if lhs = rhs, less than 0 if lhs &lt; rhs, and greater than 0 if lhs &gt; rhs.
     * @since 1.7
     */
    public static int compare(long lhs, long rhs) {
        return lhs < rhs ? -1 : (lhs == rhs ? 0 : 1);
    }

    /**
     * Compares two {@code int} values.
     * @return 0 if lhs = rhs, less than 0 if lhs &lt; rhs, and greater than 0
     *         if lhs &gt; rhs.
     * @since 1.7
     */
    public static int compare(int lhs, int rhs) {
        return lhs < rhs ? -1 : (lhs == rhs ? 0 : 1);
    }

    /**
     * Parse a float from a String in a safe manner.
     *
     * @param val           the string to parse
     * @param defVal        the default value to return if parsing fails
     * @return              the parsed float, or default value
     */
    public static float parseFloat(String val, float defVal){
        if(TextUtils.isEmpty(val)) return defVal;
        try{
            return Float.parseFloat(val);
        }catch (NumberFormatException e){
            return defVal;
        }
    }

    /**
     * Parse a int from a String in a safe manner.
     *
     * @param val           the string to parse
     * @param defValue      the default value to return if parsing fails
     * @return              the parsed int, or default value
     */
    public static int parseInt(String val, int defValue){
        if(TextUtils.isEmpty(val)) return defValue;
        try{
            return Integer.parseInt(val);
        }catch (NumberFormatException e){
            return defValue;
        }
    }

    /**
     * Parse a long from a String in a safe manner.
     *
     * @param val           the string to parse
     * @param defValue      the default value to return if parsing fails
     * @return              the parsed long, or default value
     */
    public static long parseLong(String val, long defValue){
        if(TextUtils.isEmpty(val)) return defValue;
        try{
            return Long.parseLong(val);
        }catch (NumberFormatException e){
            return defValue;
        }
    }

    /**
     * Parse a double from a String in a safe manner
     *
     * @param val           the string to parse
     * @param defValue      the default value to return in parsing fails
     * @return              the parsed double, or default value
     */
    public static double parseDouble(String val, double defValue){
        if(TextUtils.isEmpty(val)) return defValue;
        try{
            return Double.parseDouble(val);
        }catch(NumberFormatException e){
            return defValue;
        }
    }

    public static String joinStrings (List<String> strings, String delimiter) {
        String result = "";
        for (String x:strings) {
            result += x + delimiter;
        }
        return result.substring(0, result.length()-2);
    }

    public static List<KeyPairBoolData> getSpinnerValues (List<String> values) {
        Collections.sort(values);
        final List<KeyPairBoolData> kv_list = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<values.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i+1);
            h.setName(values.get(i));
            h.setSelected(false);
            kv_list.add(h);
        }
        return kv_list;
    }

    public static List<KeyPairBoolData> getIntegerSpinnerValues (List<Integer> values) {
        Collections.sort(values);
        final List<KeyPairBoolData> kv_list = new ArrayList<KeyPairBoolData>();
        for(int i=0; i<values.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i+1);
            h.setName(values.get(i).toString());
            h.setSelected(false);
            kv_list.add(h);
        }
        return kv_list;
    }

    public static String getRangeText (Object a, Object b) {
        if (a != null && b != null
                && Integer.parseInt(a.toString()) > 0
                && Integer.parseInt(b.toString()) > 0)
            return String.format("%s - %s", a.toString(), b.toString());

        else if (a == null && b == null)
            return null;

        else if ( a == null )
            return b.toString();

        else
            return a.toString();
    }

    public static Socket getUploadSocket (String server_addr) {
        if (uploadSocket == null) {
            try {
                uploadSocket = IO.socket(server_addr);
            } catch (URISyntaxException e) {
                Log.i(TAG, e.getMessage());
            }
        }
        return uploadSocket;
    }

    public static void disconnectSocket () {
        if (uploadSocket != null)
            uploadSocket.disconnect();
    }

    public static Comparator<Listing> getDateComparator () {
        return new Comparator<Listing>() {
            @Override
            public int compare(Listing lhs, Listing rhs) {
                java.text.DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                try {
                    return (fmt.parse (lhs.listedSince).before(fmt.parse(rhs.listedSince)))?-1:1;
                } catch (Exception e) {
                    return 0;
                }
            }
        };
    }

    public static Comparator<Listing> getMileageComparator () {
        return new Comparator<Listing>() {
            @Override
            public int compare(Listing lhs, Listing rhs) {
                try {
                    return lhs.mileage - rhs.mileage;
                } catch (Exception e) {
                    return 0;
                }
            }
        };
    }

    public static Comparator<Listing> getPriceComparator () {
        return new Comparator<Listing>() {
            @Override
            public int compare(Listing lhs, Listing rhs) {
                try {
                    return (int) (lhs.prices.msrp - rhs.prices.msrp);
                } catch (Exception e) {
                    return 0;
                }
            }
        };
    }


    public static void setText (TextView textview, String text) {
        if (text != null)
            textview.setText(text);
        else
            textview.setVisibility(View.GONE);
    }



    public static  List<KeyPairBoolData> getSelectedValues (List<String> values) {
        List<KeyPairBoolData> items = Util.getSpinnerValues(values);
        for (KeyPairBoolData item : items) {
            item.setSelected(true);
        }
        return items;
    }

    public static List<KeyPairBoolData> getIntSelectedValues (List<Integer> values) {
        List<KeyPairBoolData> items = Util.getIntegerSpinnerValues(values);
        for (KeyPairBoolData item : items) {
            item.setSelected(true);
        }
        return items;
    }

}
