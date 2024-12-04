package com.jj.automotive

import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService

class MyMusicService : MediaSessionService() {
    companion object {
        private const val TAG = "MyMusicService"
    }

    private var mediaSession: MediaSession? = null
    private lateinit var player: ExoPlayer

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service onCreate")
        
        // 初始化播放器
        player = ExoPlayer.Builder(this).build().apply {
            // 设置音量
            volume = 1.0f  // 确保音量不为0
            
            // 设置重复播放模式
            repeatMode = Player.REPEAT_MODE_ALL
            
            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    Log.d(TAG, "PlaybackState changed to: $playbackState")
                    when (playbackState) {
                        Player.STATE_READY -> {
                            Log.d(TAG, "Player ready")
                            // 不要自动播放，让用户控制
                            // play()
                        }
                        Player.STATE_ENDED -> {
                            Log.d(TAG, "Playback ended")
                        }
                        Player.STATE_BUFFERING -> {
                            Log.d(TAG, "Buffering")
                        }
                        Player.STATE_IDLE -> {
                            Log.d(TAG, "Player idle")
                        }
                    }
                }

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    Log.d(TAG, "isPlaying changed to: $isPlaying")
                }

                override fun onPlayerError(error: PlaybackException) {
                    Log.e(TAG, "Player error: ${error.message}")
                }
            })
        }

        // 创建 MediaSession，设置回调处理
        mediaSession = MediaSession.Builder(this, player)
            .setCallback(object : MediaSession.Callback {
                override fun onConnect(
                    session: MediaSession,
                    controller: MediaSession.ControllerInfo
                ): MediaSession.ConnectionResult {
                    Log.d(TAG, "MediaSession onConnect")
                    return super.onConnect(session, controller)
                }

                override fun onPostConnect(
                    session: MediaSession,
                    controller: MediaSession.ControllerInfo
                ) {
                    Log.d(TAG, "MediaSession onPostConnect")
                    super.onPostConnect(session, controller)
                }
            })
            .build()

        // 添加测试音频
        val mediaItem = MediaItem.Builder()
            .setUri("android.resource://${packageName}/raw/test.mp3")
            .build()
        player.setMediaItem(mediaItem)
        player.prepare()
        Log.d(TAG, "Media item added and prepared")
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        Log.d(TAG, "onGetSession for controller: $controllerInfo")
        return mediaSession
    }

    override fun onDestroy() {
        Log.d(TAG, "Service onDestroy")
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }
}