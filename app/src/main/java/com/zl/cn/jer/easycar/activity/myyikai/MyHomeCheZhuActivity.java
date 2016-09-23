package com.zl.cn.jer.easycar.activity.myyikai;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.App;
import com.zl.cn.jer.easycar.activity.MyHomeActivity;
import com.zl.cn.jer.easycar.bean.CheZhuComitBean;
import com.zl.cn.jer.easycar.bean.ChezhuBean;
import com.zl.cn.jer.easycar.bean.UserShimingRz;
import com.zl.cn.jer.easycar.net.HttpNet;
import com.zl.cn.jer.easycar.net.ResponseListener;
import com.zl.cn.jer.easycar.utils.IntentUtils;
import com.zl.cn.jer.easycar.utils.MyGetPost;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;

/*
* @车主认证
* @赵雷
*
* */

public class MyHomeCheZhuActivity extends AppCompatActivity implements View.OnClickListener {


    // 打开相机截取图片
    public static final String IMAGE_UNSPECIFIED = "image/*";

    // 打开相机截取图片

    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照

    public final static int PHOTO_ZOOM = 0;
    public final static int TAKE_PHOTO = 1;
    public final static int PHOTO_RESULT = 2;

    private Bitmap bitmap;
    private UserShimingRz.MapBean map1;
    private String path;
    private Bitmap photo;


    private PopupWindow mPopWindow;
    private ImageView chezhuback;
    private EditText etuserhomechezhupingpai;
    private EditText etuserhomechezhuid;
    private RadioButton btuserhomeranyou;
    private RadioButton btuserhomediandong;
    private RadioButton btuserhomehunhe;
    private RadioGroup rgleixingid;
    private ImageView ivuserhomechezhuimg;
    private Button userhomechezhucomint;

