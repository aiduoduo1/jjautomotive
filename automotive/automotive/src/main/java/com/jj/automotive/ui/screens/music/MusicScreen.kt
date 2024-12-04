package com.jj.automotive.ui.screens.music

import android.content.ComponentName
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.MoreExecutors
import com.jj.automotive.MyMusicService

@Composable
fun MusicScreen() {
    val context = LocalContext.current
    var isPlaying by remember { mutableStateOf(false) }
    var mediaController by remember { mutableStateOf<MediaController?>(null) }

    DisposableEffect(context) {
        val sessionToken = SessionToken(context, ComponentName(context, MyMusicService::class.java))
        val controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()

        controllerFuture.addListener(
            {
                try {
                    mediaController = controllerFuture.get()
                    Log.d("MusicScreen", "MediaController connected")
                    mediaController?.addListener(object : Player.Listener {
                        override fun onIsPlayingChanged(playing: Boolean) {
                            Log.d("MusicScreen", "Playing state changed to: $playing")
                            isPlaying = playing
                        }
                    })
                } catch (e: Exception) {
                    Log.e("MusicScreen", "Error getting MediaController", e)
                    Toast.makeText(context, "连接播放服务失败: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            },
            MoreExecutors.directExecutor()
        )

        onDispose {
            Log.d("MusicScreen", "Disposing MediaController")
            MediaController.releaseFuture(controllerFuture)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 播放控制按钮
        Row(
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 上一曲
            IconButton(
                modifier = Modifier.size(80.dp),
                onClick = {
                    try {
                        Log.d("MusicScreen", "Seeking to previous item")
                        mediaController?.seekToPreviousMediaItem()
                    } catch (e: Exception) {
                        Log.e("MusicScreen", "Error seeking to previous", e)
                        Toast.makeText(context, "切换失败: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Icon(
                    Icons.Default.SkipPrevious,
                    "上一曲",
                    modifier = Modifier.size(48.dp)
                )
            }

            // 播放/暂停
            IconButton(
                modifier = Modifier.size(100.dp),
                onClick = {
                    try {
                        if (mediaController == null) {
                            Toast.makeText(context, "播放服务未连接", Toast.LENGTH_SHORT).show()
                            return@IconButton
                        }
                        
                        if (isPlaying) {
                            Log.d("MusicScreen", "Pausing playback")
                            mediaController?.pause()
                        } else {
                            Log.d("MusicScreen", "Starting playback")
                            mediaController?.play()
                        }
                    } catch (e: Exception) {
                        Log.e("MusicScreen", "Error controlling playback", e)
                        Toast.makeText(context, "播放控制失败: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Icon(
                    if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    if (isPlaying) "暂停" else "播放",
                    modifier = Modifier.size(60.dp)
                )
            }

            // 下一曲
            IconButton(
                modifier = Modifier.size(80.dp),
                onClick = {
                    try {
                        Log.d("MusicScreen", "Seeking to next item")
                        mediaController?.seekToNextMediaItem()
                    } catch (e: Exception) {
                        Log.e("MusicScreen", "Error seeking to next", e)
                        Toast.makeText(context, "切换失败: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Icon(
                    Icons.Default.SkipNext,
                    "下一曲",
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
} 