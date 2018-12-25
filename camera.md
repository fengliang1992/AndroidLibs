# camera
## device
#### CameraId
通过fromLegacyId(int camera1Id)或from(@Nonnull String camera2Id)创建CameraId对象，
并通过computeLegacyIdFromCamera2Id(@Nonnull String camera2Id)或computeCameraIdFromLegacyId(int camera1Id)转换api1和api2的相机id，
最后通过getValue()和getLegacyValue()可以得到api1和api2的相机id
#### ActiveCameraDeviceTracker
获取相机的cameraId对象，在相机开启关闭的时候进行赋值（创建和销毁）
#### SingleDeviceActions
打开关闭设备监听
#### SingleDeviceOpenListener
打开设备的生命周期监听
#### CameraOpenException
打开设备发生异常抛出的异常类（仅含errorId）
#### CameraDeviceActionProvider
为给定的摄像机设备键提供一组可执行操作
#### LegacyCameraActions
传统相机的开启和关闭（使用的是子线程）
#### MultiCameraDeviceLifecycle

