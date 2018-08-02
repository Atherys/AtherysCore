package com.atherys.core.utils;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.effect.Viewer;
import org.spongepowered.api.effect.sound.SoundCategory;
import org.spongepowered.api.effect.sound.SoundType;

public class Sound {

    public static void playSound(Sound sound, Viewer viewer, Vector3d position){
        viewer.playSound(sound.getSoundType(), sound.getCategory(), position, sound.getVolume(), sound.getPitch(), sound.getMinVolume());
    }

    private SoundCategory category;
    private SoundType soundType;
    private double minVolume;
    private double volume;
    private double pitch;

    private Sound(SoundCategory category, SoundType type, double minVolume, double volume, double pitch){
        this.category = category;
        this.soundType = type;
        this.minVolume = minVolume;
        this.volume = volume;
        this.pitch = pitch;
    }

    public SoundCategory getCategory() {
        return category;
    }

    public SoundType getSoundType() {
        return soundType;
    }

    public double getMinVolume() {
        return minVolume;
    }

    public double getVolume() {
        return volume;
    }

    public double getPitch() {
        return pitch;
    }

    public static Builder builder(SoundType type, double volume){
        return new Builder(type, volume);
    }

    public static class Builder {
        private SoundCategory category;
        private SoundType type;
        private double minVolume = 1;
        private double pitch = 1;
        private double volume = 1;

        private Builder(SoundType type, double volume){
            this.type = type;
            this.volume = volume;
        }

        public Sound build(){
            return new Sound(category, type, minVolume, volume, pitch);
        }

        public Builder soundCategory(SoundCategory category){
            this.category = category;
            return this;
        }

        public Builder soundType(SoundType type) {
            this.type = type;
            return this;
        }

        public Builder minVolume(double minVolume) {
            this.minVolume = minVolume;
            return this;
        }

        public Builder pitch(double pitch) {
            this.pitch = pitch;
            return this;
        }

        public Builder volume(double volume) {
            this.volume = volume;
            return this;
        }
    }
}
