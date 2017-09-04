package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter {

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    private int colorID;
    private static MediaPlayer mp = null;

    public WordAdapter(Activity context, ArrayList<Word> words, int color) {
        super(context, 0, words);
        this.colorID = color;
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

        final ImageView playButton = (ImageView) listItemView.findViewById(R.id.playButtonImage);
        playButton.setBackgroundColor(color);

        final int resourceID = currentWord.getMediaResourceID();
        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMediaPlayer();
                mp = MediaPlayer.create(playButton.getContext(), resourceID);
                mp.start();
                mp.setOnCompletionListener(mOnCompletionListener);
            }
        });
//        playButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                releaseMediaPlayer();
//                mp = MediaPlayer.create(playButton.getContext(), resourceID);
//                mp.start();
//                mp.setOnCompletionListener(mOnCompletionListener);
//            }
//        });

        return listItemView;
    }


    public static void releaseMediaPlayer() {
        if (mp != null) {
            mp.release();
            mp = null;
        }

    }

}
