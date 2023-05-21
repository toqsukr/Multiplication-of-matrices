public class App {

    public static void main(String[] args) {
        int matrixRows = 3333;
        int matrixCols = 3333;

        Matrix firstMatrix = Matrix.generateRandomMatrix(matrixRows, matrixCols);
        Matrix secondMatrix = Matrix.generateRandomMatrix(matrixCols, matrixRows);
        // firstMatrix.printMatrix();
        // secondMatrix.printMatrix();
        if (firstMatrix.isMultipliable(secondMatrix)) {
            System.out.println("\nНачало перемножения одним потоком\n");
            long start1 = System.currentTimeMillis();
            Matrix firstResMatrix = firstMatrix.oneThreadingMultiplyMatrix(secondMatrix);
            long finish1 = System.currentTimeMillis();
            long elapsed1 = finish1 - start1;
            System.out.println("Конец перемножения одним потоком\n");

            try {
                System.out.println("Начало перемножения несколькими потоками\n");
                long start2 = System.currentTimeMillis();
                Matrix secondResMatrix = firstMatrix.multiThreadingMultiplyMatrix(secondMatrix);
                long finish2 = System.currentTimeMillis();
                System.out.println("Конец перемножения несколькими потоками\n");
                long elapsed2 = finish2 - start2;
                System.out.println("Время перемножения матриц одним потоком: " + elapsed1);
                System.out.println("Время перемножения матриц несколькими потоками: " +
                        elapsed2);
                // secondResMatrix.printMatrix();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            // firstResMatrix.printMatrix();
        } else
            System.out.println(
                    "Невозможно перемножить данные матрицы!\nЧисло строк первой должно совпадат с числом столбцов второй!\n");

    }
}
