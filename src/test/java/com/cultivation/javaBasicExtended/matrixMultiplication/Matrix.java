package com.cultivation.javaBasicExtended.matrixMultiplication;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//
@SuppressWarnings({"WeakerAccess", "unused"})
class Matrix {
    private final int[][] storage;

    public int rows() {
        return storage.length;
    }

    public int columns() {
        return storage[0].length;
    }

    public Matrix(int[][] matrixArray) {
        if (matrixArray == null) {
            throw new IllegalArgumentException("Raw matrix is null");
        }

        judgeParameterValid(matrixArray);

        this.storage = matrixArray;
    }

    private void judgeParameterValid(int[][] matrixArray) {
        if (matrixArray.length == 0) {
            throw new IllegalArgumentException("Raw matrix contains 0 row");
        }
        if (matrixArray[0].length == 0) {
            throw new IllegalArgumentException("At least one row of raw matrix contains 0 column");
        }

        for (int[] array : matrixArray) {
            if (array == null) {
                throw new IllegalArgumentException("Raw matrix contains null row");
            }
            if (array.length != matrixArray[0].length) {
                throw new IllegalArgumentException("Raw matrix is not rectangle");
            }
        }
    }

    public static Matrix multiply(Matrix left, Matrix right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("matrix is null");
        }
        int leftRows = left.rows();
        int rightRows = right.rows();

        int leftColumns = left.columns();
        int rightColumns = right.columns();

        if (leftColumns != rightRows) {
            throw new IllegalArgumentException();
        }

        int[][] result = new int[leftRows][rightColumns];

        for (int leftRow = 0; leftRow < leftRows; leftRow++) {
            for (int rightCol = 0; rightCol < rightColumns; rightCol++) {
                for (int leftCol = 0; leftCol < leftColumns; leftCol++) {
                    result[leftRow][rightCol] += left.storage[leftRow][leftCol] * right.storage[leftCol][rightCol];
                }
            }
        }

        return new Matrix(result);
    }

    // TODO: you can add some helper method if you like.
    // <--start

    // --end->

    public int[] getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows()) {
            throw new IllegalArgumentException();
        }
        return storage[rowIndex];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (Matrix.class != obj.getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) obj;
        if (rows() != matrix.rows() || columns() != matrix.columns()) {
            return false;
        }

        int rows = rows();
        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            if (!Arrays.equals(getRow(rowIndex), matrix.getRow(rowIndex))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = Arrays.hashCode(getRow(0));
        int rows = rows();
        for (int rowIndex = 1; rowIndex < rows; ++rowIndex) {
            hash ^= Arrays.hashCode(getRow(rowIndex));
        }

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(storage)
                .forEach(row -> formatRow(builder, row));
        return builder.toString();
    }

    private void formatRow(StringBuilder builder, int[] row) {
        for (int item : row) {
            builder.append(String.format("%-10s", Integer.toString(item)));
        }
        builder.append("\n");
    }
}
