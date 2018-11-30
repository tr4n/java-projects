package spanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Flow {
    private List<Integer> pointList;

    public Flow(List<Integer> pointList) {
        this.pointList = pointList;
    }

    public Flow() {
        pointList = new ArrayList<>();
    }

    public List<Integer> getPointList() {
        return pointList;
    }

    public void setPointList(List<Integer> pointList) {
        this.pointList = pointList;
    }


    public int getHead() {
        if (pointList == null) return -1;
        if (pointList.size() < 1) return -1;
        return pointList.get(0);
    }

    public int getTail() {
        if (pointList == null) return -1;
        if (pointList.size() < 1) return -1;
        return pointList.get(pointList.size() - 1);
    }

    public int add(int position) {
        pointList.add(position);
        return position;
    }

    public int length() {
        return pointList.size();
    }

    public Flow addFlow(Flow flow) {
        this.getPointList().addAll(flow.getPointList());
        return this;
    }

    public Flow[] split(int index) {
        Flow[] flows = new Flow[2];
        flows[0] = new Flow(this.getPointList().subList(0, index));
        flows[1] = new Flow(this.getPointList().subList(index, this.length()));
        return flows;
    }

    public static List<Flow> getTargetFlowList(List<Flow> flowList, int targetSize) {
        List<Flow> targetFlowList = new ArrayList<>();
        List<Flow> tempList = new ArrayList<>();
        targetFlowList.addAll(flowList);
        int currentSize = flowList.size();



        while (currentSize < targetSize) {
            tempList.clear();
            tempList.addAll(targetFlowList);
            targetFlowList.clear();


            for (Flow flow : tempList) {
                if (flow.length() >4 && currentSize < targetSize) {
                    int randomPosition = (new Random()).nextInt(flow.length() - 4);

                    targetFlowList.addAll(Arrays.asList(flow.split(randomPosition + 2)));
                    currentSize ++;
                }else if (flow.length() ==4 && currentSize < targetSize) {
                    targetFlowList.addAll(Arrays.asList(flow.split( 2)));
                    currentSize ++;
                }else{
                    targetFlowList.add(flow);
                }

            }
        }

        return targetFlowList;
    }

    @Override
    public String toString() {
        return getPointList().toString();
    }
}