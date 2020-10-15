
import javax.swing.JFrame;


public class CircleColorSliderTest extends JFrame

{
    public CircleColorSliderTest()
    {
        getContentPane().add(new CircleColorSlider());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setTitle("Circle ColorSlider");
        setVisible(true);
    } // ends method ColorSliderTest

    public static void main(String[] args)
    {
        final CircleColorSliderTest circleColorSliderTest= new CircleColorSliderTest();
    }// end class ColorSliderTest

} // ends class ColorSliderTest