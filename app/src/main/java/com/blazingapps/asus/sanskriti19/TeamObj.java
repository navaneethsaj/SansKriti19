package com.blazingapps.asus.sanskriti19;

class TeamObj {
    String name;
    double score;

    public TeamObj(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public TeamObj(TeamObj teamObj){
         this.name = teamObj.getName();
         this.score = teamObj.getScore();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setObj(TeamObj obj){
        this.name = obj.getName();
        this.score = obj.getScore();
    }
}
