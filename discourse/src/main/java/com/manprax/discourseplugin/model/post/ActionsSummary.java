package com.manprax.discourseplugin.model.post;

/**
 * Created by mukesh on 18/4/18.
 */

public class ActionsSummary {
   private int id;
    private int count;
    private boolean acted;
    private boolean can_act;
    private boolean can_undo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isActed() {
        return acted;
    }

    public void setActed(boolean acted) {
        this.acted = acted;
    }

    public boolean isCan_act() {
        return can_act;
    }

    public void setCan_act(boolean can_act) {
        this.can_act = can_act;
    }

    public boolean isCan_undo() {
        return can_undo;
    }

    public void setCan_undo(boolean can_undo) {
        this.can_undo = can_undo;
    }
}
