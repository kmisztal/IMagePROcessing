package pl.edu.misztal.imageprocessing.plugins.resize;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import pl.edu.misztal.imageprocessing.image.Image;
import pl.edu.misztal.imageprocessing.image.plugins.Plugin;
import pl.edu.misztal.imageprocessing.image.utils.Attributes;
import pl.edu.misztal.imageprocessing.image.utils.Mask;

/**
 *
 * @author Krzysztof
 */
public class Resize extends Plugin {

    @Override
    public void process(Image imgIn, Image imgOut, Attributes attrOut, Mask mask) {
        if (getAttribute("width") != null && getAttribute("height") != null) {
            final int width = (int) getAttribute("width");
            final int height = (int) getAttribute("height");
            imgOut.setBufferedImage(resizeImage(imgIn.getBufferedImage(), width, height));
        }
    }

    public BufferedImage resizeImage(BufferedImage image, int width, int height) {
        int type = 0;
        type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }
}
