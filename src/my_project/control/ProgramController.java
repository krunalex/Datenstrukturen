package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import KAGO_framework.model.abitur.datenstrukturen.Stack;
import my_project.model.CurrentArrow;
import my_project.model.ListTriangle;
import my_project.model.QueueBall;
import my_project.model.StackSquare;
import my_project.view.InputReceiver;

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
    private List<ListTriangle> triangleList;
    private ListTriangle previousListTriangle;
    private ListTriangle currentListTriangle;

    /**
     * Konstruktor
     * Dieser legt das Objekt der Klasse ProgramController an, das den Programmfluss steuert.
     * Damit der ProgramController auf das Fenster zugreifen kann, benötigt er eine Referenz auf das Objekt
     * der Klasse viewController. Diese wird als Parameter übergeben.
     * @param viewController das viewController-Objekt des Programms
     */
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen.
     * Sie erstellt die leeren Datenstrukturen, zu Beginn nur eine Queue
     */
    public void startProgram() {
        // Für Benutzerinteraktion
        new InputReceiver(this,viewController); // darf anonym sein, weil kein Zugriff nötig ist
        // Für die Queue:
        ballQueue = new Queue<>();
        lastBallinQueue = null; // die letzte Kugel muss für die Animation gemerkt werden
        squareStack = new Stack<>();
        squareBeforeInStack = null;
        triangleList = new List<>();
        previousListTriangle = null;
        currentListTriangle = null;
    }

    public void addBallToQueue(){
        QueueBall newQueueBall = new QueueBall(650,50,lastBallinQueue,viewController);
        ballQueue.enqueue(newQueueBall);
        lastBallinQueue = newQueueBall;
    }

    public void deleteBallFromQueue(){
        if(!ballQueue.isEmpty()){
            if(ballQueue.front().tryToDelete()) ballQueue.dequeue();
        }
    }

    public void addSquare(){
        StackSquare newStackSquare = new StackSquare(400,100, squareBeforeInStack, viewController);
        squareStack.push(newStackSquare);
            squareBeforeInStack = newStackSquare;

    }

    public void deleteSquare(){
        if(!squareStack.isEmpty()){
            if(squareStack.top().isDeletable()){
                squareStack.pop();
            }
        }
    }

    public void getPreviousTriangle(){

    }

    public void addArrow(){
        CurrentArrow newCurrentArrow = new CurrentArrow(150,60, viewController, currentListTriangle);
    }

    public void addTriangle(){
        ListTriangle newTriangleList = new ListTriangle(50,180, previousListTriangle, viewController);
        triangleList.insert(newTriangleList);
            currentListTriangle = newTriangleList;
    }

    public void deleteTriangle(){
        if(!triangleList.isEmpty()){
            triangleList.remove();
        }
    }

    public void setCurrentArrow(){

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
