package de.igelstudios.game.menus;

import de.igelstudios.igelengine.client.gui.Button;
import de.igelstudios.igelengine.client.gui.GUI;
import de.igelstudios.igelengine.client.gui.GUIManager;
import de.igelstudios.igelengine.client.gui.MouseButton;
import de.igelstudios.igelengine.client.lang.Text;
import org.joml.Vector2f;

public class Credits extends GUI {
    public void Credits(){
        render(Text.translatable("kilij"), 40, 30);
        render(Text.translatable("laurinj"),40, 28);
        render(Text.translatable("paulj"),40, 26);
        render(Text.translatable("juliusj"),40, 24);
        render(Text.translatable("unwichtidj"),40, 22);
        Button mainMenu = new Button(new Vector2f(40, 20), new Vector2f(5, 1));
        addButton(mainMenu);
        render(Text.translatable("button.back"), 40, 20);
        mainMenu.addListener(button -> {
            if (button.equals(MouseButton.LMB)) GUIManager.setGUI(new MainMenu());
        });
    }
}
