package com.secondhand.vo;

public class Good {
	
	private int id;
	private String good_name;
	private int type_id;
	private float good_price;
	private String good_intro;
	private int good_state;
	private String add_name;
	private String add_date;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGood_name() {
		return good_name;
	}
	public void setGood_name(String goodName) {
		good_name = goodName;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int typeId) {
		type_id = typeId;
	}
	
	public float getGood_price() {
		return good_price;
	}
	public void setGood_price(float goodPrice) {
		good_price = goodPrice;
	}
	public String getGood_intro() {
		return good_intro;
	}
	public void setGood_intro(String goodIntro) {
		good_intro = goodIntro;
	}
	public int getGood_state() {
		return good_state;
	}
	public void setGood_state(int goodState) {
		good_state = goodState;
	}
	public String getAdd_name() {
		return add_name;
	}
	public void setAdd_name(String addName) {
		add_name = addName;
	}
	public String getAdd_date() {
		return add_date;
	}
	public void setAdd_date(String addDate) {
		add_date = addDate;
	}
	
}
