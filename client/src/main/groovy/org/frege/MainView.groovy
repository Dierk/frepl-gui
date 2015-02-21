package org.frege

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import javafx.scene.input.KeyEvent
import javafx.scene.text.TextFlow
import org.opendolphin.binding.JFXBinder
import org.opendolphin.core.ModelStoreEvent
import org.opendolphin.core.ModelStoreListener
import org.opendolphin.core.client.ClientDolphin
import org.opendolphin.core.client.ClientPresentationModel

import javafx.scene.control.TextArea

import static groovyx.javafx.GroovyFX.start
import static org.frege.ReplConstants.*
import static org.opendolphin.binding.JFXBinder.*

class MainView {

    static show(ClientDolphin clientDolphin) {
        ListView<String> history
        TextArea input
        TextArea output

        ClientPresentationModel replModel = clientDolphin.presentationModel(PM_REPL, (ATT_INPUT):'', (ATT_OUTPUT):'')

        start { app ->

            // create the view

            stage(title: "Frege - purely functional programming for the JVM", visible: true) {
                scene(width: 640, height: 500) {
                    fxml resource('/fregeRepl.fxml')
                }
            }
            history = primaryStage.scene.lookup('#history')
            history.getSelectionModel().selectionMode = SelectionMode.SINGLE
            input   = primaryStage.scene.lookup('#editor')
            input.requestFocus()
            output  = primaryStage.scene.lookup('#repl')

            // do the bindings

            clientDolphin.addModelStoreListener TYPE_HISTORY, { event ->
                if (event.type == ModelStoreEvent.Type.ADDED) {
                    history.items << event.presentationModel[ATT_CONTENT].value
                }
            }

            bind ATT_INPUT  of replModel to 'text' of input
            bind 'text' of input to ATT_INPUT of replModel

            bind ATT_OUTPUT of replModel to 'text' of output

            input.onKeyTyped = { KeyEvent event ->
                if (event.metaDown && event.shortcutDown && event.character == "\r") { // mac: cmd + return
                    clientDolphin.send CMD_EXECUTE_INPUT
                }
            }

            history.selectionModel.selectedItemProperty().addListener( new ChangeListener() {
                @Override void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    replModel.getAt(ATT_INPUT).value = newValue
                }
            })

            clientDolphin.send CMD_INIT

            primaryStage.show()
        }
    }
}
