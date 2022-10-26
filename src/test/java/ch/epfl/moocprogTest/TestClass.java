package ch.epfl.moocprogTest;

import ch.epfl.moocprog.*;
import ch.epfl.moocprog.app.ApplicationInitializer;
import ch.epfl.moocprog.config.ImmutableConfigManager;
import ch.epfl.moocprog.utils.Time;
import ch.epfl.moocprog.utils.Vec2d;
import org.junit.jupiter.api.Test;

import java.io.File;

import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.WORLD_HEIGHT;
import static ch.epfl.moocprog.config.Config.WORLD_WIDTH;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClass {

    @Test
    void primart(){
        System.out.println("hello");
        ApplicationInitializer.initializeApplication(
            new ImmutableConfigManager(
                new File("res/app.cfg")
            )
        );
        ToricPosition tp3 = new ToricPosition(1,1);
        ToricPosition tp2 = new ToricPosition(400,300);
        Environment env = new Environment();
        // crée une termite, vérifier la méthode is dead
        Termite termite1 = new Termite(tp3);
        Termite termite2 = new Termite(tp2);
        //termite1.setHitpoints(0);
        env.addAnimal(termite1);
        //env.addAnimal(termite2);
        System.out.println("SIZE : "+ env.getAnimal().size());
        assertEquals(termite1.getSpeed(),120.0);

        System.out.println(termite1.getPosition().getPosition());
        env.update(Time.fromSeconds(0.03));
        System.out.println("SIZE : "+ env.getAnimal().size());
        System.out.println(termite1.getPosition().getPosition());


//        final int width  = getConfig().getInt(WORLD_WIDTH);
//        final int height = getConfig().getInt(WORLD_HEIGHT);
//        ToricPosition tp1 = new ToricPosition(1,1);
//        ToricPosition tp2 = new ToricPosition(9,9);
//        ToricPosition tp3 = new ToricPosition(7,7);
//
//
//        //assertEquals(tp1.getVec2d(), new Vec2d(500,350));
//        Vec2d vec2d = new Vec2d(-2,-2);
//        assertEquals(tp1.toricVector(tp2),vec2d);
        //tp1.toricVector(tp2);

//        ToricPosition tp1 = new ToricPosition();
//        ToricPosition tp2 = new ToricPosition(1.2, 2.3);
//        ToricPosition tp3 = new ToricPosition(new Vec2d(4.5, 6.7));
//        ToricPosition tp4 = tp3.add(tp2);
//        ToricPosition tp5 = new ToricPosition(width, height);
//        ToricPosition tp6 = new ToricPosition(width/2, height/2);
//        ToricPosition tp7 = tp4.add(tp6.add(new Vec2d(width/2, height/2)));
//        ToricPosition tp8 = new ToricPosition(3, 4);
//        Vec2d v1 = tp2.toricVector(tp3);
//
//        System.out.println("Some tests for ToricPosition");
//        System.out.println("Default toric position : " + tp1);
//        System.out.println("tp2 : " + tp2);
//        System.out.println("tp3 : " + tp3);
//        System.out.println("tp4 (tp2 + tp3) : " + tp4);
//        System.out.println("Toric vector between tp2 and tp3 : " + v1);
//        System.out.println("World dimension (clamped) : " + tp5);
//        System.out.println("Half world dimension : " +tp6);
//        System.out.println("tp3 + 2 * half world dimension = " + tp7);
//        System.out.println("Length of vector (3, 4) : " + tp1.toricDistance(tp8));
//
//        Positionable p1 = new Positionable();
//        Positionable p2 = new Positionable(tp4);
//
//        System.out.println();
//        System.out.println("Some tests for Positionable");
//        System.out.println("Default position : " + p1.getPosition());
//        System.out.println("Initialized at tp4 : " + p2.getPosition());

    }

    @Test
    void etape3() {
        System.out.println("hello");
        ApplicationInitializer.initializeApplication(
                new ImmutableConfigManager(
                        new File("res/app.cfg")
                )
        );
        ToricPosition tp3 = new ToricPosition(1,1);
        ToricPosition tp2 = new ToricPosition(9,9);
        Food f1 = new Food(tp2, 4.7);
        Food f2 = new Food(tp3, 6.7);

//        System.out.println();
//
//        System.out.println("Some tests for Food");
//        System.out.println("Display : ");
//        System.out.println(f1);
        System.out.println(
                "Initial : " + f1.getQuantity()
                + ", taken : "
                + f1.takeQuantity(5.0)
                + ", left : " + f1.getQuantity()
        );

        System.out.println("Initial : " + f2.getQuantity()
                + ", taken : "
                + f2.takeQuantity(2.0)
                + ", left : "
                + f2.getQuantity());
    }
    @Test
    void etape5(){
        ToricPosition tp3 = new ToricPosition(1,1);
        ToricPosition tp2 = new ToricPosition(400,300);
        // crée une termite, vérifier la méthode is dead
        Termite termite1 = new Termite(tp3);

    }

}
