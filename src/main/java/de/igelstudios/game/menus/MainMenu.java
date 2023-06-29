package de.igelstudios.game.menus;

import de.igelstudios.igelengine.client.graphics.Renderer;
import de.igelstudios.igelengine.client.gui.*;
import de.igelstudios.igelengine.client.lang.Text;
import org.joml.Vector2f;

public class MainMenu {
    GUI gui;
    public MainMenu(){
        gui = new GUI();
        GUIManager.getInstance().setGui(gui);

        Button connect = new Button(new Vector2f(40, 30), new Vector2f(5,1));
        gui.getButtons().add(connect);
        TextField connectTF = new TextField(new Vector2f(30,30),new Vector2f(0, 0), new Vector2f(5, 1));
        Renderer.get().render(Text.translatable("button.connect"), 40, 30);

        Button host = new Button(new Vector2f(40, 28), new Vector2f(5,1));
        gui.getButtons().add(host);
        TextField hostTF = new TextField(new Vector2f(30,28),new Vector2f(0, 0), new Vector2f(5, 1));
        Renderer.get().render(Text.translatable("button.host"), 40, 28);

        Button settings = new Button(new Vector2f(40, 26), new Vector2f(5,1));
        gui.getButtons().add(settings);
        settings.addListener(button -> {
            if(button.equals(MouseButton.LMB)){
                GUIManager.getInstance().setGui(new Settings());
            }
        });
        Renderer.get().render(Text.translatable("button.settings"), 40, 26);

        Button quit = new Button(new Vector2f(40, 24), new Vector2f(5,1));
        gui.getButtons().add(quit);
        Renderer.get().render(Text.translatable("button.quit"), 40, 24);
    }
}
