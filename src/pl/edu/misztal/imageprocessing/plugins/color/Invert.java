package pl.edu.misztal.imageprocessing.plugins.color;

import pl.edu.misztal.imageprocessing.plugins.util.LookUpTablePlugin;

/**
 *
 * @author Krzysztof
 */
public class Invert extends LookUpTablePlugin {

    @Override
    protected void createLUT() {
        LUT = new short[256];
        for (short i = 0; i < 256; ++i) {
            LUT[i] =  (short) (255 - i);
        }
    }

}
