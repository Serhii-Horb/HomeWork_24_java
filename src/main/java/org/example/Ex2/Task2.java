package org.example.Ex2;

import java.util.concurrent.atomic.AtomicInteger;

/* 2*. Одна компания решила заработать миллион. Для этого они решили создать курсы, где за 1000
можно обучиться чему-то очень интересному и полезному:
они наняли преподавателей, которые получают 10% от суммы оплаты каждого студента.
Напишите программу, которая даст возможность набирать студентов на курс до тех пор,
пока компания не заработает миллион:) Используйте AtomicInteger для накопления. */
public class Task2 {
    private static final int payment = (int) (1000 - (1000 * 0.1));

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger income = new AtomicInteger(0);
        AtomicInteger students = new AtomicInteger(0);

        while (income.get() < 1000000) {
            Thread thread = new Thread(() -> {
                income.addAndGet(payment);
                students.addAndGet(1);
            });
            System.out.println(thread.getName());
            thread.start();
        }
        System.out.println("Нам нужно набрать " + students.get() + " студентов и мы заработаем миллион");
    }
}
