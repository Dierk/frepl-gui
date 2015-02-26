package org.frege;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Caster {

    static public org.frege.AllNodes.TAllNodes classifyNode(Node n) {
        if (null == n)             return org.frege.AllNodes.TAllNodes.DNullNode.mk(null);
        if (n instanceof Button)   return org.frege.AllNodes.TAllNodes.DButton.mk((Button) n);
        if (n instanceof TextArea) return org.frege.AllNodes.TAllNodes.DTextArea.mk((TextArea) n);
        return org.frege.AllNodes.TAllNodes.DBottomNode.mk(null);
    }
}
