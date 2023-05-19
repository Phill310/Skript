/**
 *   This file is part of Skript.
 *
 *  Skript is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Skript is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Skript.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright Peter Güttinger, SkriptLang team and contributors
 */
package org.skriptlang.skript.registration;

import ch.njol.skript.lang.SyntaxElement;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import org.skriptlang.skript.lang.Priority;

import java.util.List;
import java.util.function.Supplier;

@ApiStatus.Experimental
public interface SyntaxInfo<T extends SyntaxElement> extends DefaultSyntaxInfos {

	@Contract("_, _, _, _ -> new")
	static <E extends SyntaxElement> SyntaxInfo<E> of(
		SyntaxOrigin origin, Class<E> type,
		@Nullable Supplier<E> supplier, List<String> patterns
	) {
		return new SyntaxInfoImpl<>(origin, type, supplier, patterns);
	}

	/**
	 * @return {@link SyntaxOrigin}
	 */
	SyntaxOrigin origin();

	Class<T> type();

	@Contract("-> new")
	T instance();

	@Unmodifiable
	List<String> patterns();

	Priority priority();

}
