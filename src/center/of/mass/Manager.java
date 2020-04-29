package center.of.mass;

import java.util.ArrayList;

import exceptions.CelestialBodyNotFoundException;
import exceptions.InvalidPositionException;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class Manager {
    private StarSystem starSystem;
    private String initialMessage;

    private MyMenu mainMenu;
    private MyMenu planetMenu;
    private MyMenu moonMenu;

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";

    /**
     * Construsctor of the {@code Manager} wich manage the program
     * @author Gabriele
     * @param initialMessage
     */
    public Manager(String initialMessage) {
        this.initialMessage = initialMessage;

        this.mainMenu = new MyMenu("Planetarium", new String[] {"Manage planets", "Manage moons"});
        this.planetMenu = new MyMenu("What do you want to do?", new String[] {"Add a new planet", "Remove a planet", "View a list of all planets", "View the information about a specific planet"});
        this.moonMenu = new MyMenu("What do you want to do?", new String[] {"Add a new moon", "Remove a moon", "View a list of all moons", "View the information about a specific moon"});
    }

    /**
     * Start the program and the interation with the user
     */
    public void startProgram() {
        this.writeOKMessage(this.initialMessage);

        this.starSystem = new StarSystem(PlanetariumUtils.readStar());

        this.useMainMenu();
    }

    /**
     * Start the main menu
     * @author Gabriele
     */
    private void useMainMenu() {
        int choose;

        do {
            choose = this.mainMenu.scegli();

            switch(choose) {
                case 1:
                    usePlanetMenu();
                    break;
                
                case 2:
                    useMoonMenu();
                    break;
            }

        } while(choose != 0);
    }

    /**
     * Start the menu used to manage the planets of the star system
     */
    private void usePlanetMenu() {
        int choose;

        do {
            choose = this.planetMenu.scegli();

            switch(choose) {
                case 1:
                    this.addCelestialBody(PlanetariumUtils.readPlanet());
                    break;

                case 2:
                    this.removePlanetByName(InputDati.leggiStringa("Name of the planet to remove: "));
                    break;
                
                case 3:
                    PlanetariumUtils.showCelestialBodiesNames(new ArrayList<CelestialBody>(this.starSystem.getPlanets()));
                    break;

                case 4:
                    this.viusalizeInfo(this.starSystem.getPlanetByName(InputDati.leggiStringa("Name of the planet to visualize: ")));
                    break;
            }

        } while(choose != 0);
    }

    /**
     * Start the menu used to manage the moons of the star system
     */
    private void useMoonMenu() {
        int choose;

        do {
            choose = this.moonMenu.scegli();

            switch(choose) {
                case 1:
                    this.addCelestialBody(PlanetariumUtils.readMoon());
                    break;
                
                case 2:
                    this.removeMoonByName(InputDati.leggiStringa("Name of the moon to remove: "));
                    break;

                case 3:
                    PlanetariumUtils.showCelestialBodiesNames(new ArrayList<CelestialBody>(this.starSystem.getMoons()));
                    break;

                case 4:
                    this.viusalizeInfo(this.starSystem.getMoonByName(InputDati.leggiStringa("Name of the moon to visualize: ")));
            }

        } while(choose != 0);
    }

    /**
     * Add a new {@code CelestialBody} in the {@code StarSystem}
     * @author Gabriele
     * @param celestialBody
     */
    private void addCelestialBody(CelestialBody celestialBody) {
        if(celestialBody instanceof Planet) {
            try {
                this.starSystem.addPlanet((Planet) celestialBody);
                this.writeOKMessage("Planet added succesfully!");
            } catch (InvalidPositionException exception) {
                this.writeErrorMessage(exception.getMessage());
            }
        }

        if(celestialBody instanceof Moon) {
            try {
                String nameOfPlanet = InputDati.leggiStringa("Name of the planet around which the moon orbits: ");
                this.starSystem.addMoonToPlanetWithName((Moon) celestialBody, nameOfPlanet);
                this.writeOKMessage("Moon added succesfully!");
            } catch (InvalidPositionException exception) {
                this.writeErrorMessage(exception.getMessage());
            } catch (CelestialBodyNotFoundException exception) {
                this.writeErrorMessage(exception.getMessage());
            }
        }
    }

    /**
     * Remove the {@code Planet} specified by the name from the {@code StarSystem}
     * @author Gabriele
     * @param name
     */
    private void removePlanetByName(String name) {
        this.starSystem.removePlanetByName(name);

        this.writeWarningMessage("Planet removed succesfully!");
    }

    /**
     * Remove the {@code Moon} specified by the name from the {@code StarSystem}
     * @author Gabriele
     * @param name
     */
    private void removeMoonByName(String name) {
        this.starSystem.removeMoonByName(name);

        this.writeWarningMessage("Moon removed succesfully!");
    }

    /**
     * Visualize the information about the specified {@code CelestialBody}
     * @param celestialBody
     */
    private void viusalizeInfo(CelestialBody celestialBody) {
        if(celestialBody == null) {
            this.writeWarningMessage("The specified celestial body doesn't exist");
        }
        else {
            this.writeOKMessage(celestialBody.toString());;
        }
    }

    /**
     * Write a RED message on the cmd
     * @author Gabriele
     * @param message
     */
    private void writeErrorMessage(String message) {
        System.out.println(RED + message + RESET);
    }

    /**
     * Write a GREEN message on the cmd
     * @author Gabriele
     * @param message
     */
    private void writeOKMessage(String message) {
        System.out.println(GREEN + message + RESET);
    }

    /**
     * Write a YELLOW message on the cmd
     * @author Gabriele
     * @param message
     */
    private void writeWarningMessage(String message) {
        System.out.println(YELLOW + message + RESET);
    }
}