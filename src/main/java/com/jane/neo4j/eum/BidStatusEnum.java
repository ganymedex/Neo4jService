package com.jane.neo4j.eum;

public enum BidStatusEnum {

	未读("未读", 1), 已读("已读", 2);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private BidStatusEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (BidStatusEnum c : BidStatusEnum.values()) {
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
