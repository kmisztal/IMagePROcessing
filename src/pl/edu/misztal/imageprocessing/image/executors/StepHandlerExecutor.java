package pl.edu.misztal.imageprocessing.image.executors;

/**
 *
 * @author Krzysztof
 */
public class StepHandlerExecutor extends Executor{

    public StepHandlerExecutor(String filename) {
        super(filename);
    }

    @Override
    public void execute() {
        StepHandlerExecutorGUI imageList = new StepHandlerExecutorGUI();
        imageList.setVisible(true);
        
        plugins.stream().forEach((p) -> {
            p.process(currentImage);
            imageList.addImage(currentImage.copy(), p);            
        });
        
        
    }
    
}
