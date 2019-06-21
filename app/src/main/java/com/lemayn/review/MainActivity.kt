package com.lemayn.review

import android.annotation.SuppressLint
import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), LifecycleObserver {

    private val liveData = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        liveData.observe(this, Observer<String> { t -> t?.let { append(it) } })
        text.movementMethod = ScrollingMovementMethod.getInstance()
        lifecycle.addObserver(this)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getData().observe(this, Observer { t -> println(t) })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onLifecycleCreate() {
        Timber.d("LifecycleObserver onCreate() called")
        liveData.postValue("LifecycleObserver onCreate() called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onLifecycleStart() {
        Timber.d("LifecycleObserver onStart() called")
        liveData.postValue("LifecycleObserver onStart() called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onLifecycleResume() {
        Timber.d("LifecycleObserver onResume() called")
        liveData.postValue("LifecycleObserver onResume() called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onLifecyclePause() {
        Timber.d("LifecycleObserver onPause() called")
        liveData.postValue("LifecycleObserver onPause() called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onLifecycleStop() {
        Timber.d("LifecycleObserver onStop() called")
        liveData.postValue("LifecycleObserver onStop() called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onLifecycleDestroy() {
        Timber.d("LifecycleObserver onDestroy() called")
        liveData.postValue("LifecycleObserver onDestroy() called")
    }

    @SuppressLint("SetTextI18n")
    fun append(str: String) {
        text.text = text.text.toString() + "\n" + str
    }
}
