import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Flow {
    private List<Integer> arrayList;


    public Flow() {
        arrayList = new ArrayList<>();

    }

    public Flow(List<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public List<Integer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public int getHead() {
        if (arrayList == null) return -1;
        if (arrayList.size() < 1) return -1;
        return arrayList.get(0);
    }

    public int getTail() {
        if (arrayList == null) return -1;
        if (arrayList.size() < 1) return -1;
        return arrayList.get(arrayList.size() - 1);
    }

    public int add(int position) {
        arrayList.add(position);
        return position;
    }

    public int getLength() {
        return arrayList.size();
    }

    public Flow merge(Flow flow) {
        this.getArrayList().addAll(flow.getArrayList());
        return this;
    }


    public static List<Flow> divideFlow(Flow parrentFlow, int index) {

        List<Flow> flowList = new ArrayList<>();
        List<Integer> parrentList = parrentFlow.getArrayList();
        List<Integer> firstList = parrentList.subList(0, index);
        List<Integer> secondList = parrentList.subList(index, parrentList.size());
        flowList.add(new Flow(firstList));
        flowList.add(new Flow(secondList));

        return flowList;
    }

    public static Flow mergeFlow(Flow firstFlow, Flow secondFlow) {
        List<Integer> targetList = new ArrayList<>();
        targetList.addAll(firstFlow.getArrayList());
        targetList.addAll(secondFlow.getArrayList());
        return new Flow(targetList);
    }

    public static Flow getLongestFlow(List<Flow> flowList) {
        if (flowList == null) return null;
        if (flowList.size() < 1) return null;
        Flow longestFlow = flowList.get(0);
        for (Flow flow : flowList) {
            if (flow.getArrayList().size() < longestFlow.getArrayList().size()) {
                longestFlow = flow;
            }
        }
        return longestFlow;
    }

    public static int getIndexLongestFlow(List<Flow> flowList) {
        if (flowList == null) return -1;
        if (flowList.size() < 1) return -1;
        int index = 0;
        for (int i = 0; i < flowList.size(); i++) {
            Flow flow = flowList.get(i);
            if (flow.getArrayList().size() < flowList.get(index).getArrayList().size()) {
                index = i;
            }
        }
        return index;
    }


    public static List<Flow> getNewFlowList(List<Flow> flowList, int width, int height) {
        int numberPairs = (new Random()).nextInt((int) Math.sqrt(width * height)) + 1;
        numberPairs = Math.max(flowList.size(), numberPairs);
     //   numberPairs = (int) Math.sqrt(width * height);
        System.out.println(numberPairs);
        List<Flow> newFlowList = new ArrayList<>();
        List<Flow> flowList1 = new ArrayList<>();
        newFlowList.addAll(flowList);

        int numberFlows = flowList.size();
        if (numberFlows < numberPairs) {

            while (numberFlows < numberPairs) {
                flowList1.clear();
                flowList1.addAll(newFlowList);
                newFlowList.clear();
                for (Flow flow : flowList1) {
                    if (flow.getLength() > 4 && numberFlows < numberPairs) {
                        int positionRandom = (new Random()).nextInt(flow.getLength() - 4);
                        List<Flow> splitedFlows = divideFlow(flow, positionRandom + 2);
                 //       System.out.println(splitedFlows.get(0) + "\n" + splitedFlows.get(1));
                        newFlowList.addAll(divideFlow(flow, positionRandom + 2));
                        numberFlows++;


                    } else if (flow.getLength() == 4 && numberFlows < numberPairs) {
                        newFlowList.addAll(divideFlow(flow, 2));
                        numberFlows++;
                    } else {
                        newFlowList.add(flow);
                    }
                }

                numberFlows = newFlowList.size();

            }
        }
        return newFlowList;

    }

    private List<Integer> getNeighborhood(int position) {
        // int x = position
        return null;
    }


    @Override
    public String toString() {
        return arrayList.toString();
    }
}
