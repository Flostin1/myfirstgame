public class Vector {
    public double x = 0;
    public double y = 0;

    public Vector(double x, double y) {
        set(x, y);
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector v) {
        set(v.x, v.y);
    }

    public Vector get() {
        return new Vector(x, y);
    }

    public double dot(Vector v) {
        return (x * v.x) + (y * v.y);
    }

    public void add(Vector v) {
        x += v.x;
        y += v.y;
    }

    @Override
    public String toString() {
        return String.format("[%f, %f]", x, y);
    }
}