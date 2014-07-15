package pl.edu.misztal.imageprocessing.plugins.statistical;

import pl.edu.misztal.imageprocessing.image.Image;
import pl.edu.misztal.imageprocessing.image.plugins.Plugin;
import pl.edu.misztal.imageprocessing.image.utils.Attributes;
import pl.edu.misztal.imageprocessing.image.utils.Mask;

/**
 *
 * <table border="0" class="img_block">
 * <tbody>
 * <tr>
 * <td>
 * <p>
 * <img src="doc-files/saltandpeper.png"></p>
 * <p>
 * Input</p>
 * </td>
 * <td>
 * <p>
 * <img src="doc-files/median.png"></p>
 * <p>
 * Output</p>
 * </td>
 * </tr>
 * </tbody>
 * </table>
 *
 *
 * @author Krzysztof
 */
public class Median extends Plugin {

    @Override
    public void process(Image imgIn, Image imgOut, Attributes attrOut, Mask mask) {

        final int maskSize = 3;
        final int width = imgIn.getWidth();
        final int height = imgIn.getHeight();
        final int outputPixels[] = new int[width * height];

        int red[], green[], blue[];
        int xMin, xMax, yMin, yMax;

        int argb, reD, greeN, bluE;
        
        int count;

        /**
         * Median Filter operation
         */
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                red = new int[maskSize * maskSize];
                green = new int[maskSize * maskSize];
                blue = new int[maskSize * maskSize];
                count = 0;
                xMin = x - (maskSize / 2);
                xMax = x + (maskSize / 2);
                yMin = y - (maskSize / 2);
                yMax = y + (maskSize / 2);
                for (int r = yMin; r <= yMax; r++) {
                    for (int c = xMin; c <= xMax; c++) {
                        if (r >= 0 && r < height && c >= 0 && c < width) {
                            argb = imgIn.getRGB(c, r);
                            reD = (argb >> 16) & 0xff;
                            red[count] = reD;
                            greeN = (argb >> 8) & 0xff;
                            green[count] = greeN;
                            bluE = (argb) & 0xFF;
                            blue[count] = bluE;
                            count++;
                        }
                    }
                }

                /**
                 * sort red, green, blue array
                 */
                java.util.Arrays.sort(red);
                java.util.Arrays.sort(green);
                java.util.Arrays.sort(blue);

                /**
                 * save median value in outputPixels array
                 */
                final int index = (count % 2 == 0) ? count / 2 - 1 : count / 2;
                final int p = (red[index] << 16) | (green[index] << 8) | blue[index];
                outputPixels[x + y * width] = p;
            }
        }
        /**
         * Write the output pixels to the image pixels
         */
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                imgOut.setRGB(x, y, outputPixels[x + y * width]);
            }
        }
    }

}
