package goliath.ou.api;

import java.util.ArrayList;

public interface GPUController<T>
{
    public void reset();
    public T getCurrentValue();
    public T getMinValue();
    public T getMaxVelue();
    public ArrayList<String> getOutput();
    public void setValue(T newVal);
}
