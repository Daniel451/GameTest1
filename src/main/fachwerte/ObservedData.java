package main.fachwerte;

public class ObservedData
{
	private final String _observedDataName;
	private final Object _objectData;
	
	public ObservedData(String name, Object data)
	{
		_observedDataName = name;
		_objectData = data;
	}
	
	public String getDataName()
	{
		return _observedDataName;
	}
	
	public Object getData()
	{
		return _objectData;
	}
}
