package swa.derivative.Functions;

public class SineFunction implements Function{
    @Override
    public double getY(double x) {
        return Math.pow(Math.E, 2*x)*Math.pow(Math.sin(x),2);
    }

    @Override
    public double getSPoint() {
        return 0;
    }

    @Override
    public double getEPoint() {
        return Math.PI;
    }

    public Function getDerivative(){
        return new derivative();
    }

    private class derivative implements Function{
        @Override
        public double getY(double x) {
            return 2*Math.pow(Math.E, 2*x) * Math.sin(x) * (Math.sin(x) + Math.cos(x));
        }

        @Override
        public double getSPoint() {
            return SineFunction.this.getSPoint();
        }

        @Override
        public double getEPoint() {
            return SineFunction.this.getEPoint();
        }
    }
}
