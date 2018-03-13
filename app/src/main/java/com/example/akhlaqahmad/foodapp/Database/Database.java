package com.example.akhlaqahmad.foodapp.Database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.akhlaqahmad.foodapp.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akhlaq Ahmad on 2/12/2018.
 */

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME =  "tasteItDB.db";
    private static final int DB_VER = 1;
    public Database(Context context)
    {
        super(context, DB_NAME, null, DB_VER);

    }

    public List<Order> getCarts()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ProductName","ProductId","Quantity","Price","Discount"};
        String sqlTable = "OrderDetail";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Order> result = new ArrayList<>();

        if(cursor.moveToFirst()){

            do{
                result.add(
                        new Order (cursor.getString(cursor.getColumnIndex("ProductId"))  ,
                                cursor.getString(cursor.getColumnIndex("ProductName"))  ,
                                cursor.getString(cursor.getColumnIndex("Quantity"))  ,
                                cursor.getString(cursor.getColumnIndex("Price"))  ,
                                cursor.getString(cursor.getColumnIndex("Discount"))
                        ));
            }while (cursor.moveToNext());


        }

        return result;


    }
    public void addToCart(Order order){


        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount) VALUES('%s','%s','%s','%s','%s');",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount()
        );
        db.execSQL(query);

    }

    public void ClearCart(){


        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");
        db.execSQL(query);

    }
}
