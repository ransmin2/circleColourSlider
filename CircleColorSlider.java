import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;


class CircleColorSlider extends JPanel
{
    // declare components and variables


    final JTextArea dimensions = new JTextArea("Please adjust the diameter slider to see circle dimensions", 5, 55);

    final JSlider sliderD;
    final JSlider sliderR;
    final JSlider sliderG;
    final JSlider sliderB;
    final OvalPanel oval = new OvalPanel();
    final JPanel panel = new JPanel();


    public CircleColorSlider()
    {
        //setLayout with GridLayout
        panel.setLayout(new GridLayout(1, 5, 10, 10));
        add(panel, BorderLayout.CENTER);

        //call getSlider to define attributes of each slider
        sliderD = getSlider(0, 300, 50, 150, 50);
        panel.add(sliderD); //add diameter slider
        sliderR = getSlider(0, 255, 0, 50, 5);
        panel.add(sliderR); //add red value slider

        sliderG = getSlider(0, 255, 0, 50, 5);
        panel.add(sliderG); //add green value slider
        sliderB = getSlider(0, 255, 0, 50, 5);
        panel.add(sliderB); //add blue value slider

        add(oval);
        add(dimensions); // add JTextArea to display dimensions
        dimensions.setEditable(false);
    } // ends ColorSlider method

    // public class sets all slider attributes
    public JSlider getSlider(int min, int max, int init, int mjrTkSp, int mnrTkSp)
    {
        JSlider slider = new JSlider(JSlider.VERTICAL, min, max, init);
        slider.setPaintTicks(true);
        slider.setInverted(true);
        slider.setMajorTickSpacing(mjrTkSp);
        slider.setMinorTickSpacing(mnrTkSp);
        slider.setPaintLabels(true);
        slider.addChangeListener(new SliderListener());
        return slider;
    } // ends getSlider

    // class to define Circle panel
    static class OvalPanel extends JPanel
    {
        //declare variables
        int redValue, greenValue, blueValue;
        private int diameter = 50; // default diameter

        // draw and fill circle of specified diameter
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.fillOval(50, 50, diameter, diameter);
        }
        // validate and set diameter, then repaint
        public void setDiameter(int newDiameter)
        {
            // if diameter invalid, default to 10
            diameter = ((newDiameter >= 0) ? newDiameter : 50);
            repaint(); // repaint panel
        }
        // get diameter
        public int getDiameter()
        {
            return diameter;
        }
        // get preferred circle size
        public Dimension getPreferredSize()
        {
            return new Dimension(350, 350);
        }
        // set minimum size
        public Dimension getMinimumSize()
        {
            return getPreferredSize();
        }


        // set foreground colour with red, green and blue values
        public void setForegroundColor()
        {
            Color color = new Color(redValue, greenValue, blueValue);
            setForeground(color);
        }

    } // ends class OvalPanel

    class SliderListener implements ChangeListener
    {

        public void stateChanged(ChangeEvent e)
        {
            JSlider slider = (JSlider) e.getSource();

            if (slider == sliderD) {
                oval.diameter = slider.getValue();
            } else if (slider == sliderR) {
                oval.redValue = slider.getValue();
                displayColor();
            } else if (slider == sliderG) {
                oval.greenValue = slider.getValue();
                displayColor();
            } else if (slider == sliderB) {
                oval.blueValue = slider.getValue();
                displayColor();
            } // ends final if statement of stateChanged Listener

            oval.repaint();

            dimensions.setText(String.format("Diameter:  %d\nCircumference:  %s\nArea:  %s",
                    oval.diameter,
                    oval.diameter * Math.PI,
                    (oval.diameter/2 * oval.diameter/2) * Math.PI));
        } // ends stateChanged

        public void displayColor()
        {
            oval.setForegroundColor();
        } // ends displayColor

    } // ends SliderListener
} // ends class ColorSlider