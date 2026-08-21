
package swa.derivative;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import swa.derivative.Differentials.DifferentialSystem;
import swa.derivative.Functions.*;

public class App extends Application {

    private final static double WIDTH = 640;
    private final static double HEIGHT = 640;
    private final static double SCALE = 0.2;

    private final static double WIDTH_SCALE = 0.2;
    private final static double HEIGHT_SCALE = 1;
    private final static String X_AXIS_NAME = "x";
    private final static String Y_AXIS_NAME = "E(h)";


    private final DifferentialSystem ds = new DifferentialSystem();
    private final FunctionComparator fc = new FunctionComparator(ds);

    private final Group group = new Group();

    @Override
    public void start(Stage stage) throws Exception {

        CubicFunction cuf = new CubicFunction();
        CosineFunction cof = new CosineFunction();
        SineFunction sif = new SineFunction();

        drawCells();

        double deltaX = 0.001;

        //drawFunction(cuf,"red");

        /*
        drawFunction(cuf.getDerivative(),"red");
        drawFunction(ds.getRightDifferential(cuf,deltaX),"green");
        drawFunction(ds.getLeftDifferential(cuf,deltaX),"blue");
        drawFunction(ds.getCentralDifferential(cuf,deltaX),"black");
        */

        /*
        drawFunction(fc.getConvergenceRight(cuf,cuf.getDerivative(), 2), "red");
        drawFunction(fc.getConvergenceLeft(cuf,cuf.getDerivative(), 2 ), "blue");
        drawFunction(fc.getConvergenceCentral(cuf,cuf.getDerivative(), 2 ), "green");
        */

        //drawFunction(cof, "red");

        /*
        drawFunction(cof.getDerivative(), "red");
        drawFunction(ds.getRightDifferential(cof,deltaX),"green");
        drawFunction(ds.getLeftDifferential(cof,deltaX),"blue");
        drawFunction(ds.getCentralDifferential(cof,deltaX),"black");
        */

        /*
        drawFunction(fc.getConvergenceRight(cof,cof.getDerivative()), "red");
        drawFunction(fc.getConvergenceLeft(cof,cof.getDerivative()), "blue");
        drawFunction(fc.getConvergenceCentral(cof,cof.getDerivative()), "green");
        */


        //drawFunction(sif, "red");

        //drawFunction(sif.getDerivative(), "red");
        //drawFunction(ds.getRightDifferential(sif,deltaX),"blue");
        //drawFunction(ds.getLeftDifferential(sif,deltaX),"green");
        //drawFunction(ds.getCentralDifferential(sif,deltaX),"black");



        /*
        drawFunction(fc.getConvergenceRight(sif,sif.getDerivative()), "red");
        drawFunction(fc.getConvergenceLeft(sif,sif.getDerivative()), "blue");
        drawFunction(fc.getConvergenceCentral(sif,sif.getDerivative()), "green");
        */
        //drawFunction(ds.getLeftDifferential(sif,deltaX),"blue");
        //drawFunction(ds.getCentralDifferential(sif,deltaX),"black");

        Scene scene = new Scene(group, WIDTH, HEIGHT);

        stage.setScene(scene);
        stage.show();
    }

    private void drawCells() {
        double XOffset = (WIDTH/2) % (1/SCALE/WIDTH_SCALE);
        double YOffset = (HEIGHT/2) % (1/SCALE/HEIGHT_SCALE);
        double centerX = WIDTH / 2.0;
        double centerY = HEIGHT / 2.0;


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

        // Стрелка X
        Polygon xArrow = new Polygon(
                WIDTH, centerY,
                WIDTH - 10, centerY - 5,
                WIDTH - 10, centerY + 5
        );
        xArrow.setStyle("-fx-fill: black;");
        group.getChildren().add(xArrow);

        // Подпись X
        Text xText = new Text(X_AXIS_NAME);
        xText.setFont(Font.font("System", FontWeight.BOLD,14));
        xText.setX(WIDTH - 25);
        xText.setY(centerY - 5);
        xText.setStyle("-fx-fill: black; -fx-font-size: 14px;");
        group.getChildren().add(xText);

        // Стрелка Y
        Polygon yArrow = new Polygon(
                centerX, 0,
                centerX - 5, 10,
                centerX + 5, 10
        );
        yArrow.setStyle("-fx-fill: black;");
        group.getChildren().add(yArrow);

        // Подпись Y
        Text yText = new Text(Y_AXIS_NAME);
        yText.setFont(Font.font("System", FontWeight.BOLD,14));
        yText.setX(centerX + 10);
        yText.setY(20);
        yText.setStyle("-fx-fill: black; -fx-font-size: 14px;");
        group.getChildren().add(yText);

        drawCoordinates();
    }

    private void drawCoordinates() {
        double cellWidth = 1.0 / SCALE / WIDTH_SCALE;
        double cellHeight = 1.0 / SCALE / HEIGHT_SCALE;

        double XOffset = (WIDTH / 2) % cellWidth;
        double YOffset = (HEIGHT / 2) % cellHeight;

        // X координаты
        for (int x = 0; x < WIDTH * SCALE / WIDTH_SCALE; x++) {
            double screenX = x / SCALE / WIDTH_SCALE + XOffset;

            double mathX = (screenX - WIDTH / 2) * SCALE * WIDTH_SCALE;

            Text text = new Text(String.format("%.0f", mathX));

            text.setX(screenX + 2);
            text.setY(HEIGHT / 2 + 15);

            text.setStyle("-fx-fill: black; -fx-font-size: 10px;");

            group.getChildren().add(text);
        }

        // Y координаты
        for (int y = 0; y < HEIGHT * SCALE / HEIGHT_SCALE; y++) {
            double screenY = y / SCALE / HEIGHT_SCALE + YOffset;

            double mathY = (HEIGHT / 2 - screenY) * SCALE * HEIGHT_SCALE;

            Text text = new Text(String.format("%.0f", mathY));

            text.setX(WIDTH / 2 + 5);
            text.setY(screenY - 2);

            text.setStyle("-fx-fill: black; -fx-font-size: 10px;");

            group.getChildren().add(text);
        }
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
