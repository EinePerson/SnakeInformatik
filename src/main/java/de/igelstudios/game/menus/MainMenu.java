package de.igelstudios.game.menus;

import de.igelstudios.ClientMain;
import de.igelstudios.ServerMain;
import de.igelstudios.game.Main;
import de.igelstudios.game.util.ServerStarter;
import de.igelstudios.igelengine.client.graphics.Renderer;
import de.igelstudios.igelengine.client.graphics.batch.ObjectBatch;
import de.igelstudios.igelengine.client.gui.*;
import de.igelstudios.igelengine.client.lang.Text;
import de.igelstudios.igelengine.common.networking.client.Client;
import de.igelstudios.igelengine.common.networking.server.Server;
import de.igelstudios.igelengine.common.scene.SceneObject;
import org.joml.Vector2f;

import java.io.IOException;
import java.util.Objects;

public class MainMenu extends GUI{
    public MainMenu(){
        render(Text.translatable("button.connect"), 40, 30);
        //render(Text.translatable("button.host"), 40, 28);
        render(Text.translatable("button.settings"), 30, 25);
        render(Text.translatable("button.quit"), 30, 23);
        Button credits =new Button(new Vector2f(30, 24), new Vector2f(5, 1));
        addButton(credits);
        credits.addListener(button -> {
            if (button.equals(MouseButton.LMB))GUIManager.setGUI(new Credits());
        });
        render(Text.translatable("button.credits"),30, 24);
        SceneObject obj = new SceneObject().setTex(ObjectBatch.pool.getID("test2.png")).setUv(0,15).setSize(new Vector2f(5,1)).setTexSize(new Vector2f(5,1));
        //SceneObject obj2 = new SceneObject().setTex(ObjectBatch.pool.getID("test2.png")).setUv(0,15).setSize(new Vector2f(5,1)).setTexSize(new Vector2f(5,1));
        Button connect = new Button(new Vector2f(40, 30), new Vector2f(5,1));
        addButton(connect);
        TextField connectTF = new TextField(new Vector2f(30,30),new Vector2f(0, 0), new Vector2f(5, 1));
        addTextField(connectTF);

        connect.addListener(button -> {
            if (button.equals(MouseButton.LMB))Main.connect(connectTF.getContent());
        });

        render(obj,30,30);
        //render(obj2,30,28);

        /*Button host = new Button(new Vector2f(40, 28), new Vector2f(5,1));
        addButton(host);
        TextField hostTF = new TextField(new Vector2f(30,28),new Vector2f(0, 0), new Vector2f(5, 1));
        addTextField(hostTF);
        host.addListener(button -> {
            if (button.equals(MouseButton.LMB))
                try {
                    int i;
                    if(Objects.equals(hostTF.getContent(), ""))i = Client.DEFAULT_PORT;
                    else i = Integer.parseInt(hostTF.getContent());
                    //new ServerMain(i);
                    //Thread server = new Thread(() -> ServerMain.getInstance().getEngine().start());
                    //server.start();
                    ServerStarter.start();

                    Main.connect("localhost:" + i);
                }catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                    render(Text.translatable("error.notnumber").setColor(1,0,0),30,27);
                }
        });*/


        Button settings = new Button(new Vector2f(30, 25), new Vector2f(5,1));
        addButton(settings);
        settings.addListener(button -> {
            if(button.equals(MouseButton.LMB)){
                GUIManager.setGUI(new Settings());
            }
        });


        Button quit = new Button(new Vector2f(30, 23), new Vector2f(5,1));
        quit.addListener(button -> ClientMain.getInstance().getEngine().stop());
        addButton(quit);


    }
}
