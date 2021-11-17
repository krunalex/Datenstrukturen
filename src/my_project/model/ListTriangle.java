package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.view.DrawTool;

public class ListTriangle extends GraphicalObject {

    private ViewController viewController;
    private ListTriangle previousListTriangle; // Vorgänger des jetzigen Triangle


    public ListTriangle(double x, double y, ListTriangle previousListTriangle, ViewController viewController){
        this.x = x;
        this.y = y;
        this.previousListTriangle = previousListTriangle;
        this.viewController = viewController;
        viewController.draw(this);
    }

    @Override
    public void draw(DrawTool drawTool){
        drawTool.drawPolygon(x,y,x+90,y,x+45,y-70);
    }

    @Override
    public void update(double dt){

    }
}
