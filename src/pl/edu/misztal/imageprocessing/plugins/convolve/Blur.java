package pl.edu.misztal.imageprocessing.plugins.convolve;

import pl.edu.misztal.imageprocessing.plugins.util.ConvolutionPlugin;

/**
 * Blurring is done for example by taking the average of the current pixel and it's 8 neighbors.
 * @author Krzysztof
 */
public class Blur extends ConvolutionPlugin {

    @Override
    protected void createKernel() {
        kernel = new float[height * width];
        final float v = 1.f / kernel.length;

        for (int i = 0; i < kernel.length; ++i) {
            kernel[i] = v;
        }
    }

    @Override
    protected void setKernelSize() {
        if (getAttribute("size") != null) {
            height = width = (int) getAttribute("size");
        } else {
            height = width = 3;
        }
    }

}
