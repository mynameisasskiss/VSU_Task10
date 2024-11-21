import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class Triangle {
    int[] a, b, c;

    double ab, bc, ca;

    public Triangle(int[] a, int[] b, int[] c) {
        this.a = a;
        this.b = b;
        this.c = c;
        ab = Math.sqrt((Math.pow(a[0] - b[0], 2) + (Math.pow(a[1] - b[1], 2)))); // Длина AB
        bc = Math.sqrt((Math.pow(b[0] - c[0], 2) + (Math.pow(b[1] - c[1], 2)))); // Длина BC
        ca = Math.sqrt((Math.pow(c[0] - a[0], 2) + (Math.pow(c[1] - a[1], 2)))); // Длина CA
        return;
    }

    public List<int[]> getInfo(boolean print) {
        List<int[]> list = Arrays.asList(a, b, c);
        if (print) System.out.println(Arrays.deepToString(list.toArray()));
        return list;
    }

    public List<Double> getLength(boolean print) {
        List<Double> list = Arrays.asList(ab, bc, ca);
        if (print) System.out.println(Arrays.toString(list.toArray()));
        return list;
    }

    private static List<Triangle> init(int[][] matrix) {
        List<Triangle> figures = new java.util.ArrayList<>();
        if (matrix[0].length == 6) {
            for (int i = 0; i < matrix.length; i++) {
                figures.add(new Triangle(
                        new int[]{matrix[i][0], matrix[i][1]},
                        new int[]{matrix[i][2], matrix[i][3]},
                        new int[]{matrix[i][4], matrix[i][5]}));
            }
        } else {
            System.out.println("Error while initializing triangle(-s). Check your input data");
            System.exit(0);
        }
        return figures;
    }

    private static boolean areTrianglesSimilar(Triangle t1, Triangle t2) {
        // Проверка деления на ноль
        List<Double> firstLenS = new ArrayList<>(List.of(t1.ab, t1.bc, t1.ca));
        List<Double> secondLenS = new ArrayList<>(List.of(t2.ab, t2.bc, t2.ca));
        firstLenS.sort(Comparator.naturalOrder());
        secondLenS.sort(Comparator.naturalOrder());

        for (int i = 0; i < 3; i++) {
            if (i == 2) {
                if (firstLenS.get(0) / secondLenS.get(0) != firstLenS.get(i) / secondLenS.get(i)) return false;
            } else if (firstLenS.get(i) / secondLenS.get(i) != firstLenS.get(i + 1) / secondLenS.get(i + 1))
                return false;
        }

        return true;
    }


    public static List<List<Triangle>> getFromMatrix(int[][] matrix) {
        List<Triangle> list = init(matrix);
        List<List<Triangle>> outList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Triangle t1 = list.get(i);
            List<Triangle> tempList = new ArrayList<>();
            tempList.add(t1);
            for (int j = i + 1; j < list.size(); j++) {
                //System.out.println(j);
                Triangle t2 = list.get(j);
                //t1.getInfo();
                //t2.getInfo();
                if (areTrianglesSimilar(t1, t2)) {
                    //System.out.printf("success %s | %s \n",Arrays.deepToString(t1.getInfo().toArray()),Arrays.deepToString(t2.getInfo().toArray()));
                    tempList.add(t2);
                    list.remove(t2);
                }
            }
            //list.remove(t1);
            outList.add(tempList);
        }
        return outList;
    }

    public static String getResult(List<List<Triangle>> list) {
        String outputData = "";
        for (int i = 0; i < list.size(); i++) {
            outputData += "{";
            for (int j = 0; j < list.get(i).size(); j++) {
                outputData += (j == 0 ? "" : ", ") + Arrays.deepToString((list.get(i).get(j).getInfo(false)).toArray());
            }
            outputData += "}";
        }
        return outputData;
    }
}
