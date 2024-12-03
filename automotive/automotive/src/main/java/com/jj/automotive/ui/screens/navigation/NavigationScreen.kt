package com.jj.automotive.ui.screens.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amap.api.maps.AMap
import com.amap.api.maps.MapView
import com.amap.api.maps.model.MyLocationStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen(
    modifier: Modifier = Modifier,
    isDriving: Boolean = false,
    onBackClick: () -> Unit
) {
    var mapView: MapView? = null
    var aMap: AMap? = null
    
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            MapView(context).also { mapView = it }
        },
        update = { view ->
            if (aMap == null) {
                aMap = view.map
                aMap?.apply {
                    // 设置定位蓝点
                    val myLocationStyle = MyLocationStyle()
                    myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE)
                    setMyLocationStyle(myLocationStyle)
                    // 设置为true表示启动显示定位蓝点
                    isMyLocationEnabled = true
                }
            }
        }
    )
    
    // 顶部工具栏
    SmallTopAppBar(
        title = { Text("导航") },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, "返回")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
        )
    )

    // 底部控制栏
    Surface(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 地图类型切换
            IconButton(
                onClick = {
                    aMap?.mapType = when (aMap?.mapType) {
                        AMap.MAP_TYPE_NORMAL -> AMap.MAP_TYPE_SATELLITE
                        AMap.MAP_TYPE_SATELLITE -> AMap.MAP_TYPE_TERRAIN
                        else -> AMap.MAP_TYPE_NORMAL
                    }
                }
            ) {
                Icon(Icons.Default.Layers, "地图类型")
            }

            // 定位按钮
            IconButton(
                onClick = { /* TODO: 重置到当前位置 */ }
            ) {
                Icon(Icons.Default.MyLocation, "我的位置")
            }

            // 路线规划
            IconButton(
                onClick = { /* TODO: 开始路线规划 */ }
            ) {
                Icon(Icons.Default.Route, "路线规划")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NavigationScreenPreview() {
    MaterialTheme {
        NavigationScreen(
            onBackClick = {}
        )
    }
} 