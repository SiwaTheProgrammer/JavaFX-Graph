package swa.derivative.Differentials;

import swa.derivative.Functions.Function;

public class DifferentialSystem {

    public Function getLeftDifferential(Function function, double deltaX) {
        return new LeftDifferential(function, deltaX);
    }
    public Function getRightDifferential(Function function, double deltaX) {
        return new RightDifferential(function, deltaX);
    }
    public Function getCentralDifferential(Function function, double deltaX) {
        return new CentralDifferential(function, deltaX);
    }


    private static class LeftDifferential implements Function {

        private final Function origin;
        private double deltaX;

        private LeftDifferential(Function origin, double deltaX) {
            this.origin = origin;
            this.deltaX = deltaX;
        }

        @Override
        public double getY(double x) {
            return (origin.getY(x) - origin.getY(x - deltaX))/deltaX;
        }

        @Override
        public double getSPoint() {
            return origin.getSPoint();
        }

        @Override
        public double getEPoint() {
            return origin.getEPoint();
        }

        public double getDeltaX() {
            return deltaX;
        }

        public void setDeltaX(double deltaX) {
            this.deltaX = deltaX;
        }
    }

    private static class RightDifferential implements Function {

        private final Function origin;
        private double deltaX;

        private RightDifferential(Function origin, double deltaX) {
            this.origin = origin;
            this.deltaX = deltaX;
        }

        @Override
        public double getY(double x) {
            return (origin.getY(x + deltaX) - origin.getY(x))/deltaX;
        }

        @Override
        public double getSPoint() {
            return origin.getSPoint();
        }

        @Override
        public double getEPoint() {
            return origin.getEPoint();
        }

        public double getDeltaX() {
            return deltaX;
        }

        public void setDeltaX(double deltaX) {
            this.deltaX = deltaX;
        }
    }

    private static class CentralDifferential implements Function {

        private final Function origin;
        private double deltaX;

        private CentralDifferential(Function origin, double deltaX) {
            this.origin = origin;
            this.deltaX = deltaX;
        }

        @Override
        public double getY(double x) {
            return (origin.getY(x + deltaX) - origin.getY(x - deltaX))/deltaX/2;
        }

        @Override
        public double getSPoint() {
            return origin.getSPoint();
        }

        @Override
        public double getEPoint() {
            return origin.getEPoint();
        }

        public double getDeltaX() {
            return deltaX;
        }

        public void setDeltaX(double deltaX) {
            this.deltaX = deltaX;
        }
    }
}
