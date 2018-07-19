package com.fltry.androidlibs.sdkmodule.gaode;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;
import com.fltry.androidlibs.utils.toast.ToastUtil3;

import butterknife.BindView;
import butterknife.OnClick;

public class AMapActivity extends BaseActivity {

    private static final int MY_PERMISSIONS_REQUEST_LOACLTION = 1;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    @BindView(R.id.a_map_view)
    MapView mMapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("高德地图");
        mMapView.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        initLocation();

        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this
                    , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOACLTION);
                return;
            }
            startLocation();
        }

    }

    /**
     * 开始定位
     */
    private void startLocation() {
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_amap;
    }


    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.strokeColor(Color.TRANSPARENT);
        myLocationStyle.radiusFillColor(Color.parseColor("#204E91E9"));
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次
        AMap aMap = mMapView.getMap();
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);
        aMap.setTrafficEnabled(true);// 显示实时交通状况
//        20-10m-（19=<zoom<20）
//        19-10m-（19=<zoom<20）
//        18-25m-（18=<zoom<19）
//        17-50m-（17=<zoom<18）
//        16-100m-（16=<zoom<17）
//        15-200m-（15=<zoom<16）
//        14-500m-（14=<zoom<15）
//        13-1km-（13=<zoom<14）
//        12-2km-（12=<zoom<13）
//        11-5km-（11=<zoom<12）
//        10-10km-（10=<zoom<11）
//        9-20km-（9=<zoom<10）
//        8-30km-（8=<zoom<9）
//        7-50km-（7=<zoom<8）
//        6-100km-（6=<zoom<7）
//        5-200km-（5=<zoom<6）
//        4-500km-（4=<zoom<5）
//        3-1000km-（3=<zoom<4）
//        2-1000km-（3=<zoom<4）
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));//初始化比例尺比例
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);//地图类型
        UiSettings uiSettings = aMap.getUiSettings();
//        uiSettings.setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        uiSettings.setScaleControlsEnabled(true);//控制比例尺控件是否显示
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        //可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        locationOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        locationOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        //可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
        locationOption.setWifiScan(false); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        locationOption.setLocationCacheEnable(false); //可选，设置是否使用缓存定位，默认为true
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    AMapLocation mLocation;

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                if (location.getErrorCode() == 0) {
                    mLocation = location;
                    //定位成功回调信息，设置相关消息

                    weather();
                    stopLocation();
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + location.getErrorCode() + ", errInfo:"
                            + location.getErrorInfo());
                }
            } else {
                ToastUtil3.showLong(mContext, "定位失败");
            }
        }
    };

    /**
     * 查询天气
     */
    private void weather() {
        WeatherSearchQuery mquery = new WeatherSearchQuery(mLocation.getCity(), WeatherSearchQuery.WEATHER_TYPE_LIVE);
        WeatherSearch mweathersearch = new WeatherSearch(this);
        mweathersearch.setOnWeatherSearchListener(weatherSearchListener);
        mweathersearch.setQuery(mquery);
        mweathersearch.searchWeatherAsyn(); //异步搜索
    }

    /**
     * 实时天气查询回调
     */
    WeatherSearch.OnWeatherSearchListener weatherSearchListener = new WeatherSearch.OnWeatherSearchListener() {
        @Override
        public void onWeatherLiveSearched(LocalWeatherLiveResult localWeatherLiveResult, int i) {
            if (i == 1000) {
                if (localWeatherLiveResult != null && localWeatherLiveResult.getLiveResult() != null) {
                    LocalWeatherLive weatherlive = localWeatherLiveResult.getLiveResult();
                    new AlertDialog.Builder(mContext)
                            .setTitle("定位结果")
                            .setMessage("城市：" + mLocation.getCity() + "\n" +
                                    "经纬度：" + mLocation.getLongitude() + "，" + mLocation.getLatitude() + "\n" +
                                    "具体位置：" + mLocation.getAddress() + "\n" +
                                    "查询时间：" + weatherlive.getReportTime() + "\n" +
                                    "天气：" + weatherlive.getWeather() + "\n" +
                                    "温度：" + weatherlive.getTemperature() + "℃\n" +
                                    "湿度：" + weatherlive.getWindDirection() + "%\n" +
                                    "风力：" + weatherlive.getWindDirection() + "风     " + weatherlive.getWindPower() + "级")
                            .setNegativeButton("知道了", null)
                            .show();
                } else {
                    ToastUtil3.showShort(mContext, "获取天气失败");
                }
            } else {
                ToastUtil3.showShort(mContext, "获取天气失败：" + i);
            }
        }

        @Override
        public void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult, int i) {

        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOACLTION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocation();
            } else {
                showMissingPermissionDialog();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage("当前应用缺少定位权限。请点击\"设置\"-\"权限\"-打开所需权限。");
        // 拒绝, 退出应用
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });
        builder.setCancelable(false);
        builder.show();
    }

    /**
     * 启动管理权限应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        if (mMapView != null)
            mMapView.onDestroy();
    }

    /**
     * 销毁定位
     */
    private void destroyLocation() {
        if (null != locationClient) {
            // 如果AMapLocationClient是在当前Activity实例化的，
            //在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @OnClick(R.id.a_map_btn)
    public void onViewClicked() {
        startLocation();
    }
}
