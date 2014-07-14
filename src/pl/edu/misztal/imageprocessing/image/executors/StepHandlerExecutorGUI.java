package pl.edu.misztal.imageprocessing.image.executors;

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
import java.io.IOException;
import javafx.util.Pair;
import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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

    StepHandlerExecutorGUI() {
        super("Step Handler Executor");
        LookAndFeel.doIt();

        gui = new JPanel(new GridLayout());

        JPanel imageViewContainer = new JPanel(new GridBagLayout());
        final JLabel imageView = new JLabel(); //IconLabel();//
        imageViewContainer.add(imageView);

        model = new DefaultListModel();
        final JList imageList = new JList(model);
        imageList.setCellRenderer(new IconCellRenderer());
        ListSelectionListener listener = new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                Object o = imageList.getSelectedValue();
                if (o instanceof Pair) {
                    imageView.setIcon(new ImageIcon((BufferedImage) ((Image) ((Pair) o).getKey()).getBufferedImage()));
                }
            }

        };
        imageList.addListSelectionListener(listener);

        fileNameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return true;
            }
        };

        gui.add(new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(
                        imageList,
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
                new JScrollPane(imageViewContainer)));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(getGui());
        setLocationByPlatform(true);
        pack();
        setSize(800, 600);
    }

    public Container getGui() {
        return gui;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StepHandlerExecutorGUI imageList = new StepHandlerExecutorGUI();
                imageList.setVisible(true);
            }
        });
    }

    public void addImage(Image img, Plugin plugin) {
        model.addElement(new Pair<>(img, plugin));
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
