package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import KAGO_framework.model.abitur.datenstrukturen.Stack;
import my_project.model.*;
import my_project.view.InputReceiver;

import javax.sound.midi.SysexMessage;
import java.awt.event.MouseEvent;
//import java.util.Stack;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute


    // Referenzen
    private ViewController viewController;  // diese Referenz soll auf ein Objekt der Klasse viewController zeigen. Über dieses Objekt wird das Fenster gesteuert.
    private Queue<QueueBall> ballQueue;
    private QueueBall lastBallinQueue;

    private Stack<StackSquare> squareStack;
    private StackSquare squareBeforeInStack;

    private CurrentArrow currentArrow;
    private List<ListTriangle> triangleList;
    private ListTriangle previousListTriangle;
    private ListTriangle currentListTriangle;
    private int triangleCounter;

    private SquareArray squareArray;

    /**
     * Konstruktor
     * Dieser legt das Objekt der Klasse ProgramController an, das den Programmfluss steuert.
     * Damit der ProgramController auf das Fenster zugreifen kann, benötigt er eine Referenz auf das Objekt
     * der Klasse viewController. Diese wird als Parameter übergeben.
     *
     * @param viewController das viewController-Objekt des Programms
     */
    public ProgramController(ViewController viewController) {
        this.viewController = viewController;
    }

    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen.
     * Sie erstellt die leeren Datenstrukturen, zu Beginn nur eine Queue
     */
    public void startProgram() {
        // Für Benutzerinteraktion
        new InputReceiver(this, viewController); // darf anonym sein, weil kein Zugriff nötig ist
        // Für die Queue:
        ballQueue = new Queue<>();
        lastBallinQueue = null;

        squareStack = new Stack<>();
        squareBeforeInStack = null;

        triangleList = new List<>();
        previousListTriangle = getPreviousTriangle();
        currentListTriangle = null;
    }

    public void addBallToQueue() {
        QueueBall newQueueBall = new QueueBall(650, 50, lastBallinQueue, viewController);
        ballQueue.enqueue(newQueueBall);
        lastBallinQueue = newQueueBall;
    }

    public void deleteBallFromQueue() {
        if (!ballQueue.isEmpty()) {
            if (ballQueue.front().tryToDelete()) ballQueue.dequeue();
        }
    }

    public void addSquare() {
        StackSquare newStackSquare = new StackSquare(400, 100, squareBeforeInStack, viewController);
        squareStack.push(newStackSquare);
        squareBeforeInStack = newStackSquare;

    }

    public void deleteSquare() {
        if (!squareStack.isEmpty()) {
            if (squareStack.top().isDeletable()) {
                squareStack.pop();
            }
        }
    }

    public void addTriangle() {
        if (triangleCounter < 13) {
            ListTriangle oneTriangle;
            triangleList.toLast();
            currentListTriangle = triangleList.getContent();
            if (currentListTriangle == null) {
                oneTriangle = new ListTriangle(50, 180, 0, 0, 0, viewController);
                triangleList.toFirst();
            } else {
                oneTriangle = new ListTriangle(110 + currentListTriangle.getX(), 180, 0, 0 ,0, viewController);
                triangleList.next();
            }
            currentListTriangle = oneTriangle;
            triangleList.append(oneTriangle);
        }
        triangleCounter++;
        if (triangleCounter == 1) currentArrow = new CurrentArrow(85, 40, viewController, currentListTriangle);
        if (currentArrow.getX() != currentListTriangle.getX()) currentArrow.setX(currentListTriangle.getX()+35);
    }

    public ListTriangle getPreviousTriangle(){
        triangleList.toFirst();
        ListTriangle tempCurrent;
        while(triangleList.hasAccess()){
            tempCurrent = triangleList.getContent();
            triangleList.next();
            if(triangleList.hasAccess()){
                if(triangleList.getContent().equals(currentListTriangle)) {
                    return tempCurrent;
                }
            }
        }
        return null;
    }

    public void rearrangeList() {
        // Aktuelle Position des Current-Zeigers merken
        ListTriangle currentTriangle = triangleList.getContent();
        // Die Liste abgehen und alle Objekte passend platzieren
        triangleList.toFirst();
        int index = 0;
        while (triangleList.hasAccess()) {
            triangleList.getContent().setX(50 + 110 * index);
            triangleList.getContent().setY(180);
            triangleList.next();
            index++;
        }
        // Current Zeiger wieder zurück setzen
        triangleList.toFirst();
            while (triangleList.getContent() != currentTriangle) triangleList.next();
    }


    public void deleteTriangle() {
        if (!triangleList.isEmpty() && triangleCounter != 0) {
            triangleList.remove();
            viewController.removeDrawable(currentListTriangle);
            currentListTriangle = getPreviousTriangle();
            triangleCounter--;
            if(triangleCounter == 0){
                viewController.removeDrawable(currentArrow);
            }
        }
    }

    public void moveCurrentArrowtoRight() {
        triangleList.next();
        if(!triangleList.hasAccess()){
            triangleList.toFirst();
        }
        currentListTriangle = triangleList.getContent();
        currentArrow.setX(currentListTriangle.getX()+35);
    }

    public void changeTriangle(){
        currentListTriangle.setB(255);
    }

    public void changeBack(){
        currentListTriangle.setB(0);
    }

    /**
     * Aufruf bei Mausklick
     * @param e das Objekt enthält alle Informationen zum Klick
     */
    public void mouseClicked(MouseEvent e){

    }

    /**
     * Aufruf mit jeder Frame
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){

    }
}
