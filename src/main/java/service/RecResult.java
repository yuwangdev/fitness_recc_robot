package service;

import modelling.ReccEx;
import modelling.ReccFood;

public class RecResult {
    private ReccEx aerobicRec;
    private ReccEx anaerobicRec;
    private ReccFood foodRec;
    private double rewards;

    public RecResult() {
    }

    public ReccEx getAerobicRec() {
        return aerobicRec;
    }

    public void setAerobicRec(ReccEx aerobicRec) {
        this.aerobicRec = aerobicRec;
    }

    public ReccEx getAnaerobicRec() {
        return anaerobicRec;
    }

    public void setAnaerobicRec(ReccEx anaerobicRec) {
        this.anaerobicRec = anaerobicRec;
    }

    public ReccFood getFoodRec() {
        return foodRec;
    }

    public void setFoodRec(ReccFood foodRec) {
        this.foodRec = foodRec;
    }

    public double getRewards() {
        return rewards;
    }

    public void setRewards(double rewards) {
        this.rewards = rewards;
    }

    @Override
    public String toString() {
        return "RecResult{" +
                "aerobicRec=" + aerobicRec +
                ", anaerobicRec=" + anaerobicRec +
                ", foodRec=" + foodRec +
                ", rewards=" + rewards +
                '}';
    }
}
