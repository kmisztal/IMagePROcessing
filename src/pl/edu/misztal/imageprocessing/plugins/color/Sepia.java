package pl.edu.misztal.imageprocessing.plugins.color;

import pl.edu.misztal.imageprocessing.image.color.MColor;
import pl.edu.misztal.imageprocessing.plugins.util.Lookup3DTablePlugin;

/**
 *
 * @author Krzysztof
 */
public class Sepia extends Lookup3DTablePlugin {
    private int W;

    @Override
    protected void createLUT() {
        if(getAttribute("W") != null){
            W = (int) getAttribute("W");
        }else{
            W = 30;
        }
        
        LUT = new short[3][256];
        for (int i = 0; i < 256; i++) {
            LUT[0][i] = (short) MColor.check(i + 2 * W);
            LUT[1][i] = (short) MColor.check(i + W);
            LUT[2][i] = (short) i;
        }
    }
}
