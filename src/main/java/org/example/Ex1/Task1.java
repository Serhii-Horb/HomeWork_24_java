package org.example.Ex1;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/* 1. На соревнованиях 5 спортсменов начинают одновременно стрелять в одну мишень.
Если кто-нибудь в нее попадает(сделать Random-но), то мишень разрушается и другие в нее уже попасть не могут,
но что-то пошло не так. Исправь программу, чтобы другие потоки "видели" изменения значения
переменной isHit и больше его не меняли. Как только мишень разрушена кем то из стрелков,
соревнования прекращаются. Воспользуйся классом AtomicBoolean и его методами. */
public class Task1 {
    private static AtomicBoolean isHit = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            if (!isHit.get()) {
                Thread thread = new Thread(() -> {
                    if (isShot()) {
                        isHit.compareAndSet(false, true);
                            System.out.println("Спортсмен " + Thread.currentThread().getName() + " попал в мишень!");
                            stopCreatingThreads();
                        } else {
                            System.out.println("Спортсмен " + Thread.currentThread().getName() + " не попал в мишень!");
                        }
                });
                thread.start();
                thread.join();
            }
        }
    }
    public static boolean isShot() {
        Random random = new Random();
        return random.nextBoolean() && random.nextBoolean();
    }
    private static void stopCreatingThreads() {
        System.out.println("Мишень разрушена! Дальнейшие потоки не создаются.");
    }
}
