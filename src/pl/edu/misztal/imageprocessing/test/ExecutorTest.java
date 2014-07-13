package pl.edu.misztal.imageprocessing.test;

import pl.edu.misztal.imageprocessing.image.executors.Executor;
import pl.edu.misztal.imageprocessing.image.executors.QuickExecutor;
import pl.edu.misztal.imageprocessing.plugins.color.Invert;
import pl.edu.misztal.imageprocessing.plugins.convolve.Blur;

/**
 *
 * @author Krzysztof
 */
public class ExecutorTest {
    public static void main(String[] args) {
        String filename = "./res/tucano.jpg";
        
        Executor exec = new QuickExecutor(filename);
        
        exec.add(new Invert());
        exec.add(new Invert());
        
        exec.add(new Blur(),
                "size", 6);
        
        exec.execute();
        
        exec.save("./res/out.png");
    }
}
