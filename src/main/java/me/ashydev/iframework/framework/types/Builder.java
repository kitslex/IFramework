package me.ashydev.iframework.framework.types;

import java.util.Collection;
import java.util.List;

public interface Builder<I, O> {
    O build(I input);
    I from(O output);

    O[] buildAll(I... inputs);
    Collection<O> buildAll(Collection<I> input);

    O update(O output);
}
