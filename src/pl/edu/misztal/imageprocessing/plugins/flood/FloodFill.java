package pl.edu.misztal.imageprocessing.plugins.flood;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Deque;
import java.util.LinkedList;
import pl.edu.misztal.imageprocessing.image.Image;
import pl.edu.misztal.imageprocessing.image.plugins.Plugin;
import pl.edu.misztal.imageprocessing.image.utils.Attributes;
import pl.edu.misztal.imageprocessing.image.utils.Mask;

/**
 *
 * @author Krzysztof
 */
public class FloodFill extends Plugin {

    private Point begin = new Point(0, 0);
    private Color target = Color.black;
    private Color replacement = Color.red;

    @Override
    public void process(Image imgIn, Image imgOut, Attributes attrOut, Mask mask) {
        if (mask != null) {
            throw new RuntimeException("Not supproted yet");
        }

        if (getAttribute("begin") != null) {
            begin = (Point) getAttribute("begin");
        } else {
            setAttribute("begin", begin);
        }

        if (getAttribute("targetColor") != null) {
            target = (Color) getAttribute("targetColor");
        } else {
            setAttribute("targetColor", target);
        }

        if (getAttribute("replacementColor") != null) {
            replacement = (Color) getAttribute("replacementColor");
        } else {
            setAttribute("replacementColor", replacement);
        }

        floodFill(imgIn.getBufferedImage(), begin, target, replacement);
    }

    public void floodFill(BufferedImage image, Point node, Color targetColor, Color replacementColor) {
        int width = image.getWidth();
        int height = image.getHeight();
        int target_ = targetColor.getRGB();
        int replacement_ = replacementColor.getRGB();
        if (target_ != replacement_) {
            Deque<Point> queue = new LinkedList<>();
            do {
                int x = node.x;
                int y = node.y;
                while (x > 0 && image.getRGB(x - 1, y) == target_) {
                    x--;
                }
                boolean spanUp = false;
                boolean spanDown = false;
                while (x < width && image.getRGB(x, y) == target_) {
                    image.setRGB(x, y, replacement_);
                    if (!spanUp && y > 0 && image.getRGB(x, y - 1) == target_) {
                        queue.add(new Point(x, y - 1));
                        spanUp = true;
                    } else if (spanUp && y > 0 && image.getRGB(x, y - 1) != target_) {
                        spanUp = false;
                    }
                    if (!spanDown && y < height - 1 && image.getRGB(x, y + 1) == target_) {
                        queue.add(new Point(x, y + 1));
                        spanDown = true;
                    } else if (spanDown && y < height - 1 && image.getRGB(x, y + 1) != target_) {
                        spanDown = false;
                    }
                    x++;
                }
            } while ((node = queue.pollFirst()) != null);
        }
    }
}
