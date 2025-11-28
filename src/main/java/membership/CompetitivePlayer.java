package main.java.membership;

import java.util.ArrayList;
import java.util.List;

public class CompetitivePlayer {

    private int playerId;
    private String name;
    private int age;
    private List<> disciplines;

    public CompetitivePlayer(int playerId, String name, int age) {
        this.playerId = playerId;
        this.name = name;
        this.age = age;
        this.disciplines = new ArrayList<>();
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<> getDisciplines() {
        return disciplines;
    }

    public void addDiscipline(DisciplineType discipline) {
        if (!disciplines.contains(discipline)) {
            disciplines.add(discipline);
        }
    }


    public void removeDiscipline(DisciplineType discipline) {
        disciplines.remove(discipline);
    }

    @Override
    public String toString() {
        return "CompetitivePlayer{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", disciplines=" + disciplines +
                '}';
    }
}
