package pl.edu.misztal.imageprocessing.plugins.threshold;

import java.awt.Color;
import pl.edu.misztal.imageprocessing.image.Image;
import pl.edu.misztal.imageprocessing.image.plugins.Plugin;
import pl.edu.misztal.imageprocessing.image.utils.Attributes;
import pl.edu.misztal.imageprocessing.image.utils.Mask;

/**
 *
 * @author Krzysztof
 */
public class ImagePartByRange extends Plugin {

    private int min = 0, max = 127;

    @Override
    public void process(Image imgIn, Image imgOut, Attributes attrOut, Mask mask) {
        if (mask == null && getAttribute("mask") != null) {
            mask = (Mask) getAttribute("mask");
        }

        if (getAttribute("min") != null) {
            min = (int) getAttribute("min");
        } else {
            setAttribute("min", min);
        }

        if (getAttribute("max") != null) {
            max = (int) getAttribute("max");
        } else {
            setAttribute("max", max);
        }

        for (int x = 0; x < imgIn.getWidth(); x++) {
            for (int y = 0; y < imgIn.getHeight(); y++) {
                if (mask.get(x, y)) {
                    final int c = imgIn.getRed(x, y);
                    if (c >= min && c < max) {
                        imgOut.setRGB(x, y, 0, 0, 0);
                    } else {
                        imgOut.setRGB(x, y, 255, 255, 255);
                    }
                }else {
                        imgOut.setRGB(x, y, 255, 255, 255);
                    }
            }
        }

    }

}
