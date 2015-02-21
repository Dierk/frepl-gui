package org.frege;

import org.opendolphin.core.comm.Command
import org.opendolphin.core.server.DTO
import org.opendolphin.core.server.ServerPresentationModel
import org.opendolphin.core.server.Slot;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.CommandHandler

import static org.frege.ReplConstants.*

public class ApplicationAction extends DolphinServerAction{

    public void registerIn(ActionRegistry actionRegistry) {

        actionRegistry.register(CMD_INIT, new CommandHandler<Command>() {
            public void handleCommand(Command command, List<Command> response) {
                presentationModel(null, TYPE_HISTORY, new DTO(new Slot(ATT_CONTENT, "1+1")))
                presentationModel(null, TYPE_HISTORY, new DTO(new Slot(ATT_CONTENT, ":type head")))
                presentationModel(null, TYPE_HISTORY, new DTO(new Slot(ATT_CONTENT, ":help module")))

                serverDolphin[PM_REPL][ATT_INPUT].value = "Cmd (Meta) + Enter to execute"

            }
        });

        actionRegistry.register(CMD_EXECUTE_INPUT, new CommandHandler<Command>() {
            public void handleCommand(Command command, List<Command> response) {
                def replModel = serverDolphin[PM_REPL]
                String code = replModel[ATT_INPUT].value

                presentationModel(null, TYPE_HISTORY, new DTO(new Slot(ATT_CONTENT, code)))

                replModel[ATT_OUTPUT].value = code.toUpperCase()

            }
        });

    }
}
