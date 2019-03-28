import collections.and.generics.*;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        TwoGenericModel<Integer, String> twoGenericModel = new TwoGenericModel<>(0, "Hello");
        TwoGenericModel<Integer, String> twoGenericModel12 = new TwoGenericModel<>(1, "world");
        System.out.println(twoGenericModel.toString());
        List<TwoGenericModel<Integer, String>> twoGenericModelList = new ArrayList<>();
        for(int i = 0; i < 10; i ++){
            twoGenericModelList.add(
                    new TwoGenericModel<>(
                            i,
                            "---- "+i + " ----"
                    )
            );
        }

        for(TwoGenericModel<Integer, String> twoGenericModel1: twoGenericModelList){
            System.out.println(twoGenericModel1.toString());
        }
        System.out.println("---------------");

        List<Pair<Integer, String>> pairList = new ArrayList<>();
        for(int i = 0; i < 10; i ++){
            pairList.add(new Pair<>(
                    (new Random()).nextInt(2),
                    ""+((new Random()).nextInt(100))

            ));
        };

        System.out.println(pairList);
        Collections.sort(pairList, Comparator.reverseOrder());
        pairList.forEach(element -> {
            System.out.print(element.getValue() + " ");
        });

    }
}
