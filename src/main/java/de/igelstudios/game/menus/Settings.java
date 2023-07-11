package de.igelstudios.game.menus;

import de.igelstudios.igelengine.client.graphics.batch.ObjectBatch;
import de.igelstudios.igelengine.client.gui.*;
import de.igelstudios.igelengine.client.lang.ClientConfig;
import de.igelstudios.igelengine.client.lang.Text;
import de.igelstudios.igelengine.common.scene.SceneObject;
import org.joml.Vector2f;

public class Settings extends GUI {
    public Settings() {
        render(Text.translatable("button.savename"),40,30);
        render(Text.translatable("button.savelang"),40,28);
        render(Text.translatable("button.back"),35,25);
        SceneObject obj = new SceneObject().setTex(ObjectBatch.pool.getID("test2.png")).setUv(0,15).setSize(new Vector2f(5,1)).setTexSize(new Vector2f(5,1));
        TextField playerName = new TextField(new Vector2f(30, 30), new Vector2f(), new Vector2f(5, 1));
        addTextField(playerName);
        render(obj,30,30);
        Button name = new Button(new Vector2f(40,30),new Vector2f(5,1));
        addButton(name);
        name.addListener(button ->{
            ClientConfig.getConfig().write("name",playerName.getContent());
            int j = playerName.getLength();
            for (int i = 0; i < j; i++) {
                playerName.remove();
            }
        });

        TextField language = new TextField(new Vector2f(30, 28), new Vector2f(), new Vector2f(5, 1));
        addTextField(language);
        render(obj,30,28);
        Button lang = new Button(new Vector2f(40,28),new Vector2f(5,1));
        addButton(lang);
        lang.addListener(button ->{
            ClientConfig.getConfig().write("lang",language.getContent());
            Text.init(language.getContent());
            int j = language.getLength();
            for (int i = 0; i < j; i++) {
                language.remove();
            }
        });
        Button button = new Button(new Vector2f(35,25),new Vector2f(5,1));
        addButton(button);
        button.addListener(button1 -> {
            if(button1.equals(MouseButton.LMB))GUIManager.setGUI(new MainMenu());
        });
    }
}
