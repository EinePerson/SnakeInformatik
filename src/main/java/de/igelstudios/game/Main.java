package de.igelstudios.game;

import de.igelstudios.ClientMain;
import de.igelstudios.igelengine.client.graphics.Renderer;
import de.igelstudios.igelengine.client.graphics.batch.ObjectBatch;
import de.igelstudios.igelengine.client.lang.Text;
import de.igelstudios.igelengine.common.networking.ErrorHandler;
import de.igelstudios.igelengine.common.networking.client.Client;
import de.igelstudios.igelengine.common.scene.SceneObject;

public class Main {

    public static void main(String[] args){
        ClientMain main = new ClientMain(Main.class);
        Renderer.get().render(new SceneObject().setTex(ObjectBatch.pool.getID("test2.png")).setUv(0,0),15.0f,15.0f);
        Renderer.get().render(Text.literal("A B").setColor(1.0f,0.0f,0.0f),10.0f,10.0f);

        new Client("localhost",new SimpleHandler()).start();
        System.out.println("A      A");
        main.start();
    }

    public static class SimpleHandler implements ErrorHandler{

        @Override
        public void handle(Throwable cause) {
            cause.printStackTrace();
        }
    }
}
