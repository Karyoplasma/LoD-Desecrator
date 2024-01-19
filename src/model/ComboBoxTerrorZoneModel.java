package model;


import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import core.TerrorZone;

public class ComboBoxTerrorZoneModel extends AbstractListModel<TerrorZone> implements ComboBoxModel<TerrorZone> {

	private static final long serialVersionUID = -4893105985912964848L;
	private List<TerrorZone> zones;
	private TerrorZone selected;
	
	public ComboBoxTerrorZoneModel() {
		this.zones = Arrays.asList(TerrorZone.values());
	}
	
	@Override
	public int getSize() {
		return zones.size();
	}

	@Override
	public TerrorZone getElementAt(int index) {
		return zones.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selected = (TerrorZone) anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selected;
	}

}
