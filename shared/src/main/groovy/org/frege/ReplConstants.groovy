package org.frege;

/**
 * Place for shared information among client and server. Typically identifiers for models, attributes and actions.
 */
public class ReplConstants {

    public static final String PM_REPL = unique("REPL");
    public static final String ATT_INPUT  = "input";
    public static final String ATT_OUTPUT = "output";

	public static final String CMD_INIT = unique("INIT");
	public static final String CMD_EXECUTE_INPUT = unique("cmd_execute_input");

	public static final String TYPE_HISTORY = unique("history");
	public static final String ATT_CONTENT  = unique("content");


    /**
     * Unify the identifier with the class name prefix.
     */
    private static String unique(String key) {
        return ReplConstants.class.getName() + "." + key;
    }

}
