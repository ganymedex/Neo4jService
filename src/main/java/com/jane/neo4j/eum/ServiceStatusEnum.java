package com.jane.neo4j.eum;

public enum ServiceStatusEnum {

	// 已创建
	// 待支付 支付成功/支付失败 发布中
	// 审核中 审核成功/审核失败 发布
	// 传播结束

	已创建("已创建", 0), 发布中("发布中", 1), 已发布("已经发布", 2), 传播结束("传播结束", 3);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private ServiceStatusEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (ServiceStatusEnum c : ServiceStatusEnum.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
