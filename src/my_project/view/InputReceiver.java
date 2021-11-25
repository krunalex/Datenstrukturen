package my_project.view;

import KAGO_framework.control.Interactable;
import KAGO_framework.control.ViewController;
import my_project.control.ProgramController;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Realisiert ein Objekt, dass alle Eingaben empfängt und dann danach passende Methoden
 * im ProgramController aufruft
 */
public class InputReceiver implements Interactable {

    private ProgramController programController;
    private ViewController viewController;

    /**
     * Objekterzeugung
     * @param programController Nötig als Objekt vom Controllerbereich, das informiert wird
     * @param viewController Nötig, um den Aufruf der Interface-Methoden sicherzustellen
     */
    public InputReceiver(ProgramController programController, ViewController viewController){
        this.programController = programController;
        this.viewController = viewController;
        viewController.register(this);
    }

    @Override
    public void keyPressed(int key) {
    }

    @Override
    public void keyReleased(int key) {
        if(key == KeyEvent.VK_F) programController.fillArray();
        if(key == KeyEvent.VK_DELETE) programController.deleteAllArrayObjects();
        if(key == KeyEvent.VK_SPACE) programController.insertArrayObj();
        if(key == KeyEvent.VK_BACK_SPACE) programController.deleteArrayObj();
        if(key == KeyEvent.VK_W) programController.arrayCurrentUp();
        if(key == KeyEvent.VK_S) programController.arrayCurrentDown();
        if(key == KeyEvent.VK_A) programController.arrayCurrentLeft();
        if(key == KeyEvent.VK_D) programController.arrayCurrentRight();
        if(key == KeyEvent.VK_Z) programController.changeArrayObj();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
