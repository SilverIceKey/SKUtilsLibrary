# **Download**  
Step 1. Add the JitPack repository to your build file
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://www.jitpack.io' }
	}
}
```
Step 2. Add the dependency
```groovy
dependencies {
	implementation 'com.github.SilverIceKey:SKUtilsLibrary:Tag'
}
```  
# APIs  
+ ## **Activity相关** -> [ActivityUtil](https://github.com/SilverIceKey/SKUtilsLibrary/blob/master/SKLibrary/src/main/java/com/silvericekey/skutilslibrary/utils/activity/ActivityUtil.java)  
```
getTopActivity                  : 获取当前activity
getTopActivityStatus            : 获取顶部Activity当前状态
getActivityStatus               : 获取指定activity当前状态
hasActivity                     : 判断activity是否存在
getActivityPosition             : 获取activity在栈中的位置
finishToActivity                : 结束到指定activity
finishToTop                     : 结束除最新的Activity之外的所有Activity
```  
+ ## **数字跳跃动画** -> [NumberAnimUtil](https://github.com/SilverIceKey/SKUtilsLibrary/blob/master/SKLibrary/src/main/java/com/silvericekey/skutilslibrary/utils/anime/NumberAnimUtil.kt)  
```
setStart                        : 设置开始值
setEnd                          : 设置结束值
setDuration                     : 设置间隔
setPrefixString                 : 设置前缀
setPostfixString                : 设置后缀
setIsInt                        : 是否为整数
setInterpolator                 : 设置插值器
addUpdateListener               : 设置值更新监听
addListener                     : 设置动画监听
pause                           : 暂停
resume                          : 恢复
isRunning                       : 是否在运行
stop                            : 停止
playOn                          : 绑定控件并执行
```  
+ ## **身份证验证相关** -> [CardCheckUtil](https://github.com/SilverIceKey/SKUtilsLibrary/blob/master/SKLibrary/src/main/java/com/silvericekey/skutilslibrary/utils/card/CardCheckUtil.kt)  
```
checkAdult                      : 检查是否成年
```  
+ ## **时间相关** -> [TimeUtil](https://github.com/SilverIceKey/SKUtilsLibrary/blob/master/SKLibrary/src/main/java/com/silvericekey/skutilslibrary/utils/date/TimeUtil.kt)  
```
getTimeIntervalofCur            : 计算到目前的时间
isToday                         : 判断是否是今天
offsetDay                       : 时间偏移天数
offsetHour                      : 时间偏移小时
offsetMin                       : 时间偏移分钟
```
+ ## **图像处理相关** -> [ImageHelper](https://github.com/SilverIceKey/SKUtilsLibrary/blob/master/SKLibrary/src/main/java/com/silvericekey/skutilslibrary/utils/image/ImageHelper.java)  
```
getNewBitmap                    : 获取亮度调整后的图片
base64ToBitmap                  : base64转bitmap
```  
+ ## **图像加载相关** -> [ImageLoader](https://github.com/SilverIceKey/SKUtilsLibrary/blob/master/SKLibrary/src/main/java/com/silvericekey/skutilslibrary/utils/image/ImageLoader.java)
(此工具需要初始化使用，建议直接观看源代码)
```
loadBaseImage                   : 创建GlideRequest
loadDrawableImage               : 创建GlideRequest<Drawable>
loadBitmapImage                 : 创建GlideRequest<Bitmap>
loadImage                       : 加载到控件
getImageView                    : 获取图片控件
getUrl                          : 获取图片地址
setOnProgressListener           : 网络图片加载进度
```
+ ## **文件IO相关** -> [FileIOUtil](https://github.com/SilverIceKey/SKUtilsLibrary/blob/master/SKLibrary/src/main/java/com/silvericekey/skutilslibrary/utils/io/FileIOUtil.kt)
```
readFile2StringFromAssets       : 从Assets中读取文件，返回String

