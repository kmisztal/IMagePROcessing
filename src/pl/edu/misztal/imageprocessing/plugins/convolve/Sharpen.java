package pl.edu.misztal.imageprocessing.plugins.convolve;

import pl.edu.misztal.imageprocessing.plugins.util.ConvolutionPlugin;

/**
 *
 * @author Krzysztof
 */
public class Sharpen extends ConvolutionPlugin {

    @Override
    protected void createKernel() {
        kernel = new float[]{
             0.0f, -1.0f,  0.0f,
            -1.0f,  5.0f, -1.0f,
             0.0f, -1.0f,  0.0f
        };
    }

    @Override
    protected void setKernelSize() {
    }

}
