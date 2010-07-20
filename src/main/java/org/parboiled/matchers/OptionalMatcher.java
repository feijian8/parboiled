/*
 * Copyright (C) 2009 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.parboiled.matchers;

import org.jetbrains.annotations.NotNull;
import org.parboiled.MatcherContext;
import org.parboiled.Rule;

/**
 * A {@link Matcher} that tries its sub matcher once against the input and always succeeds.
 *
 * @param <V> the type of the value field of a parse tree node
 */
public class OptionalMatcher<V> extends AbstractMatcher<V> {

    public final Matcher<V> subMatcher;

    public OptionalMatcher(@NotNull Rule subRule) {
        super(subRule);
        this.subMatcher = getChildren().get(0);
    }

    public boolean match(@NotNull MatcherContext<V> context) {
        subMatcher.getSubContext(context).runMatcher();
        context.createNode();
        return true;
    }

    public <R> R accept(@NotNull MatcherVisitor<V, R> visitor) {
        return visitor.visit(this);
    }
}