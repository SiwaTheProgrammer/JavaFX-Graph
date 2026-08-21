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

    public Function getConvergenceRight(Function origin, Function analytic) {
        return new ConvergenceRight(origin,analytic,differentialSystem);
    }
    public Function getConvergenceLeft(Function origin, Function analytic) {
        return new ConvergenceLeft(origin,analytic,differentialSystem);
    }
    public Function getConvergenceCentral(Function origin, Function analytic) {
        return new ConvergenceCentral(origin,analytic,differentialSystem);
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

    private class ConvergenceRight implements Function {

        private final Function origin;
        private final Function analytic;
        private final DifferentialSystem ds;

        public ConvergenceRight(Function origin, Function analytic, DifferentialSystem ds) {
            this.origin = origin;
            this.analytic = analytic;
            this.ds = ds;
        }

        @Override
        public double getY(double x) {
            double h = Math.exp(x);
            Function numerical = ds.getRightDifferential(origin, h);
            double maxError = FunctionComparator.this.getMaxError(origin,analytic,numerical);
            return Math.log(maxError);
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

    private class ConvergenceLeft implements Function {

        private final Function origin;
        private final Function analytic;
        private final DifferentialSystem ds;

        public ConvergenceLeft(Function origin, Function analytic, DifferentialSystem ds) {
            this.origin = origin;
            this.analytic = analytic;
            this.ds = ds;
        }

        @Override
        public double getY(double x) {
            double h = Math.exp(x);
            Function numerical = ds.getLeftDifferential(origin, h);
            double maxError = FunctionComparator.this.getMaxError(origin,analytic,numerical);
            return Math.log(maxError);
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

    private class ConvergenceCentral implements Function {

        private final Function origin;
        private final Function analytic;
        private final DifferentialSystem ds;

        public ConvergenceCentral(Function origin, Function analytic, DifferentialSystem ds) {
            this.origin = origin;
            this.analytic = analytic;
            this.ds = ds;
        }

        @Override
        public double getY(double x) {
            double h = Math.exp(x);
            Function numerical = ds.getCentralDifferential(origin, h);
            double maxError = FunctionComparator.this.getMaxError(origin,analytic,numerical);
            return Math.log(maxError);
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


    public double getMaxError(Function origin, Function analytic, Function numerical) {
        double maxError = 0;

        double start = origin.getSPoint();
        double end = origin.getEPoint();

        // Количество точек сетки
        int N = 1000;

        double step = (end - start) / N;

        for (int i = 0; i <= N; i++) {
            double x = start + i * step;
            double error = Math.abs(analytic.getY(x) - numerical.getY(x));

            if (error > maxError) {
                maxError = error;
            }
        }

        return maxError;
    }
}
