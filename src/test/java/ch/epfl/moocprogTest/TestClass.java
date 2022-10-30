package ch.epfl.moocprogTest;

import ch.epfl.moocprog.*;
import ch.epfl.moocprog.app.ApplicationInitializer;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.config.ImmutableConfigManager;
import ch.epfl.moocprog.utils.Time;
import ch.epfl.moocprog.utils.Vec2d;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

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
        System.out.println(termite1.getAngle());
        termite1.update(env, Time.fromSeconds(0.050));
        System.out.println(termite1.getAngle());
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
    void etape7(){
        ApplicationInitializer.initializeApplication(
                new ImmutableConfigManager(
                        new File("res/app.cfg")
                )
        );
        Anthill anthill = new Anthill(new ToricPosition(10, 20));
        System.out.println("Displaying an anthill");
        System.out.println(anthill);
        Environment env = new Environment();
        env.addAnthill(anthill);
        Food f3 = new Food(new ToricPosition(15, 15), 20.);
        Food f4 = new Food(new ToricPosition(40, 40), 15.);
        env.addFood(f3);
        env.addFood(f4);
        System.out.println();
        AntWorker worker = new AntWorker(new ToricPosition(5, 10), anthill.getAnthillId());
        System.out.println("Displaying a worker ant");
        System.out.println(worker +"\n" );
        System.out.print("Can the worker ant drop some food in its anthill : ");
        // true car la fourmi est assez proche de sa fourmilière
        System.out.println(env.dropFood(worker));
        System.out.println("Displaying the anthill after the antworker dropped food:");
        // aucun changement car la fourmi ne transporte pas de nourriture
        System.out.println(anthill);
        System.out.println("\nClosest food seen by the worker ant:" );
        // la fourmi ne « voit » que f3
        // si l'on n'avait que f4, l'appel suivant retournerait null
        System.out.println(env.getClosestFoodForAnt(worker));
    }

    @Test
    void etape8(){
        ApplicationInitializer.initializeApplication(
                new ImmutableConfigManager(
                        new File("res/app.cfg")
                )
        );
        System.out.println("Début du test");
        Environment env = new Environment();
        Anthill anthill = new Anthill(new ToricPosition());
        env.addAnthill(anthill);
        AntWorker antw = new AntWorker(new ToricPosition(), anthill.getAnthillId());
        anthill.update(env, Time.fromSeconds(0.0002));

    }
    @Test
    void etape9(){
        ApplicationInitializer.initializeApplication(
                new ImmutableConfigManager(
                        new File("res/app.cfg")
                )
        );
        Environment env = new Environment();
        Anthill anthill = new Anthill(new ToricPosition(1,1));
        AntWorker antworker = new AntWorker(new ToricPosition(0,0), anthill.getAnthillId());
        env.addFood(new Food(new ToricPosition(0,0), 10.0));
        env.addFood(new Food(new ToricPosition(1,1), 12.5));
        env.addFood(new Food(new ToricPosition(2,3), 8.3333333334));
        env.addAnt(antworker);
        env.addAnthill(anthill);
        antworker.seekForFood(env, Time.fromSeconds(0.01));

    }
    @Test
    void etape10(){
        ApplicationInitializer.initializeApplication(
                new ImmutableConfigManager(
                        new File("res/app.cfg")
                )
        );
        System.out.println();
        double minQty = getConfig().getDouble(Config.PHEROMONE_THRESHOLD);
        Pheromone pher1 = new Pheromone( new ToricPosition(10.,10.), minQty);
        System.out.print("Pheromone pher1 created with quantity PHEROMONE_THRESHOLD = ");
        System.out.println( minQty );
        System.out.println("the position of the pheromone is :" + pher1.getPosition());
        System.out.println("getQuantity() correctly returns the value " + minQty + " : "
                + (pher1.getQuantity() == minQty));
        System.out.print("the quantity of the pheromone is negligible : ");
        System.out.println(pher1.isNegligible());

       Environment env = new Environment();
        env.addPheromone(pher1);
        env.update(Time.fromSeconds(1.));
        System.out.print("After one step of evaporation (dt = 1 sec), ");
        System.out.print(" the quantity of pher1 is ");
        System.out.println(pher1.getQuantity() + "\n");

        double offset = minQty / 5.;
        Pheromone pher2 = new Pheromone( new ToricPosition(20.,20.),
                getConfig().getDouble(Config.PHEROMONE_THRESHOLD) - offset);
        System.out.println("Pheromone created with quantity PHEROMONE_THRESHOLD - " + offset);
        System.out.println("the position of the pheromone is :" + pher2.getPosition());
        System.out.print("the quantity of the pheromone is negligible : ");
        System.out.println(pher2.isNegligible() + "\n");
        env.addPheromone(pher2);

        System.out.print("The quantities of pheromone in the environment are: ");
        System.out.println(env.getPheromonesQuantities());

        env.update(Time.fromSeconds(1.));
        // toute les quantités deviennent négligeables et doivent être supprimées
        System.out.print("After one update of the environment, ");
        System.out.print("the quantities of pheromone in the environment are: ");
        System.out.println(env.getPheromonesQuantities() + "\n");

        System.out.println("Finding pheromones around a given position : ");
        ToricPosition antPosition = new ToricPosition(100., 100.);
        Pheromone pher3 = new Pheromone(new ToricPosition(105., 105.), 1.0);
        Pheromone pher4 = new Pheromone(new ToricPosition(95., 95.), 2.0);
        // cette quantité est trop éloignée (ne doit pas être perçue) :
        Pheromone pher5 = new Pheromone(new ToricPosition(500., 500.), 4.0);
        env.addPheromone(pher3);
        env.addPheromone(pher4);
        env.addPheromone(pher5);
        System.out.print("The quantities of pheromone in the environment are: ");

        System.out.println(env.getPheromonesQuantities());
        double[] pheromonesAroundPosition =
                env.getPheromoneQuantitiesPerIntervalForAnt(antPosition, 0.,
                        new double[] {Math.toRadians(-180), Math.toRadians(-100),
                                Math.toRadians(-55), Math.toRadians(-25),
                                Math.toRadians(-10), Math.toRadians(0),
                                Math.toRadians(10), Math.toRadians(25),
                                Math.toRadians(55), Math.toRadians(100),
                                Math.toRadians(180)
                        });
        System.out.println(Arrays.toString(pheromonesAroundPosition) + "\n");
        System.out.print("After enough time, no pheromones should be left : ");
        env.update(Time.fromSeconds(30.));
        System.out.println(env.getPheromonesQuantities());
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
