import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Matrix {
    private int rowCount;
    private int colCount;
    private int[][] values;
    private int[][] allCols;

    /***
     * The Matrix class constructor
     * 
     * @param matrix the matrix value array
     * @param row    matrix rows number
     * @param col    matrix columns number
     */
    public Matrix(int[][] matrix, int row, int col) {
        rowCount = row;
        colCount = col;
        values = matrix;
        allCols = new int[col][row];
        int[] column = new int[row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                column[j] = matrix[j][i];
            }
            allCols[i] = column;
            column = new int[row];
        }
    }

    /***
     * The getter of allCols
     * 
     * @return matrix columns array
     */
    public int[][] getAllCols() {
        return allCols;
    }

    /***
     * The method multiples matrix row to column of other matrix
     * 
     * @param row    the first multiplier
     * @param column the second multiplier
     * @return the result of multiplying
     */
    public static int multiplyRowByColumn(int[] row, int[] column) {
        int sum = 0;
        for (int i = 0; i < row.length; i++) {
            sum += row[i] * column[i];
        }
        return sum;
    }

    /***
     * The method finds the min divisor of number
     * 
     * @param n the transfered number
     * @return the min divisor of number
     */
    private static int findMinDivisor(int n) {
        if (n % 2 == 0) {
            return 2;
        }
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return i;
            }
        }
        // если не найден делитель, больший чем 1, то само число является простым
        return n;
    }

    /***
     * The method multiples matrix to other matrix by multiple threads
     * 
     * @param matrix the other matrix
     * @return the multiplying result
     * @throws InterruptedException Thrown when a thread is waiting, sleeping, or
     *                              otherwise occupied, and the thread is
     *                              interrupted , either before or during the
     *                              activity.
     */
    public Matrix multiThreadingMultiplyMatrix(Matrix matrix) throws InterruptedException {
        int threadPartCount = matrix.getRowCount() > 6 && matrix.getColumnCount() > 6
                ? Matrix.findMinDivisor(matrix.getRowCount())
                : 1;
        int[][] val = new int[this.getRowCount()][matrix.getColumnCount()];
        MultiPair pair = new MultiPair(this, matrix);
        System.out.println("Число потоков: " + threadPartCount + '\n');
        Runnable foo = () -> {
            pair.setThreadNumber(pair.getThreadNumber() + 1);
            int thNumber = pair.getThreadNumber();
            System.out.println("Поток " + thNumber + " запущен\n");
            int indx = pair.setIndex(pair.getIndex() + this.getRowCount() / threadPartCount);
            int innerIndex = indx;
            System.out.println("Начальный индекс потока " + thNumber + ": " + indx + '\n');
            for (; innerIndex < indx
                    + (this.getRowCount() / threadPartCount); innerIndex++) {
                for (int k = 0; k < matrix.getColumnCount(); k++) {
                    int elem = 0;
                    elem = Matrix.multiplyRowByColumn(this.values[innerIndex], matrix.getAllCols()[k]);
                    val[innerIndex][k] = elem;
                }
            }
            System.out.println("Конечный индекс потока " + thNumber + ": " + (innerIndex - 1) + '\n');
            System.out.println("Поток " + thNumber + " завершен\n");

        };
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadPartCount; i++) {
            Thread thread = new Thread(foo);
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        return new Matrix(val, this.getRowCount(), matrix.getColumnCount());
    }

    /***
     * The method multiples matrix to other matrix by single thread
     * 
     * @param matrix the other matrix
     * @return the multiplying result
     */
    public Matrix oneThreadingMultiplyMatrix(Matrix matrix) {
        int[][] val = new int[this.getRowCount()][matrix.getColumnCount()];
        for (int i = 0; i < this.getRowCount(); i++) {
            for (int k = 0; k < matrix.getColumnCount(); k++) {
                int elem = 0;
                elem = Matrix.multiplyRowByColumn(this.values[i], matrix.getAllCols()[k]);
                val[i][k] = elem;
            }

        }
        return new Matrix(val, this.getRowCount(), matrix.getColumnCount());
    }

    /***
     * Getter of rowCount
     * 
     * @return field rowCount
     */
    public int getRowCount() {
        return rowCount;
    }

    /***
     * Getter of columnCount
     * 
     * @return field columnCount
     */
    public int getColumnCount() {
        return colCount;
    }

    /***
     * The method checks whether the matrices are multipliable
     * 
     * @param matrix the other matrix
     * @return true if the matrices can be multiplied, otherwise false
     */
    public boolean isMultipliable(Matrix matrix) {
        return this.getColumnCount() == matrix.getRowCount();
    }

    /***
     * The method generates random matrix
     * 
     * @param M the matrix row count
     * @param N the matrix column count
     * @return the generated matrix
     */
    public static Matrix generateRandomMatrix(int M, int N) {
        System.out.format("Генерация случайной %d x %d матрицы...\n", M, N);
        Random rand = new Random();
        int[][] output = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                output[i][j] = rand.nextInt(100);
            }
        }

        return new Matrix(output, M, N);
    }

    /***
     * The function prints the matrix into console by values array
     * 
     * @param values of matrix
     */
    public static void printMatrix(int[][] values) {
        int rowCount = values.length;
        int colCount = values[0].length;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                System.out.print(values[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /***
     * The method prints the matrix into console
     */
    public void printMatrix() {
        System.out.println("Матрица: ");
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                System.out.print(values[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
