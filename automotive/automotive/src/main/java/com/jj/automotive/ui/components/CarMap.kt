package com.jj.automotive.ui.components

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.amap.api.maps.AMap
import com.amap.api.maps.MapView
import com.amap.api.maps.model.MyLocationStyle

@Composable
fun CarMap(
    modifier: Modifier = Modifier,
    onMapReady: (AMap) -> Unit = {}
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var mapView: MapView? by remember { mutableStateOf(null) }
    var aMap: AMap? by remember { mutableStateOf(null) }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView?.onCreate(null)
                Lifecycle.Event.ON_START -> mapView?.onResume()
                Lifecycle.Event.ON_STOP -> mapView?.onPause()
                Lifecycle.Event.ON_DESTROY -> mapView?.onDestroy()
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    AndroidView(
        factory = { context ->
            MapView(context).also {
                mapView = it
                aMap = it.map.apply {
                    // 设置定位蓝点
                    val myLocationStyle = MyLocationStyle()
                    myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE)
                    setMyLocationStyle(myLocationStyle)
                    // 设置为true表示启动显示定位蓝点
                    isMyLocationEnabled = true
                    onMapReady(this)
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
} 