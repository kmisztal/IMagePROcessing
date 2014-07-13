package pl.edu.misztal.imageprocessing.plugins.threshold;

import pl.edu.misztal.imageprocessing.plugins.util.LookUpTablePlugin;

/**
 *
 * @author Krzysztof
 */
public class Posterize extends LookUpTablePlugin {

    @Override
    protected void createLUT() {

        LUT = new short[256];
        for (int i = 0; i < 256; i++) {
            LUT[i] = (short) (i - (i % 32));
        }
    }

}
