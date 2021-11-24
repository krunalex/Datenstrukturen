package my_project.model.array;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class ArrayCurrent extends GraphicalObject {

    public ArrayCurrent(int x, int y, ViewController viewController){
        this.x = x;
        this.y = y;
        viewController.draw(this);
    }

    @Override
    public void draw(DrawTool drawTool){
        drawTool.setCurrentColor(Color.RED);
        drawTool.drawRectangle(x, y, 30, 30);
    }
}
