package com.jj.automotive

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService

class MyMusicService : MediaSessionService() {
    private var mediaSession: MediaSession? = null
    private lateinit var player: ExoPlayer

    override fun onCreate() {
        super.onCreate()
        
        // 初始化播放器
        player = ExoPlayer.Builder(this).build().apply {
            // 设置重复播放模式
            repeatMode = Player.REPEAT_MODE_ALL
            // 设置播放状态改变监听
            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    when (playbackState) {
                        Player.STATE_READY -> {
                            // 准备完成，可以播放
                        }
                        Player.STATE_ENDED -> {
                            // 播放结束
                        }
                    }
                }
            })
        }

        // 创建 MediaSession
        mediaSession = MediaSession.Builder(this, player)
            .build()

        // 添加测试音频（这里只是示例，实际应该从设备加载音频文件）
        val mediaItem = MediaItem.fromUri("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3")
        player.addMediaItem(mediaItem)
        player.prepare()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? = mediaSession

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }
}