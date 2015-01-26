package com.restapi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import com.restapi.enums.PrintingPaperTypeEnum;


/**
 * This class represents a Newspaper entity
 * @author analia.hojman
 */
@Entity
public class Newspaper extends Informative{

	private PrintingPaperTypeEnum printingPaperType=PrintingPaperTypeEnum.REGULAR;
	private Boolean printingInColor=false;
	
	/**
	 * Default constructor used by JPA
	 */
	public Newspaper(){}
	
	/**
	 * Newspaper Constructor
	 * @param id
	 * @param name
	 * @param editorialName
	 * @param date
	 * @param posts
	 */
	public Newspaper(String name, String editorialName, Date date,
			List<Post> posts) {
		super(name, editorialName, date, posts);
		this.setPrintingSettings(PrintingPaperTypeEnum.REGULAR, false);
	}
	
	/**
	 * Newspaper Constructor without posts
	 * @param id
	 * @param name
	 * @param editorialName
	 * @param date
	 */
	public Newspaper(String name, String editorialName, Date date) {
		this(name, editorialName, date, null);
	}
	
	/**
	 * @return the printingPaperType
	 */
	public PrintingPaperTypeEnum getPrintingPaperType() {
		return printingPaperType;
	}

	/**
	 * @param printingPaperType the printingPaperType to set
	 */
	public void setPrintingPaperType(PrintingPaperTypeEnum printingPaperType) {
		this.printingPaperType = printingPaperType;
	}

	/**
	 * @return the printingInColor value
	 */
	protected Boolean isPrintingInColor() {
		return printingInColor;
	}

	/**
	 * @param printingInColor the printingInColor value to set
	 */
	protected void setPrintingInColor(Boolean printingInColor) {
		this.printingInColor = printingInColor;
	}
	
	/**
	 * This method simulates how a newspaper is printed
	 */
	@Override
	public void print() {
		
		this.printingPaperType = PrintingPaperTypeEnum.REGULAR;
		this.printingInColor = Boolean.TRUE;
		
		System.out.println("--------------------------");
		System.out.println("I am " + this.getName() + ", a Newspaper");
		
		if ( this.isPrintingInColor()){
			System.out.println("I will print myself in color");
		}
		else{
			System.out.println("I will print myself in black and white");
		}
		
		System.out.println("My paper type will be " + this.getPrintingPaperType().toString());
		System.out.println("--------------------------");
	}
	
	/**
	 * This method allow to configure printing settings
	 */
	public void setPrintingSettings(PrintingPaperTypeEnum printingPaperType, Boolean printingInColor ){
		this.printingInColor=printingInColor;
		this.printingPaperType = printingPaperType;
	}


	
}
