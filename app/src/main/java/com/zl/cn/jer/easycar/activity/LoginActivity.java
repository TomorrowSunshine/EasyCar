package com.zl.cn.jer.easycar.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.bean.Loginbean;
import com.zl.cn.jer.easycar.bean.UpdateBean;
import com.zl.cn.jer.easycar.utils.SecurityUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 * 登录页面
 * 周辉
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private String string_user_tel;
    private String string_user_passworld;
    private String path;
    private String versionname;
    private String old_versionName;
    static String YES = "yes";
    static String NO = "no";
    private String isMemory = "";//isMemory变量用来判断SharedPreferences有没有数据，包括上面的YES和NO
    private String FILE = "saveUserNamePwd";//用于保存SharedPreferences的文件
    private SharedPreferences sp = null;//声明一个SharedPreferences
    static String name, password;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Button login_btn;

    private EditText user_tel;
    private EditText user_passworld;
    private String telephone;
    private String pass;

    private AlertDialog noticeDialog;
    private TextView forgets_password;
    private TextView zhu_ce;
    private CheckBox ck_1;
    private boolean isfirst;
    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //版本更新
        signsUp();
        //找到控件
        find();
        login_btn.setOnClickListener(this);
        zhu_ce.setOnClickListener(this);
        forgets_password.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }
    //检查权限（赵雷）
    public void checkPression(){

        if (ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "请开启权限", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(LoginActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_ACCESS_FINE_LOCATION);

            }
        }else {
            signUp();
        }
    }
    public void find() {
        //注册按钮
        zhu_ce = (TextView) findViewById(R.id.login_zhu_ce);
        //登陆按钮
        login_btn = (Button) findViewById(R.id.login_btn);
        //忘记密码
        forgets_password = (TextView) findViewById(R.id.forgets_password);
        //输入手机号
        user_tel = (EditText) findViewById(R.id.user_tel);
        //输入密码
        user_passworld = (EditText) findViewById(R.id.user_passworld);
        //记住密码
        ck_1 = (CheckBox) findViewById(R.id.ck_1);
        sp = getSharedPreferences(FILE, MODE_PRIVATE);
        isMemory = sp.getString("isMemory", NO);
        //进入界面时，这个if用来判断SharedPreferences里面name和password有没有数据，有的话则直接打在EditText上面
        if (isMemory.equals(YES)) {
            name = sp.getString("name", "");
            password = sp.getString("password", "");
            user_tel.setText(name);
            user_passworld.setText(password);
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(name, user_tel.toString());
        editor.putString(password, user_passworld.toString());
        editor.commit();

        //修改输入时，字体的颜色为白色
        user_tel.setTextColor(Color.WHITE);
        user_passworld.setTextColor(Color.WHITE);
    }

    //登陆请求方法
    public void signUp() {
        RequestQueue requestqueue = Volley.newRequestQueue(LoginActivity.this);
        requestqueue.start();
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.eakay.cn/user-api/api/user/login", new Response.Listener<String>() {



            @Override
            public void onResponse(String s) {
                Log.e("ybb",s);

                //进行解析
                Gson gson=new Gson();
                Loginbean bean=gson.fromJson(s, Loginbean.class);
                boolean flag= bean.getMsg().isSuccess();

                //  用户登陆成功
                if(flag){
                    //为 true时，说明登陆成功将请求过来的gson字符串用sp存储
                    SharedPreferences login = getSharedPreferences("Login", MODE_PRIVATE);
                    SharedPreferences.Editor edit = login.edit();
                    edit.putString("LoginBean",s);
                    edit.commit();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();

                }else{
                    Toast.makeText(LoginActivity.this,bean.getMsg().getInfo(),Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(LoginActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        }) {
            private String s;

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("phone", telephone);//注册电话
                params.put("password", SecurityUtils.MD516(pass));//注册密码
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String hehe = dateFormat.format(now);
                params.put("timestamp", hehe);
                params.put("format", "json");
                params.put("app_key", "car");
                params.put("sign_method", "md5");
                params.put("signMethod", "md5");
                params.put("appKey", "car");
                params.put("ver", "2.4.1");
                params.put("client_type", "android");
                params.put("clientType", "android");
                Object[] objects = params.keySet().toArray();
                Arrays.sort(objects);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < objects.length; i++) {
                    sb.append(objects[i].toString() + params.get(objects[i].toString()));
                }
                String s =   SecurityUtils.MD5(sb.toString() + "car");
                params.put("sign", s);
                return params;
            }
        };
        requestqueue.add(request);
    }
    //版本更新请求方法
    public void signsUp() {
        RequestQueue requestqueue = Volley.newRequestQueue(LoginActivity.this);
        requestqueue.start();
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.eakay.cn/order-api/api/version/checkVersion.htm", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                System.out.println(s);
                //进行解析
                Gson gson=new Gson();
                UpdateBean update=gson.fromJson(s, UpdateBean.class);
                versionname=update.getMap().getVersion();//获得最新版本号
                path=update.getMap().getDownAddress();//更新包地址
                String[] array =versionname.split("[.]");//拆
                StringBuffer buffer=new StringBuffer();
                for (int i=0;i<array.length;i++){
                    buffer.append(array[i]);//拼接
                }
                int code=Integer.parseInt(buffer.toString());//转型（整型）
                try {
                    old_versionName=getPackageManager().getPackageInfo(LoginActivity.this.getPackageName(),0).versionName;//获得当前版本号
                    //进行新旧版本的判断
                    CheckVersionTask(versionname,path);

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(LoginActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();

                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String hehe = dateFormat.format(now);
                params.put("timestamp", hehe);
                params.put("format", "json");
                params.put("app_key", "car");
                params.put("sign_method", "md5");
                params.put("signMethod", "md5");
                params.put("appKey", "car");
                params.put("ver", "2.4.1");
                params.put("client_type", "android");
                params.put("clientType", "android");
                Object[] objects = params.keySet().toArray();
                Arrays.sort(objects);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < objects.length; i++) {
                    sb.append(objects[i].toString() + params.get(objects[i].toString()));
                }
                String s =  SecurityUtils.MD5(sb.toString() + "car");
                params.put("sign", s);
                return params;
            }
        };
        requestqueue.add(request);
    }

    private void CheckVersionTask(String versionname, final String path) {
        try {
            String  version1=LoginActivity.this.getPackageManager().getPackageInfo(LoginActivity.this.getPackageName(), 0).versionName;
            if(!versionname.equals(version1)){
                AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
                        this);// Builder，可以通过此builder设置改变AleartDialog的默认的主题样式及属性相关信息
                builder.setTitle("软件版本更新");
                builder.setMessage("有最新的软件包，请下载！");
                builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();// 当取消对话框后进行操作一定的代码？取消对话框
                        downLoadApk(path);
                    }
                });
                builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                noticeDialog = builder.create();
                noticeDialog.show();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void downLoadApk(final String dAddr) {
        final ProgressDialog pd; // 进度条对话框
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(dAddr, pd);
                    sleep(3000);
                    // 安装APk
                    installApk(file);
                    pd.dismiss(); // 结束掉进度条对话框
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    /**
     * 下载方法
     *
     * @param path
     * @param pd
     * @return
     * @throws Exception
     */
    public File getFileFromServer(String path, ProgressDialog pd)
            throws Exception {
        // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            // 获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            File file = new File(Environment.getExternalStorageDirectory(),
                    "updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                // 获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    // 安装apk
    public void installApk(File file) {
        Intent intent = new Intent();
        // 执行动作
        intent.setAction(Intent.ACTION_VIEW);
        // 执行的数据类型
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://host/path"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn://登陆
                checkPression();
                //获得输入框信息
                telephone = user_tel.getText().toString().trim();
                pass = user_passworld.getText().toString().trim();
                remenber();
                if(!telephone.equals("")){
                    if(!pass.equals("")){
                        checkPression();
                    }else{
                        Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this,"请先输入手机号",Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.forgets_password://忘记密码
                startActivity(new Intent(LoginActivity.this, Password_forget.class));
                //finish();
                break;
            case R.id.login_zhu_ce://注册
                startActivity(new Intent(LoginActivity.this, ZhuCeActivity.class));
                //finish();
                break;
        }
    }
    //记住密码
    // remenber方法用于判断是否记住密码，checkBox1选中时，提取出EditText里面的内容，放到SharedPreferences里面的name和password中
    public void remenber() {
        if (ck_1.isChecked()) {
            if (sp == null) {
                sp = getSharedPreferences(FILE, MODE_PRIVATE);
            }
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("name", user_tel.getText().toString());
            edit.putString("password", user_passworld.getText().toString());
            edit.putString("isMemory", YES);
            edit.commit();
        } else if (!ck_1.isChecked()) {
            if (sp == null) {
                sp = getSharedPreferences(FILE, MODE_PRIVATE);
            }
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("isMemory", NO);
            edit.commit();
        }
    }

    //检查权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == MY_PERMISSIONS_ACCESS_FINE_LOCATION)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                //回调请求
                signUp();
            } else
            {
                Toast.makeText(LoginActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}

