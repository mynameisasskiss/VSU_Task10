import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        if(CmdArgs.ifGUI(args)) Graphics.main(args);
        if(CmdArgs.ifConsole(args)) {
            String readFile = args[1];
            String writeFile = args[3];
            try {
                List<Triangle> triangles = initTriangles(FileIO.readIntMatrixFromFile(readFile));
                List<List<Triangle>> result = Triangle.smartGroup(triangles);
                String resultStr = Triangle.getResult(result).replace("}{", "},\n\n{").replace("]], [[", "]],\n[[");
                System.out.println(resultStr);
                FileIO.writeStringToFile(writeFile, resultStr);
            } catch (Exception e) {
                System.out.println("Error");
                System.exit(0);
            }

        }
    }

    public static List<Triangle> initTriangles(int[][] matrix) {
        List<Triangle> figures = new java.util.ArrayList<>();
        if (matrix[0].length == 6) {
            for (int i = 0; i < matrix.length; i++) {
                figures.add(new Triangle(
                        new int[]{matrix[i][0], matrix[i][1]},
                        new int[]{matrix[i][2], matrix[i][3]},
                        new int[]{matrix[i][4], matrix[i][5]}));
            }
        } else {
            throw new ArithmeticException("Неверный формат матрицы");
        }
        return figures;
    }
}