package swa.derivative.Functions;

import swa.derivative.Differentials.DifferentialSystem;

public class FunctionComparator {

    private final DifferentialSystem differentialSystem;

    public FunctionComparator(DifferentialSystem differentialSystem) {
        this.differentialSystem = differentialSystem;
    }


    public Function compareFunctions(Function origin, Function compareTo) {
        return new ComparedFunc(origin,compareTo);
    }

    public Function getConvergenceRight(Function origin, Function analytic, double fixedX) {
        return new ConvergenceRight(origin,analytic,differentialSystem,fixedX);
    }
    public Function getConvergenceLeft(Function origin, Function analytic, double fixedX) {
        return new ConvergenceLeft(origin,analytic,differentialSystem,fixedX);
    }
    public Function getConvergenceCentral(Function origin, Function analytic, double fixedX) {
        return new ConvergenceCentral(origin,analytic,differentialSystem,fixedX);
    }

    private record ComparedFunc(Function origin, Function compareTo) implements Function {

        @Override
            public double getY(double x) {
                return Math.abs(origin.getY(x) - compareTo.getY(x));
            }

            @Override
            public double getSPoint() {
                return origin.getSPoint();
            }

            @Override
            public double getEPoint() {
                return origin.getEPoint();
            }
    }

    private record ConvergenceRight(Function origin, Function analytic, DifferentialSystem ds, double fixedX) implements Function {

        @Override
        public double getY(double x) {
            double h = Math.exp(x);
            Function numerical = ds.getRightDifferential(origin, h);
            return Math.log(Math.abs(analytic.getY(fixedX) - numerical.getY(fixedX)));
        }

        @Override
        public double getSPoint() {
            return -100;
        }

        @Override
        public double getEPoint() {
            return 0;
        }
    }

    private record ConvergenceLeft(Function origin, Function analytic, DifferentialSystem ds, double fixedX) implements Function {

        @Override
        public double getY(double x) {
            double h = Math.exp(x);
            Function numerical = ds.getLeftDifferential(origin, h);
            return Math.log(Math.abs(analytic.getY(fixedX) - numerical.getY(fixedX)));
        }

        @Override
        public double getSPoint() {
            return -100;
        }

        @Override
        public double getEPoint() {
            return 0;
        }
    }

    private record ConvergenceCentral(Function origin, Function analytic, DifferentialSystem ds, double fixedX) implements Function {

        @Override
        public double getY(double x) {
            double h = Math.exp(x);
            Function numerical = ds.getCentralDifferential(origin, h);
            return Math.log(Math.abs(analytic.getY(fixedX) - numerical.getY(fixedX)));
        }

        @Override
        public double getSPoint() {
            return -100;
        }

        @Override
        public double getEPoint() {
            return 0;
        }
    }
}
