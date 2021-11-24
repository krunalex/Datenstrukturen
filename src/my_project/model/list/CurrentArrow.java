package my_project.model.list;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class CurrentArrow extends GraphicalObject {

    private ViewController viewController;
    private ListTriangle currentListTriangle;

    public CurrentArrow(double x, double y, ViewController viewController, ListTriangle currentListTriangle){
        this.x = x;
        this.y = y;
        this.currentListTriangle = currentListTriangle;
        viewController.draw(this);
    }

    @Override
    public void draw(DrawTool drawTool){
        drawTool.drawRectangle(x,y,10,40);
    }


}
