
package swa.derivative;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import swa.derivative.Differentials.DifferentialSystem;
import swa.derivative.Functions.CosineFunction;
import swa.derivative.Functions.CubicFunction;
import swa.derivative.Functions.Function;
import swa.derivative.Functions.SineFunction;

public class App extends Application {

    private final static double WIDTH = 1200;
    private final static double HEIGHT = 800;
    private final static double SCALE = 0.3;

    private final static double WIDTH_SCALE = 0.02;
    private final static double HEIGHT_SCALE = 1;

    private final DifferentialSystem ds = new DifferentialSystem();
    private final Group group = new Group();

    @Override
    public void start(Stage stage) throws Exception {

        CubicFunction cuf = new CubicFunction();
        CosineFunction cof = new CosineFunction();
        SineFunction sif = new SineFunction();

        drawCells();

        double deltaX = 0;

        //drawFunction(cuf,"red");
        /*
        drawFunction(cuf.getDerivative(),"red");
        drawFunction(ds.getRightDifferential(cuf,deltaX),"green");
        drawFunction(ds.getLeftDifferential(cuf,deltaX),"blue");
        drawFunction(ds.getCentralDifferential(cuf,deltaX),"black");
        */

        //drawFunction(cof, "red");

        /*
        drawFunction(cof.getDerivative(), "red");
        drawFunction(ds.getRightDifferential(cof,deltaX),"green");
        drawFunction(ds.getLeftDifferential(cof,deltaX),"blue");
        drawFunction(ds.getCentralDifferential(cof,deltaX),"black");
        */

        //drawFunction(sif, "red");


        drawFunction(sif.getDerivative(), "red");
        drawFunction(ds.getRightDifferential(sif,deltaX),"green");
        drawFunction(ds.getLeftDifferential(sif,deltaX),"blue");
        drawFunction(ds.getCentralDifferential(sif,deltaX),"black");

        Scene scene = new Scene(group, WIDTH, HEIGHT);

        stage.setScene(scene);
        stage.show();
    }

    private void drawCells() {
        double XOffset = (WIDTH/2) % (1/SCALE/WIDTH_SCALE);
        double YOffset = (HEIGHT/2) % (1/SCALE/HEIGHT_SCALE);


        for (int x = 0; x < WIDTH*SCALE/WIDTH_SCALE; x++) {
            Line l = new Line(x/SCALE/WIDTH_SCALE + XOffset,0,x/SCALE/WIDTH_SCALE + XOffset,HEIGHT);
            l.setStyle("-fx-stroke: grey;");
            group.getChildren().add(l);
        }

        for (int y = 0; y < HEIGHT*SCALE/HEIGHT_SCALE; y++) {
            Line l = new Line(0,y/SCALE/HEIGHT_SCALE + YOffset,WIDTH,y/SCALE/HEIGHT_SCALE + YOffset);
            l.setStyle("-fx-stroke: grey;");
            group.getChildren().add(l);
        }


        group.getChildren().addAll(
                new Line(0,HEIGHT/2,WIDTH,HEIGHT/2),
                new Line(WIDTH/2,0,WIDTH/2,HEIGHT)
        );
    }

    private void drawFunction(Function function, String color) {
        int startPoint = (int) (WIDTH/2 + function.getSPoint()/SCALE/WIDTH_SCALE);
        int endPoint = (int) (WIDTH/2 + function.getEPoint()/SCALE/WIDTH_SCALE);

        for (int x = Math.max(startPoint,0); x < Math.min(endPoint,WIDTH); x++) {
            Line l = new Line(x, getFuncY(x,function), x+1, getFuncY(x+1,function));
            l.setStyle("-fx-stroke:"+ color +";");
            group.getChildren().add(l);
        }
    }

    private double getFuncY(double x, Function function) {
        double realX = (x - WIDTH/2) * SCALE * WIDTH_SCALE;
        double realY = function.getY(realX);

        return HEIGHT/2 - (realY/SCALE/HEIGHT_SCALE);
    }

    static void main(String[] args) {
        Application.launch();
    }
}
