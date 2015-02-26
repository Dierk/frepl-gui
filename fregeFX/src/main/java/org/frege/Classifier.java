package org.frege;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Classifier {

    static public org.frege.AllNodes.TAllNodes classify(Node n) throws IllegalArgumentException {
        if (n instanceof Button)   return org.frege.AllNodes.TAllNodes.DButton.mk((Button) n);
        if (n instanceof TextArea) return org.frege.AllNodes.TAllNodes.DTextArea.mk((TextArea) n);
        throw new IllegalArgumentException("Cannot cast node to a known type");
    }
}
