package com.ygo.model.pojo;

import lombok.Data;

import java.util.List;

@Data
public class DataItem {

	private String id;
	private String name;
	private String type;
	private String frameType;
	private String desc;
	private Integer atk;
	private Integer def;
	private Integer level;
	private String race;
	private String attribute;
	private String archetype;
	private String ygoprodeckUrl;
	private List<CardSetsItem> card_sets;
	private List<CardImagesItem> card_images;
	private List<CardPricesItem> card_prices;


}
