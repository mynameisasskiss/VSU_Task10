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
        ab = evalLength(a,b); // Длина AB
        bc = evalLength(b,c); // Длина BC
        ca = evalLength(c,a); // Длина CA
        return;
    }

    private static double evalLength(int[] a, int[] b){
        return Math.sqrt((Math.pow(a[0] - b[0], 2) + (Math.pow(a[1] - b[1], 2))));
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

    private static boolean areSimilar(Triangle t1, Triangle t2) {
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


    public static List<List<Triangle>> smartGroup(List<Triangle> triangles) {
        List<List<Triangle>> outList = new ArrayList<>();
        for (int i = 0; i < triangles.size(); i++) {
            Triangle t1 = triangles.get(i);
            List<Triangle> tempList = new ArrayList<>();
            tempList.add(t1);
            for (int j = i + 1; j < triangles.size(); j++) {
                //System.out.println(j);
                Triangle t2 = triangles.get(j);
                //t1.getInfo();
                //t2.getInfo();
                if (areSimilar(t1, t2)) {
                    //System.out.printf("success %s | %s \n",Arrays.deepToString(t1.getInfo().toArray()),Arrays.deepToString(t2.getInfo().toArray()));
                    tempList.add(t2);
                    triangles.remove(t2);
                }
            }
            //list.remove(t1);
            outList.add(tempList);
        }
        return outList;
    }

    public static String getResult(List<List<Triangle>> list) {
        String outputData = "[\n";
        for (int i = 0; i < list.size(); i++) {
            outputData +=  (i!=0 ? ",\n\t[" : "\t[");
            for (int j = 0; j < list.get(i).size(); j++) {
                outputData += (j == 0 ? " " : ", ") + Arrays.deepToString((list.get(i).get(j).getInfo(false)).toArray()).replace("[[","\n\t\t[[").replace("]]","]]");
            }
            outputData += "\n\t]";
        }
        return outputData+"\n]";
    }
}
