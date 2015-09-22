package org.istic.gli.models;


/**
 * Created by stephane on 22/09/15.
 */
public class Model
{

	private double amount;
	private String comment;
	private String name;
	
	public Model(){
		super();
	}

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

