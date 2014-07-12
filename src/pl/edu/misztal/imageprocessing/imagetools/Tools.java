package pl.edu.misztal.imageprocessing.imagetools;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import pl.edu.misztal.imageprocessing.image.Image;

/**
 *
 * @author Krzysztof
 */
public class Tools {

    /**
     * Resize the image passing the new height and width
     *
     * @param height
     * @param width
     * @return
     */
    public static Image resize(final Image image, final int width, final int height) {
        // using the new approach of Java 2D API 
        BufferedImage buf = new BufferedImage(width, height, image.getType());
        Graphics2D g2d = (Graphics2D) buf.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image.getBufferedImage(), 0, 0, width, height, null);
        g2d.dispose();
        return new Image(buf);
    }

    /**
     * crop the image
     * @param image
     * @param x
     * @param y
     * @param w
     * @param h
     * @return 
     */
    public static Image crop(Image image, int x, int y, int w, int h) {
        return (new Image(image.getBufferedImage().getSubimage(x, y, w, h)));
    }
}
