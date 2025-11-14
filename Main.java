import functions.*;
public class Main {
    public static void main(String[] args) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
        double[] values = {1, 3, 5, 7, 9};
        FunctionPoint P1 = new FunctionPoint(2, 4);
        FunctionPoint P2 = new FunctionPoint(4, 4);
        FunctionPoint P_er = new FunctionPoint(0, 1);
        
        
            
        
        TabulatedFunction function = new ArrayTabulatedFunction(0.0, 10.0, values);
        System.out.println("Исходный массив:");
        for (int i = 0; i < function.getPointsCount(); i++) {
            System.out.println(i + ") X = " + function.getPointX(i) + " Y = " + function.getPointY(i));
        }
        
        

        System.out.println("Значение функции в точке: " + function.getFunctionValue(1.5));
        
        function.deletePoint(2);
        System.out.println("После удаления:");
        for (int i = 0; i < function.getPointsCount(); i++) {
            System.out.println(i + ") X = " + function.getPointX(i) + " Y = " + function.getPointY(i));
        }
        
        function.addPoint(P1);
        function.addPoint(P2);
        System.out.println("После добавления:");
        for (int i = 0; i < function.getPointsCount(); i++) {
            System.out.println(i + ") X = " + function.getPointX(i) + " Y = " + function.getPointY(i));
        }

        System.out.println("Ловля ошибок:");
        try{
            TabulatedFunction function_err = new ArrayTabulatedFunction(10, -2, values);
            System.out.println(" ОШИБКА: Не выброшено IllegalStateException");
        }
        catch(IllegalArgumentException e){
            System.out.println(" Поймано: IllegalStateException - " + e.getMessage());
        }
         try{
            function.setPointX(0,-12);
            System.out.println("ОШИБКА: Не выброшено InappropriateFunctionPointException");
        }
        catch(InappropriateFunctionPointException e){
            System.out.println("Поймано: InappropriateFunctionPointException - " + e.getMessage());
        }
        try{
            function.deletePoint(10);
            System.out.println("ОШИБКА: Не выброшено FunctionPointIndexOutOfBoundsException");
        }
        catch(FunctionPointIndexOutOfBoundsException e){
            System.out.println("Поймано: FunctionPointIndexOutOfBoundsException - " + e.getMessage());
        }
        try{
            function.addPoint(P_er);
            System.out.println("ОШИБКА: Не выброшено FunctionPointIndexOutOfBoundsException");
        }
        catch(InappropriateFunctionPointException e){
            System.out.println("Поймано: FunctionPointIndexOutOfBoundsException - " + e.getMessage());
        }

        TabulatedFunction function1 = new LinkedListTabulatedFunction(0.0, 10.0, values);
        System.out.println("Исходный список:");
        System.out.println("Количество элементов:" + function1.getPointsCount());
        for (int i = 0; i < function1.getPointsCount(); i++) {
            System.out.println(i + ") X = " + function1.getPointX(i) + " Y = " + function1.getPointY(i));
        }
        System.out.println("Значение функции в точке: " + function1.getFunctionValue(3.75));
        function1.deletePoint(1);
        //function1.deletePoint(2);
        //function1.deletePoint(3);
        System.out.println("После удаления:");
        for (int i = 0; i < function1.getPointsCount(); i++) {
            System.out.println(i + ") X = " + function1.getPointX(i) + " Y = " + function1.getPointY(i));
        }
        function1.addPoint(P2);
        System.out.println("После добавления:");
        for (int i = 0; i < function1.getPointsCount(); i++) {
            System.out.println(i + ") X = " + function1.getPointX(i) + " Y = " + function1.getPointY(i));
        }
        System.out.println("Ловля ошибок номер 2:");
        try{
            TabulatedFunction function_err = new LinkedListTabulatedFunction(10, -2, values);
            System.out.println(" ОШИБКА: Не выброшено IllegalStateException");
        }
        catch(IllegalArgumentException e){
            System.out.println(" Поймано: IllegalStateException - " + e.getMessage());
        }
         try{
            function1.setPointX(0,-12);
            System.out.println("ОШИБКА: Не выброшено InappropriateFunctionPointException");
        }
        catch(InappropriateFunctionPointException e){
            System.out.println("Поймано: InappropriateFunctionPointException - " + e.getMessage());
        }
        try{
            function1.deletePoint(10);
            System.out.println("ОШИБКА: Не выброшено FunctionPointIndexOutOfBoundsException");
        }
        catch(FunctionPointIndexOutOfBoundsException e){
            System.out.println("Поймано: FunctionPointIndexOutOfBoundsException - " + e.getMessage());
        }
        try{
            function1.addPoint(P_er);
            System.out.println("ОШИБКА: Не выброшено FunctionPointIndexOutOfBoundsException");
        }
        catch(InappropriateFunctionPointException e){
            System.out.println("Поймано: FunctionPointIndexOutOfBoundsException - " + e.getMessage());
        }
        
    }
}

