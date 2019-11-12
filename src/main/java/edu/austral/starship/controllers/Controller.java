package edu.austral.starship.controllers;

import edu.austral.starship.engines.Engine;
import edu.austral.starship.models.Element;
import edu.austral.starship.views.View;

import java.util.List;

public abstract class Controller {
    private Engine engine;
    private View view;
    private List<Element> list;
}
