package frc.robot.subsystems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * {@code JsonConfigurableSubsystem} extends {@code SubsystemBase}, meaning the
 * core functionality of the subsystem is still present.
 * The only addition of this subsystem is access to a map of configuration
 * values which have been parsed from a JSON file. JSON files should be placed
 * in the deploy directory/config/name_of_class.json
 * 
 * Configuration values can be accessed via the {@code getConfig()} method.
 * Values are returned as an object, but the a actual type of the object is
 * either Double or Integer depending on whether or not the value in the
 * configuration file includes a "." character. Users of the class should simply
 * cast the object as the value should be known to them.
 */
public abstract class JsonConfigurableSubsystem extends SubsystemBase {

    private HashMap<String, Object> config = new HashMap<String, Object>();

    public JsonConfigurableSubsystem() {
        populateConfigMap();
    }

    /**
     * Populate the configuration hashmap with the contents of the file. Values are
     * expected to be comma-seperated, and keys are seperated from values using a
     * colon. Keys are kept as strings, while values are parsed into either integers
     * or doubles based on whether or not a dot character exists in the string key.
     */
    private void populateConfigMap() {
        String json = getDataString();
        String[] props = json.split(",");
        String[] working;
        for (String s : props) {
            working = s.split(":");
            if (working[1].contains(".")) {
                config.put(working[0], Double.parseDouble(working[1]));
            } else {
                config.put(working[0], Integer.parseInt(working[1]));
            }
        }
    }

    /**
     * Retrieve the data string from the file. Files should be placed in the config
     * directory under the deploy directory. Files should be named the same name as
     * the implementing class, plus '.json'. If the file does not exist, the program
     * throws an error and ends.
     * 
     * @return The contents of the file represented a string, with the following
     *         characters removed: newline, {, }, quotes, and spaces.
     */
    private String getDataString() {
        File f = new File(Filesystem.getDeployDirectory(), "/config/" + this.getSubsystem() + ".json");
        String rtn = "";
        Scanner scan;

        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File " + f.getAbsolutePath() + " not found while initalizing JsonConfigurableSubsystem "
                    + this.getSubsystem());
            throw new Error(e);
        }
        while (scan.hasNext()) {
            rtn += scan.next();
        }
        scan.close();

        return rtn.replaceAll("[{}\"\n]", "").replaceAll(" ", "");//java handles whitespaces weirdly with regex so it is simpler to do it this way
    }

    /**
     * Retrieve the value which corresponds to this key as either a {@code Double}
     * or {@code Integer}. If no value corresponds to this key, return null.
     * 
     * @param key the key which corresponds to the value passed in the JSON config
     *            file
     * @return The corresponding value, a {@code Double}
     *         or {@code Integer}
     */
    protected Object getConfig(String key) {
        return config.get(key);
    }

}
