package pl.edu.misztal.imageprocessing.image.executors.gui;

import pl.edu.misztal.imageprocessing.image.executors.Executor;
import pl.edu.misztal.imageprocessing.image.executors.gui.helpers.StepHandlerExecutorGUI;
import pl.edu.misztal.imageprocessing.image.executors.progressbar.ProcessingProgress;
import pl.edu.misztal.imageprocessing.plugins.util.TimeExecution;

/**
 *
 * @author Krzysztof
 */
public class StepHandlerExecutor extends Executor {

    public StepHandlerExecutor(String filename) {
        super(filename);
    }

    @Override
    public void execute() {
        StepHandlerExecutorGUI imageList = new StepHandlerExecutorGUI();
        ProcessingProgress progress = new ProcessingProgress(imageList, plugins.size());
        imageList.setVisible(true);

        TimeExecution te = new TimeExecution();
        te.startEvent();
        plugins.stream().forEach((p) -> {
            te.startJob(p.getName());
            
            p.process(currentImage);
            imageList.addImage(currentImage.copy(), p);
            progress.increment();

//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(StepHandlerExecutor.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
            te.endJob(true);                     
        });
        
        te.stopEvent();
        te.printEventExecutionTime();
    }

}
