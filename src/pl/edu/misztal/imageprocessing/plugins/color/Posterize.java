package pl.edu.misztal.imageprocessing.plugins.color;

import pl.edu.misztal.imageprocessing.plugins.util.LookupTablePlugin;

/**
 *
 * @author Krzysztof
 */
public class Posterize extends LookupTablePlugin {

    @Override
    protected void createLUT() {

        LUT = new short[256];
        for (int i = 0; i < 256; i++) {
            LUT[i] = (short) (i - (i % 32));
        }
    }

}
