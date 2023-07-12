package de.igelstudios.game.menus;

import de.igelstudios.igelengine.client.graphics.Renderer;
import de.igelstudios.igelengine.client.gui.*;
import de.igelstudios.igelengine.client.lang.Text;
import de.igelstudios.igelengine.common.networking.client.Client;
import org.joml.Vector2f;

public class DeathMenu extends GUI {

    public DeathMenu(){
        render(Text.translatable("menu.death.title"),30,30);
        //render(Text.translatable("menu.death.wait"),30,27);
        //Button wait = new Button(new Vector2f(30,27),new Vector2f(10,1));
        //wait.addListener(button -> GUIManager.getInstance().removeGUI());
        //addButton(wait);
        render(Text.translatable("menu.death.disconnect"),30,25);
        Button disconnect = new Button(new Vector2f(30,25),new Vector2f(10,1));
        disconnect.addListener(button ->{
            Client.disconnect();
            GUIManager.setGUI(new MainMenu());
        });
        addButton(disconnect);
    }
}
