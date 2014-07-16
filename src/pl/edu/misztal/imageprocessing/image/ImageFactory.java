package pl.edu.misztal.imageprocessing.image;

import java.awt.Color;
import pl.edu.misztal.imageprocessing.plugins.util.ColorTable;

/**
 *
 * @author Krzysztof
 */
public class ImageFactory {
    public static int[][] toIntArray(Image img, int signal, int background){
        int [][] ret = new int[img.getWidth()][img.getHeight()];
        for(int i = 0; i < img.getWidth(); i++) {
            for(int j = 0; j < img.getHeight(); j++) {
                ret[i][j] = img.getRed(i, j) < 128 ? signal : background;
            }
        }
        return ret;
    }
    
    /**
     * signal - 1, background - 0
     * @param img
     * @return 
     */
    public static int[][] toIntArray(Image img){
        return toIntArray(img, 1, 0);
    }

    public static void fromIntArray(int[][] im, int size, Image out) {
        Color [] c = ColorTable.randomColorArray(size);
        c[0] = Color.white;
        for (int i = 0; i < out.getWidth(); i++) {
            for (int j = 0; j < out.getHeight(); j++) {                
                out.setRGB(i, j, c[im[i][j]].getRGB());        
            }
        }
    }

}
