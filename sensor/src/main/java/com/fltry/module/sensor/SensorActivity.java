package com.fltry.module.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;

import com.fltry.module.lib_common.AlertDialogUtils;
import com.fltry.module.lib_common.BaseActivity;
import com.fltry.module.sensor.databinding.ActivitySensorBinding;

import java.util.List;

public class SensorActivity extends BaseActivity<ActivitySensorBinding> implements SensorEventListener {
    private SensorManager sm;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sensor;
    }

    @Override
    protected String title() {
        return "传感器";
    }

    @Override
    protected void initView() {
        //创建一个SensorManager来获取系统的传感器服务
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    public void startListener(View v) {
        start();
    }

    public void stopListener(View v) {
        sm.unregisterListener(this);
    }

    public void readListener(View v) {
        // 得到设置支持的所有传感器的List
        List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);
        String sensors = "";
        for (Sensor sensor : sensorList) {

            if (getSensorName(sensor).equals("")) {
                sensors += sensor.getName() + "\n";
            } else {
                sensors += getSensorName(sensor) + "\n";
            }
        }
        AlertDialogUtils.getMyAlert(mContext, "传感器列表", sensors).show();
    }


    private String getSensorName(Sensor sensor) {
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            return "加速度传感器";
        } else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            return "磁场传感器";
        } else if (sensor.getType() == Sensor.TYPE_ORIENTATION) {
            return "方向传感器";
        } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            return "陀螺仪传感器";
        } else if (sensor.getType() == Sensor.TYPE_GRAVITY) {
            return "重力传感器";
        } else if (sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            return "线性加速度传感器";
        } else if (sensor.getType() == Sensor.TYPE_TEMPERATURE) {
            return "温度传感器";
        } else if (sensor.getType() == Sensor.TYPE_LIGHT) {
            return "光传感器";
        } else if (sensor.getType() == Sensor.TYPE_PROXIMITY) {
            return "距离传感器";
        } else if (sensor.getType() == Sensor.TYPE_PRESSURE) {
            return "压力传感器";
        } else if (sensor.getType() == Sensor.TYPE_STEP_COUNTER | sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            return "计步传感器";
        }
        return "";
    }

    private void start() {
        //加速度传感器
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        // 为磁场传感器注册监听器
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
        // 为方向传感器注册监听器
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
        // 为陀螺仪传感器注册监听器
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
        // 为重力传感器注册监听器
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_NORMAL);
        // 为线性加速度传感器注册监听器
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
        // 为温度传感器注册监听器
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_TEMPERATURE), SensorManager.SENSOR_DELAY_NORMAL);
        Sensor tempertureSensor = sm.getDefaultSensor(Sensor.TYPE_PRESSURE);
        // 为光传感器注册监听器
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);
        // 为距离传感器注册监听器
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
        // 为压力传感器注册监听器
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_PRESSURE), SensorManager.SENSOR_DELAY_NORMAL);
        // 计步统计
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER), SensorManager.SENSOR_DELAY_NORMAL);
        // 单次计步
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float X_lateral = sensorEvent.values[0];
            float Y_longitudinal = sensorEvent.values[1];
            float Z_vertical = sensorEvent.values[2];
            dataBinding.setAccelerometer("加速度传感器：\nx：" + X_lateral
                    + "\ny：" + Y_longitudinal + "\nz：" + Z_vertical);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float X_lateral = sensorEvent.values[0];
            float Y_longitudinal = sensorEvent.values[1];
            float Z_vertical = sensorEvent.values[2];
            dataBinding.setMagneticField("磁场传感器：\nx轴：" + X_lateral
                    + "\ny轴：" + Y_longitudinal + "\nz轴：" + Z_vertical);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float X_lateral = sensorEvent.values[0];
            float Y_longitudinal = sensorEvent.values[1];
            float Z_vertical = sensorEvent.values[2];
            dataBinding.setOrientation("方向传感器：\n绕z轴转过的角度：" + X_lateral
                    + "\n绕x轴转过的角度：" + Y_longitudinal + "\n绕y轴转过的角度：" + Z_vertical);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            //需要将弧度转为角度
            float X = (float) Math.toDegrees(sensorEvent.values[0]);
            float Y = (float) Math.toDegrees(sensorEvent.values[1]);
            float Z = (float) Math.toDegrees(sensorEvent.values[2]);
            dataBinding.setGyroscope("陀螺仪传感器：\n绕x轴转过的角速度：" + X
                    + "\n绕y轴转过的角速度：" + Y + "\n绕z轴转过的角速度：" + Z);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_GRAVITY) {
            float X = sensorEvent.values[0];
            float Y = sensorEvent.values[1];
            float Z = sensorEvent.values[2];
            dataBinding.setGravity("重力传感器：\nx方向的重力加速度：" + X
                    + "\nY方向的重力加速度：" + Y + "\nZ方向的重力加速度：" + Z);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            float X = sensorEvent.values[0];
            float Y = sensorEvent.values[1];
            float Z = sensorEvent.values[2];
            dataBinding.setLinearAccelerometer("线性加速度传感器：\nx方向的线性加速度：" + X
                    + "\nY方向的线性加速度：" + Y + "\nZ方向的线性加速度：" + Z);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_TEMPERATURE) {
            float X = sensorEvent.values[0];
            dataBinding.setTemperature("温度传感器：\n温度为" + X);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            float X = sensorEvent.values[0];
            dataBinding.setLight("光传感器：\n光强度为为：" + X);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float X = sensorEvent.values[0];
            dataBinding.setProximity("距离传感器：\n距离为：" + X);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_PRESSURE) {
            float X = sensorEvent.values[0];
            dataBinding.setPressure("压力传感器：\n压强为：" + X);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            float X = sensorEvent.values[0];
            dataBinding.setStepCounter("计步传感器:\nCOUNTER：" + X);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            float X = sensorEvent.values[0];
            dataBinding.setStepDetector("该计步是否有效：\nDetector：" + X);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPause() {
        sm.unregisterListener(this);
        super.onPause();
    }
}
