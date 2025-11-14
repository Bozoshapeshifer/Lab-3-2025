package functions;

public class LinkedListTabulatedFunction implements TabulatedFunction{
    private class FunctionNode
    {
    private FunctionPoint point;
    private FunctionNode prev;
    private FunctionNode next;
    
    public FunctionNode()
    {
        next=null;
        prev=null;
        point = new FunctionPoint();

    }

    public FunctionNode(FunctionNode prev,FunctionNode next)
    {
        point = new FunctionPoint();
        this.prev = prev;
        this.next = next;
        
    }

    // Безопасные геттеры
    public FunctionPoint getPoint() {
        return new FunctionPoint(point); // Возвращаем копию
    }

    
    public void setPoint(FunctionPoint point) {
        this.point = new FunctionPoint(point);
    }

    FunctionNode getPrev() {
        return prev;
    }
    
    FunctionNode getNext() {
        return next;
    }

    void setPrev(FunctionNode prev) {
        this.prev = prev;
    }

    void setNext(FunctionNode next) {
        this.next = next;
    }
}
private FunctionNode head;
private int pointsCount;


public LinkedListTabulatedFunction(double leftX, double rightX, int pointsCount) {
    this.pointsCount = 0; // сначала 0
    if (leftX >= rightX) throw new IllegalArgumentException("the left border is larger than the right one");
    if (pointsCount < 2) throw new IllegalArgumentException("Number of points is less than 2");
    
    head = new FunctionNode();
    head.setNext(head);
    head.setPrev(head); // создаем циклический список
    
    double step = (rightX - leftX) / (pointsCount - 1);
    for (int i = 0; i < pointsCount; i++) {
        FunctionPoint point = new FunctionPoint(leftX + i * step, 0);
        addNodeToTail().setPoint(point);
    }
}

    public LinkedListTabulatedFunction(double leftX, double rightX, double values[]) {
        if (leftX >= rightX) throw new IllegalArgumentException("the left border is larger than the right one");
        if (values.length < 2) throw new IllegalArgumentException("Number of points is less than 2");
        head = new FunctionNode(head, head);
        double step = (rightX - leftX) / (values.length - 1);
        for (int i = 0; i < values.length; i++) {
            FunctionPoint point = new FunctionPoint(leftX + i * step, values[i]);
            addNodeToTail().setPoint(point);
        }
    }
    
    public double getLeftDomainBorder() 
    {
        return head.getNext().getPoint().getX();
    }

    public double getRightDomainBorder() 
    {
        return head.getPrev().getPoint().getX();
    }
     public int getPointsCount() 
    {
        return this.pointsCount;
    }

