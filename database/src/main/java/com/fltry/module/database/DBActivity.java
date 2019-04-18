package com.fltry.module.database;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.fltry.module.database.databinding.ActivityDbBinding;
import com.fltry.module.lib_common.BaseActivity;
import com.fltry.module.lib_common.GlobalValues;

public class DBActivity extends BaseActivity<ActivityDbBinding> {

    private SQLiteDatabase databaseWrite;
    private SQLiteDatabase databaseRead;
    private AlertDialog.Builder builder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_db;
    }

    @Override
    protected String title() {
        return "数据库";
    }

    @Override
    protected void initView() {
        builder = new AlertDialog.Builder(mContext);
        builder.setPositiveButton("知道了", null);

        DBHelper dbHelper = new DBHelper(mContext);
        databaseWrite = dbHelper.getWritableDatabase();
        databaseRead = dbHelper.getReadableDatabase();

        dataBinding.dbInsertBtn.setOnClickListener(v -> DBInsert());
        dataBinding.dbUpdateBtn.setOnClickListener(v -> DBUpdate());
        dataBinding.dbDeleteBtn.setOnClickListener(v -> DBDelete());
        dataBinding.dbSearchBtn.setOnClickListener(v -> DBSearch());
        dataBinding.dbSearchAllBtn.setOnClickListener(v -> DBSearchAll());
        dataBinding.dbCreatTableBtn.setOnClickListener(v -> {
            try {
                databaseWrite.execSQL(dbHelper.createTable());
            } catch (Exception e) {
                showAlert(e.getMessage());
            }
        });
        dataBinding.dbDeleteTableBtn.setOnClickListener(v -> {
            databaseWrite.execSQL("DROP TABLE IF EXISTS " + DBHelper.TABLE_NAME);
        });
    }

    private void showAlert(String msg) {
        builder.setMessage(msg);
        builder.show();
    }

    private void DBSearchAll() {
        try {
            Cursor cursor = databaseRead.query(DBHelper.TABLE_NAME, null, null,
                    null, null, null, null);
            int nameIndex = cursor.getColumnIndex(DBHelper.NAME);
            int ageIndex = cursor.getColumnIndex(DBHelper.AGE);
            int idIndex = cursor.getColumnIndex(DBHelper.ID);
            StringBuilder msg = new StringBuilder();
            while (cursor.moveToNext()) {
                String id = cursor.getString(idIndex);
                String name = cursor.getString(nameIndex);
                String age = cursor.getString(ageIndex);
                msg.append("id：").append(id).append("\tname：").append(name).append("\tage：").append(age).append("\n");
            }
            cursor.close();
            dataBinding.dbSearchResultTv.setText(msg.toString());
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    /*
        table           from table_name             指定查询的表名
        columns         select column1, column2     指定查询的列名
        selection       where column = value        指定 where 的约束条件
        selectionArgs   -                           为 where 中的占位符提供具体的值
        groupBy         group by column             指定需要 group by 的列
        having          having column = value       对 group by 后的结果进一步约束
        orderBy         order by column1, column2   指定查询结果的排序方式
     */
    private void DBSearch() {
        if (TextUtils.isEmpty(id())) {
            showAlert("请输入id");
            return;
        }
        try {
            String table = DBHelper.TABLE_NAME;
            String[] columns = new String[]{DBHelper.ID, DBHelper.NAME, DBHelper.AGE};
            String selection = DBHelper.ID + "=?";
            String[] selectionArgs = new String[]{id()};
            String groupBy = null;
            String having = null;
            String orderBy = null;
            Cursor cursor = databaseRead.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, null);
            StringBuilder msg = new StringBuilder();
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(DBHelper.ID));
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME));
                String age = cursor.getString(cursor.getColumnIndex(DBHelper.AGE));
                msg.append("id：").append(id).append("\tname：").append(name).append("\tage：").append(age).append("\n");
            }
            dataBinding.dbSearchResultTv.setText(msg.toString());
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(e.getMessage());
        }
    }

    private void DBDelete() {
        if (TextUtils.isEmpty(name()) && TextUtils.isEmpty(age()) && TextUtils.isEmpty(id())) {
            showAlert("至少输入一个删除的条件");
            return;
        }

        try {
            if (!TextUtils.isEmpty(id()))
                databaseWrite.delete(DBHelper.TABLE_NAME, DBHelper.ID + "=?", new String[]{id()});
            if (!TextUtils.isEmpty(name()))
                databaseWrite.delete(DBHelper.TABLE_NAME, DBHelper.NAME + "=?", new String[]{name()});
            if (!TextUtils.isEmpty(age()))
                databaseWrite.delete(DBHelper.TABLE_NAME, DBHelper.AGE + "=?", new String[]{age()});
            DBSearchAll();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(e.getMessage());
        }
    }

    private void DBUpdate() {
        if (TextUtils.isEmpty(name()) || TextUtils.isEmpty(age()) || TextUtils.isEmpty(id())) {
            showAlert("请输入姓名年龄及要修改的id");
            return;
        }
        String aql = "UPDATE " + DBHelper.TABLE_NAME + " set " + DBHelper.NAME + " = '" + name() + "' , " + DBHelper.AGE
                + " = '" + age() + "' WHERE " + DBHelper.ID + " = " + id();
        databaseWrite.execSQL(aql);
        DBSearchAll();
    }

    private void DBInsert() {
        if (TextUtils.isEmpty(name()) || TextUtils.isEmpty(age())) {
            Toast.makeText(mContext, "请输入姓名及年龄", Toast.LENGTH_LONG).show();
            return;
        }
        ContentValues values = new ContentValues();
        values.put(DBHelper.NAME, name());
        values.put(DBHelper.AGE, age());
        try {
            databaseWrite.insert(DBHelper.TABLE_NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(e.getMessage());
        }
        DBSearchAll();
    }

    private String name() {
        return dataBinding.dbNameEt.getText().toString();
    }

    private String age() {
        return dataBinding.dbAgeEt.getText().toString();
    }

    private String id() {
        return dataBinding.dbIdEt.getText().toString();
    }
}
