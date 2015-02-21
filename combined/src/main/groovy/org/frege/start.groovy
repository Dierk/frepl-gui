package org.frege

import org.opendolphin.LogConfig
import org.opendolphin.core.client.comm.JavaFXUiThreadHandler
import org.opendolphin.core.comm.DefaultInMemoryConfig
import org.frege.ApplicationDirector
import org.frege.MainView

def config = new DefaultInMemoryConfig()
config.clientDolphin.clientConnector.uiThreadHandler = new JavaFXUiThreadHandler()
config.serverDolphin.registerDefaultActions()
config.serverDolphin.register(new ApplicationDirector())
LogConfig.noLogs()

MainView.show(config.clientDolphin)
