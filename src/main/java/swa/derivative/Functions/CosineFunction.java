package swa.derivative.Functions;

public class CosineFunction implements Function{
    @Override
    public double getY(double x) {
        return Math.pow(Math.cos(x),3);
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
            return -3*Math.pow(Math.cos(x),2)*Math.sin(x);
        }

        @Override
        public double getSPoint() {
            return CosineFunction.this.getSPoint();
        }

        @Override
        public double getEPoint() {
            return CosineFunction.this.getEPoint();
        }
    }
}
