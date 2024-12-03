package com.jj.automotive.media

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.PowerManager

class MusicPlayer(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null
    private var currentPosition: Int = 0
    private var musicList: List<MusicItem> = emptyList()

    init {
        initializeMediaPlayer()
    }

    private fun initializeMediaPlayer() {
        mediaPlayer = MediaPlayer().apply {
            setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK)
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }
    }

    fun setMusicList(list: List<MusicItem>) {
        musicList = list
    }

    fun play() {
        mediaPlayer?.let { player ->
            if (!player.isPlaying) {
                player.start()
            }
        }
    }

    fun pause() {
        mediaPlayer?.let { player ->
            if (player.isPlaying) {
                player.pause()
            }
        }
    }

    fun playNext() {
        if (musicList.isEmpty()) return
        currentPosition = (currentPosition + 1) % musicList.size
        playCurrentTrack()
    }

    fun playPrevious() {
        if (musicList.isEmpty()) return
        currentPosition = if (currentPosition > 0) currentPosition - 1 else musicList.size - 1
        playCurrentTrack()
    }

    private fun playCurrentTrack() {
        if (musicList.isEmpty()) return
        val currentTrack = musicList[currentPosition]
        
        mediaPlayer?.apply {
            reset()
            setDataSource(context, Uri.parse(currentTrack.uri))
            prepareAsync()
            setOnPreparedListener { play() }
        }
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

data class MusicItem(
    val id: String,
    val title: String,
    val artist: String,
    val uri: String,
    val duration: Long = 0
) 