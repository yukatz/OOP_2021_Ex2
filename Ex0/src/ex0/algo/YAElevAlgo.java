package ex0.algo;

import ex0.Building;
import ex0.CallForElevator;
import ex0.Elevator;

import java.util.ArrayList;
import java.util.HashMap;

public class YAElevAlgo implements ElevatorAlgo{
    private HashMap<Integer, CallForElevator> ActCalls;




    @Override
    public Building getBuilding() {
        return null;
    }

    @Override
    public String algoName() {
        return "Ex0_OOP_YA_Elevator_Algo";
    }

    @Override
    public int allocateAnElevator(CallForElevator c) {
        int ans=0;
        ArrayList<Integer> elT = new ArrayList<Integer>();
        if(getBuilding().numberOfElevetors()>1){
            for (int i = 0; i < getBuilding().numberOfElevetors(); i++) {
                Elevator elI=getBuilding().getElevetor(i);
                int state = elI.getState();
                if(state==0){
                    int Time = (int) (elI.getStartTime()+(elI.getSpeed()*(Math.abs(elI.getPos()-c.getSrc())))+elI.getStopTime()+(2*elI.getTimeForOpen())+elI.getTimeForClose()+(elI.getSpeed()*(Math.abs(c.getDest()-c.getSrc()))));
                    elT.add(Time);
                }
                if(state==1||state==-1){
                    int MDest = this.ActCalls.get(i).getDest();
                    int SSrc = this.ActCalls.get(i).getSrc();
                    int Time;
                    //באיזה מקרים היא תעצור
                    if(c.getSrc()<SSrc && this.ActCalls.get(i).getState()==0&&c.getState()==0 && c.getSrc()<(Math.abs(MDest-SSrc))/2) {
                        Time = (int) ((elI.getTimeForOpen()*2)+(elI.getStopTime()*2)+elI.getStartTime()+ elI.getTimeForClose()+(elI.getSpeed()*(Math.abs(c.getDest()-c.getSrc()))));
                        elT.add(Time);
                    }
                    if(c.getSrc()>SSrc && this.ActCalls.get(i).getState()==1&&c.getState()==1 && c.getSrc()<(Math.abs(MDest-SSrc))/2) {
                        Time = (int) ((elI.getTimeForOpen()*2)+(elI.getStopTime()*2)+elI.getStartTime()+ elI.getTimeForClose()+(elI.getSpeed()*(Math.abs(c.getDest()-c.getSrc()))));
                        elT.add(Time);

                    }
                    else{
                        Time=(int) (elI.getTimeForClose()+elI.getStartTime()+(elI.getSpeed()*(Math.abs(MDest-SSrc))+elI.getTimeForOpen()+ elI.getTimeForClose()+(elI.getStartTime() + (elI.getSpeed() * (Math.abs(elI.getPos() - c.getSrc()))) + elI.getStopTime() + (2 * elI.getTimeForOpen()) + elI.getTimeForClose() + (elI.getSpeed() * (Math.abs(c.getDest() - c.getSrc()))))));

                    }

                }
            }
        }
        this.ActCalls.put(ans,c);
        return ans;

    }

    @Override
    public void cmdElevator(int elev) {
        Elevator elI=getBuilding().getElevetor(elev);
        elI.goTo(this.ActCalls.get(elev).getDest());
        this.ActCalls.remove(elev);
    }






        }
