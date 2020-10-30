package functions;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionDocument implements TabulatedFunction{
    private TabulatedFunction function;
    private String fileName;
    private boolean isModified = false;
    private boolean fileNameAssigned = false;

    public TabulatedFunctionDocument() { }

    public TabulatedFunction getFunction() {
        return function;
    }

    public String getFileName() {
        return fileName;
    }

    public TabulatedFunctionDocument(TabulatedFunction function, String fileName) {
        this.function = function;
        this.fileName = fileName;
        fileNameAssigned = true;
    }

    public TabulatedFunctionDocument(String fileName) throws IOException {
        this.fileName = fileName;
        loadFunction(fileName);
        fileNameAssigned = true;
    }

    public void newFunction(double leftX, double rightX, int pointsCount) throws IOException {
        function = new ArrayListTabulatedFunction(leftX, rightX, pointsCount);
        isModified = true;
    }

    public void saveFunction() throws IOException {
        TabulatedFunctions.writeTabulatedFunction(function, new FileWriter(fileName));
        isModified = false;
    }

    public void saveFunctionAs(String fileName) throws IOException {
        this.fileName = fileName;
        TabulatedFunctions.writeTabulatedFunction(function, new FileWriter(fileName));
        isModified = false;
        fileNameAssigned = true;
    }

    public void loadFunction(String fileName) throws IOException {
        this.fileName = fileName;
        function = TabulatedFunctions.readTabulatedFunction(new FileReader(fileName));
        fileNameAssigned = true;
    }

    public void tabulateFunction(Function function, double leftX, double rightX, int pointsCount) throws IOException {
        this.function = TabulatedFunctions.tabulate(function, leftX, rightX, pointsCount);
        isModified = true;
    }

    @Override
    public int getPointsCount() {
        return function.getPointsCount();
    }

    @Override
    public FunctionPoint getPoint(int index) throws FunctionPointIndexOutOfBoundsException {
        return function.getPoint(index);
    }

    @Override
    public void setPoint(int index, FunctionPoint point) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
        function.setPoint(index, point);
        isModified = true;
    }

    @Override
    public double getPointX(int index) throws FunctionPointIndexOutOfBoundsException {
        return function.getPointX(index);
    }

    @Override
    public void setPointX(int index, double x) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
        function.setPointX(index, x);
        isModified = true;
    }

    @Override
    public double getPointY(int index) throws FunctionPointIndexOutOfBoundsException {
        return function.getPointY(index);
    }

    @Override
    public void setPointY(int index, double y) throws FunctionPointIndexOutOfBoundsException {
        function.setPointY(index, y);
        isModified = true;
    }

    @Override
    public void deletePoint(int index) throws FunctionPointIndexOutOfBoundsException, IllegalStateException {
        function.deletePoint(index);
        isModified = true;
    }

    @Override
    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        function.addPoint(point);
        isModified = true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new TabulatedFunctionDocument((TabulatedFunction) function.clone(), fileName);
    }

    @Override
    public int hashCode() {
        return function.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TabulatedFunctionDocument)) return false;
        TabulatedFunctionDocument document = (TabulatedFunctionDocument) o;
        return function.equals(document.function) && fileName.equals(document.fileName);
    }

    @Override
    public String toString() {
        return function.toString();
    }

    @Override
    public double getLeftDomainBorder() {
        return function.getLeftDomainBorder();
    }

    @Override
    public double getRightDomainBorder() {
        return function.getRightDomainBorder();
    }

    @Override
    public double getFunctionValue(double x) {
        return function.getFunctionValue(x);
    }
}
