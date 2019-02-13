1、打开新Activity时，必须在onPause执行完之后才会执行新Activity的onResume，然后执行onStop。（旧Activity先onPause，新的Activity才会启动）（新Activity为透明主题时，不会调用onStop）      
2、异常情况下Activity的生命周期： ① 旋转屏幕 ② 内存不足，Activity被杀死。
     异常情况下Activity会先被杀死，然后重新创建：onPause->onStop->onSaveInstanceState->onDestroy->onCreate->onRestoreInstanceState  
     ~~onSaveInstanceState方法在onStop之前调用，与onPause没有既定的时序关系。~~（mi8 Android P下，本demo中onSaveInstanceState总是在onStop之后调用）  
     onRestoreInstanceState方法被调用，其入参一定是有值的。而onCreate中入参需要进行判空操作。  
3、Activity的优先级：① 前台Activity  ② 可见但非前台的Activity（弹出对话框样式的Activity时只执行了onPause）③后台Activity（执行了onStop）  
4、不能使用ApplicationContext去启动一个standard模式的Activity，因为启动ApplicationContext没有一个可供新Activity使用的任务站。  
5、启动singleTask模式的Activity时，若无所需的任务栈会先创建任务栈然后创建Activity。  
6、FLAG_ACTIVITY_CLEAR_TOP，被启动的Activity已存在时，当Activity接收到设置了该flag的Intent后，可以决定是让Activity走onNewIntent，还是销毁并重新创建实例。如果Activity的launchMode为standard并且接收到的Intent中不包含FLAG_ACTIVITY_SINGLE_TOP，此时会销毁并重新创建实例。如果Activity的launchMode为其他任一种模式，或者接收到的Intent中包含FLAG_ACTIVITY_SINGLE_TOP，就会调用onNewIntent方法。  
7、affinity：当启动一个launchMode为singTask的Activity时，系统会去检测要启动的这个Activity的affinity和当前任务的affinity是否相同，如果相同的话就会把它放入到现有任务当中，如果不同则会去创建一个新的任务。而同一个程序中所有Activity的affinity默认都是相同的，这也是为什么同一个应用程序中即使声明成"singleTask"，也不会为这个Activity再去创建一个新的任务。  
8、[切屏生命周期各版本，这篇很全](https://blog.csdn.net/qq_36713816/article/details/80538467)  
9、生命周期流程图![生命周期流程图](https://images2015.cnblogs.com/blog/860643/201602/860643-20160201115258100-1397803685.png)    
10、上面各条demo基本都已包含