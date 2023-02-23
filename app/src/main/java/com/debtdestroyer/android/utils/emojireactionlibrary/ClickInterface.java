package com.debtdestroyer.android.utils.emojireactionlibrary;

/**
 * Click interface for the EmojiReactionView.
 */
public interface ClickInterface {

    void onEmojiClicked(int emojiIndex, int x, int y);

    void onEmojiUnclicked(int emojiIndex, int x, int y);

}