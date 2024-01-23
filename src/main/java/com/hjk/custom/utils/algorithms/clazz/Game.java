package com.hjk.custom.utils.algorithms.clazz;

public class Game {

    public static void main(String... args) {
        Legend<Champion> champion = new Legend<>();
        Legend<Yasuo> yasuo = new Legend<>();
        champion.add(new Champion());
        yasuo.add(new Yasuo());

        champion.get(0).doAct();
        yasuo.get(0).doAct();

        print(champion);
    }

    static void print(Legend<? extends Champion> box) {
        for(int i = 0; i < box.size(); i++) {
            box.get(i).doAct();
        }
    }
}
