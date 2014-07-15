package pl.edu.misztal.imageprocessing.test;

import pl.edu.misztal.imageprocessing.image.executors.Executor;
import pl.edu.misztal.imageprocessing.image.executors.gui.StepHandlerExecutorWithProgressBar;
import pl.edu.misztal.imageprocessing.plugins.color.Invert;
import pl.edu.misztal.imageprocessing.plugins.convolve.Blur;

/**
 *
 * @author Krzysztof
 */
public class StepHandlerExecutorWithProgressBarTest {
     public static void main(String[] args) {
        String filename = "./res/senna.jpg";
        
        Executor exec = new StepHandlerExecutorWithProgressBar(filename);
        
        exec.add(new Invert());
        exec.add(new Invert());
        
        exec.add(new Blur(),
                "size", 6);
        
        exec.execute();
        
        exec.save("./res/out.png");
    }
}
