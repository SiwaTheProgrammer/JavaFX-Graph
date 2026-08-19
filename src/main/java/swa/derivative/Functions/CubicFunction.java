package swa.derivative.Functions;

public class CubicFunction implements Function{
    @Override
    public double getY(double x) {
        return 2 * Math.pow(x, 3) + 3 * Math.pow(x, 2);
    }

    @Override
    public double getSPoint() {
        return 1.0;
    }

    @Override
    public double getEPoint() {
        return 3.0;
    }

    public Function getDerivative(){
        return new derivative();
    }

    private class derivative implements Function{
        @Override
        public double getY(double x) {
            return 6*Math.pow(x,2) + 6*x;
        }

        @Override
        public double getSPoint() {
            return CubicFunction.this.getSPoint();
        }

        @Override
        public double getEPoint() {
            return CubicFunction.this.getEPoint();
        }
    }
}
