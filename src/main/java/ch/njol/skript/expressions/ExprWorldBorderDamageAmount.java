package ch.njol.skript.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.WorldBorder;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Damage Amount of World Border")
@Description({
	"The amount of damage a player takes per second for each block they are outside the border plus the border buffer.",
	"Note: Players only take damage when outside of the world's world border"
})
@Examples("set world border damage amount of {_worldborder} to 1")
@Since("INSERT VERSION")
public class ExprWorldBorderDamageAmount extends SimplePropertyExpression<WorldBorder, Double> {

	static {
		register(ExprWorldBorderDamageAmount.class, Double.class, "world[ ]border damage amount", "worldborders");
	}

	@Override
	@Nullable
	public Double convert(WorldBorder worldBorder) {
		return worldBorder.getDamageAmount();
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		return switch (mode) {
			case SET, ADD, REMOVE, RESET -> CollectionUtils.array(Number.class);
			default -> null;
		};
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		double input = mode == ChangeMode.RESET ? 0.2 : ((Number) delta[0]).doubleValue();
		for (WorldBorder worldBorder : getExpr().getArray(event)) {
			switch (mode) {
				case SET, RESET -> worldBorder.setDamageAmount(input);
				case ADD -> worldBorder.setDamageAmount(worldBorder.getDamageAmount() + input);
				case REMOVE -> worldBorder.setDamageAmount(worldBorder.getDamageAmount() - input);
			}
		}
	}

	@Override
	protected String getPropertyName() {
		return "border damage amount";
	}

	@Override
	public Class<? extends Double> getReturnType() {
		return Double.class;
	}

}