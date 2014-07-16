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
public class ImagePartByColor extends Plugin {

    private Color color;

    @Override
    public void process(Image imgIn, Image imgOut, Attributes attrOut, Mask mask) {
        if (mask != null) {
            throw new RuntimeException("Not supproted yet");
        }

        if (getAttribute("color") != null) {
            color = (Color) getAttribute("color");
        } else {
            setAttribute("size", color);
        }
        
        final int col_v = color.getRGB();
        for (int x = 0; x < imgIn.getWidth(); x++) {
            for (int y = 0; y < imgIn.getHeight(); y++) {
                if (imgIn.getRGB(x, y) == col_v) {
                    imgOut.setRGB(x, y, 0, 0, 0);
                } else {
                    imgOut.setRGB(x, y, 255, 255, 255);
                }
            }
        }

    }

}
