package io.github.restioson.koth.game;

import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import xyz.nucleoid.plasmid.game.GameWorld;
import xyz.nucleoid.plasmid.widget.SidebarWidget;

import java.util.ArrayList;
import java.util.List;

public class KothScoreboard implements AutoCloseable {
    private final SidebarWidget sidebar;

    public KothScoreboard(GameWorld world, String name) {
        this.sidebar = SidebarWidget.open(
                new LiteralText(name).formatted(Formatting.BLUE, Formatting.BOLD),
                world.getPlayerSet()
        );
    }

    public void renderTitle() {
        this.sidebar.set(new String[]{""});
    }

    public void render(List<KothPlayer> leaderboard) {
        List<String> lines = new ArrayList<>();

        for (KothPlayer entry : leaderboard) {
            String line = String.format(
                    "%s%s%s: %ds",
                    Formatting.AQUA,
                    entry.player.getEntityName(),
                    Formatting.RESET,
                    entry.score
            );
            lines.add(line);
        }

        this.sidebar.set(lines.toArray(new String[0]));
    }


    public void close() {
        this.sidebar.close();
    }
}
