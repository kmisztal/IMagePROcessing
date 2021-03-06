package pl.edu.misztal.imageprocessing.image.executors.gui.helpers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import javafx.util.Pair;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import pl.edu.misztal.imageprocessing.image.Image;
import pl.edu.misztal.imageprocessing.image.plugins.Plugin;
import pl.edu.misztal.imageprocessing.plugins.util.gui.LookAndFeel;

/**
 *
 * @author Krzysztof
 */
public final class StepHandlerExecutorGUI extends JFrame {

    private JPanel gui;
    FilenameFilter fileNameFilter;
    DefaultListModel model;
    final JList imageList;
    final JEditorPane info;
    final ResizableImagePanel imageView;

    public StepHandlerExecutorGUI(String title) {
        super(title == null ? "Step Handler Executor" : title);
        LookAndFeel.doIt();

        gui = new JPanel(new GridLayout());

        imageView = new ResizableImagePanel();

        info = new JEditorPane();
        info.setContentType("text/html");
        info.setEditable(false);

        model = new DefaultListModel();
        imageList = new JList(model);
        imageList.setCellRenderer(new IconCellRenderer());
        ListSelectionListener listener = (ListSelectionEvent lse) -> {
            if (imageList.getSelectedValue() instanceof Pair) {
                Pair<Image, Plugin> o = (Pair<Image, Plugin>) imageList.getSelectedValue();
                imageView.setImage(o.getKey().getBufferedImage());
                info.setText(o.getValue().getInfo().replaceAll("\n", "<br/>"));
            }
        };
        imageList.addListSelectionListener(listener);

        fileNameFilter = (File file, String name1) -> true;

        JScrollPane guiSP = new JScrollPane(
                info,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        gui.add(new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JSplitPane(
                        JSplitPane.VERTICAL_SPLIT,
                        guiSP,
                        new JScrollPane(
                                imageList,
                                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
                ),
                new JScrollPane(imageView)));
        guiSP.setMaximumSize(new Dimension(Short.MAX_VALUE, 150));
        guiSP.setMinimumSize(new Dimension(10, 150));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(getGui());
        setLocationByPlatform(true);
        pack();
        setSize(800, 600);
    }

    private StepHandlerExecutorGUI() {
        this(null);
    }

    public Container getGui() {
        return gui;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StepHandlerExecutorGUI imageList1 = new StepHandlerExecutorGUI();
            imageList1.setVisible(true);
        });
    }

    public void addImage(Image img, Plugin plugin) {
        model.addElement(new Pair<>(img, plugin));
        imageList.setSelectedIndex(imageList.getModel().getSize() - 1);
    }

}

class IconCellRenderer extends DefaultListCellRenderer {

    private static final long serialVersionUID = 1L;

    private int size;
    private BufferedImage icon;

    IconCellRenderer() {
        this(100);
    }

    IconCellRenderer(int size) {
        this.size = size;
        icon = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (c instanceof JLabel && value instanceof Pair) {
            Pair<Image, Plugin> pair = (Pair) value;
            Image i = (Image) pair.getKey();
            Plugin plugin = (Plugin) pair.getValue();

            JLabel l = (JLabel) c;
            l.setText(plugin.getName());
            l.setIcon(new ImageIcon(icon));

            final String html
                    = "<html><body>"
                    + "<p>Look Ma, no hands!";
            l.setToolTipText(html);

            Graphics2D g = icon.createGraphics();
            g.setColor(Color.white);
            g.setBackground(Color.white);
            g.clearRect(0, 0, size, size);

            int w = i.getWidth(), h = i.getHeight(), begx = 0, begy = 0;
            if (w > h) {
                h = (int) (1.0 * h / w * size);
                w = size;
                begy = (size - h) / 2;
            } else {
                w = (int) (1.0 * w / h * size);
                h = size;
                begx = (size - w) / 2;
            }

            g.drawImage(i.getBufferedImage(), begx, begy, w, h, this);

            g.dispose();
        }
        return c;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size, size);
    }

}
