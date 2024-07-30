package app.src;

/**
 * Dataclass to store fixed values
 */
public class StaticValues {

    /** Empty constructor. */
    public StaticValues() {
        // empty
    }
    
    /** name of the gamewindow */
    public static String GAMENAME = "BOWMASTER";
    public static String IMAGEDIR = "images/";
    public static String SOUNDDIR = "sounds/";
    /** width of the gamewindow */
    public static int CANVAS_WIDTH = 1920;
    /** height of the gamewindow */
    public static int CANVAS_HEIGHT = 1080;
    /** maximum distance an Entity can travel */
    public static int MAX_DISTANCE = 600;
    /** Spawnline for Monsters */
    public static int SpawnY = 100;
    /** Despawnline for Monsters */
    public static int TRAVEL_DISTANCE_Y = CANVAS_HEIGHT - 300;
    /** Framerate */
    public static int UPDATE_PERIOD = 60;
    /** Base Value for damagecaluculation */
    public static int BASEDAMAGE = 10;
    /** Base value for the cooldown of the bow */
    public static int BOWCOOLDOWN = 5;
    /** Determines the Padding to the window edge for Monster spawns */
    public static int SPAWNPADDING = 100;
    /** Player start location x */
    public static int PLAYERSPAWNX = CANVAS_WIDTH/2;
    /** Player start location y */
    public static int PLAYERSPAWNY = CANVAS_HEIGHT-200;
    /** Available Rectangle corners */
    public static enum Corners {
        /** Top Right corner */
        TOP_RIGHT,
        /** Top Left corner */
        TOP_LEFT,
        /** Bottom Left corner */
        BOTTOM_RIGHT,
        /** Bottom Right corner */
        BOTTOM_LEFT,
    }
    /** Available Tags for a Scene */
    public static enum SceneTag {
        /** Tag for active Scene */
        ACTIVE,
        /** Tag for previous Scene */
        PREVIOUS,
        /** Tag for new Scenes */
        NEW,
    }
}