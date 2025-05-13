package utils;

import types.interfaces.Printable;

public interface Utility<T extends Printable> {
    T Add();
    T Update();
    T Delete();
    T Get();
}
