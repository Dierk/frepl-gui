package org.frege;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;

public class Classifier {

    static public org.frege.AllNodes.TAllNodes classify(Node n) throws IllegalArgumentException {
        if (n instanceof Button)   return org.frege.AllNodes.TAllNodes.DButton.mk((Button) n);
        if (n instanceof TextArea) return org.frege.AllNodes.TAllNodes.DTextArea.mk((TextArea) n);
        if (n instanceof ListView) return org.frege.AllNodes.TAllNodes.DListView.mk((ListView) n);
        System.out.println("Cannot cast node " + n.getClass() + " to a known type");
        throw new IllegalArgumentException("Cannot cast node " + n.getClass() + " to a known type");
    }
}