```
+ ## **logger初始化帮助类** -> [LoggerHelper](https://github.com/SilverIceKey/SKUtilsLibrary/blob/master/SKLibrary/src/main/java/com/silvericekey/skutilslibrary/utils/log/LoggerHelper.java)
```
init                            : logger适配器全加载
Builder下:
addAndroidLogAdapter            : 添加logger logcat适配器
addDiskTxtLogAdapter            : 添加logger保存到本地适配器
addCustomLogAdapter             : 添加自定义适配器
build                           : 创建helper类
```
+ ## **二维码工具类** -> [QRCodeUtil](https://github.com/SilverIceKey/SKUtilsLibrary/blob/master/SKLibrary/src/main/java/com/silvericekey/skutilslibrary/utils/qrcode/QRCodeUtil.kt)
```
createQRCode                    : 快捷创建二维码bitmap
readQRCode                      : 根据bitmap读取二维码
addLogoToQRCode                 : 添加logo到二维码图片上
```
+ ## **分享相关** -> [ShareUtil](https://github.com/SilverIceKey/SKUtilsLibrary/blob/master/SKLibrary/src/main/java/com/silvericekey/skutilslibrary/utils/share/ShareUtil.kt)
```
调用系统的分享
shareText                       : 分享文字
shareImage                      : 分享单张图片
shareImages                     : 分享多张图片
```
+ ## **字符串相关** -> [StringUtils](https://github.com/SilverIceKey/SKUtilsLibrary/blob/master/SKLibrary/src/main/java/com/silvericekey/skutilslibrary/utils/string/StringUtils.java)
```
isContainChinese                : 判断字符串中是否包含中文
List2String                     : 字符串数组转字符串逗号隔开
ListGetVideo                    : 字符串数组获取视频地址,支持格式:mp4、avi、mkv
```
+ ## **状态栏工具类** -> [SystemBarUtil](https://github.com/SilverIceKey/SKUtilsLibrary/blob/master/SKLibrary/src/main/java/com/silvericekey/skutilslibrary/utils/view/SystemBarUtil.java)
```
tintStatusBar                   : Android4.4以上的状态栏着色
tintStatusBarForDrawer          : Android4.4以上的状态栏着色(针对于DrawerLayout)
immersiveStatusBar              : Android4.4以上的沉浸式全屏模式
setStatusBarDarkMode            : 设置状态栏darkMode,字体颜色及icon变黑
setStatusBar                    : 创建假的状态栏View
setTranslucentView              : 创建假的透明栏
getStatusBarHeight              : 获取状态栏高度
isFlyme4Later                   : 判断是否Flyme4以上
isMIUI6Later                    : 判断是否为MIUI6以上
setHeightAndPadding             : 增加View的高度以及paddingTop,增加的值为状态栏高度.
setPadding                      : 增加View的paddingTop,增加的值为状态栏高度
forceFitsSystemWindows          : 强制rootView下面的子View的FitsSystemWindows为false
```
+ ## **retrofit封装** -> [RetrofitClient](https://github.com/SilverIceKey/SKUtilsLibrary/blob/master/SKLibrary/src/main/java/com/silvericekey/skutilslibrary/net/RetrofitClient.java)
```
getInstance                     : RetrofitClient单例获取
defaultConfig                   : 设置默认配置
config                          : 设置临时配置
addInterceptor                  : 添加拦截器
addNetworkInterceptor           : 添加网络拦截器
createService                   : 返回请求接口实例
```
+ ## **retrofit配置相关** -> [RetrofitConfig](https://github.com/SilverIceKey/SKUtilsLibrary/blob/master/SKLibrary/src/main/java/com/silvericekey/skutilslibrary/net/RetrofitConfig.java)
```
继承此接口设置默认配置，设置临时配置时实现此接口用于更新基础host
getBaseUrl                      : 获取基础host
connectTimeout                  : 获取连接超时时间
ReadTimeout                     : 获取读取超时时间
WriteTimeout                    : 获取写入超时时间
```
