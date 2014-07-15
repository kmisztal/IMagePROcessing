package pl.edu.misztal.imageprocessing.image.executors.quick;

import pl.edu.misztal.imageprocessing.image.executors.Executor;

/**
 * executor in place - input is the same as output for each plugin
 * @author Krzysztof
 */
public class QuickExecutor extends Executor{

    public QuickExecutor(String filename) {
        super(filename);
    }
    
    @Override
    public void execute() {
        plugins.stream().forEach((p) -> {
            p.process(currentImage);
        });
    }
    
}
