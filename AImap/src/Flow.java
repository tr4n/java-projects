import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Flow {
    private List<Integer> arrayList;


    public Flow(){
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

    public int add(int position){
        arrayList.add(position);
        return position;
    }
    public int getLength(){
        return arrayList.size();
    }

    public Flow merge(Flow flow){
        this.getArrayList().addAll(flow.getArrayList());
        return this;
    }


    public static List<Flow> divideFlow(Flow parrentFlow, int index){
        List<Flow> flowList = new ArrayList<>();
        ArrayList<Integer> parrentList = (ArrayList<Integer>) parrentFlow.getArrayList();
        ArrayList<Integer> firstList = (ArrayList<Integer>) parrentList.subList(0, index);
        ArrayList<Integer> secondList = (ArrayList<Integer>) parrentList.subList(index, parrentList.size());
        flowList.add(new Flow(firstList));
        flowList.add(new Flow(secondList));
        return flowList;
    }

    public static Flow mergeFlow(Flow firstFlow, Flow secondFlow){
        List<Integer> targetList = new ArrayList<>();
        targetList.addAll(firstFlow.getArrayList());
        targetList.addAll(secondFlow.getArrayList());
        return new Flow(targetList);
    }

    public static Flow getLongestFlow(List<Flow> flowList){
        if(flowList == null ) return null;
        if(flowList.size() < 1) return null;
        Flow longestFlow = flowList.get(0);
        for(Flow flow: flowList){
            if(flow.getArrayList().size() < longestFlow.getArrayList().size()){
                longestFlow = flow;
            }
        }
        return longestFlow;
    }
    public static int getIndexLongestFlow(List<Flow> flowList){
        if(flowList == null ) return -1;
        if(flowList.size() < 1) return -1;
        int index = 0;
        for(int i = 0 ; i < flowList.size() ;i ++){
            Flow flow = flowList.get(i);
            if(flow.getArrayList().size() < flowList.get(index).getArrayList().size()){
                index = i;
            }
        }
        return index;
    }



    public static List<Flow> getNewFlowList(List<Flow> flowList, int numberFlows){

       List<Flow> newFlowList = flowList;
       int index = 0;
        for(index = 0 ; index < flowList.size() ; index ++ ) {
            if(flowList.get(index).getLength() == 1){
                newFlowList.addAll(flowList.subList(0, index));
                newFlowList.addAll(flowList.subList(index + 1, flowList.size()));
            }
        }
        for(Flow flow : flowList){
          //  if(flow.getTail())
        }
        return null;
    }

    private List<Integer> getNeighborhood(int position){
       // int x = position
        return null;
    }




    @Override
    public String toString() {
        return arrayList.toString();
    }
}
