package com.slins.flutterfft;

public class VolumeAnalyser {
    public static double REFERENCE = 0.00002;

    public static double getNoiseLevel(float[] samples) {
        double sumOfAmplitudes = 0.0;
        double amountOfValues = samples.length;
        for (float s : samples) {
            if (s > 0)
                sumOfAmplitudes += s;
            else
                amountOfValues -= 1;
        }
        double averageAmplitudes = sumOfAmplitudes / amountOfValues;
        double db = 0;
        // calculating the pascal pressure based on the idea that the max amplitude (between 0 and 32767) is
        // relative to the pressure
        double pressure = averageAmplitudes / 51805.5336; //the value 51805.5336 can be derived from assuming that averageAmplitudes=32767=0.6325 Pa and averageAmplitudes=1 = 0.00002 Pa (the reference value)
        db = (20 * Math.log10(pressure / REFERENCE));
        return db;
    }
}