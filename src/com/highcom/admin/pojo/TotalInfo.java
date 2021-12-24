package com.highcom.admin.pojo;

/**
 * 统计查询的具体信息(本类用于订单统计,企业信息统计,订单访问量统计)
 */
public class TotalInfo {
	public TotalInfo() {
	}
	
	/**
	 * 只用月构造本类的目的,是为了处理让查出来的列表满12个月
	 * @param month
	 */
	public TotalInfo(Integer month ) {
		this.month=month;
	}

	
   /**
    * 这个equals方法的重写是别有目的的,它只比较月,也是为了其他程序处理列表要满12个月的情况
    */
	public boolean equals(Object obj) {
		TotalInfo t=(TotalInfo)obj;
		return this.month==t.month;
	}

	public TotalInfo(Integer year, Integer month, Integer value) {
		this.year = year;
		this.month = month;
		this.value = value;
	}
	private int year;
	private int month;
	private int value;
	

	public String toString() {
		return "OrderTotal [year=" + year + ", month=" + month + ", value=" + value + "]";
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
