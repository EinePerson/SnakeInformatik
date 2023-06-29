package de.igelstudios.game.menus;

import de.igelstudios.igelengine.client.gui.Button;
import de.igelstudios.igelengine.client.gui.GUI;
import de.igelstudios.igelengine.client.gui.TextField;
import org.joml.Vector2f;

public class Settings extends GUI {
    public Settings() {
        TextField playerName = new TextField(new Vector2f(40, 30), new Vector2f(), new Vector2f(5, 1));
        this.getTextFields().add(playerName);

        TextField language = new TextField(new Vector2f(40, 28), new Vector2f(), new Vector2f(5, 1));
        this.getTextFields().add(language);
    }
}
