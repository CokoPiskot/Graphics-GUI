package com.example.graphicsgui;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class Filters {
    public static Image Negative(Image image) {

        WritableImage newImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter writer = newImage.getPixelWriter();
        PixelReader reader = image.getPixelReader();

        Color white = new Color(1.0, 1.0, 1.0, 1.0);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = reader.getColor(x, y);
                writer.setColor(x, y, new Color(1.0 - pixelColor.getRed(), 1.0 - pixelColor.getGreen(), 1.0 - pixelColor.getBlue(), 1.0));
            }
        }

        return newImage;
    }

    public static Image Pixelize(Image image, float ammount) {
        return image;
    }

    public static Image Noise(Image image, float density, float strength, float vibrance) {
        return image;
    }

    public static Image BlackAndWhite(Image image) {
        return image;
    }
}