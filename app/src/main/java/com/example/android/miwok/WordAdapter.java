package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rizao on 8/31/2017.
 */

public class WordAdapter extends ArrayAdapter {

    private int colorID;
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mAfChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange ==
                    AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mp.pause();
                mp.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
                am.abandonAudioFocus(mAfChangeListener);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mp.start();
            }
        }
    };

    private static MediaPlayer mp = null;
    private Context mContext;
    private AudioManager am;

    public WordAdapter(Activity context, ArrayList<Word> words, int color) {
        super(context, 0, words);
        this.colorID = color;
        this.mContext = context;
        am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = (Word) getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.upper);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        TextView defTextView = (TextView) listItemView.findViewById(R.id.lower);
        defTextView.setText(currentWord.getDefaultTranslation());

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);
        if (currentWord.hasImage()) {
            iconView.setImageResource(currentWord.getImageResourceID());
        } else {
            iconView.setVisibility(View.GONE);
        }

        View frame = listItemView.findViewById(R.id.frame);
        int color = ContextCompat.getColor(getContext(), colorID);
        frame.setBackgroundColor(color);

        final ImageButton playButton = (ImageButton) listItemView.findViewById(R.id.playButton);
        playButton.setBackgroundColor(color);
        final int resourceID = currentWord.getMediaResourceID();
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = am.requestAudioFocus(mAfChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    releaseMediaPlayer();
                    mp = MediaPlayer.create(playButton.getContext(), resourceID);
                    mp.start();
                    mp.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });

        return listItemView;
    }


    public static void releaseMediaPlayer() {
        if (mp != null) {
            mp.release();
            mp = null;
        }

    }

}
