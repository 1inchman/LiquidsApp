package com.example.oneinchman.liquids;


public class Liquids {

    int _id;
    private String _components;
    private String _proportions;
    private String _liquidName;

    public Liquids(String _components, String _proportions, String _liquidName) {
        this._components = _components;
        this._proportions = _proportions;
        this._liquidName = _liquidName;
    }

    public void set_liquidName(String _liquidName) {
        this._liquidName = _liquidName;
    }

    public String get_liquidName() {

        return _liquidName;
    }

    public String get_components() {
        return _components;
    }

    public String get_proportions() {
        return _proportions;
    }

    public void set_components(String _components) {
        this._components = _components;
    }

    public void set_proportions(String _proportions) {
        this._proportions = _proportions;
    }
}
