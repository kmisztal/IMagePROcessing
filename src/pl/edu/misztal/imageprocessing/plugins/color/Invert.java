package pl.edu.misztal.imageprocessing.plugins.color;

import pl.edu.misztal.imageprocessing.plugins.util.LookUpTablePlugin;

/**
 *
 * <table border="0" class="img_block">
 * <tbody>
 * <tr>
 * <td>
 * <p>
 * <img src="../../image/doc-files/image.jpg"></p>
 * <p>
 * Input</p>
 * </td>
 * <td>
 * <p>
 * <img src="doc-files/invert.png"></p>
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
public class Invert extends LookUpTablePlugin {

    @Override
    protected void createLUT() {
        LUT = new short[256];
        for (short i = 0; i < 256; ++i) {
            LUT[i] = (short) (255 - i);
        }
    }

}
