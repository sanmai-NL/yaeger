package com.github.hanyaeger.api.engine.media.audio;

import com.github.hanyaeger.api.engine.media.repositories.AudioRepository;
import javafx.scene.media.AudioClip;

/**
 * A {@link SoundClip} encapsulates a mp3 audio file. This file can be played once, or looped for a given amount
 * of times, or indefinite. The path of the mp3 file should be passed to the constructor and the file should be
 * available on the class path.
 */
public class SoundClip {

    private final String path;
    private final int cycleCount;
    private AudioClip audioClip;
    private AudioRepository audioRepository;

    /**
     * When instantiating a {@link SoundClip}, the value of the constructor parameter {@code cycleCount} can
     * be used to set the number of times the audio file should be played. When the constant {#link {@link #INDEFINITE}
     * is used, the file be played in ann infinite loop.
     */
    public static final int INDEFINITE = AudioClip.INDEFINITE;

    /**
     * Instantiate a new {@link SoundClip} for the given file, which should be played only once.
     *
     * @param path The path of the mp3 file, which should be available on the class path.
     */
    public SoundClip(final String path) {
        this(path, 1);
    }

    /**
     * Instantiate a new {@link SoundClip} for the given file, which should be played for the given amount
     * provided of {@code cycleCount}.
     *
     * @param path       The path of the mp3 file, which should be available on the class path.
     * @param cycleCount The number of times the audio file should be played. To loop a file indefinitely, use
     *                   a cycleCount of {@link #INDEFINITE}.
     */

    public SoundClip(final String path, final int cycleCount) {
        this.path = path;
        this.cycleCount = cycleCount;

        this.audioRepository = AudioRepository.getInstance();
    }

    /**
     * Play the file. It will be played for the given {@code cycleCount}, which is 1 by default. If the file should
     * be looped indefinably, the cycleCount should be set to the constant value {@link #INDEFINITE}.
     */
    public void play() {
        audioClip = audioRepository.get(path);
        audioClip.setCycleCount(cycleCount);
        audioClip.play();
    }

    /**
     * Stop playing the file.
     */
    public void stop() {
        audioClip.stop();
    }
}
