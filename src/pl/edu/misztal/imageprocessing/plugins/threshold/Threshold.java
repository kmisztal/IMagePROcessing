package pl.edu.misztal.imageprocessing.plugins.threshold;

import pl.edu.misztal.imageprocessing.plugins.util.LookUpTablePlugin;

/**
 *
 * @author Krzysztof
 */
public class Threshold extends LookUpTablePlugin {

    private int threshold = 127;

    @Override
    protected void createLUT() {
        if (getAttribute("threshold") != null) {
            threshold = (int) getAttribute("threshold");
        }

        LUT = new short[256];
        for (short i = 0; i < 256; ++i) {
            LUT[i] = (i < threshold) ? 0 : (short) 255;
        }
    }

}
