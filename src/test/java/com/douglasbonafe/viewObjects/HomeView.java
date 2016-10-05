package com.douglasbonafe.viewObjects;

import static com.douglasbonafe.util.Helpers.find;

import org.openqa.selenium.By;


public abstract class HomeView {

	static By title = By.id("action_bar_title");
	static By edge1 = By.id("txt_lado1");
	static By edge2 = By.id("txt_lado2");
	static By edge3 = By.id("txt_lado3");
	static By calculateBtn = By.id("btn_Calcular");
	static By messageArea = By.id("txt_triangulo");
		
	public static Boolean loaded(){
		return find(title) != null;
	}
	
	public static void clickOnBtnCalcular(){
		find(calculateBtn).click();
	}
	
	public static void setEdge1(int l1){
		find(edge1).sendKeys(l1+"");
	}
	
	public static void setEdge2(int l2){
		find(edge2).sendKeys(l2+"");
	}
	
	public static void setEdge3(int l3){
		find(edge3).sendKeys(l3+"");
	}

	public static String getMessage() {
		return find(messageArea).getText();
	}
}
