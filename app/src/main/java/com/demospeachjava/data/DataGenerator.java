package com.demospeachjava.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.TypedArray;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.demospeachjava.R;

import com.demospeachjava.activity.contact.Contact;
import com.demospeachjava.helper.ContactHelper;
import com.demospeachjava.model.People;
import com.demospeachjava.model.SayCategory;
import com.demospeachjava.model.ShopCategory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.demospeachjava.helper.ContactHelper.TABLE_CONTACT;

@SuppressWarnings("ResourceType")
public class DataGenerator {
    private static ContactHelper contactHelper;
    /**
     * Generate dummy data shopping category
     *
     * @param ctx android context
     * @return list of object
     */

    public static List<ShopCategory> getShoppingCategory(Context ctx) {
        List<ShopCategory> items = new ArrayList<>();
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.shop_category_icon);
        String title_arr[] = ctx.getResources().getStringArray(R.array.shop_category_title);
        for (int i = 0; i < drw_arr.length(); i++) {
            ShopCategory obj = new ShopCategory();
            obj.image = drw_arr.getResourceId(i, -1);
            obj.title = title_arr[i];
            obj.imageDrw = ctx.getResources().getDrawable(obj.image);
            items.add(obj);
        }
        return items;
    }
    public static List<SayCategory> getSayCategory(Context ctx) {
        List<SayCategory> items = new ArrayList<>();
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.category_say_icon);
        String title_arr[] = ctx.getResources().getStringArray(R.array.category_say_title);
        for (int i = 0; i < drw_arr.length(); i++) {
            SayCategory obj = new SayCategory();
            obj.image = drw_arr.getResourceId(i, -1);
            obj.title = title_arr[i];
            obj.imageDrw = ctx.getResources().getDrawable(obj.image);
            items.add(obj);
        }
        return items;
    }
}
