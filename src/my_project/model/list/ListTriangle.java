package my_project.model.list;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class ListTriangle extends GraphicalObject {

    private ViewController viewController;
    private int r;
    private int g;
    private int b;


    public ListTriangle(double x, double y, int r, int g, int b,  ViewController viewController){
        this.x = x;
        this.y = y;
        this.r = r;
        this.g = g;
        this.b = b;
        this.viewController = viewController;
        viewController.draw(this);
    }

    @Override
    public void draw(DrawTool drawTool){
        drawTool.setCurrentColor(r,g,b,255);
        drawTool.drawFilledPolygon(x,y,x+80,y,x+40,y-80);
    }

    public void setB(int b) {
        this.b = b;
    }
}