    private String imageDir;
    private ChezhuBean.CarInfoBean carInfo;
    private static final int MY_PERMISSIONS_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home_che_zhu);
        initialize();
        //RadioButton的循环点击
        initEvent();
        Intent ints = getIntent();

        if (ints.getIntExtra("code",0)==1){
            inittNeComint();
            etuserhomechezhupingpai.setFocusable(false);
            etuserhomechezhuid.setFocusable(false);
            userhomechezhucomint.setVisibility(View.GONE);
            ivuserhomechezhuimg.setFocusable(false);
        }else if (ints.getIntExtra("code",0)==2){
            inittNeComint();
        }

    }


    private void initEvent() {
        int gg = rgleixingid.getChildCount();
        for (int i = 0; i < gg; i++) {
            final int index = i;
            View child = rgleixingid.getChildAt(i);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selecct(index);
                }
            });
        }
    }
    private void selecct(int index) {
        int count = rgleixingid.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = rgleixingid.getChildAt(i);
            child.setSelected(i == index);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chezhu_back:
                IntentUtils.intentClass(this, MyHomeActivity.class);
                break;
            case R.id.iv_user_home_chezhu_img:
                checkPression();
                break;
            case R.id.user_home_chezhu_comint:
                if (TextUtils.isEmpty(etuserhomechezhupingpai.getText().toString())) {
                    Toast.makeText(MyHomeCheZhuActivity.this, "车辆品牌不能为空！", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(etuserhomechezhuid.getText().toString()) ) {
                    Toast.makeText(MyHomeCheZhuActivity.this, "车牌不能号码为空！", Toast.LENGTH_LONG).show();
                } else if (photo==null){
                    Toast.makeText(this, "请拍照上传", Toast.LENGTH_SHORT).show();

                }else {
                    initNetComint();
                }
                break;
        }
    }
    //获取
    private void inittNeComint() {
        String pingpai = etuserhomechezhupingpai.getText().toString();
        String chezhuid = etuserhomechezhuid.getText().toString();
        final String imgurlchezhunew = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=364904791,404746197&fm=116&gp=0.jpg";
        final String imgurlchezhuold = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=364904791,404746197&fm=116&gp=0.jpg";

        String urls = "https://api.eakay.cn/order-api/api/carInfo/CustomerCarInfo.htm";
        Map<String, String> map = MyGetPost.getMap("carNumber", chezhuid, "carType", "燃油", "icon", imgurlchezhunew, "ficon", imgurlchezhuold, "carBrand", pingpai, "customerId", String.valueOf(App.Cus), "phone", App.Phone);
        HttpNet.postObjectMinongApi(urls, map, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MyHomeCheZhuActivity.this, "请求错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s) {
                Toast.makeText(MyHomeCheZhuActivity.this, "获取数据成功"+s, Toast.LENGTH_SHORT).show();
                 Log.d("TAG", s);
                Gson g = new Gson();
                carInfo = g.fromJson(s, ChezhuBean.class).getCarInfo();
                etuserhomechezhupingpai.setText(carInfo.getCarBrand());
                etuserhomechezhuid.setText(carInfo.getCarNumber());
                initImg();
            }
        });
    }
    //提交
    private void initNetComint() {
        String pingpai = etuserhomechezhupingpai.getText().toString();
        String chezhuid = etuserhomechezhuid.getText().toString();
        final String imgurlchezhunew = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=364904791,404746197&fm=116&gp=0.jpg";
        final String imgurlchezhuold = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=364904791,404746197&fm=116&gp=0.jpg";

        String urls = "https://api.eakay.cn/order-api/api/carInfo/certificateCustomerCarDfs.htm";
        Map<String, String> map = MyGetPost.getMap("carNumber", chezhuid, "carType", "燃油", "icon", imgurlchezhunew, "ficon", imgurlchezhuold, "carBrand", pingpai, "customerId", String.valueOf(App.Cus), "phone", App.Phone);
        HttpNet.postObjectMinongApi(urls, map, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MyHomeCheZhuActivity.this, "请求错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s) {
                Toast.makeText(MyHomeCheZhuActivity.this, "提交成功"+s, Toast.LENGTH_SHORT).show();
                Log.d("TAG", s);
                Gson g = new Gson();
                String carStatus = g.fromJson(s, CheZhuComitBean.class).getCarStatus();
                App.carStatus = carStatus;
                IntentUtils.intentClass(MyHomeCheZhuActivity.this,MyHomeActivity.class);
            }
        });
    }

    //打开相机相册
    private void openPhont(){
        LayoutInflater inflater = LayoutInflater.from(this);
        final View contentView = inflater.from(MyHomeCheZhuActivity.this).inflate(R.layout.popwindow, null);
        mPopWindow = new PopupWindow(contentView, ViewPager.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 获取屏幕宽
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        //window的高
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //window的宽
        mPopWindow.setWidth((int) (screenWidth / 1.2));
        mPopWindow.setTouchable(true);
        mPopWindow.setFocusable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setContentView(contentView);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        //创建当前界面的一个参数对象
        params.alpha = 0.4f;
        //设置参数的透明度为0.8，透明度取值为0~1，1为完全不透明，0为完全透明，因为android中默认的屏幕颜色都是纯黑色的，所以如果设置为1，那么背景将都是黑色，设置为0，背景显示我们的当前界面
        getWindow().setAttributes(params);
        //把该参数对象设置进当前界面中
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //设置PopupWindow退出监听器
            @Override
            public void onDismiss() {
                //如果PopupWindow消失了，即退出了，那么触发该事件，然后把当前界面的透明度设置为不透明
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1.0f;
                //设置为不透明，即恢复原来的界面
                getWindow().setAttributes(params);
            }
        });
        //第一个参数为父View对象，即PopupWindow所在的父控件对象，第二个参数为它的重心，后面两个分别为x轴和y轴的偏移量
        mPopWindow.showAtLocation(inflater.inflate(R.layout.activity_my_home_shi_ming, null), Gravity.CENTER, 0, 0);
        TextView tv_pop_computer = (TextView) contentView.findViewById(R.id.pop_computer);
        TextView tv_pop = (TextView) contentView.findViewById(R.id.pop_financial);
        tv_pop_computer.setText("相机");
        tv_pop.setText("相册");
        tv_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
                // 打开相册的意图
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(IMAGE_UNSPECIFIED);
                Intent wrapperIntent = Intent.createChooser(intent, null);
                startActivityForResult(wrapperIntent, PHOTO_ZOOM);
            }
        });
        tv_pop_computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开相机的意图
                mPopWindow.dismiss();
                Toast.makeText(MyHomeCheZhuActivity.this, "可以裁剪", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                imageDir = "temp.jpg";
                Intent intents = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intents.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                        Environment.getExternalStorageDirectory(), imageDir)));

                startActivityForResult(intents, PHOTO_REQUEST_CAMERA);

            }
        });
    }
    //请求图片
    private void initImg() {
        RequestQueue mQueue = Volley.newRequestQueue(MyHomeCheZhuActivity.this);
        ImageRequest imageRequest = new ImageRequest(
                carInfo.getFastdfsUrl(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        ivuserhomechezhuimg.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ivuserhomechezhuimg.setImageResource(R.drawable.shibai);
            }
        });
        mQueue.add(imageRequest);
    }

    private void initialize() {

        chezhuback = (ImageView) findViewById(R.id.chezhu_back);
        etuserhomechezhupingpai = (EditText) findViewById(R.id.et_user_home_chezhu_pingpai);
        etuserhomechezhuid = (EditText) findViewById(R.id.et_user_home_chezhu_id);
        btuserhomeranyou = (RadioButton) findViewById(R.id.bt_user_home_ranyou);
        btuserhomediandong = (RadioButton) findViewById(R.id.bt_user_home_diandong);
        btuserhomehunhe = (RadioButton) findViewById(R.id.bt_user_home_hunhe);
        rgleixingid = (RadioGroup) findViewById(R.id.rg_leixing_id);
        ivuserhomechezhuimg = (ImageView) findViewById(R.id.iv_user_home_chezhu_img);
        userhomechezhucomint = (Button) findViewById(R.id.user_home_chezhu_comint);
        rgleixingid.getChildAt(0).setSelected(true);
        chezhuback.setOnClickListener(this);
        userhomechezhucomint.setOnClickListener(this);
        ivuserhomechezhuimg.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == PHOTO_ZOOM) {
                photoZoom(data.getData());
                String[] proj = {MediaStore.Images.Media.DATA};

                //好像是android多媒体数据库的封装接口，具体的看Android文档
                Cursor cursor = managedQuery(data.getData(), proj, null, null, null);
                if (cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)!=0){
                    //按我个人理解 这个是获得用户选择的图片的索引值
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    //将光标移至开头 ，这个很重要，不小心很容易引起越界
                    cursor.moveToFirst();
                    //最后根据索引值获取图片路径
                    path = cursor.getString(column_index);
                    Toast.makeText(this, "图片路径"+ path, Toast.LENGTH_SHORT).show();
                }

            }
            if (requestCode == TAKE_PHOTO) {
                File picture = new File(
                        Environment.getExternalStorageDirectory() + "/"
                                + imageDir);
                photoZoom(Uri.fromFile(picture));
            }

            if (requestCode == PHOTO_RESULT) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    photo = extras.getParcelable("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);
                    ivuserhomechezhuimg.setImageBitmap(photo);
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 剪切图片
     *
     * @function:
     * @author:Jerry
     * @date:2013-12-30
     * @param uri
     */
    // 图片缩放
    public void photoZoom(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_RESULT);
    }
    //检查权限（赵雷）
    public void checkPression(){

        if (ContextCompat.checkSelfPermission(MyHomeCheZhuActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MyHomeCheZhuActivity.this,
                    Manifest.permission.CAMERA)) {

                Toast.makeText(this, "请开启权限", Toast.LENGTH_SHORT).show();
                checkPression();
            } else {
                ActivityCompat.requestPermissions(MyHomeCheZhuActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_CAMERA);

            }
        }
    }

    //检查权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == MY_PERMISSIONS_CAMERA)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                openPhont();
            } else
            {
                Toast.makeText(MyHomeCheZhuActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
