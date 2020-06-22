package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Expenses implements Serializable{
	
	private String expenseId;
	private String expense;
	private String name;
	private BigDecimal amount;
	private String category;
	private String amountStr;
	public String getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}
	public String getExpense() {
		return expense;
	}
	public void setExpense(String expense) {
		this.expense = expense;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAmountStr() {
		return amountStr;
	}
	public void setAmountStr(String amountStr) {
		this.amountStr = amountStr;
	}
	@Override
	public String toString() {
		return "Expenses [expenseId=" + expenseId + ", expense=" + expense + ", name=" + name + ", amount=" + amount
				+ ", category=" + category + ", amountStr=" + amountStr + "]";
	}
	
	

}
