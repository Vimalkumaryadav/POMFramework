package com.BestClass.office;

import org.openqa.selenium.WebDriver;

import findElements.Find;
import findElements.FindChild;
import helpers.actions.*;

public abstract class BasePage {

	protected WebDriver lDriver;
	protected Browser browser;
	protected Find find;
	protected FindChild findChild;
	protected Element element;
	protected Get get;
	protected Check check;
	protected Choose select;
	protected Wait objWait;
	protected Snap objSnap;
	protected Alert objAlert;
	protected Switch objSwitch;
	
	public BasePage(WebDriver driver) {
		lDriver = driver;
		browser = new Browser(lDriver);
		findChild = new FindChild(lDriver);
		find = new Find(lDriver);
		element = new Element();
		get = new Get(lDriver);
		check = new Check(lDriver);
		select = new Choose(lDriver);
		objWait = new Wait(lDriver);
		objSnap = new Snap(lDriver);
		objAlert = new Alert(lDriver);
		objSwitch = new Switch(lDriver);
	}
	
	protected abstract boolean hasItLoaded();
	
}
