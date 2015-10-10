package com.zhiyisoft.associations.util;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.TextView;

public class MusicPlayer implements OnBufferingUpdateListener,
		OnCompletionListener, MediaPlayer.OnPreparedListener {
	public MediaPlayer mediaPlayer;
	private SeekBar skbProgress;
	private TextView mTvProgress;
	private int mTotal;
	private Timer mTimer = new Timer();

	/**
	 * @param skbProgress
	 */
	public MusicPlayer(SeekBar skbProgress) {
		this.skbProgress = skbProgress;
		try {
			initMediaPlay();
		} catch (Exception e) {
			Log.e("mediaPlayer", "error", e);
		}

		mTimer.schedule(mTimerTask, 0, 1000);
	}

	public MusicPlayer(TextView TvProgress, int total) {
		this.mTvProgress = TvProgress;
		this.mTotal = total;
		initMediaPlay();
		mTimer.schedule(mTimerTask, 0, 1000);
	}

	private void initMediaPlay() {
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setOnBufferingUpdateListener(this);
		mediaPlayer.setOnPreparedListener(this);
	}

	/*******************************************************
	 * 通过定时器和Handler来更新进度条
	 ******************************************************/
	TimerTask mTimerTask = new TimerTask() {
		@Override
		public void run() {
			if (mediaPlayer == null)
				return;
			if (skbProgress != null) {
				if (mediaPlayer.isPlaying() && skbProgress.isPressed() == false) {
					handleProgress.sendEmptyMessage(0);
				}
			}
			if (mTvProgress != null) {
				handleProgress.sendEmptyMessage(0);
			}
		}
	};

	Handler handleProgress = new Handler() {
		public void handleMessage(Message msg) {

			int position = mediaPlayer.getCurrentPosition();
			int duration = mediaPlayer.getDuration();

			if (duration > 0) {
				if (skbProgress != null) {
					long pos = skbProgress.getMax() * position / duration;
					skbProgress.setProgress((int) pos);
				}
				if (mTvProgress != null) {
					int pos = mTotal * position / duration;
					LayoutParams params = new LayoutParams(
							mTvProgress.getLayoutParams());
					params.width = pos;
					mTvProgress.setLayoutParams(params);
				}
			}
		};
	};

	// *****************************************************

	public void play() {
		mediaPlayer.start();
	}

	public void setplayUrl(String musicUrl) {
		try {
			mediaPlayer.reset();
			mediaPlayer.setDataSource(musicUrl);
			mediaPlayer.prepare();// prepare之后自动播放
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pause() {
		mediaPlayer.pause();
	}

	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	@Override
	/**  
	 * 通过onPrepared播放  
	 */
	public void onPrepared(MediaPlayer arg0) {
		arg0.start();
		Log.e("mediaPlayer", "onPrepared");
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		Log.e("mediaPlayer", "onCompletion");
	}

	@Override
	public void onBufferingUpdate(MediaPlayer arg0, int bufferingProgress) {
		// skbProgress.setSecondaryProgress(bufferingProgress);
		if (skbProgress != null) {
			int currentProgress = skbProgress.getMax()
					* mediaPlayer.getCurrentPosition()
					/ mediaPlayer.getDuration();
			Log.e(currentProgress + "% play", bufferingProgress + "% buffer");
		}

	}

}