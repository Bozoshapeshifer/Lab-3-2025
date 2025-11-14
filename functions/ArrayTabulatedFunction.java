package functions;
public class ArrayTabulatedFunction implements TabulatedFunction {
    
    private FunctionPoint points[];
    private int pointsCount;
    
    public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount) {
        if(leftX >= rightX)
        {
            throw new IllegalArgumentException("the left border is larger than the right one");
        }

        if(pointsCount < 2)
        {
            throw new IllegalArgumentException("Number of points is less than 2");
        }
        
        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            points[i] = new FunctionPoint(leftX + i * step, 0);
        }

    }

    public ArrayTabulatedFunction(double leftX, double rightX, double[] values) {
        if(leftX >= rightX)
        {
            throw new IllegalArgumentException("the left border is larger than the right one");
        }

        if(values.length < 2)
        {
            throw new IllegalArgumentException("Number of points is less than 2");
        }
        
        pointsCount = values.length;
        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            points[i] = new FunctionPoint(leftX + i * step, values[i]);
        }
    }

    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[pointsCount-1].getX();
    }

    public double getFunctionValue(double x)
     {
        double epcilon=1e-9;
        double leftX=points[0].getX();
        double rightX=points[pointsCount-1].getX();
        if (x < leftX|| x > rightX)
            return Double.NaN;
        if(Math.abs(x-leftX)<epcilon)
        {
            return points[0].getY();
        }
        if(Math.abs(x-rightX)<epcilon)
        {
            return points[pointsCount-1].getY();
        }
        int i = 0;
        double value=0;
        for (i=0;x > points[i].getX();++i)
        {
        value = points[i].getY() + (points[i + 1].getY() - points[i].getY()) * (x - points[i].getX()) / (points[i + 1].getX() - points[i].getX());
        }
        return value;

    }

    public int getPointsCount() {
        return pointsCount;
    }

    public FunctionPoint getPoint(int index) {
        if(index<0||index>=pointsCount)
        {
            throw new FunctionPointIndexOutOfBoundsException("out-of-bounds");
        }
        return points[index];
    }

    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException {
        double leftX=getLeftDomainBorder();
        double rightX=getRightDomainBorder();
        if (index < 0 || index >= pointsCount)
        {
            throw new FunctionPointIndexOutOfBoundsException("out-of-bounds");
        }
        if(leftX > point.getX() || rightX < point.getX())
        {
            throw new InappropriateFunctionPointException("x out of boreder");
        }
        points[index] = new FunctionPoint(point);
    }

    public double getPointX(int index) {

        if (index < 0 || index >= pointsCount)
        {
            throw new FunctionPointIndexOutOfBoundsException("out-of-bounds");
            
        }
             if (index < 0 || index >= pointsCount)
             {
                return Double.NaN;
             }
        return points[index].getX();
    }


public void setPointX(int index, double x) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
    double leftX=getLeftDomainBorder();
    double rightX=getRightDomainBorder();
    if (index < 0 || index >= pointsCount) {
        throw new FunctionPointIndexOutOfBoundsException("out-of-bounds");
    }

 
    
    if (x < leftX || x > rightX) {
        throw new InappropriateFunctionPointException("x out of border");
    }

    double epсilon = 1e-9;
    for (int i = 0; i < pointsCount; i++) {
        if (i != index && Math.abs(points[i].getX() - x) < epсilon) {
            throw new InappropriateFunctionPointException("Точка с таким x уже существует");
        }
    }

    points[index].setX(x);
}

    public double getPointY(int index) {
        if (index < 0 || index > pointsCount)
        {
            throw new FunctionPointIndexOutOfBoundsException("out-of-bounds");
            
        }
         if (index < 0 || index >= pointsCount)
        {
        return Double.NaN;
        }

        return points[index].getY();
    }

    public void setPointY(int index, double y)
    {
        if (index < 0 || index > pointsCount)
        {
            throw new FunctionPointIndexOutOfBoundsException("out-of-bounds");
        }
        points[index].setY(y);
    }

  public void deletePoint(int index){
    if (index < 0 || index >= pointsCount)
        {
            throw new FunctionPointIndexOutOfBoundsException("out-of-bounds");
        }
    if(pointsCount<3)
    {
        throw new IllegalStateException("The number of points is less than 3");
    }
        
        if(index == pointsCount - 1){
            pointsCount--;
            points[pointsCount] = null;
        }
        else{ 
            System.arraycopy(points, index + 1, points, index, pointsCount - 1 - index);
            pointsCount--;
            points[pointsCount] = null;
        }
        
    
        }

public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
    double epсilon = 1e-9;
    
    
    for (int i = 0; i < pointsCount; i++) {
        if (Math.abs(points[i].getX() - point.getX()) < epсilon) {
            throw new InappropriateFunctionPointException("There is already such an x");
        }
    }
    
    
    int insertIndex = 0;
    while (insertIndex < pointsCount && point.getX() > points[insertIndex].getX()) {
        insertIndex++;
    }
    
    
    FunctionPoint[] newPoints = new FunctionPoint[pointsCount + 1];
    System.arraycopy(points, 0, newPoints, 0, insertIndex);
    newPoints[insertIndex] = new FunctionPoint(point);
    System.arraycopy(points, insertIndex, newPoints, insertIndex + 1, pointsCount - insertIndex);
    
    points = newPoints;
    pointsCount++;
}

}