package Lessons348;

public class TheNumbers {
    public static class ComplexNumber {
        private double re;
        private double im;

        public ComplexNumber() {
        }

        public ComplexNumber(double re, double im) {
            this.re = re;
            this.im = im;
        }

        public double getRe() {
            return re;
        }

        public double getIm() {
            return im;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o){
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ComplexNumber that = (ComplexNumber) o;
            return Double.compare(that.getRe(), this.getRe()) == 0 && Double.compare(that.getIm(), this.getIm()) == 0;
        }
        @Override
        public int hashCode() {
            Long temp = (long) (re * im);
            return Double.hashCode(re / im + Double.longBitsToDouble(temp));
        }
    }
}
