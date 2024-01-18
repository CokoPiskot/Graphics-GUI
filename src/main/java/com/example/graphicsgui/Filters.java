package com.example.graphicsgui;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;

public class Filters {
    public static Image Negative(Image image) {
        WritableImage newImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter writer = newImage.getPixelWriter();
        PixelReader reader = image.getPixelReader();

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color clr = reader.getColor(x, y);
                writer.setColor(x, y, new Color(1.0 - clr.getRed(), 1.0 - clr.getGreen(), 1.0 - clr.getBlue(), 1.0));
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
        WritableImage newImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter writer = newImage.getPixelWriter();
        PixelReader reader = image.getPixelReader();

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color clr = reader.getColor(x, y);
                double colorAverage = 0.299 * clr.getRed() + 0.587 * clr.getGreen() + 0.114 * clr.getBlue();
                writer.setColor(x, y, new Color(colorAverage, colorAverage, colorAverage, 1.0));
            }
        }

        return newImage;
    }

    public static Image EdgeDetection(Image image)
    {
        float[][] kernel = {
                {-3f/18, -2f/13, -1f/10, 0,  1f/10, 2f/13, 3f/18,},
                {-3f/13, -2f/8 , -1f/5 , 0,  1f/5 , 2f/8 , 3f/13,},
                {-3f/10, -2f/5 , -1f/2 , 0,  1f/2 , 2f/5 , 3f/10,},
                {-3f/9,  -2f/4,  -1f/1,  0,  1f/1,  2/4f,  3f/9,},
                {-3f/10, -2/5f,  -1/2f,  0,  1f/2,  2f/5,  3f/10}
        };

        return ConvolutionFilter(image, kernel);
    }

    public static Image Blur(Image image)
    {
        float[][] kernel = {
                {0.0059170296f, 0.0059170621f, 0.0059170888f, 0.0059171095f, 0.0059171243f, 0.0059171331f, 0.0059171361f, 0.0059171331f, 0.0059171243f, 0.0059171095f, 0.0059170888f, 0.0059170621f, 0.0059170296f},
                {0.0059170621f, 0.0059170947f, 0.0059171213f, 0.0059171420f, 0.0059171568f, 0.0059171657f, 0.0059171686f, 0.0059171657f, 0.0059171568f, 0.0059171420f, 0.0059171213f, 0.0059170947f, 0.0059170621f},
                {0.0059170888f, 0.0059171213f, 0.0059171479f, 0.0059171686f, 0.0059171834f, 0.0059171923f, 0.0059171953f, 0.0059171923f, 0.0059171834f, 0.0059171686f, 0.0059171479f, 0.0059171213f, 0.0059170888f},
                {0.0059171095f, 0.0059171420f, 0.0059171686f, 0.0059171893f, 0.0059172041f, 0.0059172130f, 0.0059172160f, 0.0059172130f, 0.0059172041f, 0.0059171893f, 0.0059171686f, 0.0059171420f, 0.0059171095f},
                {0.0059171243f, 0.0059171568f, 0.0059171834f, 0.0059172041f, 0.0059172189f, 0.0059172278f, 0.0059172308f, 0.0059172278f, 0.0059172189f, 0.0059172041f, 0.0059171834f, 0.0059171568f, 0.0059171243f},
                {0.0059171331f, 0.0059171657f, 0.0059171923f, 0.0059172130f, 0.0059172278f, 0.0059172367f, 0.0059172396f, 0.0059172367f, 0.0059172278f, 0.0059172130f, 0.0059171923f, 0.0059171657f, 0.0059171331f},
                {0.0059171361f, 0.0059171686f, 0.0059171953f, 0.0059172160f, 0.0059172308f, 0.0059172396f, 0.0059172426f, 0.0059172396f, 0.0059172308f, 0.0059172160f, 0.0059171953f, 0.0059171686f, 0.0059171361f},
                {0.0059171331f, 0.0059171657f, 0.0059171923f, 0.0059172130f, 0.0059172278f, 0.0059172367f, 0.0059172396f, 0.0059172367f, 0.0059172278f, 0.0059172130f, 0.0059171923f, 0.0059171657f, 0.0059171331f},
                {0.0059171243f, 0.0059171568f, 0.0059171834f, 0.0059172041f, 0.0059172189f, 0.0059172278f, 0.0059172308f, 0.0059172278f, 0.0059172189f, 0.0059172041f, 0.0059171834f, 0.0059171568f, 0.0059171243f},
                {0.0059171095f, 0.0059171420f, 0.0059171686f, 0.0059171893f, 0.0059172041f, 0.0059172130f, 0.0059172160f, 0.0059172130f, 0.0059172041f, 0.0059171893f, 0.0059171686f, 0.0059171420f, 0.0059171095f},
                {0.0059170888f, 0.0059171213f, 0.0059171479f, 0.0059171686f, 0.0059171834f, 0.0059171923f, 0.0059171953f, 0.0059171923f, 0.0059171834f, 0.0059171686f, 0.0059171479f, 0.0059171213f, 0.0059170888f},
                {0.0059170621f, 0.0059170947f, 0.0059171213f, 0.0059171420f, 0.0059171568f, 0.0059171657f, 0.0059171686f, 0.0059171657f, 0.0059171568f, 0.0059171420f, 0.0059171213f, 0.0059170947f, 0.0059170621f},
                {0.0059170296f, 0.0059170621f, 0.0059170888f, 0.0059171095f, 0.0059171243f, 0.0059171331f, 0.0059171361f, 0.0059171331f, 0.0059171243f, 0.0059171095f, 0.0059170888f, 0.0059170621f, 0.0059170296f}};
        Image result = image;
        int steps = 2;
        for(int i = 0; i < steps; i++)
            result = ConvolutionFilter(image, kernel);
        return result;
    }

    private static int Clamp(int value, int min, int max)
    {
        return Math.max(Math.min(value, max), min);
    }

    private static float Clamp(float value, float min, float max)
    {
        return Math.max(Math.min(value, max), min);
    }
    private static Image ConvolutionFilter(Image image, float[][] kernel)
    {
        WritableImage newImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter writer = newImage.getPixelWriter();
        PixelReader reader = image.getPixelReader();

        int imageHeight = (int)image.getHeight();
        int imageWidth = (int)image.getWidth();
        int kernelHeight = kernel.length;
        int kernelWidth = kernel[0].length;

        for (int x = 0; x < imageWidth; x++) {
            for (int y = 0; y < imageHeight; y++) {
                float r = 0, g = 0, b = 0;
                int startX = x - kernelWidth/2;
                int startY = y - kernelHeight/2;
                for(int sx = 0; sx < kernelWidth; sx++){
                    for(int sy = 0; sy < kernelHeight; sy++){
                        int clampedIndexX = Clamp(startX + sx, 0, imageWidth - 1);
                        int clampedIndexY = Clamp(startY + sy, 0, imageHeight - 1);

                        Color sampledPixel = reader.getColor(clampedIndexX, clampedIndexY);
                        float sampledKernel = kernel[kernelHeight - (sy + 1)][kernelWidth - (sx + 1)];
                        r += (sampledPixel.getRed() * sampledKernel);
                        g += (sampledPixel.getGreen() * sampledKernel);
                        b += (sampledPixel.getBlue() * sampledKernel);
                    }
                }
                r = Clamp(r, 0, 1);
                g = Clamp(g, 0, 1);
                b = Clamp(b, 0, 1);

                writer.setColor(x, y, new Color(r, g, b, 1));
            }
        }

        return newImage;
    }
}