  public double getFunctionValue(double x) {
    double epsilon = 1e-9;
    double leftX = getLeftDomainBorder();
    double rightX = getRightDomainBorder();
    
    if (x < leftX || x > rightX) return Double.NaN;
    
    FunctionNode current = head.getNext();
    
    
    if (Math.abs(x - leftX) < epsilon) 
    {
        return current.getPoint().getY();
    }

    if (Math.abs(x - rightX) < epsilon) 
    {
        return head.getPrev().getPoint().getY();
    }
    
    while (current != head && current.getNext() != head) {
        double x1 = current.getPoint().getX();
        double x2 = current.getNext().getPoint().getX();
        
        if (x >= x1 && x <= x2) {
            double y1 = current.getPoint().getY();
            double y2 = current.getNext().getPoint().getY();
            return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
        }
        current = current.getNext();
    }
    
    return Double.NaN;
}

FunctionNode getNodeByIndex(int index) {
    if (index < 0 || index >= pointsCount) 
        throw new FunctionPointIndexOutOfBoundsException("Invalid index");
    
    FunctionNode current;
    if(index < pointsCount/2) {
        current = head.getNext(); 
        for(int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    } else {
        current = head.getPrev();
        for(int i = pointsCount - 1; i > index; i--) {
            current = current.prev;
        }
        return current;
    }
}
private FunctionNode addNodeToTail() {
        FunctionNode newNode = new FunctionNode();
        
        
        if (pointsCount == 0) {
            newNode.setNext(head);
            newNode.setPrev(head);
            head.setNext(newNode);
            head.setPrev(newNode);
        } else {
            
            FunctionNode tail = head.getPrev();
            newNode.setPrev(tail);
            newNode.setNext(head);
            tail.setNext(newNode);
            head.setPrev(newNode);
        }
        
        pointsCount++;
        return newNode;
    }

FunctionNode addNodeByIndex(int index)
{
    FunctionNode Node = getNodeByIndex(index);
    FunctionNode prevNode = Node.getPrev();
    FunctionNode newNode=new FunctionNode();
    newNode.setNext(Node);
    newNode.setPrev(prevNode);
    Node.setPrev(newNode);
    prevNode.setNext(newNode);
    pointsCount++;
    return newNode;
}

FunctionNode deleteNodeByIndex(int index)
{
    FunctionNode Node = getNodeByIndex(index);
    FunctionNode nextNode = Node.getNext();
    FunctionNode prevNode = Node.getPrev();
    Node.setNext(null);
    Node.setPrev(null);
    pointsCount--;
    prevNode.setNext(nextNode);
    nextNode.setPrev(prevNode);
    return Node;

}

public FunctionPoint getPoint(int index) {
        if (index < 0 || index >= pointsCount) {
            throw new FunctionPointIndexOutOfBoundsException("out-of-bounds");
        }
        return getNodeByIndex(index).getPoint();
    }

public void setPoint(int index,FunctionPoint point) throws InappropriateFunctionPointException
{
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
        FunctionNode node = getNodeByIndex(index);
        node.setPoint(point);
    }

public double getPointX(int index)
{
    if (0 > index || index >= pointsCount) throw new FunctionPointIndexOutOfBoundsException("out-of-bounds");
    return getPoint(index).getX();
}

public void setPointX(int index, double x) throws InappropriateFunctionPointException
{
    double leftX=getLeftDomainBorder();
    double rightX=getRightDomainBorder();
    if (index < 0 || index >= pointsCount)
        {
            throw new FunctionPointIndexOutOfBoundsException("out-of-bounds");
        }
        if(leftX > x || rightX < x)
        {
            throw new InappropriateFunctionPointException("x out of boreder");
        }
        FunctionNode node = getNodeByIndex(index);
        node.getPoint().setX(x);

}
public double getPointY(int index)
{
    if (index < 0 || index >= pointsCount)
    {
        throw new FunctionPointIndexOutOfBoundsException("out-of-bounds");
    }
    FunctionNode node = getNodeByIndex(index);
    return node.getPoint().getY();
}

public void setPointY(int index, double y)
{
    if (index < 0 || index >= pointsCount)
        {
            throw new FunctionPointIndexOutOfBoundsException("out-of-bounds");
        }

    FunctionNode node = getNodeByIndex(index);
    node.getPoint().setY(y);
}

public void deletePoint(int index)
{
    if (index < 0 || index >= pointsCount)
        {
            throw new FunctionPointIndexOutOfBoundsException("out-of-bounds");
        }

    if(pointsCount<3)
    {
        throw new IllegalStateException("The number of points is less than 3");
    }
    
    deleteNodeByIndex(index); 
}

public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
    double epsilon = 1e-9;
    
    // Проверка уникальности X
    FunctionNode current = head.getNext();
    while (current != head) {
        if (Math.abs(current.getPoint().getX() - point.getX()) < epsilon) {
            throw new InappropriateFunctionPointException("There is already such an x");
        }
        current = current.getNext();
    }
    
    // Поиск позиции для вставки
    current = head.getNext();
    int index = 0;
    while (current != head && current.getPoint().getX() < point.getX()) {
        current = current.getNext();
        index++;
    }
    
    // Вставка
    if (current == head) {
        addNodeToTail().setPoint(point);
    } else {
        addNodeByIndex(index).setPoint(point);
    }
}
}