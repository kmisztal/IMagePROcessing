package pl.edu.misztal.imageprocessing.plugins.morphology;

import pl.edu.misztal.imageprocessing.image.Image;
import pl.edu.misztal.imageprocessing.image.plugins.Plugin;
import pl.edu.misztal.imageprocessing.image.utils.Attributes;
import pl.edu.misztal.imageprocessing.image.utils.Mask;

/**
 * (dodawanie, max)
 * @author Krzysztof
 */
public class Dilation extends Plugin {

    int maskSize = 3;

    @Override
    public void process(Image imgIn, Image imgOut, Attributes attrOut, Mask mask) {
        if (mask != null) {
            throw new RuntimeException("Not supproted yet");
        }

        if (getAttribute("size") != null) {
            maskSize = (Integer) getAttribute("size");
        } else {
            setAttribute("size", maskSize);
        }

        final int width = imgIn.getWidth();
        final int height = imgIn.getHeight();
        final int outputPixels[] = new int[width * height];

        int xMin, xMax, yMin, yMax;

        int newCol;

        /**
         * Median Filter operation
         */
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                xMin = x - (maskSize / 2);
                xMax = x + (maskSize / 2);
                yMin = y - (maskSize / 2);
                yMax = y + (maskSize / 2);
                newCol = 0;
                outer:
                for (int r = yMin; r <= yMax; r++) {
                    for (int c = xMin; c <= xMax; c++) {
                        if (r >= 0 && r < height && c >= 0 && c < width) {
                            if (imgIn.getRed(c, r) == 255) {
                                newCol = 255;
                                break outer;
                            }
                        }
                    }
                }
                outputPixels[x + y * width] = newCol;
            }
        }
        /**
         * Write the output pixels to the image pixels
         */
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int c = outputPixels[x + y * width];
                imgOut.setRGB(x, y, c, c, c);
            }
        }
    }

}

