package com.jj.automotive.ui.screens.music

import android.content.ComponentName
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
                mediaController = controllerFuture.get()
                mediaController?.addListener(object : Player.Listener {
                    override fun onIsPlayingChanged(playing: Boolean) {
                        isPlaying = playing
                    }
                })
            },
            MoreExecutors.directExecutor()
        )

        onDispose {
            MediaController.releaseFuture(controllerFuture)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 播放控制按钮
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 上一曲
            IconButton(
                onClick = {
                    mediaController?.seekToPreviousMediaItem()
                }
            ) {
                Icon(Icons.Default.SkipPrevious, "上一曲")
            }

            // 播放/暂停
            IconButton(
                onClick = {
                    if (isPlaying) {
                        mediaController?.pause()
                    } else {
                        mediaController?.play()
                    }
                }
            ) {
                Icon(
                    if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    if (isPlaying) "暂停" else "播放"
                )
            }

            // 下一曲
            IconButton(
                onClick = {
                    mediaController?.seekToNextMediaItem()
                }
            ) {
                Icon(Icons.Default.SkipNext, "下一曲")
            }
        }
    }
} 