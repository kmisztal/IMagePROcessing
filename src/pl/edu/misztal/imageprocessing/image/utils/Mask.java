package pl.edu.misztal.imageprocessing.image.utils;

import java.awt.Color;
import pl.edu.misztal.imageprocessing.image.Image;

/**
 *
 * @author Krzysztof
 */
public class Mask {

    private boolean mask[][];

    public Mask(Image img, Color trueColor) {
        mask = new boolean[img.getWidth()][img.getHeight()];
        final int trueColor_ = trueColor.getRGB();
        for (int x = 0; x < img.getWidth(); ++x) {
            for (int y = 0; y < img.getHeight(); ++y) {
                mask[x][y] = img.getRGB(x, y) == trueColor_;
            }
        }
    }

    public boolean get(int x, int y) {
        return mask[x][y];
    }

}
