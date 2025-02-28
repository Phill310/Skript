package ch.njol.skript.expressions;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

@Name("Offline players")
@Description("All players that have ever joined the server. This includes the players currently online.")
@Examples({"send \"Size of all players who have joined the server: %size of all offline players%\""})
@Since("2.2-dev35")
public class ExprOfflinePlayers extends SimpleExpression<OfflinePlayer> {
	
	static {
		Skript.registerExpression(ExprOfflinePlayers.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] offline[ ]players");
	}
	
	@Override
	public boolean init(final Expression<?>[] exprs, final int matchedPattern, final Kleenean isDelayed, final ParseResult parseResult) {
		return true;
	}
	
	@Override
	public boolean isSingle() {
		return false;
	}
	
	@Override
	public Class<? extends OfflinePlayer> getReturnType() {
		return OfflinePlayer.class;
	}
	
	@Override
	@Nullable
	protected OfflinePlayer[] get(final Event event) {
		return Bukkit.getOfflinePlayers();
	}
	
	@Override
	public String toString(final @Nullable Event event, final boolean debug) {
		return "offline players";
	}
	
}
